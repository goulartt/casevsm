package  br.com.casevsm.vendedores.services;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.casevsm.vendedores.dao.DAOService;
import br.com.casevsm.vendedores.modelo.Vendedor;

@Path("/")
public class Service {


 
 @GET
 @Path("{id}")
 @Produces(MediaType.APPLICATION_XML)
 public String busca(@PathParam("id") Integer id) {
	 Vendedor teste = new Vendedor(); 
     teste = new DAOService().buscaPorIdService(id);
     return teste.toXML();
 }
     
}