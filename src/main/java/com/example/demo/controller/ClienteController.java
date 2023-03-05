package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.Reserva;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.modelo.dto.VehiculoDTO;
import com.example.demo.modelo.dto.VehiculoMyMDTO;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	private String datocedula;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IReservaService iReservaService;

	@GetMapping("/funciones")
	public String paginaVistaCliente() {
		return "vistaCliCliente";
	}

	@GetMapping("/buscarVehiculoDisponible")
	public String paginaVistaBuscarVehiculo(Model model, Vehiculo vehiculo) {
		List<VehiculoMyMDTO> lista = this.iVehiculoService.buscarTodos();
		model.addAttribute("vehiculomymdto", lista);
		return "vistaCliListaMYMY";
	}

	@GetMapping("/registrarCliente")
	public String paginaVistaRegistrar(Cliente cliente) {
		return "vistaCliRegistrarCliente";
	}

	@GetMapping("/actualizarCliente")
	public String paginaVistaActualizar(Cliente cliente) {
		return "vistaCliActualizarCliente";
	}

	@PostMapping("/insertar")
	public String insertarCliente(Cliente cliente) {
		cliente.setRegistro("C");
		this.clienteService.crear(cliente);
		return "guardado";
	}

	@GetMapping("/buscarmm")
	public String paginaVistaBuscarVehiculoMM(Vehiculo vehiculo, Model model) {
		List<VehiculoDTO> lista = this.iVehiculoService.buscarVehiculoDisponible(vehiculo.getMarca(),
				vehiculo.getModelo());
		model.addAttribute("vehiculodto", lista);
		return "vistaCliListaMM";
	}

	@GetMapping("/buscarcedula")
	public String paginaVistaBuscarClienteCedula(Cliente cliente, Model model) {

		List<Cliente> cliente1 = this.clienteService.buscarCedula(cliente.getCedula());
		String pagina;
		datocedula = cliente.getCedula();
		model.addAttribute("cedulaCliente", cliente1);
		if (cliente1.isEmpty()) {
			pagina = "guardado";
		} else {
			pagina = "vistaCliActualizarDatos";
		}

		return pagina;
	}

	@PutMapping("/actualizar")
	public String actualizarPorCedula(Cliente cliente) {
		Cliente cliente2 = this.clienteService.buscarCedula(datocedula).get(0);
		cliente.setId(cliente2.getId());
		cliente.setCedula(datocedula);
		cliente.setRegistro(cliente2.getRegistro());
		cliente.setReserva(cliente2.getReserva());
		System.out.println(cliente);
		this.clienteService.actualizar(cliente);
		return "redirect:/clientes/funciones";
	}

	// RESERVA-----------------------------
	@GetMapping("/reservaVehiculo")
	public String paginaVistaReservaVehiculo(Vehiculo vehiculo, Cliente cliente, Reserva reserva) {
		return "vistaCliReservaCliente";
	}

	@GetMapping("/buscarReserva")
	public String paginaVistaRetornoDisponibilidad(Vehiculo vehiculo, Cliente cliente, Reserva reserva) {
		String pagina;
		Boolean val;

		val = this.iReservaService.Reservar(vehiculo.getPlaca(), cliente.getCedula(), reserva.getFechaInicio(),
				reserva.getFechafinal());
		if (val) {
			pagina = "vistaCliFechaSi";
		} else {
			pagina = "vistaCliFechaNo";
		}
		return pagina;
	}

}
