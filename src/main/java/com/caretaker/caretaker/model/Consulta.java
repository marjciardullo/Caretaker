package com.caretaker.caretaker.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_consulta")
public class Consulta extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Usuario usuario;
	
	@Column(name = "nm_consulta")
	private String nome;

	@Column(name = "ds_consulta")
	private String descricao;

	@Column(name = "dt_consulta")
	private Date data;
	
	@Column(name = "hr_consulta")
	private Time hora;

}
