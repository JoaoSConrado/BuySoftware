package com.magna.buySoftware.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magna.buySoftware.entity.UsuarioEntity;

public class SoftwareVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String softwareDesejado;
	private Integer valorSoftware;
	private String desenvolvedor;
	private LocalDate dataEntrega;
	private UsuarioEntity usuarioEntity;
	private LocalDateTime dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSoftwareDesejado() {
		return softwareDesejado;
	}

	public void setSoftwareDesejado(String softwareDesejado) {
		this.softwareDesejado = softwareDesejado;
	}

	public Integer getValorSoftware() {
		return valorSoftware;
	}

	public void setValorSoftware(Integer valorSoftware) {
		this.valorSoftware = valorSoftware;
	}

	public String getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(String desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	@JsonIgnore
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}

