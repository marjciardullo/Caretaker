package com.caretaker.caretaker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.caretaker.caretaker.repository.UsuarioRepository;
import com.caretaker.caretaker.security.JWTAuthenticationFilter;
import com.caretaker.caretaker.security.JWTAuthorizationFilter;
import com.caretaker.caretaker.security.JWTUtil;
import com.caretaker.caretaker.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
	private UsuarioRepository usuariorepo;
	
	private static final String[] PUBLIC_MATCHERS = {
			"/usuarios/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/usuarios/**",
			"/consulta/**",
			"/lembrete/**",
			"/medicamento/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
		.anyRequest()
		.authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil, usuariorepo));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, usuarioDetailsService));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetailsService).passwordEncoder(bCryptPasswordEncoder());
		
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
