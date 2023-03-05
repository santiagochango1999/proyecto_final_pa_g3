package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.repository.IReservaRepo;
import com.example.demo.modelo.Reserva;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService VehiculoService;

	@Autowired
	private IReservaRepo iReservaRepo;

	@Override
	public Boolean Reservar(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin) {
		// TODO Auto-generated method stub
		Boolean val = true;
		Boolean devolucion = null;
		Long range = Long.valueOf(0);
		Random r = new Random();

		Cliente cliente = this.clienteService.buscarCedula(cedula).get(0);
		Vehiculo vehiculo = this.VehiculoService.buscarPlaca(placa);
		Reserva reserva = new Reserva();
		List<Reserva> list = this.iReservaRepo.buscarReserva();

		vehiculo.setReserva(list);

		List<Reserva> lista = vehiculo.getReserva();

		// VALIDACION DE FECHAS
		for (Reserva listare : lista) {
			val = validacion(inicio, fin, listare);
		}

		if (val) {
			// DISPONIBLE EN FECHA
			vehiculo.setDisponibilidad("ND");
			this.VehiculoService.actualizar(vehiculo);

			// NUMERO DE DIAS
			if (reserva.getFechafinal() != null || reserva.getFechaInicio() != null) {
				range = ChronoUnit.DAYS.between(reserva.getFechaInicio().toLocalDate(),
						reserva.getFechafinal().toLocalDate());
			}

			// INGRESO LOS DATOS
			reserva.setValorSubtotal(vehiculo.getValorDia().multiply(new BigDecimal(range)));
			reserva.setValorIva(reserva.getValorSubtotal().multiply(new BigDecimal(0.12)));
			reserva.setValorTotalPagar(reserva.getValorSubtotal().add(reserva.getValorIva()));
			reserva.setFechaInicio(inicio);
			reserva.setFechafinal(fin);
			reserva.setCliente(cliente);
			reserva.setVehiculo(vehiculo);
			// G DE GENERADO LA RESERVA
			reserva.setEstado("G");
			reserva.setNumero(r.nextInt());
			list.add(reserva);
			this.iReservaRepo.ingresar(reserva);
			vehiculo.setReserva(list);
			cliente.setReserva(list);
			this.VehiculoService.actualizar(vehiculo);
			this.clienteService.actualizar(cliente);
			devolucion = true;

		} else {
			// IMPRESION DE FECHAS DISPONIBLES
			devolucion = false;
		}

		return devolucion;

	}

	// ORDENAMIENTO DE FECHAS
	@Override
	public List<LocalDateTime> oredenamientoFechas(Vehiculo vehiculo) {

		List<Reserva> lista = vehiculo.getReserva();
		List<LocalDateTime> listafecha = new ArrayList<>();
		List<LocalDateTime> listafinal = new ArrayList<>();

		// VALIDACION DE FECHAS
		for (Reserva listare : lista) {
			listafecha.add(listare.getFechaInicio());
			listafecha.add(listare.getFechafinal());
		}
		List<LocalDateTime> listaOrden = listafecha.stream().sorted().collect(Collectors.toList());

		for (int i = 1; i <= (listaOrden.size()) / 2; i = i + 2) {
//			System.out.println("desde: " + listaOrden.get(i) + " hasta: " + listaOrden.get(i + 1));
			listafinal.add(listaOrden.get(i));
			listafinal.add(listaOrden.get(i + 1));
		}

		return listafinal;
	}

	// VALIDACION DE FECHAS
	public Boolean validacion(LocalDateTime inicio, LocalDateTime fin, Reserva reserva) {

		if (reserva.getFechafinal() != null || reserva.getFechaInicio() != null) {

			if ((reserva.getFechaInicio().isBefore(inicio) && reserva.getFechaInicio().isBefore(fin))
					&& (reserva.getFechafinal().isBefore(inicio) && reserva.getFechafinal().isBefore(fin))
					|| (reserva.getFechaInicio().isAfter(inicio) && reserva.getFechaInicio().isAfter(fin))
							&& (reserva.getFechafinal().isAfter(inicio) && reserva.getFechafinal().isAfter(fin))) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public void Retirar(String tipoRetiro) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reserva> buscarPorRangoDeFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		return this.iReservaRepo.buscarPorRangoDeFechas(fechaInicio, fechaFin);
	}

}
