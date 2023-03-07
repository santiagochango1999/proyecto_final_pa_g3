package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.modelo.Reserva;
import com.example.demo.modelo.Vehiculo;

public interface IReservaService {

	public List<Reserva> Reservar(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin);

	public void Retirar(String tipoRetiro);

	public List<Reserva> buscarPorRangoDeFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public List<Reserva> ordenarFechas(List<Reserva> reserva, String inicio);

	public Boolean validacion(LocalDateTime inicio, LocalDateTime fin, List<Reserva> reserva);

	public List<Reserva> buscarReserva();

	public Reserva buscarNumero(Integer numero);

	public void crear(Reserva reserva);

}
