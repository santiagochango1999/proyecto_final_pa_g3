package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
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
import com.example.demo.modelo.dto.Reporte;
import com.example.demo.modelo.dto.ReporteVehi;
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
	@Override
	public List<Reserva> ordenarFechas(List<Reserva> reserva) {
		List<Reserva> reservas;
		reservas = reserva.stream().sorted(Comparator.comparing(Reserva::getFechaInicio)).collect(Collectors.toList());
		return reservas;
	}

	// VALIDACION DE FECHAS
	public Boolean validacion(LocalDateTime inicio, LocalDateTime fin, List<Reserva> reserva) {

		Boolean val = true;
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
	public List<Reserva> reporteFechas(LocalDateTime inicio, LocalDateTime fin, List<Reserva> reserva) {
		List<Reserva> lista = new ArrayList<>();
		for (Reserva r : reserva) {
			if (r.getFechafinal() != null && r.getFechaInicio() != null) {

				if ((r.getFechaInicio().isBefore(inicio) && r.getFechaInicio().isBefore(fin))
						&& (r.getFechafinal().isBefore(inicio) && r.getFechafinal().isBefore(fin))
						|| (r.getFechaInicio().isAfter(inicio) && r.getFechaInicio().isAfter(fin))
								&& (r.getFechafinal().isAfter(inicio) && r.getFechafinal().isAfter(fin))) {
				} else {
					lista.add(r);
				}
			}
		}

		return lista;
	}

	@Override
	public void Retirar(String tipoRetiro) {
		// TODO Auto-generated method stub

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

	@Override
	public Reserva buscarPorfecha(LocalDateTime fechaInicio) {
		// TODO Auto-generated method stub
		return this.iReservaRepo.buscarPorfecha(fechaInicio);
	}

	@Override
	public List<Reserva> buscarReporte(String nombre) {
		// TODO Auto-generated method stub
		return this.iReservaRepo.buscarReporte(nombre);
	}

	@Override
	public List<Reporte> segundoReporte(List<Cliente> clientes) {

		List<Reporte> reporte = new ArrayList<>();

		for (int i = 0; i < clientes.size(); i++) {

			List<Reserva> list = this.buscarReporte(clientes.get(i).getNombre());

			for (Reserva res : list) {
				Reporte reportes = new Reporte();
				reportes.setCedula(clientes.get(i).getCedula());
				reportes.setNombre(clientes.get(i).getNombre());
				reportes.setValorIva(res.getValorIva());
				reportes.setValoTotal(res.getValorTotalPagar());
				reporte.add(reportes);
			}
		}
		return reporte;
	}

	@Override
	public List<ReporteVehi> tercerReporte(List<Vehiculo> vehiculos) {

		BigDecimal valorIvaF = new BigDecimal(0);
		BigDecimal valorTotalF = new BigDecimal(0);
		List<ReporteVehi> reporte = new ArrayList<>();

		for (Vehiculo vehiculo : vehiculos) {

			ReporteVehi reportes = new ReporteVehi();

			List<Reserva> list2 = this.buscarReporteVehi(vehiculo.getPlaca());

			for (Reserva res : list2) {
				valorIvaF = valorIvaF.add(res.getValorIva());
				valorTotalF = valorTotalF.add(res.getValorTotalPagar());
			}
			reportes.setPlaca(vehiculo.getPlaca());
			reportes.setModelo(vehiculo.getModelo());
			reportes.setValorIva(valorIvaF);
			reportes.setValoTotal(valorTotalF);
			reporte.add(reportes);
			valorIvaF = new BigDecimal(0);
			valorTotalF = new BigDecimal(0);
		}
		return reporte;
	}

	@Override
	public List<Reserva> buscarReporteVehi(String placa) {
		// TODO Auto-generated method stub
		return this.iReservaRepo.buscarReporteVehi(placa);
	}

}
