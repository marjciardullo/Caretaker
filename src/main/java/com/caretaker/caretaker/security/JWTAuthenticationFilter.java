package com.caretaker.caretaker.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.caretaker.caretaker.DTO.CredenciaisDTO;
import com.caretaker.caretaker.model.Usuario;
import com.caretaker.caretaker.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;
	private UsuarioRepository usuariorepo;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UsuarioRepository usuariorepo) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.usuariorepo = usuariorepo;
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			CredenciaisDTO credenciais = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credenciais.getLogin(), credenciais.getSenha(), new ArrayList<>());
			Authentication auth = authenticationManager.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) 
			throws IOException, ServletException {
		String username = ((UsuarioDetails) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.addHeader("Authentication", "Bearer " + token);
		response.addHeader("access-control-expose-headers","Authorization");
		
		Usuario usuario = usuariorepo.findByLogin(username);
		response.setContentType("application/json");
		response.getWriter().append(jsonAuth(token, usuario));
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) 
			throws java.io.IOException, javax.servlet.ServletException {
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(jsonError());
	}
	
	private String jsonError() {
		return "{\"timestamp\": " + new Date().getTime() + ", "
				+ "\"status\": 401, " + "\"error\": \"Não autorizado\", " 
				+ "\"message\": \"Email ou senha inválidos\", "
				+ "\"path\": \"/login\"}";
	}
	
	private String jsonAuth(String token, Usuario usuario) {
		return "{\"token\": \"" + token + "\"" + ", " + 
				"\"email\": \"" + usuario.getEmail() + "\", " 
				+ "\"id\": \"" + usuario.getId() + "\", " 
				+ "\"username\": \"" + usuario.getLogin() + "\", " 
				+ "\"profile\": " + usuario.getUsuarios().stream()
				.map(x -> "\"" + x + "\"")
				.collect(Collectors.toList()) + "}";
	}
}

