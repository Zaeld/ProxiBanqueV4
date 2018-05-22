
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
	 *            : le customer a creer en BDD
	 * @return le client cree
	 */
	@PostMapping({ "", "/" })
	Customer create(@RequestBody Customer customer) {
		// Creation des numeros de comptes et des Floats
		final String randomnumber1 = this.numberGenerator(15);
		final String numberAccount1 = "c" + randomnumber1;
		final String randomnumber2 = this.numberGenerator(15);
		final String numberAccount2 = "s" + randomnumber2;
		// TODO changer pour que ce ne soit pas en dur
		final String dateCreation = "25/04/1997";
		final Float zero = (float) 0;
		final Float rate = (float) 3;
		// Instenciation des Comtpes
		final CurrentAccount currentAccount = new CurrentAccount(numberAccount1, false, dateCreation, zero, zero);
		final SavingAccount savingAccount = new SavingAccount(numberAccount2, false, dateCreation, zero, rate);
		// Creation des comptes en BDD
		final CurrentAccount ca = this.daoAccount.save(currentAccount);
		final SavingAccount sa = this.daoAccount.save(savingAccount);
		// Implementation dans le customer
		customer.setIdca(ca.getId());
		customer.setCurrentAccount(ca);
		customer.setIdsa(sa.getId());
		customer.setSavingAccount(sa);
		// Creation du customer dans la BDD
		final Customer retour = this.daoCustomer.save(customer);
		CustomerService.LOGGER.info("Création de " + retour + " en BDD.");
		return retour;
	}

	/**
	 * Methode delete permettant la suppression d'un client en BDD via son ID.
	 * 
	 * @param customerId
	 *            : l'id du customer a supprimer
	 * @return 1 pour signifier que le client est bien supprimer en BDD.
	 */
	@DeleteMapping("/{customerId}")
	Integer delete(@PathVariable Integer customerId) {
		if (this.daoCustomer.existsById(customerId)) {// si le client demande existe on renvoie 1
			// Récupération du Client
			final Optional<Customer> retour = this.daoCustomer.findById(customerId);
			final Customer response = retour.get();
			// Suppresssion des comptes associes
			this.daoAccount.deleteById(response.getIdca());
			this.daoAccount.deleteById(response.getIdsa());
			// µSuppression du client
			this.daoCustomer.deleteById(customerId);
			CustomerService.LOGGER.info("Suppression de " + response + " de la BDD.");
			return 1;
		} else {// si le client demande n'existe pas on renvoie 0
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
		// Recupere la liste de clients
		final List<Customer> retour = this.daoCustomer.findAll();
		for (final Customer customer : retour) { // Ajoute les comptes pour tous les clients
			// Ajoute le compte courant
			final Optional<Account> ca = this.daoAccount.findById(customer.getIdca());
			final CurrentAccount cabis = (CurrentAccount) ca.get();
			customer.setCurrentAccount(cabis);
			// Ajoute le compte epargne
			final Optional<Account> sa = this.daoAccount.findById(customer.getIdsa());
			final SavingAccount sabis = (SavingAccount) sa.get();
			customer.setSavingAccount(sabis);
		}
		CustomerService.LOGGER.info("Récupération de la liste de tous les customers de ProxiBanque.");
		return retour;
	}

	/**
	 * Methode get permettant de recuperer un client en BDD via son ID.
	 * 
	 * @param customerId
	 *            : l'id du customer demande
	 * @return le client demande
	 */
	@GetMapping("/{customerId}")
	Customer read(@PathVariable Integer customerId) {
		// cherche le client demandé
		final Optional<Customer> retour = this.daoCustomer.findById(customerId);
		if (retour.isPresent()) {// Si existe en BDD renvoie le client
			// recupere le client
			final Customer response = retour.get();
			// recupere le compte courant du client et l'implemente
			final Optional<Account> currentAccount = this.daoAccount.findById(response.getIdca());
			final CurrentAccount ca = (CurrentAccount) currentAccount.get();
			response.setCurrentAccount(ca);
			// recupere le compte epargne du client et l'implemente
			final Optional<Account> savingAccount = this.daoAccount.findById(response.getIdsa());
			final SavingAccount sa = (SavingAccount) savingAccount.get();
			response.setSavingAccount(sa);
			// retourne le client
			CustomerService.LOGGER.info("Récupération de " + response + ".");
			return response;
		} else {// sinon renvoi un client vide + id = 0
			final Customer response = new Customer();
			response.setId(0);
			CustomerService.LOGGER.error("Tentative de récupération d'un customer échouée car l'ID donné n'a aucune correspondance en BDD.");
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
	 *            : l'id du customer a modifier
	 * @param customer
	 *            : le nouvel etat du customer a modifier en BDD
	 * @return le client modifie
	 */
	@PutMapping("/{customerId}")
	Customer update(@PathVariable Integer customerId, @RequestBody Customer customer) {
		if (this.daoCustomer.existsById(customerId)) {
			final Customer retour = this.daoCustomer.save(customer);
			CustomerService.LOGGER.info("Modification de " + customer + " en " + retour + " dans la BDD.");
			return retour;
		} else {
			final Customer response = new Customer();
			response.setId(0);
			CustomerService.LOGGER.error("Tentative de modification de " + customer + " échouée car cet employe n'existe pas en BDD ou l'ID de la requête n'a pas de correspondance en BDD.");
			return response;
		}
	}

	/**
	 * Methode permettant d'obtenir une chaine de caractere aleatoire de taille
	 * donnee.
	 * 
	 * @param length
	 *            : taille de la chaine de caractere aleatoire desiree
	 * @return : une chaine de caractere aleatoire de la taille desirees
	 */
	public String numberGenerator(int length) {
		final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		final StringBuffer pass = new StringBuffer();
		for (int x = 0; x < length; x++) {
			final int i = (int) Math.floor(Math.random() * (chars.length() - 1));
			pass.append(chars.charAt(i));
		}
		return pass.toString();
	}

}
