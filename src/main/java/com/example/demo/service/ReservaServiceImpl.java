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
	public List<Reserva> Reservar(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin) {
		// TODO Auto-generated method stub
		Boolean val = true;
		Boolean devolucion = null;
		Long range = null;
		Random r = new Random();

		Cliente cliente = this.clienteService.buscarCedula(cedula).get(0);
		Vehiculo vehiculo = this.VehiculoService.buscarPlaca(placa);
		Reserva reserva = new Reserva();
		List<Reserva> list = this.iReservaRepo.buscarReserva();

		val = validacion(inicio, fin, list);

		if (val) {
			// NUMERO DE DIAS
			if (inicio != null || fin != null) {
				range = ChronoUnit.DAYS.between(inicio.toLocalDate(), fin.toLocalDate());
			}

			// INGRESO LOS DATOS
			reserva.setValorSubtotal(vehiculo.getValorDia().multiply(new BigDecimal(range)));
			reserva.setValorIva((reserva.getValorSubtotal().multiply(new BigDecimal(12))).divide(new BigDecimal(100)));
			reserva.setValorTotalPagar(reserva.getValorSubtotal().add(reserva.getValorIva()));
			reserva.setFechaInicio(inicio);
			reserva.setFechafinal(fin);
			reserva.setCliente(cliente);
			reserva.setVehiculo(vehiculo);
			// G DE GENERADO LA RESERVA
			reserva.setNumero(r.nextInt());

			list.add(reserva);

		}
		return list;
	}

	// ORDENAMIENTO DE FECHAS
//	@Override
//	public List<LocalDateTime> oredenamientoFechas(Vehiculo vehiculo) {
//
//		List<Reserva> lista = vehiculo.getReserva();
//		List<LocalDateTime> listafecha = new ArrayList<>();
//		List<LocalDateTime> listafinal = new ArrayList<>();
//
//		// VALIDACION DE FECHAS
//		for (Reserva listare : lista) {
//			listafecha.add(listare.getFechaInicio());
//			listafecha.add(listare.getFechafinal());
//		}
//		List<LocalDateTime> listaOrden = listafecha.stream().sorted().collect(Collectors.toList());
////
////		for (int i = 1; i <= (listaOrden.size()) / 2; i = i + 2) {
//////			System.out.println("desde: " + listaOrden.get(i) + " hasta: " + listaOrden.get(i + 1));
////			listafinal.add(listaOrden.get(i));
////			listafinal.add(listaOrden.get(i + 1));
////		}
//
//		return listaOrden;
//	}

	@Override
	public List<Reserva> ordenarFechas(List<Reserva> reserva, String inicio) {
		List<Reserva> reservas;
		if (inicio.equals("inicio")) {
			reservas = reserva.stream().sorted(Comparator.comparing(Reserva::getFechaInicio))
					.collect(Collectors.toList());
		} else {
			reservas = reserva.stream().sorted(Comparator.comparing(Reserva::getFechafinal))
					.collect(Collectors.toList());
		}
		return reservas;
	}

	// VALIDACION DE FECHAS
	public Boolean validacion(LocalDateTime inicio, LocalDateTime fin, List<Reserva> reserva) {

		Boolean val = null;
		for (Reserva r : reserva) {
			if (r.getFechafinal() != null && r.getFechaInicio() != null) {

				if ((r.getFechaInicio().isBefore(inicio) && r.getFechaInicio().isBefore(fin))
						&& (r.getFechafinal().isBefore(inicio) && r.getFechafinal().isBefore(fin))
						|| (r.getFechaInicio().isAfter(inicio) && r.getFechaInicio().isAfter(fin))
								&& (r.getFechafinal().isAfter(inicio) && r.getFechafinal().isAfter(fin))) {
					val = true;
				} else {
					val = false;
					break;
				}
			}
			val = true;
		}

		return val;

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

	@Override
	public List<Reserva> buscarReserva() {
		// TODO Auto-generated method stub
		return this.iReservaRepo.buscarReserva();
	}

	@Override
	public Reserva buscarNumero(Integer numero) {
		// TODO Auto-generated method stub
		return this.iReservaRepo.buscarNumero(numero);
	}

	@Override
	public void crear(Reserva reserva) {
		// TODO Auto-generated method stub
		this.iReservaRepo.ingresar(reserva);
	}

}
