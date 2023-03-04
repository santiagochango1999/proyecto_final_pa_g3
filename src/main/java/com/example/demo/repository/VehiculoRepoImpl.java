package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.Vehiculo;
import com.example.demo.modelo.dto.VehiculoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class VehiculoRepoImpl implements IVehiculoRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.persist(vehiculo);
	}

	@Override
	public Vehiculo buscarId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	public List<Vehiculo> buscarTodos() {

		Query query = this.entityManager.createNativeQuery("select * from vehiculo");
		List<Vehiculo> listaTotal = query.getResultList();

		return listaTotal;
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo = this.buscarId(id);
		this.entityManager.remove(vehiculo);
	}

	@Override
	public List<Vehiculo> buscarDispo(String disponibilidad) {

		TypedQuery<Vehiculo> typedQuery = this.entityManager
				.createQuery("select v from vehiculo v where v.disponibilidad= :datosDisponibilidad ", Vehiculo.class);
		typedQuery.setParameter("datoDisponibilidad", disponibilidad);
		List<Vehiculo> lista = typedQuery.getResultList();
		return lista;
	}

	@Override
	public Vehiculo buscarPlaca(String placa) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> typedQuery = this.entityManager
				.createQuery("select v from vehiculo v where v.placa= :datosPlaca ", Vehiculo.class);
		typedQuery.setParameter("datoPlaca", placa);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<VehiculoDTO> buscarVehiculoDisponible(String marca, String modelo) {
		// TODO Auto-generated method stub
		TypedQuery<VehiculoDTO> query = this.entityManager.createQuery(
				"SELECT NEW com.example.demo.modelo.dto.VehiculoDTO(v.placa , v.modelo , v.marca , v.añoFabricion , v.disponibilidad , v.valorDia)"
						+ "FROM Vehiculo v WHERE v.marca =:datoMarca AND v.modelo =:datoModelo",
				VehiculoDTO.class);
		query.setParameter("datoMarca", marca);
		query.setParameter("datoModelo", modelo);
		return query.getResultList();
	}

}
