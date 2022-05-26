package com.caretaker.caretaker.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caretaker.caretaker.DTO.MedicamentoDTO;
import com.caretaker.caretaker.model.Medicamento;
import com.caretaker.caretaker.model.Usuario;
import com.caretaker.caretaker.service.UsuarioService;

@Component
public class MedicamentoMapper {
	
	public MedicamentoMapper() { }
	
	@Autowired
	private UsuarioService service;
	
	public Medicamento toEntity(MedicamentoDTO medicamentoDTO) {
		Medicamento medicamento = new Medicamento();
		medicamento.setDosagem(medicamentoDTO.getDosagem());
		medicamento.setDs_frequencia_horas(medicamentoDTO.getDs_frequencia_horas());
		medicamento.setId(medicamentoDTO.getId());
		medicamento.setNome(medicamentoDTO.getNome());
		medicamento.setObs_medicamento(medicamentoDTO.getObs_medicamento());
		medicamento.setQt_frequencia_diaria(medicamentoDTO.getQt_frequencia_diaria());
		medicamento.setQt_medicamento(medicamentoDTO.getQt_medicamento());
		
		Usuario usuario = service.findById(medicamentoDTO.id_usuario);
		medicamento.setUsuario(usuario);
		
		return medicamento;
	}
	
	public MedicamentoDTO toDTO(Medicamento medicamento) {
		MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
		medicamentoDTO.setDosagem(medicamento.getDosagem());
		medicamentoDTO.setDs_frequencia_horas(medicamento.getDs_frequencia_horas());
		medicamentoDTO.setId(medicamento.getId());
		medicamentoDTO.setNome(medicamento.getNome());
		medicamentoDTO.setObs_medicamento(medicamento.getObs_medicamento());
		medicamentoDTO.setQt_frequencia_diaria(medicamento.getQt_frequencia_diaria());
		medicamentoDTO.setQt_medicamento(medicamento.getQt_medicamento());
		
		medicamentoDTO.setId_usuario(medicamento.getUsuario().getId());
		return medicamentoDTO;
	}
	
	public List<MedicamentoDTO> toDTO(List<Medicamento> medicamentos) {
		List<MedicamentoDTO> dto = new ArrayList<>();
		for (Medicamento d : medicamentos) {
			dto.add(toDTO(d));
		}
		return dto;
	}
}
