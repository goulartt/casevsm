package com.treino.springboot.vendedorSpring;

import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

import com.treino.springboot.models.Vendedor;

public class Consumer {
	public static void main(String[] args) {
			System.out.println("Digite o Id do vendedor que deseja consumir: ");
			Scanner scanner = new Scanner(System.in);
			String id = scanner.nextLine();
		  	RestTemplate restTemplate = new RestTemplate();
	        Vendedor vendedor = restTemplate.getForObject("http://localhost:8080/vendedores/"+id+"/json", Vendedor.class);
	        System.out.println(vendedor.getNome() + " Foi consumido");
	      
	}
}
