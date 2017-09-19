package com.treino.springboot.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.treino.springboot.models.Role;
import com.treino.springboot.models.Usuario;
import com.treino.springboot.models.Vendedor;

@Repository
public class VendedorDao {
	
	@PersistenceContext EntityManager em;
	
	
	@Transactional
	public void saveVendedor(Vendedor v) {
		try {
			em.persist(v);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Transactional
	public List<Vendedor> findAll() {
		try {
			return em.createQuery("select v from Vendedor v", Vendedor.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
		
		
	
	@Transactional
	public Vendedor find(Integer id) {
		try {
			return em.createQuery("select v from Vendedor v where :pId = v.id", Vendedor.class).setParameter("pId", id).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
	@Transactional
	public void delete(Integer v) {
		try {
			em.remove(find(v));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Transactional
	public void atualiza(Vendedor vendedor) {
		try {
			em.merge(vendedor);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Transactional
	public Vendedor findByCpf(String cpf) {
		try {
			return em.createQuery("select v from Vendedor v where :pCpf = v.cpf", Vendedor.class).setParameter("pCpf", cpf).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
	@Transactional
	public void consome(Integer id) {
		Vendedor v = find(id);
		v.setConsumido("S");
		em.merge(v);
	}
	public Vendedor findNaoConsumido(Integer id) {
		try {
			return em.createQuery("select v from Vendedor v where :pCpf = v.cpf and v.consumido = 'N'", Vendedor.class).setParameter("pCpf", find(id).getCpf()).getSingleResult();
		}catch(Exception e) {
			Vendedor v = new Vendedor();
			v.setConsumido("Esse vendedor ja foi consumido ou não existe");
			return v;
			
		}
	}
}
