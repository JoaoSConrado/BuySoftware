package com.magna.buySoftware.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	@OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL)
	private List<SoftwareEntity> softwares = new ArrayList<>();

	public UsuarioEntity() {

	}

	public UsuarioEntity(Long id, String nome, String cpf, String email, String senha, List<SoftwareEntity> softwares) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.softwares = softwares;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<SoftwareEntity> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<SoftwareEntity> softwares) {
		this.softwares = softwares;
	}
}
