package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.modelo.Reserva;

public interface IReservaRepo {

	public void ingresar(Reserva reserva);

	public Reserva buscarNumero(Integer numero);

	public Reserva buscarPorfecha(LocalDateTime fechaInicio);

	public List<Reserva> buscarReporte(String nombre);

	public List<Reserva> buscarReporteVehi(String placa);

	public List<Reserva> buscarReserva();

	public void eliminar(Integer numero);

}
