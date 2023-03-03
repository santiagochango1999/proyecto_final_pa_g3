package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Reserva;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class ReservaRepoImpl implements IReservaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void ingresar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.persist(reserva);
	}

	@Override
	public Reserva buscarNumero(Integer numero) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Reserva.class, numero);
	}

	@Override
	public List<Reserva> buscarReserva() {
		Query query = this.entityManager.createNativeQuery("select * from reserva");
		List<Reserva> listaTotal = query.getResultList();
		return listaTotal;
	}

	@Override
	public void eliminar(Integer numero) {
		// TODO Auto-generated method stub
		Reserva reserva = this.buscarNumero(numero);
		this.entityManager.remove(reserva);
	}

}
