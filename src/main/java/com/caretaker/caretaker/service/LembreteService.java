package com.caretaker.caretaker.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caretaker.caretaker.DTO.LembreteDTO;
import com.caretaker.caretaker.mapper.LembreteMapper;
import com.caretaker.caretaker.model.Lembrete;
import com.caretaker.caretaker.repository.LembreteRepository;

@Service
@Transactional
public class LembreteService {

	@Autowired
	private LembreteRepository repository;
	
	@Autowired
	private LembreteMapper mapper;

	public void create(LembreteDTO obj) {
		Lembrete lembrete = mapper.toEntity(obj);
		repository.save(lembrete);
	}
	
	public LembreteDTO findByIds(String hora, String data, Long medicamento) {
		Optional<Lembrete> lembrete_obj = repository.findByIds(hora, data, medicamento);
		if (lembrete_obj.isPresent())
			return mapper.toDTO(lembrete_obj.get());
		return null;
	}


	public List<LembreteDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}


	public boolean update(String hora, String data, Long medicamento, LembreteDTO lembrete) {
		if (repository.findByIds(hora,data, medicamento) != null) {
			delete(hora, data, medicamento);
			repository.save(mapper.toEntity(lembrete));
			return true;
		}
		return false;
	}

	
	public boolean delete(String hora, String data, Long medicamento) {
		 if(repository.findByIds(hora, data, medicamento) != null ) {
			repository.deleteByIds(hora, data, medicamento);
			return true;
		}
		return false;
	}
	
	
}
