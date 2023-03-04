package com.example.demo.repository;

import java.util.List;

import com.example.demo.modelo.Vehiculo;
import com.example.demo.modelo.dto.VehiculoDTO;

public interface IVehiculoRepo {

	public void insertar(Vehiculo vehiculo);

	public Vehiculo buscarId(Integer id);

	public List<Vehiculo> buscarTodos();
	
	public Vehiculo buscarPlaca(String placa);

	public void actualizar(Vehiculo vehiculo);

	public void borrar(Integer id);
	
	public List<Vehiculo> buscarDispo(String disponibilidad);
	
	public List<VehiculoDTO> buscarVehiculoDisponible(String marca , String modelo);
	
}
