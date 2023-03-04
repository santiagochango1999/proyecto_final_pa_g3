package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IVehiculoService;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
	
	@Autowired
	private IClienteService iClienteService;
	
	@Autowired
	private IVehiculoService iVehiculoService;
	//Funcionando
	@GetMapping("/funciones")
	public String paginaNuevaPersona() {
		return "vistaFuncionesEmpleado";
	}
	
	@GetMapping("/nuevoVehiculo")
	public String paginaNuevoVehiculo(Vehiculo vehiculo) {
		return "vistaNuevoVehiculo";
	}
	@PostMapping("/insertarVehiculo")
	public String pruebaInsertarVehiculo(Vehiculo vehiculo) {
		this.iVehiculoService.crear(vehiculo);
		return "vistaPrueba";
		
	}
	//Probando
	@GetMapping("/nuevoCliente")
	public String paginaNuevoCliente(Cliente cliente) {
		return "vistaNuevoCliente";
	}
	
	@PostMapping("/insertar")
	public String pruebaInsertar(Cliente cliente) {
		cliente.setRegistro("E");
		cliente.setVip("V");
		this.iClienteService.crear(cliente);
		return "vistaPrueba";
	}
	
	
	
	
	
	
	
	
	
	
	

	@GetMapping("/buscar")
	public String regresar(Cliente cliente) {
		
		return "vistaPrueba";
	}
	@GetMapping("/actualizar{id}")
	public String actualizar(@PathVariable("id")Integer id ,Cliente cliente) {
		
		return "vistaPrueba";
	}
}
