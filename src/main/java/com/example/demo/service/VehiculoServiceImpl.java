package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Vehiculo;
import com.example.demo.repository.IVehiculoRepo;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepo iVehiculoRepo;

	@Override
	public void crear(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.iVehiculoRepo.insertar(vehiculo);
	}

	@Override
	public Vehiculo buscarId(Integer id) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepo.buscarId(id);
	}

	@Override
	public List<Vehiculo> buscarTodos() {
		// TODO Auto-generated method stub
		return this.iVehiculoRepo.buscarTodos();
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.iVehiculoRepo.actualizar(vehiculo);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.iVehiculoRepo.borrar(id);
	}

	@Override
	public List<Vehiculo> buscarDispo(String disponibilidad) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepo.buscarDispo(disponibilidad);
	}

	@Override
	public Vehiculo buscarPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepo.buscarPlaca(placa);
	}

}
