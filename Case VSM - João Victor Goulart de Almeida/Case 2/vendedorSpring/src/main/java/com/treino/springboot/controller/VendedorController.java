package com.treino.springboot.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.treino.springboot.models.Cidade;
import com.treino.springboot.models.Usuario;
import com.treino.springboot.models.Vendedor;
import com.treino.springboot.repository.CidadeDao;
import com.treino.springboot.repository.UsuarioDao;
import com.treino.springboot.repository.VendedorDao;

@Controller
public class VendedorController {
	
	
	
	@Autowired VendedorDao dao;
	@Autowired CidadeDao cdao;
	@Autowired UsuarioDao udao;
	
	BCryptPasswordEncoder bcript = new BCryptPasswordEncoder();
	
	@GetMapping("/add")
	public ModelAndView cadastroVendedor(Vendedor vendedor, Cidade cidade) {
		ModelAndView mv = new ModelAndView("vendedor");
	    mv.addObject("vendedor", vendedor);
	    mv.addObject("cidades", cdao.findAll());
		return mv;
	}
	@PostMapping("/save")
	public String form(@Valid Vendedor vendedor, BindingResult bindingResult, Cidade cidade, Model model ) throws ParseException {
		if(bindingResult.hasErrors()) {
			model.addAttribute("cidades", cdao.findAll());
			return "vendedor";
		}
		vendedor.setConsumido("N");
		if(vendedor.getId() == null) {
			 dao.saveVendedor(vendedor);
			 Usuario user = new Usuario(vendedor.getCpf(), bcript.encode(vendedor.getSenha()), vendedor.getNome(), vendedor.getId());
			 udao.save(user);
			
		}else {
			dao.atualiza(vendedor);
			Usuario user = udao.find(vendedor.getId());
			user.setCpf(vendedor.getCpf());
			user.setSenha(bcript.encode(vendedor.getSenha()));
			user.setNome(vendedor.getNome());
			udao.atualiza(user);
		}
	   
	    return "redirect:/";
	}
	
 	@GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id, Cidade cidade) {
 		Vendedor v = dao.find(id);
 		v.setConsumido("N");
 		return cadastroVendedor(v, cidade);
    }
	     
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        udao.delete(dao.find(id).getId());
        dao.delete(id);
         
        return "redirect:/";
    }
	
	@GetMapping("/")
	public ModelAndView vendedores() {
		ModelAndView model = new ModelAndView("lista");
		model.addObject("vendedores", dao.findAll());
		return model;
	}
	
	@PostMapping("/cadastrarCidade")
	public ModelAndView form(@Valid Cidade cidade,  BindingResult bindingResult, Vendedor vendedor) { 
		if(bindingResult.hasErrors()) {
			return cadastroVendedor(vendedor, new Cidade());
		}
		cidade.setEstado(cidade.getEstado().toUpperCase());
		cdao.saveCidade(cidade);
	    return cadastroVendedor(vendedor, new Cidade()); 
	}
	


	

}
