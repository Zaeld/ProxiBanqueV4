package fr.gtm.pbsi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.Employe;
import fr.gtm.pbsi.domain.Transaction;

public class CustomerService {
	private Client client = Client.create();
	private ObjectMapper mapper = new ObjectMapper();
	
	public Customer createCustomer(Customer customer) {
		String input=null;
		String output=null;
		Customer newCustomer=null;
		try {
			input = mapper.writeValueAsString(customer);
		System.out.println(input);
		WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/customer/");
		
		ClientResponse reponse = webResource.type("application/json").post(ClientResponse.class, input);
		
		output=reponse.getEntity(String.class);
		
		newCustomer=mapper.readValue(output, Customer.class);
		System.out.println(newCustomer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newCustomer;
		
	}
	
	public Customer updateCustomer(Customer customer) {
		String input=null;
		String output=null;
		Customer newCustomer=null;
			try {
				input = mapper.writeValueAsString(customer);
				WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/customer/"+customer.getId());
				ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);
				output = response.getEntity(String.class);
				newCustomer = mapper.readValue(output, Customer.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newCustomer;
		}
	

	public List<Account> AccountList(Customer customer){
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(customer.getMyCurrentAccount());
		accountList.add(customer.getMySavingAccount());
		return accountList;
		
	}
	
}
