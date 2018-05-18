package fr.gtm.pbsi.service;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.gtm.pbsi.domain.Employe;

public class EmployeService {

	public Employe loginVerification(Employe employe) {

		Client client = ClientBuilder.newBuilder().build();
		WebTarget webTarget = client.target("http://localhost:8080/webServiceProxiBanque/employe/authentification");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(employe, MediaType.APPLICATION_JSON));
		Employe newEmploye = response.readEntity(Employe.class);

		return newEmploye;

	}
}
