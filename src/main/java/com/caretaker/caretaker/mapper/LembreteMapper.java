package com.caretaker.caretaker.mapper;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caretaker.caretaker.DTO.LembreteDTO;
import com.caretaker.caretaker.DTO.MedicamentoDTO;
import com.caretaker.caretaker.model.Lembrete;
import com.caretaker.caretaker.model.Medicamento;
import com.caretaker.caretaker.service.MedicamentoService;

@Component
public class LembreteMapper {
	
	public LembreteMapper() { }
	
	@Autowired
	private MedicamentoService service;
	
	@Autowired
	private MedicamentoMapper mapper;
	
	public Lembrete toEntity(LembreteDTO lembreteDTO) {
		Lembrete lembrete = new Lembrete();
		
		Date data = Date.valueOf(lembreteDTO.getData());
		lembrete.setData(data);
		
		Time hora = Time.valueOf(lembreteDTO.getHora());
		lembrete.setHora(hora);
		
		MedicamentoDTO medicamentoDTO = service.findById(lembreteDTO.getId_medicamento());
		Medicamento medicamento = mapper.toEntity(medicamentoDTO);
		lembrete.setMedicamento(medicamento);
		
		lembrete.setId_medicamento(medicamento.getId());
		return lembrete;
	}
	
	public LembreteDTO toDTO(Lembrete lembrete) {
		LembreteDTO lembreteDTO = new LembreteDTO();

		lembreteDTO.setData(lembrete.getData().toString());
		
		lembreteDTO.setHora(lembrete.getHora().toString());
		
		lembreteDTO.setId_medicamento(lembrete.getMedicamento().getId());
		
		lembreteDTO.setMedicamento(lembrete.getMedicamento());
		
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
