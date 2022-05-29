package com.caretaker.caretaker.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.caretaker.caretaker.service.UsuarioDetailsService;



public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWTUtil jwtUtil;
	private UsuarioDetailsService usuarioDetailsService;
	
	public JWTAuthorizationFilter(
			AuthenticationManager authenticationManager, 
			JWTUtil jwtUtil, 
			UsuarioDetailsService usuarioDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.usuarioDetailsService = usuarioDetailsService;
		
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
			if (auth != null)
				SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails usuarioDetails = usuarioDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(usuarioDetails, null, usuarioDetails.getAuthorities());
		}
		return null;
	}

}
