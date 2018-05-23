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

public class CustomerService {
	private final Client client = Client.create();
	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Méthode permettant d'insérer un objet client en base de donnée
	 * 
	 * @param customer
	 *            : Objet à insérer en base de donnée
	 * @return Customer : Objet inséré en base de donnée
	 */
	public Customer createCustomer(Customer customer) {
		String input = null;
		String output = null;
		Customer newCustomer = null;
		try {
			input = this.mapper.writeValueAsString(customer);
			System.out.println(input);
			final WebResource webResource = this.client
					.resource("http://localhost:8080/webServiceProxiBanque/customer/");

			final ClientResponse reponse = webResource.type("application/json").post(ClientResponse.class, input);

			output = reponse.getEntity(String.class);

			newCustomer = this.mapper.readValue(output, Customer.class);
			System.out.println(newCustomer);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return newCustomer;

	}

	/**
	 * Méthode permettant la modification d'un objet client en bdd
	 * 
	 * @param customer
	 *            : Objet déjà présent en bdd et devant être modifié
	 * @return Customer : Objet client après modification
	 */
	public Customer updateCustomer(Customer customer) {
		String input = null;
		String output = null;
		Customer newCustomer = null;
		try {
			input = this.mapper.writeValueAsString(customer);
			final WebResource webResource = this.client
					.resource("http://localhost:8080/webServiceProxiBanque/customer/" + customer.getId());
			final ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);
			output = response.getEntity(String.class);
			newCustomer = this.mapper.readValue(output, Customer.class);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return newCustomer;
	}

	/**
	 * Regroupement des comptes du client en une liste
	 * 
	 * @param customer
	 *            : Client dont les comptes vont être ajoutés dans une liste
	 * @return List : Liste de compte du client
	 */
	public List<Account> accountList(Customer customer) {
		final List<Account> accountList = new ArrayList<Account>();
		if (customer.getCurrentAccount().getIsActive()) {

			accountList.add(customer.getCurrentAccount());
		}
		if (customer.getSavingAccount().getIsActive()) {
			accountList.add(customer.getSavingAccount());
		}
		return accountList;

	}
}
