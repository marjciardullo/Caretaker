package com.caretaker.caretaker.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_medicamento_usuario")
@IdClass(MedicamentoUsuarioId.class)
public class MedicamentoUsuario {
	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Usuario usuario;

	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Medicamento medicamento;
	
	@Column(name = "ds_usuario_medicamento")
	private String observacao;

	@Column(name = "qt_frequencia_diaria")
	private String frequencia_diaria;
	
	@Column(name = "ds_frequencia_horas")
	private String frequencia_horas;
}
