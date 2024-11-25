package br.net.gest.sys.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

////	@Bean
////	public WebSecurityCustomizer webSecurityCustomizer() {
////		return (web) -> web.ignoring().requestMatchers("/acesso");
////		
////	}
////	
////	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
////		
////		http
////				.csrf(csrf -> csrf.disable())
////				.authorizeHttpRequests(auth -> auth
////						.anyRequest().permitAll()); // Permite acesso a todas as URLs sem autenticação
////	
////		return http.build();
////	}
//	
//	
//	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) // Desativa CSRF, útil para APIs REST
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(HttpMethod.POST, "/saveAcesso").permitAll() // Permite acesso sem autenticação
	            .requestMatchers(HttpMethod.GET, "/acessos").permitAll() // Permite acesso sem autenticação
	            .anyRequest().authenticated() // Exige autenticação para outras URLs
	        );
	    return http.build();
	}




}
