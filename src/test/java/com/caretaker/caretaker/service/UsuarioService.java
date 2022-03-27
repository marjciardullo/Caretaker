package com.caretaker.caretaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caretaker.caretaker.model.Usuario;
import com.caretaker.caretaker.repository.UsuarioRepository;

@Service
public class UsuarioService implements ServiceInterface<Usuario> {
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario create(Usuario usuario) {
		repository.save(usuario);
		return null;
	}

	@Override
	public Usuario findById(Long id) {
		Optional<Usuario> usuario_obj = repository.findById(id);
		return usuario_obj.orElse(null);
	}

	@Override
	public List<Usuario> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Usuario usuario) {
		if (repository.existsById(usuario.getId())) {
			repository.save(usuario);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

}
