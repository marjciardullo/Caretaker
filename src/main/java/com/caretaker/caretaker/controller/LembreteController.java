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

import com.caretaker.caretaker.DTO.LembreteDTO;
import com.caretaker.caretaker.DTO.LembretePostDTO;
import com.caretaker.caretaker.service.LembreteService;

@RestController
@RequestMapping("/lembrete")
public class LembreteController {
	
	@Autowired
	private LembreteService service;

	@GetMapping
	public ResponseEntity<List<LembreteDTO>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<LembreteDTO>> getAll(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.findByUser(id));
	}


	@GetMapping("/{hora}/{data}/{medicamento}")
	public ResponseEntity<?> get(@PathVariable("hora") String hora, 
			@PathVariable("data") String data,
			@PathVariable("medicamento") Long medicamento) {
		
		LembreteDTO lembrete = service.findByIds(hora, data, medicamento);
		if (lembrete != null) {
			return ResponseEntity.ok(lembrete);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<LembretePostDTO> post(@RequestBody LembretePostDTO lembrete) throws URISyntaxException {
		LembreteDTO lembreteDTO = new LembreteDTO();
		lembreteDTO.setData(lembrete.getData());
		lembreteDTO.setHora(lembrete.getHora());
		lembreteDTO.setId_medicamento(lembrete.getId_medicamento());
		
		service.create(lembreteDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{hora}/{data}/{medicamento}")
				.buildAndExpand(
						lembrete.getHora(),
						lembrete.getData(), 
						lembrete.getId_medicamento())
				.toUri();
		return ResponseEntity.created(location).body(lembrete);
	}

	@PutMapping("/{hora}/{data}/{medicamento}")
	public ResponseEntity<?> put(@PathVariable("hora") String hora, 
			@PathVariable("data") String data,
			@PathVariable("medicamento") Long medicamento,
			@RequestBody LembreteDTO lembrete) {
		if (service.update(hora, data, medicamento, lembrete)) {
			return ResponseEntity.ok(lembrete);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{hora}/{data}/{medicamento}")
	public ResponseEntity<?> delete(@PathVariable("hora") String hora, 
			@PathVariable("data") String data,
			@PathVariable("medicamento") Long medicamento) {
		if (service.delete(hora, data, medicamento)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
