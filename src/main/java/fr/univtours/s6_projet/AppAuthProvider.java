package fr.univtours.s6_projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AppAuthProvider extends DaoAuthenticationProvider {

	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

		String name = authentication.getName();
		String password = authenticationToken.getCredentials().toString();

		UserDetails user = userService.loadUserByUsername(name);

		if (user == null) {
			throw new BadCredentialsException("Nom d'utilisateur ou mot de passe invalide");
		}

		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
