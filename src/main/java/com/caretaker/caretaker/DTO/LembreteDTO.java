package com.caretaker.caretaker.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LembreteDTO extends AbstractEntity {
	public String hora;
	public String data;
	public Long id_medicamento;
}
