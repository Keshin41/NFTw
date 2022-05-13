package fr.univtours.s6_projet;

import fr.univtours.s6_projet.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/admin").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/commercant/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/commercant/delete/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/commercant/edit/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/commercant/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/commercant/category/edit/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/commercant/category/delete/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/commercant/category/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/order/delete/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/order/edit/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/order/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/panier/delete/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/panier/edit/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/panier/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/panier/category/delete/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/panier/category/edit/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/panier/category/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/user/delete/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/user/edit/*").hasAuthority(Role.ADMIN)
				.antMatchers("/admin/user/*").hasAuthority(Role.ADMIN)
				.antMatchers("/order/*").authenticated()
				.antMatchers("/account").authenticated()
				.antMatchers("/account/*").authenticated()
				.antMatchers("/account/orders/confirm/*").authenticated()
				.antMatchers("/account/orders/delete/*").authenticated()
				.antMatchers("/account/orders/delivered/*").authenticated()
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.usernameParameter("email")
				.permitAll()
				.and()
				.logout().logoutSuccessUrl("/").permitAll();
	}
}
