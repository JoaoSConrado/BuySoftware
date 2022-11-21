package com.magna.buySoftware.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_SOFTWARE")
public class SoftwareEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String softwareDesejado;
	private Integer valorSoftware;
	private String desenvolvedor;
	private LocalDate dataEntrega;
	@ManyToOne(fetch = FetchType.LAZY)
	private UsuarioEntity usuarioEntity;
	private LocalDateTime dataPedido;

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

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
}

