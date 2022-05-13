package fr.univtours.s6_projet.controller.admin;

import fr.univtours.s6_projet.entity.User;
import fr.univtours.s6_projet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAdminController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/admin/user")
	public String index(Model model) {
		model.addAttribute("listUsers", userRepository.findAll());
		return "/admin/user/index";
	}

	@PostMapping("/admin/user/save")
	public String saveUser(User user) {
		userRepository.saveAndFlush(user);
		return "redirect:/admin/user";
	}

	@GetMapping("/admin/user/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "/admin/user/form";
	}

	@GetMapping("/admin/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		userRepository.deleteById(id);
		return "redirect:/admin/user";
	}
}
