package com.caretaker.caretaker.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
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
@IdClass(LembreteId.class)
public class Lembrete {
	@Id
    @Column(name = "medicamento_pk_id")
    protected Long id_medicamento;
	
	@ManyToOne
	@JoinColumn(name="medicamento_pk_id", insertable = false, updatable = false)
	private Medicamento medicamento;
	
	@Id
	@Column(name = "dt_lembrete")
	private Date data;
	
	@Id
	@Column(name = "hr_lembrete")
	private Time hora;
}
