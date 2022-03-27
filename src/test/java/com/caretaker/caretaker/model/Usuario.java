package com.caretaker.caretaker.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	public Usuario() {
	}
	
	// getters + setters
	
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public String getSenha() {return senha;}
	public void setSenha(String senha) {this.senha = senha;}

	public Calendar getNascimento() {return nascimento;}
	public void setNascimento(Calendar nascimento) {this.nascimento = nascimento;}
	
	public String getRecuperarSenha() {return recuperar_senha;}
	public void setRecuperarSenha(String recuperar_senha) {this.recuperar_senha = recuperar_senha;}
	
	public Boolean getCuidador() {return cuidador;}
	public void setCuidador(Boolean cuidador) {this.cuidador = cuidador;}
	
	
	

}
