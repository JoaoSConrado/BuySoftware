package com.magna.buySoftware.vo;

import java.io.Serializable;
import java.util.List;

import com.magna.buySoftware.entity.SoftwareEntity;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private List<SoftwareEntity> softwares;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SoftwareEntity> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<SoftwareEntity> softwares) {
		this.softwares = softwares;
	}
}

