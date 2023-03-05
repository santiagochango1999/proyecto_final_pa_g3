package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.modelo.Reserva;

public interface IReservaService {

	public void Reservar(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin);

	public void Retirar(String tipoRetiro);
	
	public List<Reserva> buscarPorRangoDeFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
