package com.caretaker.caretaker.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicamentoId implements Serializable {
	private Usuario usuario;
    private Long id;
}
