package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Empleado;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

	@GetMapping("/nuevaPersona")
	public String paginaNuevaPersona(Empleado empleado) {
		return "vistaPrueba";
	}
}
