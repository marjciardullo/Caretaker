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

import com.caretaker.caretaker.model.Lembrete;
import com.caretaker.caretaker.service.LembreteService;

@RestController
@RequestMapping("/lembrete")
public class LembreteController {
	
	@Autowired
	private LembreteService service;

	@GetMapping
	public ResponseEntity<List<Lembrete>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}


	@GetMapping("/{hora}/{data}/{medicamento}/{usuario}")
	public ResponseEntity<?> get(@PathVariable("hora") String hora, 
			@PathVariable("data") String data,
			@PathVariable("medicamento") Long medicamento,
			@PathVariable("usuario") Long usuario) {
		
		Lembrete lembrete = service.findByIds(hora, data, medicamento, usuario);
		if (lembrete != null) {
			return ResponseEntity.ok(lembrete);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<Lembrete> post(@RequestBody Lembrete lembrete) throws URISyntaxException {
		service.create(lembrete);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{hora}/{data}/{medicamento}/{usuario}")
				.buildAndExpand(
						lembrete.getHora(),
						lembrete.getData(), 
						lembrete.getMedicamento().getId(), 
						lembrete.getUsuario().getId())
				.toUri();
		return ResponseEntity.created(location).body(lembrete);
	}

	@PutMapping("/{hora}/{data}/{medicamento}/{usuario}")
	public ResponseEntity<?> put(@PathVariable("hora") String hora, 
			@PathVariable("data") String data,
			@PathVariable("medicamento") Long medicamento,
			@PathVariable("usuario") Long usuario,
			@RequestBody Lembrete lembrete) {
		if (service.update(hora, data, medicamento, usuario, lembrete)) {
			return ResponseEntity.ok(lembrete);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Lembrete lembrete) {
		if (service.delete(lembrete)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
