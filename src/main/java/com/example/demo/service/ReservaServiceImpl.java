package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.modelo.Reserva;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService VehiculoService;

	@Override
	public void Reserva(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin) {
		// TODO Auto-generated method stub
		Cliente cliente = this.clienteService.buscarCedula(cedula);
		Vehiculo vehiculo = this.VehiculoService.buscarPlaca(placa);
		Reserva reserva=new Reserva(); 
		List<Reserva> lista=vehiculo.getReserva();
		
		Boolean val=null;
		for(Reserva listare : lista) {
			val=validacion(inicio, fin, listare);
		}
		
		List<Reserva> listaInicioOrden =lista.stream().sorted(Comparator.comparing(Reserva::getFechaInicio)).collect(Collectors.toList());
		List<Reserva> listaFinOrden =lista.stream().sorted(Comparator.comparing(Reserva::getFechafinal)).collect(Collectors.toList());

//		if(){
//			
//		}
//		
//		for() {
//			
//		}
		
		//VALIDACION DE FECHAS
		if(val) {
			//PRESENTAR VISTA Y REALIZAR BOTON 
			//ingresar tarjeta credito
			System.out.println("DISPONIBLE");
			vehiculo.setDisponibilidad("ND");
			this.VehiculoService.actualizar(vehiculo);
		}else {
			
		}
		
	}
	
	public Boolean validacion(LocalDateTime inicio, LocalDateTime fin,Reserva reserva) {

		if ((reserva.getFechaInicio().isBefore(inicio) && reserva.getFechaInicio().isBefore(fin))&&(reserva.getFechafinal().isBefore(inicio) && reserva.getFechafinal().isBefore(fin))||(reserva.getFechaInicio().isAfter(inicio) && reserva.getFechaInicio().isAfter(fin))&&(reserva.getFechafinal().isAfter(inicio) && reserva.getFechafinal().isAfter(fin))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void Retirar(String tipoRetiro) {
		// TODO Auto-generated method stub

	}

}
