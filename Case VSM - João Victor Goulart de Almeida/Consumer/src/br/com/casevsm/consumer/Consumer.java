package br.com.casevsm.consumer;

import java.io.IOException;
import java.util.Scanner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Consumer {
	public static void main(String[] args) throws IOException {
		
		System.out.println("Digite o Id do vendedor que deseja consumir: ");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/vendedor/rest/"+id);
	    String json = wr.get(String.class);
	    System.out.println(json);
	}
}
