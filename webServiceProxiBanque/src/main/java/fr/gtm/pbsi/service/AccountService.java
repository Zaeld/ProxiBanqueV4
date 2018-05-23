
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
	private static final Logger TRANSACTION = LoggerFactory.getLogger("transactions");

	/**
	 * Methode post permettant l'insertion en BDD d'un compte.
	 * 
	 * @param account
	 *            : le compte a ajouter en BDD
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
	 *            : l'id du compte a supprimer
	 * @return 1 pour signifier que la suppression s'est bien passee
	 */
	@DeleteMapping("/{accountId}")
	Integer delete(@PathVariable Integer accountId) {
		if (this.daoAccount.existsById(accountId)) {
			final Optional<Account> retour = this.daoAccount.findById(accountId);
			this.daoAccount.deleteById(accountId);
			AccountService.LOGGER.info("Suppression de " + retour.get() + " de la BDD.");
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
	 *            : id du compte demande
	 * @return le compte demande
	 */
	@GetMapping("/{accountId}")
	Account read(@PathVariable Integer accountId) {
		final Optional<Account> retour = this.daoAccount.findById(accountId);
		if (retour.isPresent()) {
			AccountService.LOGGER.info("Récupération de " + retour + ".");
			return retour.get();
		} else {
			final CurrentAccount response = new CurrentAccount();
			response.setId(0);
			AccountService.LOGGER.error("Tentative de récupération d'un account échouée car l'ID donné n'a aucune correspondance en BDD.");
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
	 *            : id du compte a modifier
	 * @param customer
	 *            : nouvel etat du compte a modifier en BDD
	 * @return le client modifie
	 */
	@PutMapping("/{accountId}")
	Account update(@PathVariable Integer accountId, @RequestBody Account account) {
		if (this.daoAccount.existsById(accountId)) {
			final Account retour = this.daoAccount.save(account);
			AccountService.LOGGER.info("Modification de " + account + " en " + retour + " dans la BDD.");
			return retour;
		} else {
			final CurrentAccount response = new CurrentAccount();
			response.setId(0);
			AccountService.LOGGER.error("Tentative de modification de " + account + " échouée car cet employe n'existe pas en BDD ou l'ID de la requête n'a pas de correspondance en BDD.");
			return response;
		}
	}

	

	/**
	 * Methode qui debite le montant renseigner sur le compte debiteur 
 	 *renseigner
	 * @param account Le compte dont le solde va etre mis a jour 
	 * @param amount montant qui va etre soustrait a la solde du compte
	 * @return le compte mis a jour avec sont nouveau solde
	 */
	public Account debited(Account account, Float amount) {
		account.setBalance(account.getBalance() - amount);
		return account = this.daoAccount.save(account);

	}

	/**
	 * Methode qui credite le montant sur le compte a crediter renseigne . 
	 * @param account Le compte dont le solde va etre mis a jour
	 * @param amount montant qui va etre ajoute a la solde du compte
	 * @return le compte mis a jour avec sont nouveau solde
	 */
	public Account credited(Account account, Float amount) {
		account.setBalance(account.getBalance() + amount);
		return account = this.daoAccount.save(account);

	}

	/**
	 * Methode qui realise un virement de compte à compte. Pour cela, elle credite
	 * le compte a crediter du montant donne par la requete puis debite le compte a
	 * debiter de ce meme montant. Si un probleme survient, la methode retourne
	 * false. Sinon le virement s'est bien effectue et elle retourne true.
	 * @param debitedAccount appel de la méthode debited
	 * @param creditedAccount appel de la méthode credited
	 * @param amount montant echanger entre les deux comptre lors du virement
	 */
	public void transfert(Account debitedAccount, Account creditedAccount, Float amount) {
		this.debited(debitedAccount, amount);

		this.credited(creditedAccount, amount);

	}
	
	/**
	 * Methode @Post permetant de realiser un debit credit ou virement 
	 * et qui permet de mettre en place un enregistrement de l'operation banquaire
	 * @param transaction enregistrement d'une operation banquaire
	 * @return un transaction
	 */
	@PostMapping("/transaction")
	public Transaction transactionOperation(@RequestBody Transaction transaction) {

		Transaction retour = new Transaction();

		final Integer typeTransaction = transaction.getTypeTransaction();
		switch (typeTransaction) {
		case 1:
			this.debited(transaction.getDebitAccount(), transaction.getValue());
			retour = this.daoTransaction.save(transaction);
			AccountService.TRANSACTION
					.info("Le compte " + transaction.getDebitAccount() + " a été débité de " + transaction.getValue() + ". Le nouvel état du compte est " + retour.getDebitAccount() + ".");
			break;
		case 2:
			this.credited(transaction.getCreditAccount(), transaction.getValue());
			retour = this.daoTransaction.save(transaction);
			AccountService.TRANSACTION
					.info("Le compte " + transaction.getCreditAccount() + " a été crédité de " + transaction.getValue() + ". Le nouvel état du compte est " + retour.getCreditAccount() + ".");
			break;
		case 3:
			this.transfert(transaction.getDebitAccount(), transaction.getCreditAccount(), transaction.getValue());
			retour = this.daoTransaction.save(transaction);
			AccountService.TRANSACTION.info("Un virement compte à compte du compte " + transaction.getDebitAccount() + " au compte " + transaction.getCreditAccount() + " d'un montant de "
					+ transaction.getValue() + " a été effectué. Le nouvel état de ces comptes est " + retour.getDebitAccount() + " et " + retour.getCreditAccount() + ".");
			break;
		default:
			final Transaction vide = new Transaction();
			vide.setId(0);
			retour = vide;
			AccountService.TRANSACTION.error("Une erreur est survenue lors de la réalisation d'une transaction car cette dernière n'est pas reconnue par le système.");
		}
		return retour;
	}

}
