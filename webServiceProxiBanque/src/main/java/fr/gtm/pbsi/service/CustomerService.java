package fr.gtm.pbsi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.gtm.pbsi.dao.ICustomerDao;
import fr.gtm.pbsi.domain.Customer;

/**
 * Classe WebService/Service de Customer contenant les methodes CRUDs.
 * 
 * @author Stagiaire
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerService {

	@Autowired
	private ICustomerDao daoCustomer;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	/**
	 * Methode post permettant l'insertion en BDD d'un client.
	 * 
	 * @param customer
	 * @return le client cree
	 */
	@PostMapping({ "", "/" })
	// TODO creation des comptes avant la creation du client
	Customer create(@RequestBody Customer customer) {
		final Customer retour = this.daoCustomer.save(customer);
		CustomerService.LOGGER.info("Création de " + retour + " en BDD.");
		return retour;
	}

	/**
	 * Methode delete permettant la suppression d'un client en BDD via son ID.
	 * 
	 * @param customerId
	 * @return 1 pour signifier que le client est bien supprimer en BDD.
	 */
	// TODO Suppression des comptes avant la suppression du client ou deja pris en
	// compte par la cascade ?
	@DeleteMapping("/{customerId}")
	Integer delete(@PathVariable Integer customerId) {
		if (this.daoCustomer.existsById(customerId)) {
			final Optional<Customer> retour = this.daoCustomer.findById(customerId);
			CustomerService.LOGGER.info("Suppression de " + retour.get() + " de la BDD.");
			this.daoCustomer.deleteById(customerId);
			return 1;
		} else {
			CustomerService.LOGGER.error("Tentative de suppression d'un customer échouée car l'ID donné n'est pas trouvable dans la BDD.");
			return 0;
		}

	}

	/**
	 * Methode getAll permettant de recuperer la liste de tous les clients presents
	 * en BDD
	 * 
	 * @return la liste de tous les clients
	 */
	@GetMapping({ "", "/" })
	List<Customer> readAll() {
		CustomerService.LOGGER.info("Récupération de la liste de tous les customers de ProxiBanque.");
		return this.daoCustomer.findAll();
	}
	// TODO ajouter une méthode pour récupérer les clients d'un conseiller

	/**
	 * Methode get permettant de recuperer un client en BDD via son ID.
	 * 
	 * @param customerId
	 * @return le client demande
	 */
	@GetMapping("/{customerId}")
	Customer read(@PathVariable Integer customerId) {
		final Optional<Customer> retour = this.daoCustomer.findById(customerId);
		if (retour.isPresent()) {
			CustomerService.LOGGER.info("Récupération de " + retour + ".");
			return retour.get();
		} else {
			CustomerService.LOGGER.error("Tentative de récupération d'un customer échouée car l'ID donné n'a aucune correspondance en BDD.");
			final Customer response = new Customer();
			response.setId(0);
			return response;
		}
	}

	/**
	 * Methode put permettant de modifier un client en BDD grace a son ID et au
	 * nouvel etat du client. Si l'ID donne n'existe pas en BDD, la methode renvoie
	 * un client possedant un ID = 0. Sinon elle modifie le client et renvoie le
	 * client modifie.
	 * 
	 * @param customerId
	 * @param customer
	 * @return le client modifie
	 */
	@PutMapping("/{customerId}")
	Customer update(@PathVariable Integer customerId, @RequestBody Customer customer) {
		if (this.daoCustomer.existsById(customerId)) {
			final Customer retour = this.daoCustomer.save(customer);
			CustomerService.LOGGER.info("Modification de " + customer + " en " + retour + " dans la BDD.");
			return retour;
		} else {
			CustomerService.LOGGER.error("Tentative de modification de " + customer + " échouée car cet employe n'existe pas en BDD ou l'ID de la requête n'a pas de correspondance en BDD.");
			final Customer response = new Customer();
			response.setId(0);
			return response;
		}
	}
}
