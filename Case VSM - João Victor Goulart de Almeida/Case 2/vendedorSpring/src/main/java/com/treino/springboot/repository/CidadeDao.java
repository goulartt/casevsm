package com.treino.springboot.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.treino.springboot.models.Cidade;
import com.treino.springboot.models.Vendedor;

@Repository
public class CidadeDao {

	@PersistenceContext EntityManager em;
	
	@Transactional
	public void saveCidade(Cidade c) {
		try {
			em.persist(c);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Transactional
	public List<Cidade> findAll() {
		try {
			return em.createQuery("select c from Cidade c", Cidade.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Transactional
	public Cidade findById(Integer id) {
		try {
			return em.createQuery("select c from Cidade c where :pId = c.id", Cidade.class).setParameter("pId", id).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
