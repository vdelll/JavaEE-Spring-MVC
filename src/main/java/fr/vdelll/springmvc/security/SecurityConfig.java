package fr.vdelll.springmvc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * Permet de dire à spring où aller chercher les utilisateurs
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Définition d'utilisateurs en mémoire
		// {noop} permet de définir qu'on utilise pas d'encodeur (Bcrypt est utilisé par défaut)
		auth.inMemoryAuthentication().withUser("user1").password("{noop}test").roles("USER");
		auth.inMemoryAuthentication().withUser("user2").password("{noop}1234").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN", "USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Utilisation d'un formulaire d'authentification
		http.formLogin();
		
		// Toutes les requêtes HTTP nécessitent une authentification
		http.authorizeRequests().anyRequest().authenticated();
	}

}
