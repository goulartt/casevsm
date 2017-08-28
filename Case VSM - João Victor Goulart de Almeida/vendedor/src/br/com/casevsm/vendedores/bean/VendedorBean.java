package br.com.casevsm.vendedores.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.casevsm.vendedores.dao.CidadeDao;
import br.com.casevsm.vendedores.dao.UsuarioDAO;
import br.com.casevsm.vendedores.dao.VendedorDao;
import br.com.casevsm.vendedores.model.Cep;
import br.com.casevsm.vendedores.modelo.Cidade;
import br.com.casevsm.vendedores.modelo.Vendedor;
import br.com.casevsm.vendedores.tx.Transacional;

@Named
@ViewScoped
public class VendedorBean implements Serializable {

	private static final long serialversionUID = 1L;

	private Vendedor vendedor = new Vendedor();
	private Integer vendedorId;
	private Integer cidadeId;
	
	private List<Vendedor> vendedores;
	@Inject
	private CidadeDao CidadeDao;
	@Inject
	private VendedorDao dao;
	
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public void carregaPelaId() {
		this.vendedor = dao.buscaPorId(this.vendedorId);
	}
	
	

	public Integer getvendedorId() {
		return vendedorId;
	}

	public void setVendedorId(Integer vendedorId) {
		this.vendedorId = vendedorId;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}


	public Vendedor getvendedor() {
		return vendedor;
	}

	public List<Cidade> getCidades() {
		return CidadeDao.listaTodos();
	}

	public List<Vendedor> getvendedores() {
	

		if (this.vendedores == null) {
			this.vendedores = dao.listaTodos();
		}

		return vendedores;

	}

	
	@Transacional 
	public void gravar() {
		System.out.println("Gravando vendedor " + this.vendedor.getNome());
			Cidade cidade = CidadeDao.buscaPorId(cidadeId);
			this.vendedor.setCidade(cidade);
			if (this.vendedor.getId() == null) {
				if(!usuarioDAO.existeUsuario(vendedor.getCpf())) {
					vendedor.setConsumido("N");
					dao.adiciona(this.vendedor);
					usuarioDAO.salvaUsuario(vendedor.getCpf(), vendedor.getSenha(), vendedor.getNome());
					this.vendedores = dao.listaTodos();
					
					FacesContext.getCurrentInstance().addMessage("Vendedor",
							new FacesMessage("Vendedor "+ vendedor.getNome()+" adicionado com sucesso"));
					this.vendedor = new Vendedor();
				}else {
					FacesContext.getCurrentInstance().addMessage("Cpf",
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ja existe um vendedor com esse CPF", "CPF Invalido"));
				}
				
			} else {
				vendedor.setConsumido("N");
				usuarioDAO.atualiza(this.vendedor, vendedor.getCpf(), vendedor.getSenha(), vendedor.getNome());
				dao.atualiza(this.vendedor);
				
				FacesContext.getCurrentInstance().addMessage("Vendedor",
						new FacesMessage("Vendedor "+ vendedor.getNome()+" atualizado com sucesso"));
				this.vendedor = new Vendedor();
			}
			
		
		}
	
	@Transacional
	public void remover(Vendedor l) {
		String nome = l.getNome();
		usuarioDAO.remove(l);
		dao.remove(l);
		this.vendedores = this.dao.listaTodos();
		FacesContext.getCurrentInstance().addMessage("Cidade",
				new FacesMessage("vendedor "+nome+" removido com sucesso"));
		
	}
	
/*	public void remover(Vendedor l)
	{
		Vendedor teste = dao.buscaPorId(1);
		System.out.println(teste.toXML());
	}*/
	public void carregar(Vendedor l) {
	
		this.vendedor = dao.buscaPorId(l.getId());
		
	}
	public void buscaCep() {
		String cep = vendedor.getCep().replaceAll("-", "");
		Client c = Client.create();
		WebResource wr = c.resource("https://viacep.com.br/ws/"+cep+"/json/");
	    Gson gson = new Gson();
	    Cep json = wr.get(Cep.class);
	    try {
	    	 if(json.getLogradouro().isEmpty());
			   else {
			    	this.vendedor.setBairro(json.getBairro());
			    	this.vendedor.setRua(json.getLogradouro());
			    }
	    }catch(Exception e) {
	    	FacesContext.getCurrentInstance().addMessage("Cep",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cep inválido", "Cep inválido"));
			
	    }
		   
		    	
	}



	public Integer getCidadeId() {
		return cidadeId;
	}



	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	

}
