package com.caretaker.caretaker.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caretaker.caretaker.DTO.ConsultaDTO;
import com.caretaker.caretaker.model.Consulta;
import com.caretaker.caretaker.model.Usuario;
import com.caretaker.caretaker.service.UsuarioService;

@Component
public class ConsultaMapper {
	
	public ConsultaMapper() { }
	
	@Autowired
	private UsuarioService service;
	
	public Consulta toEntity(ConsultaDTO consultaDTO) {
		Consulta consulta = new Consulta();
		consulta.setId(consultaDTO.getId());
		consulta.setNome(consultaDTO.getNome());
		consulta.setData(consultaDTO.getData());
		consulta.setDescricao(consultaDTO.getDescricao());
		consulta.setHora(consultaDTO.getHora());
		
		Usuario usuario = service.findById(consultaDTO.id_usuario);
		consulta.setUsuario(usuario);
		
		return consulta;
	}
	
	public ConsultaDTO toDTO(Consulta consulta) {
		ConsultaDTO consultaDTO = new ConsultaDTO();
		consultaDTO.setId(consulta.getId());
		consultaDTO.setNome(consulta.getNome());
		consultaDTO.setData(consulta.getData());
		consultaDTO.setDescricao(consulta.getDescricao());
		consultaDTO.setHora(consulta.getHora());
		consultaDTO.setId_usuario(consulta.getUsuario().getId());
		return consultaDTO;
	}
	
	public List<ConsultaDTO> toDTO(List<Consulta> consultas) {
		List<ConsultaDTO> dto = new ArrayList<>();
		for (Consulta d : consultas) {
			dto.add(toDTO(d));
		}
		return dto;
	}
}
