package com.caretaker.caretaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caretaker.caretaker.model.Lembrete;
import com.caretaker.caretaker.repository.LembreteRepository;

@Service
public class LembreteService {

	@Autowired
	private LembreteRepository repository;

	public Lembrete create(Lembrete lembrete) {
		repository.save(lembrete);
		return null;
	}
	
	public Lembrete findByIds(String hora, String data, Long medicamento, Long usuario) {
		Optional<Lembrete> lembrete_obj = repository.findByIds(hora, data, medicamento, usuario);
		return lembrete_obj.orElse(null);
	}


	public List<Lembrete> findAll() {
		return repository.findAll();
	}


	public boolean update(String hora, String data, Long medicamento, Long usuario, Lembrete lembrete) {
		if (repository.findByIds(hora,data, medicamento, usuario) != null) {
			repository.save(lembrete);
			return true;
		}
		return false;
	}

	
	public boolean delete(Lembrete lembrete) {
		 if(repository.findByIds(lembrete.getHora().toString(), lembrete.getData().toString(), 
					lembrete.getMedicamento().getId(), lembrete.getUsuario().getId()
					) != null ) {
			repository.deleteByIds(lembrete.getHora().toString(), lembrete.getData().toString(), 
					lembrete.getMedicamento().getId(), lembrete.getUsuario().getId());
			return true;
		}
		return false;
	}
	
	
}
