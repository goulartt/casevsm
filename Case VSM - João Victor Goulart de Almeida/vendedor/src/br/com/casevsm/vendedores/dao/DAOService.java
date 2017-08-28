package br.com.casevsm.vendedores.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.com.casevsm.vendedores.modelo.Vendedor;

public class DAOService implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6481542664225132079L;
	private Vendedor classe;
	

	public Vendedor buscaPorIdService(Integer id) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Vendedor instancia = em.createQuery("select v from Vendedor v where v.id = :pId", Vendedor.class).setParameter("pId", id).getSingleResult();
		instancia.setConsumido("S");
		em.merge(instancia);
		em.getTransaction().commit();
		return instancia;
	}

	

}
