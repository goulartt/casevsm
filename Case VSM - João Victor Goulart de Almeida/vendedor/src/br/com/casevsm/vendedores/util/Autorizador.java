package br.com.casevsm.vendedores.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.casevsm.vendedores.modelo.Usuario;

public class Autorizador implements PhaseListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		FacesContext context = event.getFacesContext();
		String pagina = context.getViewRoot().getViewId();
		if ("/login.xhtml".equals(pagina)) {
			return;
		}
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (usuarioLogado != null) {
			return;
		}

		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login?faces-redirect=true");

		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
