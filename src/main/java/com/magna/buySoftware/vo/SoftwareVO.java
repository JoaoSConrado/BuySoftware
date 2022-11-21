package com.magna.buySoftware.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magna.buySoftware.entity.UsuarioEntity;

public class SoftwareVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "O Campo 'Software Desejado' não pode estar vazio!")
	private String softwareDesejado;
	
	@NotNull(message = "O Campo 'Valor Software' não pode ser nulo!")
	private Integer valorSoftware;
	
	@NotBlank(message = "O Campo 'desenvolvedor' não pode estar vazio!")
	private String desenvolvedor;
	
	@NotNull(message = "O Campo 'dataEntrega' não pode estar vazio!")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	@FutureOrPresent
	private LocalDate dataEntrega;
	
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

