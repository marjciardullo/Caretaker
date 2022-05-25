package com.caretaker.caretaker.DTO;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaDTO extends AbstractEntity {
	public Time hora;
	public Date data;
	public String descricao;
	public String nome;
}
