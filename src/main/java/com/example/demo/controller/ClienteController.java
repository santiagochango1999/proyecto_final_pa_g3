package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping("/nuevaPersona")
	public String paginaNuevaPersona(Cliente cliente) {
		return "vistaPrueba";
	}
}
