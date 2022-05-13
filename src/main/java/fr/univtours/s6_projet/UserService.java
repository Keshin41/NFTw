package fr.univtours.s6_projet;

import fr.univtours.s6_projet.entity.User;
import fr.univtours.s6_projet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Objects.requireNonNull(email);
		return userRepository.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
	}
}
