package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Reserva;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
		TypedQuery<Reserva> query = this.entityManager.createQuery("select r from Reserva r where r.numero=: dato",
				Reserva.class);
		query.setParameter("dato", numero);
		return query.getSingleResult();
	}

	@Override
	public List<Reserva> buscarReserva() {
		TypedQuery<Reserva> query = this.entityManager.createQuery("select r from Reserva r", Reserva.class);
		List<Reserva> listaTotal = query.getResultList();
		return listaTotal;
	}

	@Override
	public void eliminar(Integer numero) {
		// TODO Auto-generated method stub
		Reserva reserva = this.buscarNumero(numero);
		this.entityManager.remove(reserva);
	}

	@Override
	public Reserva buscarPorfecha(LocalDateTime fechaInicio) {
		TypedQuery<Reserva> query = this.entityManager.createQuery("select r from Reserva r where r.fechaInicio= :dato",
				Reserva.class);
		query.setParameter("dato", fechaInicio);
		return query.getSingleResult();
	}

	@Override
	public List<Reserva> buscarReporte(String nombre) {
		TypedQuery<Reserva> query = this.entityManager
				.createQuery("select r from Reserva r join fetch r.cliente cl where cl.nombre= :dato", Reserva.class);
		query.setParameter("dato", nombre);
		return query.getResultList();
	}

	@Override
	public List<Reserva> buscarReporteVehi(String placa) {
		TypedQuery<Reserva> query = this.entityManager
				.createQuery("select r from Reserva r join fetch r.vehiculo vh where vh.placa= :dato", Reserva.class);
		query.setParameter("dato", placa);
		return query.getResultList();
	}

}
