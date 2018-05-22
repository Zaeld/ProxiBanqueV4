
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

import fr.gtm.pbsi.dao.IAccountDao;
import fr.gtm.pbsi.dao.ICustomerDao;
import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.CurrentAccount;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.SavingAccount;

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

	@Autowired
	private IAccountDao daoAccount;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	/**
	 * Methode post permettant l'insertion en BDD d'un client.
	 * 
	 * @param customer
	 * @return le client cree
	 */
	@PostMapping({ "", "/" })
	Customer create(@RequestBody Customer customer) {
		// TODO changer pour que ce ne soit pas en dur
		final String numberAccount1 = "c023654";
		final String numberAccount2 = "s023654";
		final String dateCreation = "25/04/1997";
		final Float zero = (float) 0;
		final Float rate = (float) 3;
		final CurrentAccount currentAccount = new CurrentAccount(numberAccount1, false, dateCreation, zero, zero);
		final SavingAccount savingAccount = new SavingAccount(numberAccount2, false, dateCreation, zero, rate);
		final CurrentAccount ca = this.daoAccount.save(currentAccount);
		final SavingAccount sa = this.daoAccount.save(savingAccount);
		customer.setIdca(ca.getId());
		customer.setIdsa(sa.getId());
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
	@DeleteMapping("/{customerId}")
	Integer delete(@PathVariable Integer customerId) {
		if (this.daoCustomer.existsById(customerId)) {
			final Optional<Customer> retour = this.daoCustomer.findById(customerId);
			final Customer response = retour.get();
			this.daoAccount.deleteById(response.getIdca());
			this.daoAccount.deleteById(response.getIdsa());
			this.daoCustomer.deleteById(customerId);
			CustomerService.LOGGER.info("Suppression de " + response + " de la BDD.");
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
			final Customer response = retour.get();
			final Optional<Account> currentAccount = this.daoAccount.findById(response.getIdca());
			final Optional<Account> savingAccount = this.daoAccount.findById(response.getIdsa());
			final CurrentAccount ca = (CurrentAccount) currentAccount.get();
			final SavingAccount sa = (SavingAccount) savingAccount.get();
			response.setCurrentAccount(ca);
			response.setSavingAccount(sa);
			CustomerService.LOGGER.info("Récupération de " + response + ".");
			return response;
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
