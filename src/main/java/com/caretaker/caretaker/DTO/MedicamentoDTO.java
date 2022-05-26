package com.caretaker.caretaker.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicamentoDTO extends AbstractEntity {
	public String nome;
	public String dosagem;
	public String obs_medicamento;
	public String ds_frequencia_horas;
	public Float qt_medicamento;
	public Integer qt_frequencia_diaria;
	public Long id;
}
