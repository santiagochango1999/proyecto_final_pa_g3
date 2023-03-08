package com.example.demo.modelo.dto;

import java.math.BigDecimal;

public class Reporte {
	private String nombre;
	private String cedula;
	private BigDecimal valorIva;
	private BigDecimal valoTotal;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValoTotal() {
		return valoTotal;
	}

	public void setValoTotal(BigDecimal valoTotal) {
		this.valoTotal = valoTotal;
	}

}
