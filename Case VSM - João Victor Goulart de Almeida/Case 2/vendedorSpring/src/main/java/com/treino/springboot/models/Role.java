package com.treino.springboot.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	@Id
	private String nome;
	
	public Role() {
		
	}
	public Role(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nome;
	}
	
}
