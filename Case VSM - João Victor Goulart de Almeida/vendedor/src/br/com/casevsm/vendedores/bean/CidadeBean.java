package br.com.casevsm.vendedores.bean;



import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.RollbackException;

import br.com.casevsm.vendedores.dao.CidadeDao;
import br.com.casevsm.vendedores.modelo.Cidade;
import br.com.casevsm.vendedores.tx.Transacional;

@Named
@ViewScoped
public class CidadeBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8968996148377509723L;
	private Cidade Cidade = new Cidade();
	private List<Cidade> cidades;
	public Cidade getCidade() {
		return Cidade;
	}
	
	
	@Inject
	private CidadeDao dao;
	
	@PostConstruct
	public void init() {
		this.Cidade = new Cidade();
	}
	@Transacional
	public void gravar() {
		System.out.println("Gravando Cidade " + this.Cidade.getNome());
	    if(Cidade.getId() == null) {
		    this.dao.adiciona(this.Cidade);
	    }else {
	    	this.dao.atualiza(this.Cidade);
	    }
		
	    cidades = dao.listaTodos();
		this.Cidade = new Cidade();
	}

	
	public void editaNome(Cidade a) {
		this.Cidade = a;
	}
	
	@Transacional
	public void removeCidade(Cidade a) {
		
			String nome = a.getNome();
			this.dao.remove(a);
			cidades = dao.listaTodos();
			FacesContext.getCurrentInstance().addMessage("Cidade",
					new FacesMessage("Cidade "+nome+" removido com sucesso"));
		
		
	}

	public List<Cidade> getCidades() {
		if (this.cidades == null) {
			this.cidades = dao.listaTodos();
		}

		return cidades;
	}
	
	
}
