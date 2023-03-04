package com.example.demo.modelo.dto;

import java.math.BigDecimal;

public class VehiculoDTO {
	
	private String placa;
	
	private String modelo;
	
	private String marca;

	private Integer añoFabricion;
	
	private String disponibilidad;

	private BigDecimal valorDia;
	
	
	
	public VehiculoDTO() {
		
	}

	public VehiculoDTO(String placa, String modelo, String marca, Integer añoFabricion, String disponibilidad,
			BigDecimal valorDia) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.añoFabricion = añoFabricion;
		this.disponibilidad = disponibilidad;
		this.valorDia = valorDia;
		
		//get and set 
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getAñoFabricion() {
		return añoFabricion;
	}

	public void setAñoFabricion(Integer añoFabricion) {
		this.añoFabricion = añoFabricion;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public BigDecimal getValorDia() {
		return valorDia;
	}

	public void setValorDia(BigDecimal valorDia) {
		this.valorDia = valorDia;
	}

	@Override
	public String toString() {
		return "VehiculoDTO [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", añoFabricion="
				+ añoFabricion + ", disponibilidad=" + disponibilidad + ", valorDia=" + valorDia + "]";
	}
	
	
	
	
}
