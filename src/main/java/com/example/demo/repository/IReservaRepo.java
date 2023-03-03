package com.example.demo.repository;

import java.util.List;

import com.example.demo.modelo.Reserva;

public interface IReservaRepo {

	public void ingresar(Reserva reserva);

	public Reserva buscarNumero(Integer numero);

	public List<Reserva> buscarReserva();

	public void eliminar(Integer numero);
}
