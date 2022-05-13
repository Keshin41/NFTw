package fr.univtours.s6_projet.controller;

import fr.univtours.s6_projet.entity.Role;
import fr.univtours.s6_projet.entity.User;
import fr.univtours.s6_projet.repository.RoleRepository;
import fr.univtours.s6_projet.repository.UserRepository;
import fr.univtours.s6_projet.type.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@GetMapping("/signup")
	public String registrate(RegistrationForm registrationForm) {
		return "authentification/register";
	}

	@PostMapping("/singup")
	public String processRegistration(@Valid RegistrationForm registrationForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return "authentification/register";

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = new User(
				roleRepository.findByName(Role.USER),
				registrationForm.getFirstname(),
				registrationForm.getLastname(),
				registrationForm.getEmail(),
				passwordEncoder.encode(registrationForm.getPassword())
		);
		userRepository.saveAndFlush(user);
		return "redirect:/";
	}
}
