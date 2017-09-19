package com.treino.springboot.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.treino.springboot.models.Usuario;
import com.treino.springboot.models.Vendedor;

@Repository
public class UsuarioDao implements UserDetailsService{
	
	@PersistenceContext private EntityManager em;
	
	
	@Transactional
	@Override
	public Usuario loadUserByUsername(String cpf) {
		try {
			return em.createQuery("select v from Usuario v where :pCpf = v.cpf", Usuario.class).setParameter("pCpf", cpf).getSingleResult();
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public void delete(Integer cpf) {
		try {
			Usuario user = em.createQuery("select v from Usuario v where :pId = v.idVendedor", Usuario.class).setParameter("pId", cpf).getSingleResult();
			em.remove(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Transactional
	public void atualiza(Usuario user) {
		try {
			em.merge(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Transactional
	public void save(Usuario usuario) {
		try {
			em.persist(usuario);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public Usuario find(Integer id) {
		return  em.createQuery("select v from Usuario v where :pId = v.idVendedor", Usuario.class).setParameter("pId", id).getSingleResult();
	}
	

	
}
