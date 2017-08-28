package br.com.casevsm.vendedores.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.casevsm.vendedores.modelo.Cidade;
import br.com.casevsm.vendedores.tx.Log;

public class CidadeDao implements Serializable{

	DAO<Cidade> dao;
	@Inject
	EntityManager em;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Cidade>(em, Cidade.class);
	}
	@Log
	public void adiciona(Cidade t) {
		dao.adiciona(t);

	}
	@Log
	public void remove(Cidade t) {
		dao.remove(t);
	}
	@Log
	public void atualiza(Cidade t) {
		dao.atualiza(t);
	}
	
	public List<Cidade> listaTodos() {
		return dao.listaTodos();
	}
	@Log
	public Cidade buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	@Log
	public int contaTodos() {
		return dao.contaTodos();
	}


}
