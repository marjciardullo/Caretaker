package com.caretaker.caretaker.mapper;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caretaker.caretaker.DTO.LembreteDTO;
import com.caretaker.caretaker.model.Lembrete;
import com.caretaker.caretaker.model.Medicamento;
import com.caretaker.caretaker.service.MedicamentoService;

@Component
public class LembreteMapper {
	
	public LembreteMapper() { }
	
	@Autowired
	private MedicamentoService service;
	
	public Lembrete toEntity(LembreteDTO lembreteDTO) {
		Lembrete lembrete = new Lembrete();
		
		Date data = Date.valueOf(lembreteDTO.getData());
		lembrete.setData(data);
		
		Time hora = Time.valueOf(lembreteDTO.getHora());
		lembrete.setHora(hora);
		
		Medicamento medicamento = service.findByIds(lembreteDTO.getId_medicamento(), lembreteDTO.getId_usuario());
		lembrete.setMedicamento(medicamento);
		
		return lembrete;
	}
	
	public LembreteDTO toDTO(Lembrete lembrete) {
		LembreteDTO lembreteDTO = new LembreteDTO();
		
		Date data = Date.valueOf(lembreteDTO.getData());
		lembrete.setData(data);
		
		Time hora = Time.valueOf(lembreteDTO.getHora());
		lembrete.setHora(hora);
		
		lembreteDTO.setId_usuario(lembrete.getMedicamento().getUsuario().getId());
		lembreteDTO.setId_medicamento(lembrete.getMedicamento().getId());
		return lembreteDTO;
	}
	
	public List<LembreteDTO> toDTO(List<Lembrete> lembretes) {
		List<LembreteDTO> dto = new ArrayList<>();
		for (Lembrete d : lembretes) {
			dto.add(toDTO(d));
		}
		return dto;
	}
}
