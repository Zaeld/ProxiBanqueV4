package fr.gtm.pbsi.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.Employe;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;


public class EmployeService {
private Client client = Client.create();
private ObjectMapper mapper = new ObjectMapper();

	/** Méthode permettant à un employé de s'identifier sur la page de connexion du site
	 * @param employe : Objet comportant les identifiants de connexion à comparer avec la base de donnée pour retourner l'employé correspondant
	 * @return Employe : Objet employé de la base de donnée correspondant aux identifiants
	 */
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

	}
	/** Méthode permettant de mettre à jour un employé déjà existant en base de donnée
	 * @param employe : Employé présent en base de donnée et devant être modifié
	 * @return Employe : Employé après modification de ses propriétés
	 */
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
	
	/** Méthode permettant d'insérer un objet Employe en base de donnée
	 * @param employe : Objet à insérer en base de donnée
	 * @return Employe : Objet inséré en base de donnée
	 */
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
	
	/** Méthode permettant de supprimer un employé de la base de donnée
	 * @param employe : Employe à supprimer
	 */
	public void deleteEmploye(Employe employe) {
		WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/employe/delete/"+employe.getId());
		ClientResponse response = webResource.accept("application/json").delete(ClientResponse.class);
	
	}
	
	/** Méthode utilisée pour récupérer la liste de tous les employés de la banque
	 * @return List : liste des employés de la banque
	 */
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
	/** Méthode permettant de retourner tous les clients d'un conseiller
	 * @param employe : Conseiller dont les clients doivent être retournés
	 * @return List : Liste de clients du conseilelr
	 */
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
	
		
		



		
	

