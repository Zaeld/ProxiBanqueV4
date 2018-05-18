package fr.gtm.pbsi.service;

import java.util.List;
import java.util.Optional;

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
import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.CurrentAccount;

/**
 * Classe WebService/Service de Account contenant les methodes CRUDs.
 * 
 * @author Stagiaire
 *
 */
@RestController
@RequestMapping("/account")
public class AccountService {

	@Autowired
	private IAccountDao daoAccount;

	/**
	 * Methode post permettant l'insertion en BDD d'un compte.
	 * 
	 * @param account
	 * @return le compte cree
	 */
	@PostMapping({ "", "/" })
	Account create(@RequestBody Account account) {
		return this.daoAccount.save(account);
	}

	/**
	 * Methode delete permettant la suppression d'un compte en BDD via son ID.
	 * 
	 * @param accountId
	 * @return 1 pour signifier que la suppression s'est bien passee
	 */
	@DeleteMapping("/{accountId}")
	Integer delete(@PathVariable Integer accountId) {
		this.daoAccount.deleteById(accountId);
		return 1;
	}

	/**
	 * Methode getAll permettant de recuperer la liste de tous les comptes presents
	 * en BDD
	 * 
	 * @return la liste de tous les clients
	 */
	@GetMapping({ "", "/" })
	List<Account> readAll() {
		return this.daoAccount.findAll();
	}

	/**
	 * Methode get permettant de recuperer un compte en BDD via son ID.
	 * 
	 * @param accountId
	 * @return le compte demande
	 */
	@GetMapping("/{accountId}")
	Account read(@PathVariable Integer accountId) {
		final Optional<Account> retour = this.daoAccount.findById(accountId);
		if (retour.isPresent()) {
			return retour.get();
		} else {
			final CurrentAccount response = new CurrentAccount();
			response.setId(0);
			return response;
		}
	}

	/**
	 * Methode put permettant de modifier un compte en BDD grace a son ID et au
	 * nouvel etat de l'employe. Si l'ID donne n'existe pas en BDD, la methode
	 * renvoie un compte possedant un ID = 0. Sinon elle modifie le compte et
	 * renvoie le compte modifie.
	 * 
	 * @param customerId
	 * @param customer
	 * @return le client modifie
	 */
	@PutMapping("/{accountId}")
	Account update(@PathVariable Integer accountId, @RequestBody Account account) {
		if (this.daoAccount.existsById(accountId)) {
			return this.daoAccount.save(account);
		} else {
			final CurrentAccount response = new CurrentAccount();
			response.setId(0);
			return response;
		}
	}
}
