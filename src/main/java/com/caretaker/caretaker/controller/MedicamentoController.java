package com.caretaker.caretaker.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caretaker.caretaker.DTO.MedicamentoDTO;
import com.caretaker.caretaker.model.Medicamento;
import com.caretaker.caretaker.service.MedicamentoService;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoService service;

	@GetMapping
	public ResponseEntity<List<MedicamentoDTO>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		MedicamentoDTO medicamento = service.findById(id);
		if (medicamento != null) {
			return ResponseEntity.ok(medicamento);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<MedicamentoDTO> post(@RequestBody MedicamentoDTO medicamento) throws URISyntaxException {
		service.create(medicamento);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{hora}/{data}/{medicamento}")
				.buildAndExpand(medicamento.getId())
				.toUri();
		return ResponseEntity.created(location).body(medicamento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> put(@RequestBody MedicamentoDTO medicamento) {
		if (service.update(medicamento)) {
			return ResponseEntity.ok(medicamento);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
