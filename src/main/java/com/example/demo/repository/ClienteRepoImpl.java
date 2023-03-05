package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class ClienteRepoImpl implements IClienteRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cliente);
	}

	@Override
	public Cliente buscarId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.merge(cliente);
	}

	@Override
	public List<Cliente> buscarTodos() {

		Query query = this.entityManager.createNativeQuery("select * from cliente");
		List<Cliente> listaTotal = query.getResultList();

		return listaTotal;
	}

	@Override
	public List<Cliente> buscarApellido(String apellido) {

		Query query = this.entityManager.createQuery("select c from Cliente c where c.apellido= :datoApellido");
		query.setParameter("datoApellido", apellido);
		return query.getResultList();
	}

	@Override
	public void eliminar(Integer id) {

		Cliente cliente = this.buscarId(id);
		this.entityManager.remove(cliente);

	}

	@Override
	public List<Cliente> buscarCedula(String cedula) {

		Query query = this.entityManager.createQuery("select c from Cliente c where c.cedula= :datoCedula");
		query.setParameter("datoCedula", cedula);
		return  query.getResultList();
	}

}
