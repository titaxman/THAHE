package rdvmedecins.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import rdvmedecins.security.AppUserDetailsService;
import rdvmedecins.web.models.ApplicationModel;

@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	@Autowired
	private ApplicationModel application;

	@Override
	protected void configure(AuthenticationManagerBuilder registry) throws Exception {
		// l'authentification est faite par le bean [appUserDetailsService]
		// le mot de passe est crypté par l'algorithme de hachage BCrypt
		registry.userDetailsService(appUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF
		http.csrf().disable();
		// application sécurisée ?
		if (application.isSecured()) {
			// le mot de passe est transmis par le header Authorization: Basic xxxx
			http.httpBasic();
			// la méthode HTTP OPTIONS doit être autorisée pour tous
			http.authorizeRequests() //
					.antMatchers(HttpMethod.OPTIONS, "/", "/**").permitAll();
			// seul le rôle ADMIN peut utiliser l'application
			http.authorizeRequests() //
					.antMatchers("/", "/**") // toutes les URL
					.hasRole("ADMIN");
			// pas de session
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
	}
}
