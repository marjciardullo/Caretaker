package com.caretaker.caretaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caretaker.caretaker.DTO.MedicamentoDTO;
import com.caretaker.caretaker.mapper.MedicamentoMapper;
import com.caretaker.caretaker.model.Medicamento;
import com.caretaker.caretaker.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository repository;
	
	@Autowired
	private MedicamentoMapper mapper;

	public void create(MedicamentoDTO medicamento) {
		repository.save(mapper.toEntity(medicamento));
	}
	
	public MedicamentoDTO findById(Long id) {
		Optional<Medicamento> medicamento_obj = repository.findById(id);
		
		if (medicamento_obj.isPresent())
			return mapper.toDTO(medicamento_obj.get());
		return null;
	}

	public List<MedicamentoDTO> findAll() {
		return  mapper.toDTO(repository.findAll());
	}

	public boolean update(MedicamentoDTO medicamento) {
		if (repository.existsById(medicamento.getId())) {
			repository.save(mapper.toEntity(medicamento));
			return true;
		}
		return false;
	}

	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
	
	
}
