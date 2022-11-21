package com.magna.buySoftware.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.magna.buySoftware.entity.SoftwareEntity;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "O Campo 'nome' não pode estar vazio!")
	private String nome;

	@NotBlank(message = "O Campo 'cpf' não pode estar vazio!")
	@Length(min = 14, max = 14)
	@CPF
	private String cpf;

	@NotBlank(message = "O Campo 'email' não pode estar vazio!")
	@Email(message = "Email Incorreto")
	private String email;

	@NotBlank(message = "O Campo 'senha' não pode estar vazio!")
	@Length(min = 8)
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
