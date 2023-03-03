package com.example.demo.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

	// DATOS
	@Id
	@GeneratedValue(generator = "seq_rese", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_rese", sequenceName = "seq_rese", allocationSize = 1)
	@Column(name = "rese_id")
	private Integer id;
	@Column(name = "rese_valor_subtotal")
	private BigDecimal valorSubtotal;
	@Column(name = "rese_valor_iva")
	private BigDecimal valorIva;
	@Column(name = "rese_valor_total_pagar")
	private BigDecimal valorTotalPagar;

	// RELACION

	@ManyToOne
	@JoinColumn(name = "rese_id_vehiculo")
	private Vehiculo vehiculo;

	@ManyToOne
	@JoinColumn(name = "rese_id_cliente")
	private Cliente cliente;

	// METODOS GET Y SET

	public Integer getId() {
		return id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValorTotalPagar() {
		return valorTotalPagar;
	}

	public void setValorTotalPagar(BigDecimal valorTotalPagar) {
		this.valorTotalPagar = valorTotalPagar;
	}

	// TO STRING

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", valorSubtotal=" + valorSubtotal + ", valorIva=" + valorIva
				+ ", valorTotalPagar=" + valorTotalPagar + "]";
	}

}
