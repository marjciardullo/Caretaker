package com.caretaker.caretaker.DTO;

import com.caretaker.caretaker.model.Medicamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LembreteDTO {
	public String hora;
	public String data;
	public Long id_medicamento;
	public Medicamento medicamento;
}
