package com.caretaker.caretaker.model;

public enum TipoUsuario {
	USUARIO(1, "ROLE_USUARIO");
	
	private Integer cod;
	private String desc;
	
	private TipoUsuario(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public Integer getCod() { 
		return cod;
	}
	
	public String getDesc() { 
		return desc;
	}
	
	public static TipoUsuario toEnum(Integer cod) {
		if (cod == null) return null;
		for (TipoUsuario usuario : TipoUsuario.values()) {
			if (cod.equals(usuario.getCod()))
			return usuario;
		}
		throw new IllegalArgumentException("Código do usuário inválido: " + cod);
	}
}
