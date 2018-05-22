
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
import fr.gtm.pbsi.dao.ITransactionDao;
import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.CurrentAccount;
import fr.gtm.pbsi.domain.Transaction;

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
	
	@Autowired
	private ITransactionDao daoTransaction;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

	/**
	 * Methode post permettant l'insertion en BDD d'un compte.
	 * 
	 * @param account
	 * @return le compte cree
	 */
	@PostMapping({ "", "/" })
	Account create(@RequestBody Account account) {
		final Account retour = this.daoAccount.save(account);
		AccountService.LOGGER.info("Création de " + retour + " en BDD.");
		return retour;
	}

	/**
	 * Methode delete permettant la suppression d'un compte en BDD via son ID.
	 * 
	 * @param accountId
	 * @return 1 pour signifier que la suppression s'est bien passee
	 */
	@DeleteMapping("/{accountId}")
	Integer delete(@PathVariable Integer accountId) {
		if (this.daoAccount.existsById(accountId)) {
			final Optional<Account> retour = this.daoAccount.findById(accountId);
			AccountService.LOGGER.info("Suppression de " + retour.get() + " de la BDD.");
			this.daoAccount.deleteById(accountId);
			return 1;
		} else {
			AccountService.LOGGER.error("Tentative de suppression d'un account échouée car l'ID donné n'est pas trouvable dans la BDD.");
			return 0;
		}

	}

	/**
	 * Methode getAll permettant de recuperer la liste de tous les comptes presents
	 * en BDD
	 * 
	 * @return la liste de tous les clients
	 */
	@GetMapping({ "", "/" })
	List<Account> readAll() {
		AccountService.LOGGER.info("Récupération de la liste de tous les accounts de ProxiBanque.");
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
			AccountService.LOGGER.info("Récupération de " + retour + ".");
			return retour.get();
		} else {
			AccountService.LOGGER.error("Tentative de récupération d'un account échouée car l'ID donné n'a aucune correspondance en BDD.");
			final CurrentAccount response = new CurrentAccount();
			response.setId(0);
			return response;
		}
	}

	/**
	 * Methode put permettant de modifier un compte en BDD grace a son ID et au
	 * nouvel etat du compte. Si l'ID donne n'existe pas en BDD, la methode renvoie
	 * un compte possedant un ID = 0. Sinon elle modifie le compte et renvoie le
	 * compte modifie.
	 * 
	 * @param customerId
	 * @param customer
	 * @return le client modifie
	 */
	@PutMapping("/{accountId}")
	Account update(@PathVariable Integer accountId, @RequestBody Account account) {
		if (this.daoAccount.existsById(accountId)) {
			final Account retour = this.daoAccount.save(account);
			AccountService.LOGGER.info("Modification de " + account + " en " + retour + " dans la BDD.");
			return retour;
		} else {
			AccountService.LOGGER.error("Tentative de modification de " + account + " échouée car cet employe n'existe pas en BDD ou l'ID de la requête n'a pas de correspondance en BDD.");
			final CurrentAccount response = new CurrentAccount();
			response.setId(0);
			return response;
		}
		
	}
	public Account debited(Account account, Float amount) {
		account.setBalance(account.getBalance() - amount);
		return account = daoAccount.save(account);
		
		
	
}
	public Account credited(Account account, Float amount) {
		account.setBalance(account.getBalance() + amount);
		return account = daoAccount.save(account);
		
	
}
	public void transfert (Account debitedAccount, Account creditedAccount, Float amount) {
		 this.debited(debitedAccount,amount);
		
		 this.credited(creditedAccount, amount);
		
		
		
		
		
	}
	@PostMapping("/transaction")
	public Transaction transactionOperation (@RequestBody Transaction transaction) {
	
		
	
		Transaction retour = new Transaction();
		
	 Integer typeTransaction=transaction.getTypeTransaction();
	switch(typeTransaction)
	{
	  case 1:
		 
		  this.debited(transaction.getDebitAccount(), transaction.getValue());
		  retour=daoTransaction.save(transaction);
		  
	
		break;
	  case 2:
		  this.credited(transaction.getCreditAccount(),transaction.getValue());
		  retour=daoTransaction.save(transaction);
		
		break;
	  case 3:
		  this.transfert(transaction.getDebitAccount(), transaction.getCreditAccount(), transaction.getValue());
		  retour=daoTransaction.save(transaction);
		  
		break;
	  default:
		  
		  
		
	}
	return retour;
	}
	
}
