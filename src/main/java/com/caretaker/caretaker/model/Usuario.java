package com.caretaker.caretaker.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "ds_login")
	private String login;

	@Column(name = "nm_email")
	private String email;

	@Column(name = "nm_senha")
	private String senha;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Calendar nascimento;

	@Column(name = "nm_recuperar_senha")
	private String recuperar_senha;

	@Column(name = "ic_cuidador")
	private Boolean cuidador;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_tipo_usuario")
	private Set<Integer> usuarios = new HashSet<>();

	public Usuario() {
	}

	// getters + setters
	
	public Set<TipoUsuario> getUsuarios() {
		return usuarios.stream()
				.map(x -> TipoUsuario.toEnum(x))
				.collect(Collectors.toSet());
	}
	
	public void addUsuario(TipoUsuario usuario) {
		this.usuarios.add(usuario.getCod());
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}
	
	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Calendar getNascimento() {
		return nascimento;
	}

	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}

	public String getRecuperarSenha() {
		return recuperar_senha;
	}

	public void setRecuperarSenha(String recuperar_senha) {
		this.recuperar_senha = recuperar_senha;
	}

	public Boolean getCuidador() {
		return cuidador;
	}

	public void setCuidador(Boolean cuidador) {
		this.cuidador = cuidador;
	}

}
