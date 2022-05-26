package com.caretaker.caretaker.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LembreteId implements Serializable {
	protected Long id_medicamento;
    protected Date data;
    protected Time hora;
}
