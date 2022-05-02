package com.caretaker.caretaker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caretaker.caretaker.model.Medicamento;
import com.caretaker.caretaker.repository.MedicamentoRepository;

@Service
public class MedicamentoService implements ServiceInterface<Medicamento>{

	@Autowired
	private MedicamentoRepository repository;

	@Override
	public Medicamento create(Medicamento medicamento) {
		repository.save(medicamento);
		return null;
	}
	
	@Override
	public Medicamento findById(Long id) {
		Optional<Medicamento> medicamento_obj = repository.findById(id);
		return medicamento_obj.orElse(null);
	}

	@Override
	public List<Medicamento> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean update(Medicamento medicamento) {
		if (repository.existsById(medicamento.getId())) {
			repository.save(medicamento);
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
