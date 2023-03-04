package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Cliente;
import com.example.demo.service.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/funciones")
	public String paginaVistaCliente() {
		return "vistaCliente";
	}
	
	@GetMapping("/buscarVehiculoDisponible")
	public String paginaVistaBuscarVehiculo() {
		return "vistaBuscarVehiculoDisponible";
	}
	
	@GetMapping("/reservaVehiculo")
	public String paginaVistaReservaVehiculo() {
		return "vistaReservaCliente";
	}
	
	@GetMapping("/registrarCliente")
	public String paginaVistaRegistrar(Cliente cliente) {
		return "vistaRegistrarCliente";
	}
	
	@GetMapping("/actualizarCliente")
	public String paginaVistaActualizar() {
		return "vistaActualizarCliente";
	}
	
	@PostMapping("/insertar")
	public String insertarCliente(Cliente cliente) {
		cliente.setRegistro("C");
		this.clienteService.crear(cliente);
		return "guardado";
	}
	

}
