package com.caretaker.caretaker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "nm_medicamento")
	private String login;

	@Column(name = "nm_dosagem")
	private String email;

	@Column(name = "qt_medicamento")
	private Float senha;

}
