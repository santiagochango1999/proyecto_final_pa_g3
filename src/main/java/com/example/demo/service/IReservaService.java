package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.modelo.Reserva;
import com.example.demo.modelo.Vehiculo;

public interface IReservaService {

	public Boolean Reservar(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin);

	public void Retirar(String tipoRetiro);
	
	public List<Reserva> buscarPorRangoDeFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	public List<LocalDateTime> oredenamientoFechas(Vehiculo vehiculo);

}
