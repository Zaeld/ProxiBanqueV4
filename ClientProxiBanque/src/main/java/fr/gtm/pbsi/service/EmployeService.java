package fr.gtm.pbsi.service;




import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

import fr.gtm.pbsi.domain.Employe;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class EmployeService {
private Client client = Client.create();
private ObjectMapper mapper = new ObjectMapper();

	public Employe loginVerification(Employe employe) {
		
		String input=null;
		String output=null;
		Employe newEmploye=null;
		try {
			input = mapper.writeValueAsString(employe);
		
		WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/employe/authentification");
		
		ClientResponse reponse = webResource.type("application/json").post(ClientResponse.class, input);
		
		output=reponse.getEntity(String.class);
		
		newEmploye=mapper.readValue(output, Employe.class);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newEmploye;
		
	 
		
		
		
		
//		String res = "{login:\"test\",paswword:\"123\"}";
//		Client client = ClientBuilder.newBuilder().build();
//		WebTarget webTarget = client.target("http://localhost:8080/webServiceProxiBanque/employe/authentification");
//		
//		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//		System.out.println(res);
//		Response response = invocationBuilder.post(Entity.entity(res, MediaType.APPLICATION_JSON));
//		System.out.println(res);
//		Employe newEmploye = response.readEntity(Employe.class);
//		System.out.println(newEmploye);
//		return newEmploye;

	}
}
