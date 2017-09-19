package br.com.casevsm.vendedores.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Log
@Interceptor
public class TempoDeExecucaoInterceptor implements Serializable {
	@Inject
	EntityManager manager;
	@AroundInvoke
	public Object tempoGasto(InvocationContext context) throws Exception {
		long antes = System.currentTimeMillis();

		Object resultado = context.proceed();

		long depois = System.currentTimeMillis();
		
		System.out.println("O tempo para executar o metodo " + context.getMethod().getName() + " da classe " + context.getTarget().getClass().getSimpleName() + " levou: " + (depois - antes)+"ms");
		return resultado;
	}
}
