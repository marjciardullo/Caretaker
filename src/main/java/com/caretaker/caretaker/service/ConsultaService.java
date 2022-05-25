package com.caretaker.caretaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caretaker.caretaker.DTO.ConsultaDTO;
import com.caretaker.caretaker.mapper.ConsultaMapper;
import com.caretaker.caretaker.model.Consulta;
import com.caretaker.caretaker.repository.ConsultaRepository;

@Service
public class ConsultaService implements ServiceInterface<ConsultaDTO>{

	@Autowired
	private ConsultaRepository repository;
	
	@Autowired
	private ConsultaMapper mapper;

	@Override
	public ConsultaDTO create(ConsultaDTO obj) {
		repository.save(mapper.toEntity(obj));
		return obj;
	}
	
	@Override
	public ConsultaDTO findById(Long id) {
		Optional<Consulta> consulta_obj = repository.findById(id);
		if (consulta_obj.isPresent())
			return mapper.toDTO(consulta_obj.get());
		return null;
	}

	@Override
	public List<ConsultaDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}

	@Override
	public boolean update(ConsultaDTO consulta) {
		if (repository.existsById(consulta.getId())) {
			repository.save(mapper.toEntity(consulta));
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
