
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
import fr.gtm.pbsi.dao.IEmployeDao;
import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.CurrentAccount;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.Employe;
import fr.gtm.pbsi.domain.SavingAccount;

/**
 * Classe WebService/Service de Employe contenant les methodes CRUDs et la
 * methode d'authentification des employes.
 * 
 * @author Stagiaire
 *
 */
@RestController
@RequestMapping("/employe")
public class EmployeService {

	@Autowired
	private IEmployeDao daoEmploye;

	@Autowired
	private ICustomerDao daoCustomer;

	@Autowired
	private IAccountDao daoAccount;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeService.class);

	/**
	 * Methode post permettant l'insertion en BDD de l'employe. La methode verifie
	 * qu'aucun autre employe ne possede le même mot de passe et/ou login. Si oui
	 * retourne un employe vide d'ID 0.
	 * 
	 * @param employe
	 *            : l'employe a creer en BDD
	 * @return l'employe cree
	 */
	@PostMapping({ "", "/" })
	Employe create(@RequestBody Employe employe) {
		// Verifie si le login ET le password sont deja utilises par un autre employe
		Employe retour = this.daoEmploye.findByLoginAndPassword(employe.getLogin(), employe.getPassword());
		if (retour == null) {// Si non on cree l'employe en BDD
			// Sauvegarde en BDD de l'employe
			retour = this.daoEmploye.save(employe);
			EmployeService.LOGGER.info("Création de " + retour + " en BDD.");
			return retour;
		} else {// Si oui on renvoie un employe d'id = 0
			retour.setId(0);
			EmployeService.LOGGER.info("Impossible de créer l'employe en BDD car le login et mot de passe existe déjà pour un autre employe.");
			return retour;
		}
	}

	/**
	 * Methode post permettant de verifier le login et le password de l'employe
	 * essayant de se connecter a l'application. Si le login et/ou le password sont
	 * mauvais, l'employe retourne possede un ID = 0.
	 * 
	 * @param employe
	 *            : employe (login + password) a tester
	 * @return l'employe qui se connecte
	 */
	@PostMapping("/authentification")
	Employe authentification(@RequestBody Employe employe) {
		final Employe retour = this.daoEmploye.findByLoginAndPassword(employe.getLogin(), employe.getPassword());
		if (retour == null) {// Si aucun employe ne correspond on renvoie un employe vide + id = 0
			final Employe employeVide = new Employe();
			employeVide.setId(0);
			EmployeService.LOGGER.info("Tentative de connection à l'application échouée due à un mauvais login ou password.");
			return employeVide;
		} else {// sinon on regarde s'il s'agit d'un gerant ou d'un conseiller
			if (retour.getTypeFunction() == 0) {// si c'est un gerant on renvoie l'employe
				EmployeService.LOGGER.info("Connection du gérant " + retour.getName() + " " + retour.getFirstName() + " à l'application.");
			} else {// si c'est un conseiller on le renvoie avec sa liste de clients (et leurs
					// compte)
				final List<Customer> customers = this.daoCustomer.findAllByIdEmploye(employe.getId());
				for (final Customer customer : customers) {// on ajoute les comptes pour chaque clients
					// Ajoute le compte courant
					final Optional<Account> ca = this.daoAccount.findById(customer.getIdca());
					final CurrentAccount cabis = (CurrentAccount) ca.get();
					customer.setCurrentAccount(cabis);
					// Ajoute le compte epargne
					final Optional<Account> sa = this.daoAccount.findById(customer.getIdsa());
					final SavingAccount sabis = (SavingAccount) sa.get();
					customer.setSavingAccount(sabis);
				}
				retour.setListCustomer(customers);
				retour.setNumberCustomers(customers.size());
				EmployeService.LOGGER.info("Connection du conseiller " + retour.getName() + " " + retour.getFirstName() + " à l'application.");
			}
			return retour;
		}
	}

	/**
	 * Methode delete permettant la suppression d'un employe en BDD via son ID. Si
	 * l'ID donne n'existe pas la methode renvoie 0. De même, si l'employe
	 * correspondant existe mais possede encore des clients, la methode renvoie 0.
	 * Si l'employe existe et ne possede plus de client alors la methode le supprime
	 * et renvoie 1.
	 * 
	 * @param employeId
	 *            : employe a supprimer
	 * @return 0 si l'employe n'existe pas, 1 si la suppression s'est bien
	 *         effectuee, ou -1 si l'employe possede encore des clients
	 */
	@DeleteMapping("/{employeId}")
	Integer delete(@PathVariable Integer employeId) {
		if (this.daoEmploye.existsById(employeId)) {// si l'employe existe
			final Optional<Employe> retour = this.daoEmploye.findById(employeId);
			final Employe employe = retour.get();
			final List<Customer> customers = this.daoCustomer.findAllByIdEmploye(employe.getId());
			if (customers.isEmpty()) {// s'il possede encore des clients on renvoie -1
				EmployeService.LOGGER.info("Tentative de suppression d'un employe échouée car il possède encore des clients.");
				return -1;
			} else {// sinon on le supprime et on renvoie 1
				this.daoEmploye.deleteById(employeId);
				EmployeService.LOGGER.info("Suppression de " + employe + " de la BDD.");
				return 1;
			}
		} else {// sinon on revoie 0
			EmployeService.LOGGER.error("Tentative de suppression d'un employe échouée car l'ID donné n'est pas trouvable dans la BDD.");
			return 0;
		}
	}

	/**
	 * Methode getAll permettant de recuperer la liste de tous les employes presents
	 * en BDD.
	 * 
	 * @return la liste des employes
	 */
	@GetMapping({ "", "/" })
	List<Employe> readAll() {
		// Recuperation de la liste des employes
		final List<Employe> retour = this.daoEmploye.findAll();
		// on implemente le nombre de clients que possede chaque employe
		for (final Employe employe : retour) {
			final List<Customer> customers = this.daoCustomer.findAllByIdEmploye(employe.getId());
			employe.setNumberCustomers(customers.size());
		}
		EmployeService.LOGGER.info("Récupération de la liste des employes de ProxiBanque.");
		return retour;
	}

	/**
	 * Methode get permettant de recuperer un employe en BDD via son ID.
	 * 
	 * @param employeId
	 *            : id de l'employe demande
	 * @return l'employe demande
	 */
	@GetMapping("/{employeId}")
	Employe read(@PathVariable Integer employeId) {
		final Optional<Employe> retour = this.daoEmploye.findById(employeId);
		if (retour.isPresent()) {// si l'employe demande existe on le renvoie avec sa liste de clients (et leur
									// comptes)
			// Recuperation de l'employe
			final Employe response = retour.get();
			// Ajout de la liste des clients avec leurs comptes
			final List<Customer> listCustomer = this.daoCustomer.findAllByIdEmploye(response.getId());
			for (final Customer customer : listCustomer) {// on ajoute les comptes pour chaque clients
				// Ajoute le compte courant
				final Optional<Account> ca = this.daoAccount.findById(customer.getIdca());
				final CurrentAccount cabis = (CurrentAccount) ca.get();
				customer.setCurrentAccount(cabis);
				// Ajoute le compte epargne
				final Optional<Account> sa = this.daoAccount.findById(customer.getIdsa());
				final SavingAccount sabis = (SavingAccount) sa.get();
				customer.setSavingAccount(sabis);
			}
			response.setListCustomer(listCustomer);
			EmployeService.LOGGER.info("Récupération de " + response + ".");
			return response;
		} else {// sinon on renvoie un employe vide + id = 0
			final Employe response = new Employe();
			response.setId(0);
			EmployeService.LOGGER.error("Tentative de récupération d'un employe échouée car l'ID donné n'a aucune correspondance en BDD.");
			return response;
		}
	}

	/**
	 * Methode permettant de recuperer tous les conseillers present en BDD.
	 * 
	 * @return la liste des conseillers
	 */
	@GetMapping("/adviser")
	List<Employe> readAdviser() {
		final List<Employe> retour = this.daoEmploye.findAllByTypeFunction(1);
		for (final Employe employe : retour) {// ajout du nombre de client pour chaque employe
			final List<Customer> customers = this.daoCustomer.findAllByIdEmploye(employe.getId());
			employe.setNumberCustomers(customers.size());
		}
		EmployeService.LOGGER.info("Récupération de la liste des conseiller de ProxiBanque.");
		return retour;
	}

	/**
	 * Methode put permettant de modifier un employe en BDD grace a son ID et au
	 * nouvel etat de l'employe. Si l'ID donne n'existe pas en BDD, la methode
	 * renvoie un employe possedant un ID = 0. Sinon elle modifie l'employe et
	 * renvoie l'employe modifie.
	 * 
	 * @param employeId
	 *            : id de l'employe a modifier
	 * @param employe
	 *            : nouvel etat de l'employe a modifier en BDD
	 * @return l'employe modifie
	 */
	@PutMapping("/{employeId}")
	Employe update(@PathVariable Integer employeId, @RequestBody Employe employe) {
		if (this.daoEmploye.existsById(employeId)) {// si l'employe existe on le modifie et on le renvoie
			final Employe retour = this.daoEmploye.save(employe);
			// Ajout de la liste des clients avec leurs comptes
			final List<Customer> listCustomer = this.daoCustomer.findAllByIdEmploye(retour.getId());
			for (final Customer customer : listCustomer) {// on ajoute les comptes pour chaque clients
				// Ajoute le compte courant
				final Optional<Account> ca = this.daoAccount.findById(customer.getIdca());
				final CurrentAccount cabis = (CurrentAccount) ca.get();
				customer.setCurrentAccount(cabis);
				// Ajoute le compte epargne
				final Optional<Account> sa = this.daoAccount.findById(customer.getIdsa());
				final SavingAccount sabis = (SavingAccount) sa.get();
				customer.setSavingAccount(sabis);
			}
			retour.setListCustomer(listCustomer);
			EmployeService.LOGGER.info("Modification de " + employe + " en " + retour + " dans la BDD.");
			return retour;
		} else {// sinon on renvoie un employe vide + id = 0
			final Employe response = new Employe();
			response.setId(0);
			EmployeService.LOGGER.error("Tentative de modification de " + employe + " échouée car cet employe n'existe pas en BDD ou l'ID de la requête n'a pas de correspondance en BDD.");
			return response;
		}
	}
}
