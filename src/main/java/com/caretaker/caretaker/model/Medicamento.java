package com.caretaker.caretaker.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_medicamento")
public class Medicamento extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Usuario usuario;
	
	@Column(name = "nm_medicamento")
	private String nome;

	@Column(name = "nm_dosagem")
	private String dosagem;

	@Column(name = "qt_medicamento")
	private Float qt_medicamento;
	
	@Column(name = "ds_medicamento")
	private String obs_medicamento;
	
	@Column(name = "qt_frequencia_diaria")
	private Integer qt_frequencia_diaria;
	
	@Column(name = "ds_frequencia_horas")
	private String ds_frequencia_horas;

}
