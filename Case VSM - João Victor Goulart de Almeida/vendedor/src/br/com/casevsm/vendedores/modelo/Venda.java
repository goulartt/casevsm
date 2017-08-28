package br.com.casevsm.vendedores.modelo;

import java.io.Serializable;

public class Venda implements Serializable{
	private int quantidade;
	private Vendedor vendas;
	
	public Venda(int quantidade, Vendedor venda) {
		this.quantidade = quantidade;
		this.vendas = venda;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Vendedor getVendas() {
		return vendas;
	}
	public void setVendas(Vendedor vendas) {
		this.vendas = vendas;
	}
	
	
}
