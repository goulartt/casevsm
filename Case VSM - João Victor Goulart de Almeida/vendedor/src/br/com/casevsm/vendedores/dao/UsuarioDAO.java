package br.com.casevsm.vendedores.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.casevsm.vendedores.modelo.Usuario;
import br.com.casevsm.vendedores.modelo.Vendedor;

public class UsuarioDAO {
	

	EntityManager em;
	public boolean verificaLogin(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();

		TypedQuery<Usuario> result = em
				.createQuery("select u from Usuario u where u.cpf = :pCpf and u.senha = :pSenha", Usuario.class);
		result.setParameter("pCpf", usuario.getCpf());
		result.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario resultado = result.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		em.close();
		return true;
	}
	public Usuario retornaUsuario(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		TypedQuery<Usuario> result = em
				.createQuery("select u from Usuario u where u.cpf = :pCpf and u.senha = :pSenha", Usuario.class);
		result.setParameter("pCpf", usuario.getCpf());
		result.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario resultado = result.getSingleResult();
			em.close();
			return resultado;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public void salvaUsuario( String cpf, String senha, String nome) {
	
			EntityManager em = new JPAUtil().getEntityManager();
			em.getTransaction().begin();
			Usuario u = new Usuario(cpf, senha, nome);
		em.persist(u);
		em.getTransaction().commit();
	}
	
	public boolean existeUsuario(String cpf) {
		EntityManager em = new JPAUtil().getEntityManager();

		TypedQuery<Usuario> result = em
				.createQuery("select u from Usuario u where u.cpf = :pCpf", Usuario.class);
		result.setParameter("pCpf", cpf);
		try {
			Usuario resultado = result.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		em.close();
		return true;
	}
	public void atualiza(Vendedor t, String cpf, String senha, String nome) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		TypedQuery<Vendedor> result = em
				.createQuery("select v from Vendedor v where v.id = :pId", Vendedor.class);
		result.setParameter("pId", t.getId());
		Vendedor v = result.getSingleResult();
		TypedQuery<Usuario> result2 = em
				.createQuery("select u from Usuario u where u.cpf = :pCpf and u.senha = :pSenha", Usuario.class);
		result2.setParameter("pCpf", v.getCpf());
		result2.setParameter("pSenha", v.getSenha());
		Usuario u = result2.getSingleResult();
		u.setCpf(cpf);
		u.setSenha(senha);
		u.setNome(nome);
		em.merge(u);
		em.getTransaction().commit();
	}

	public void remove(Vendedor l) {
		// TODO Auto-generated method stub
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		TypedQuery<Vendedor> result = em
				.createQuery("select v from Vendedor v where v.id = :pId", Vendedor.class);
		result.setParameter("pId", l.getId());
		Vendedor v = result.getSingleResult();
		TypedQuery<Usuario> result2 = em
				.createQuery("select u from Usuario u where u.cpf = :pCpf and u.senha = :pSenha", Usuario.class);
		result2.setParameter("pCpf", v.getCpf());
		result2.setParameter("pSenha", v.getSenha());
		Usuario u = result2.getSingleResult();
		em.remove(u);
		em.getTransaction().commit();
	}
	public void criarUsuario(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	

}
