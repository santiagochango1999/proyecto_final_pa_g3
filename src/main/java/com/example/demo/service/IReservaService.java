package com.example.demo.service;

import java.time.LocalDateTime;

public interface IReservaService {

	public void Reserva(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin);

	public void Retirar(String tipoRetiro);

}
