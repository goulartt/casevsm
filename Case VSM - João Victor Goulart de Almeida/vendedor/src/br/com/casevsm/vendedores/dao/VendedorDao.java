package br.com.casevsm.vendedores.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.casevsm.vendedores.modelo.Vendedor;
import br.com.casevsm.vendedores.tx.Log;

public class VendedorDao implements Serializable {
	DAO<Vendedor> dao;
	@Inject
	EntityManager em;
	VendedorDao(){
		
	}
	@PostConstruct
	void init() {
		this.dao = new DAO<Vendedor>(em, Vendedor.class);
	}
	@Log
	public void adiciona(Vendedor t) {
		dao.adiciona(t);
	}
	@Log
	public void remove(Vendedor t) {
		dao.remove(t);
	}
	@Log
	public void atualiza(Vendedor t) {
		dao.atualiza(t);
	}

	public List<Vendedor> listaTodos() {
		return dao.listaTodos();
	}
	@Log
	public Vendedor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	@Log
	public int contaTodos() {
		return dao.contaTodos();
	}

	
}
