package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.Reserva;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.modelo.dto.Reporte;
import com.example.demo.modelo.dto.ReporteVehi;

public interface IReservaService {

	public List<Reserva> Reservar(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin);

	public void Retirar(String tipoRetiro);

	public Reserva buscarPorfecha(LocalDateTime fechaInicio);

	public List<Reserva> ordenarFechas(List<Reserva> reserva);

	public Boolean validacion(LocalDateTime inicio, LocalDateTime fin, List<Reserva> reserva);

	public List<Reserva> reporteFechas(LocalDateTime inicio, LocalDateTime fin, List<Reserva> reserva);

	public List<Reserva> buscarReserva();

	public Reserva buscarNumero(Integer numero);

	public void crear(Reserva reserva);

	public List<Reserva> buscarReporte(String nombre);

	public List<Reporte> segundoReporte(List<Cliente> clientes);

	public List<ReporteVehi> tercerReporte(List<Vehiculo> vehiculos);

	public List<Reserva> buscarReporteVehi(String placa);

}
