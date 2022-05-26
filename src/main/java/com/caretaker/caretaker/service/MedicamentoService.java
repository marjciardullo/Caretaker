package com.caretaker.caretaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caretaker.caretaker.model.Medicamento;
import com.caretaker.caretaker.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository repository;

	public Medicamento create(Medicamento medicamento) {
		repository.save(medicamento);
		return null;
	}
	
	public Medicamento findByIds(Long id, Long usuario) {
		Optional<Medicamento> medicamento_obj = repository.findByIds(id, usuario);
		return medicamento_obj.orElse(null);
	}

	public List<Medicamento> findAll() {
		return repository.findAll();
	}

	public boolean update(Medicamento medicamento) {
		if (repository.existsById(medicamento.getId())) {
			repository.save(medicamento);
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
