package com.caretaker.caretaker.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_lembrete")
@IdClass(Lembrete.class)
public class Lembrete implements Serializable {
	@Id
	@ManyToOne
	private Medicamento medicamento;
	
	@Id
	@Column(name = "dt_lembrete")
	private Date data;
	
	@Id
	@Column(name = "hr_lembrete")
	private Time hora;
}
