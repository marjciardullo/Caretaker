package com.caretaker.caretaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.caretaker.caretaker.model.Usuario;
import com.caretaker.caretaker.repository.UsuarioRepository;
import com.caretaker.caretaker.security.UsuarioDetails;

@Service
public class UsuarioDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuariorepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuariorepo.findByLogin(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UsuarioDetails(usuario.getId(), usuario.getLogin(), usuario.getSenha(), usuario.getUsuarios());
	}
		

}
