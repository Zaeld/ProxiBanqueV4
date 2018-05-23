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
	private Client client = Client.create();
	private ObjectMapper mapper = new ObjectMapper();

	/** Méthode permettant d'insérer un objet client en base de donnée
	 * @param customer : Objet à insérer en base de donnée
	 * @return Customer : Objet inséré en base de donnée
	 */
	public Customer createCustomer(Customer customer) {
		String input = null;
		String output = null;
		Customer newCustomer = null;
		try {
			input = mapper.writeValueAsString(customer);
			System.out.println(input);
			WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/customer/");

			ClientResponse reponse = webResource.type("application/json").post(ClientResponse.class, input);

			output = reponse.getEntity(String.class);

			newCustomer = mapper.readValue(output, Customer.class);
			System.out.println(newCustomer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newCustomer;

	}

	/** Méthode permettant la modification d'un objet client en bdd
	 * @param customer : Objet déjà présent en bdd et devant être modifié
	 * @return Customer : Objet client après modification
	 */
	public Customer updateCustomer(Customer customer) {
		String input = null;
		String output = null;
		Customer newCustomer = null;
		try {
			input = mapper.writeValueAsString(customer);
			WebResource webResource = client
					.resource("http://localhost:8080/webServiceProxiBanque/customer/" + customer.getId());
			ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);
			output = response.getEntity(String.class);
			newCustomer = mapper.readValue(output, Customer.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newCustomer;
	}

	/** Regroupement des comptes du client en une liste
	 * @param customer : Client dont les comptes vont être ajoutés dans une liste
	 * @return List : Liste de compte du client
	 */
	public List<Account> accountList(Customer customer) {
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(customer.getMyCurrentAccount());
		accountList.add(customer.getMySavingAccount());
		return accountList;

	}

	/** Méthode activant le compte courant du client et le mettant à jour
	 * @param customer : client du compte à activer
	 * @param solde : Montant de départ du compte
	 * @return Customer : Client après opération pour mettre à jour le bean
	 */
	public Customer currentAccountActivation(Customer customer, Float solde) {
		customer.getMyCurrentAccount().setIsActive(true);
		customer.getMyCurrentAccount().setBalance(solde);
		this.updateCustomer(customer);
		return customer;
	}
	
	/** Méthode activant le compte épargne du client et le mettant à jour
	 * @param customer : client du compte à activer
	 * @param solde : Montant de départ du compte
	 * @return Customer : Client après opération pour mettre à jour le bean
	 */
	public Customer savingAccountActivation(Customer customer, Float solde) {
		customer.getMySavingAccount().setIsActive(true);
		customer.getMySavingAccount().setBalance(solde);
		this.updateCustomer(customer);
		return customer;
	}
}
