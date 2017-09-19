package com.treino.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.treino.springboot.models.Vendedor;
import com.treino.springboot.repository.VendedorDao;

@RestController
public class Rest {

	@Autowired VendedorDao vendedor;
	
	  @RequestMapping(value = "/vendedores/json", method = RequestMethod.GET)
	  public ResponseEntity<List<Vendedor>> listar() {
	    return new ResponseEntity<List<Vendedor>>(vendedor.findAll(), HttpStatus.OK);
	  }
	  @RequestMapping(value = "/vendedores/{id}/json", method = RequestMethod.GET)
	  public ResponseEntity<Vendedor> listar(@PathVariable("id") Integer id) {
		 Vendedor v = vendedor.findNaoConsumido(id);
		  vendedor.consome(id);
		  return new ResponseEntity<Vendedor>(v, HttpStatus.OK);
	  }
	
	 
}
