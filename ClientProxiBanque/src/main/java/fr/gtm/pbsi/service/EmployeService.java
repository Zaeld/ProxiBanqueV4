package fr.gtm.pbsi.service;




import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.Customer;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

import fr.gtm.pbsi.domain.Employe;

import java.io.IOException;
import java.util.List;

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
	public Employe updateEmploye(Employe employe) {
		String input=null;
		String output=null;
		Employe newEmploye=null;
			try {
				input = mapper.writeValueAsString(employe);
				WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/employe/"+employe.getId());
				ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);
				output = response.getEntity(String.class);
				newEmploye = mapper.readValue(output, Employe.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newEmploye;
		}
	
	public Employe createEmploye(Employe employe) {
		String input=null;
		String output=null;
		Employe newEmploye=null;
			try {
				input = mapper.writeValueAsString(employe);
				WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/employe");
				ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
				output = response.getEntity(String.class);
				newEmploye = mapper.readValue(output, Employe.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newEmploye;
		}
	
	public void deleteEmploye(Employe employe) {
		WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/employe/delete/"+employe.getId());
		ClientResponse response = webResource.accept("application/json").delete(ClientResponse.class);
	
	}
	
	public List<Employe> getAllAdvisor(){
		String output = null;
		List<Employe> advisorList = null;
		try {

			WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/employe/");

			ClientResponse reponse = webResource.accept("application/json").get(ClientResponse.class);

			output = reponse.getEntity(String.class);

			advisorList = mapper.readValue(output, List.class);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return advisorList;
	}
	public List<Customer> getAllCustomerOfAdvisor(Employe employe){
		String output = null;
		List<Customer> customerList = null;
		try {

			WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/employe/"+employe.getId());

			ClientResponse reponse = webResource.accept("application/json").get(ClientResponse.class);

			output = reponse.getEntity(String.class);

			customerList = mapper.readValue(output, List.class);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerList;
	}
	
	
	}
	
		
		



		
	

