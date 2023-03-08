package com.example.demo.modelo.dto;

import java.math.BigDecimal;

public class ReporteVehi {

	private String placa;
	private String modelo;
	private BigDecimal valoTotal;
	private BigDecimal valorIva;

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

	public BigDecimal getValoTotal() {
		return valoTotal;
	}

	public void setValoTotal(BigDecimal valoTotal) {
		this.valoTotal = valoTotal;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

}
