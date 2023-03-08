package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.Reserva;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.modelo.dto.Reporte;
import com.example.demo.modelo.dto.ReporteVehi;
import com.example.demo.modelo.dto.ReservaDTO;
import com.example.demo.modelo.dto.VehiculoDTO;
import com.example.demo.modelo.dto.VehiculoMyMDTO;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
	@Autowired
	private IReservaService iReservaService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IClienteService iClienteService;

	// Funcionando
	@GetMapping("/funciones")
	public String paginaNuevasFunciones() {
		return "vistaRepoFunciones";
	}

	@GetMapping("/reporteReservas")
	public String paginaReporteReserva(Reserva reserva) {
		return "vistaReporteReservas";
	}

	@GetMapping("/buscarReservas")
	public String paginaBuscarReserva(Reserva reserva, Model model) {

		List<Vehiculo> listVehiculo = new ArrayList<>();
		List<Cliente> listCliente = new ArrayList<>();

		List<Reserva> lista = this.iReservaService.reporteFechas(reserva.getFechaInicio(), reserva.getFechafinal(),
				this.iReservaService.buscarReserva());
		String pagina;
		if (lista.isEmpty()) {
			pagina = "guardado";
		} else {
			for (Reserva reserva2 : lista) {
				listVehiculo.add(this.iVehiculoService.buscarPlaca(reserva2.getVehiculo().getPlaca()));
				listCliente.add(this.iClienteService.buscarCedulaUna(reserva2.getCliente().getCedula()));
			}
			pagina = "vistaReporte";
			model.addAttribute("cliente", listCliente);
			model.addAttribute("vehiculo", listVehiculo);
			model.addAttribute("reserva", lista);
		}
		return pagina;
	}

	// 3b
	@GetMapping("/reporteClientesVip")
	public String paginareporteClientesVip(Model model) {
		List<Reporte> listreporte = this.iReservaService.segundoReporte(this.iClienteService.buscarTodos());
		model.addAttribute("reporte", listreporte.stream()
				.sorted(Comparator.comparing(Reporte::getValoTotal).reversed()).collect(Collectors.toList()));
		return "vistaReporteCliente";
	}

	// 3b

	@GetMapping("/reporteAutomovilVip")
	public String paginaReporteAutomovilVip(Reserva reserva) {
		return "vistaReporteAutomovil";
	}

	@GetMapping("/buscarAutomovilVip")
	public String paginaBuscarAutomovilVip(Reserva reserva, Model model) {

		String fecha = "%" + reserva.getEstado() + "%";

		List<Vehiculo> vh = this.iVehiculoService.buscarVehiculosVip(fecha);
		System.out.println(vh);

		List<ReporteVehi> lista2 = this.iReservaService.tercerReporte(this.iVehiculoService.buscarVehiculosVip(fecha));

		String pagina;
		if (lista2.isEmpty()) {
			pagina = "guardado";
		} else {

			model.addAttribute("reporte", lista2.stream()
					.sorted(Comparator.comparing(ReporteVehi::getValoTotal).reversed()).collect(Collectors.toList()));
			pagina = "vistatercero";
		}
		return pagina;
	}

}
