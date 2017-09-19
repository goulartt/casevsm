package com.treino.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.treino.springboot.repository.VendedorDao;

@Controller
public class LoginController {
	
	@Autowired VendedorDao dao;
		
	 @GetMapping("/login")
	  public String login() {
	
	    return "login";
	  }
		
	
}
