package br.net.gest.sys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.net.gest.sys.service.UsuarioDetailsServiceImplementation;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

	
	
	@Autowired
	private UsuarioDetailsServiceImplementation usuarioDetailsServiceImplementation;

//	@Override
//	// Consulta o user no banco com o Spring Security
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.userDetailsService(usuarioDetailsServiceImplementation).passwordEncoder(new BCryptPasswordEncoder());
//	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) // Desativa CSRF, útil para APIs REST
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(HttpMethod.POST, "/saveAcesso").permitAll() // Permite acesso sem autenticação
	            .requestMatchers(HttpMethod.GET, "/acessos").permitAll() // Permite acesso sem autenticação
	            .requestMatchers(HttpMethod.DELETE, "/acessos", "/deleteAcesso").permitAll() // Permite acesso sem autenticação
	            .anyRequest().authenticated() // Exige autenticação para outras URLs
	        );
	    return http.build();
	}




}
