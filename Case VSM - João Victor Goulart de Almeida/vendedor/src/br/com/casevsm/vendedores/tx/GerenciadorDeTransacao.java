package br.com.casevsm.vendedores.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.jboss.weld.interceptor.proxy.InterceptionContext;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
	
	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object intercepta(InvocationContext context) throws Exception {
		System.out.println("Entrou aqui");
		manager.getTransaction().begin();
		Object resultado = context.proceed();
		manager.getTransaction().commit();
		
		return resultado;
	}
}
