package com.example.demo.service;

import java.util.List;

import com.example.demo.modelo.Cliente;

public interface IClienteService {

	public void crear(Cliente cliente);

	public Cliente buscarId(Integer id);

	public void actualizar(Cliente cliente);

	public List<Cliente> buscarTodos();

	public List<Cliente> buscarApellido(String apellido);

	public Cliente buscarCedula(String cedula);

	public void borrar(Integer id);

}
