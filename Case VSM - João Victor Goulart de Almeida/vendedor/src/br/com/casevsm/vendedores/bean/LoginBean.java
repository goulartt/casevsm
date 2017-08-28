package br.com.casevsm.vendedores.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.casevsm.vendedores.dao.UsuarioDAO;
import br.com.casevsm.vendedores.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String logar() {
		boolean existe = new UsuarioDAO().verificaLogin(this.usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		if(existe) {
			Usuario login = new UsuarioDAO().retornaUsuario(this.usuario);
			context.getExternalContext().getSessionMap().put("usuarioLogado", login);
			return "vendedor?faces-redirect=true";
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Login ou senha incorretos."));
		return "login?faces-redirect=true";
	}
	
	public String cadastrar() {
		boolean existe = new UsuarioDAO().existeUsuario(this.usuario.getCpf());
		FacesContext context = FacesContext.getCurrentInstance();
		if(!existe) {
			new UsuarioDAO().criarUsuario(this.usuario);
			FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Usuario criado com sucesso!"));
			return "login?faces-redirect=true";
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Ja existe um login com essas credenciais"));
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
		
	}
}
