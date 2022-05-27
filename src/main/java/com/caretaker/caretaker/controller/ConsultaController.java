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

import com.caretaker.caretaker.DTO.ConsultaDTO;
import com.caretaker.caretaker.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController implements ControllerInterface<ConsultaDTO>{
	
	@Autowired
	private ConsultaService service;

	@Override
	@GetMapping
	public ResponseEntity<List<ConsultaDTO>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		ConsultaDTO obj = service.findById(id);
		if (obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<ConsultaDTO>> getByUser(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.findByUser(id));
	}

	@Override
	@PostMapping
	public ResponseEntity<ConsultaDTO> post(@RequestBody ConsultaDTO consulta) throws URISyntaxException {
		service.create(consulta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consulta.getId())
				.toUri();
		return ResponseEntity.created(location).body(consulta);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody ConsultaDTO consulta) {
		if (service.update(consulta)) {
			return ResponseEntity.ok(consulta);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
