package fr.gtm.pbsi.service;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.CurrentAccount;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.SavingAccount;
import fr.gtm.pbsi.domain.Transaction;

public class AccountService {
	private Client client = Client.create();
	private ObjectMapper mapper = new ObjectMapper();

	/** Récupération de la liste des comptes de la banque
	 * @return List<Account> : liste de compte 
	 */
	public List<Account> getAllAccount() {

		String output = null;
		List<Account> accountList = null;
		try {

			WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/account/isActive");

			ClientResponse reponse = webResource.accept("application/json").get(ClientResponse.class);

			output = reponse.getEntity(String.class);

			accountList = mapper.readValue(output, List.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return accountList;
	}

	/** Permet de retourner un compte de la base de donnée à partir de son Id
	 * @param idAccount : id du compte à récupérer
	 * @return Account : Entrée de la table account de la bdd ayant l'id en paramètre d'entrée
	 */
	public Account readAccount(Integer idAccount) {

		String output = null;
		try {

			WebResource webResource = client
					.resource("http://localhost:8080/webServiceProxiBanque/account/" + idAccount);

			ClientResponse reponse = webResource.accept("application/json").get(ClientResponse.class);

			output = reponse.getEntity(String.class);

			return mapper.readValue(output, Account.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Méthode permettant de mettre à jour un compte déjà existant en base de
	 * donnée
	 * @param account  compte présent en base de donnée et devant être modifié
	 * @return
	 */
	public Account updateAccount(Account account) {
		String input=null;
		String output=null;
		Account newAccount=null;
			try {
				input = mapper.writeValueAsString(account);
				WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/account/"+account.getId());
				ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);
				output = response.getEntity(String.class);
				newAccount = mapper.readValue(output, Account.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newAccount;
		}
	/**
	 * Méthode permettant de mettre à jour un compte courant déjà existant en base de
	 * donnée
	 * @param account  compte courant présent en base de donnée et devant être modifié
	 * @return compte courant après modification de ses propriétés
	 */
	public CurrentAccount updateCurrentAccount(CurrentAccount account) {
		String input=null;
		String output=null;
		CurrentAccount newAccount=null;
			try {
				input = mapper.writeValueAsString(account);
				WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/account/updatecurrentaccount/"+account.getId());
				ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);
				output = response.getEntity(String.class);
				newAccount = mapper.readValue(output, CurrentAccount.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newAccount;
		}
	/**
	 * Méthode permettant de mettre à jour un compte epargne déjà existant en base de
	 * donnée
	 * @param account compte epargne présent en base de donnée et devant être modifié
	 * @return compte epargne après modification de ses propriétés
	 */
	public SavingAccount updateSavingAccount(SavingAccount account) {
		String input=null;
		String output=null;
		SavingAccount newAccount=null;
			try {
				input = mapper.writeValueAsString(account);
				WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/account/updatesavingaccount/"+account.getId());
				ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);
				output = response.getEntity(String.class);
				newAccount = mapper.readValue(output, SavingAccount.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return newAccount;
		}
	
	/**
	 Méthode permettant de supprimer un compte de la base de donnée via sont id
	 * @param account le compte bancaire qui sera supprimer
	 */
	public void deleteAccount(Account account) {
		WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/account/delete/"+account.getId());
		ClientResponse response = webResource.accept("application/json").delete(ClientResponse.class);
	}

	/** Création d'un objet transaction
	 * @param amountTransaction : montant de la transaction
	 * @param idDebitAccount : id du compte débiteur
	 * @param idCreditAccount : id du compte créditeur
	 * @return Transaction : objet envoyé au webService pour le traitement du virement
	 */
	public Transaction createTransaction(Float amountTransaction, Integer idDebitAccount, Integer idCreditAccount) {
System.out.println("Création d'une transaction");
//SavingAccount savingAccountDebit;
//SavingAccount savingAccountCredit;
//CurrentAccount CurrentAccountDebit;
//CurrentAccount CurrentAccountCredit;
//
//if(this.readAccount(idDebitAccount).getNumberAccount().charAt(0)=='d') {
//	
//	
//}
//		Account debitAccount = this.readAccount(idDebitAccount);
//		Account creditAccount = this.readAccount(idDebitAccount);

		Transaction transaction = new Transaction(amountTransaction, idDebitAccount, idCreditAccount);
		transaction.setTypeTransaction(3);

		System.out.println("retour d'une transaction");

		return transaction;

	}

	/** Méthode permettant d'envoyer l'objet transaction nécessaire au traitement de l'opération bancaire
	 * @param transaction : Objet contenant les informations de la transaction
	 * @return Transaction : Objet permettant de mettre à jour les informations des vues
	 */
	public Transaction transfert(Transaction transaction) {
		String input = null;
		String output = null;
		Transaction transactionDone = null;
		try {
			input = mapper.writeValueAsString(transaction);
System.out.println("début webService avec"+input);
			WebResource webResource = client
					.resource("http://localhost:8080/webServiceProxiBanque/account/transaction");

			ClientResponse reponse = webResource.type("application/json").post(ClientResponse.class, input);

			output = reponse.getEntity(String.class);
			System.out.println("fin webService avec"+output);

			transactionDone = mapper.readValue(output, Transaction.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return transactionDone;
	}
	/**
	 * Méthode activant le compte courant du client et le mettant à jour
	 * 
	 * @param customer
	 *            : client du compte à activer
	 * @param solde
	 *            : Montant de départ du compte
	 * @return Customer : Client après opération pour mettre à jour le bean
	 */
	public Customer currentAccountActivation(Customer customer, Float solde) {
		customer.getCurrentAccount().setIsActive(true);
		customer.getCurrentAccount().setBalance(solde);
		this.updateCurrentAccount(customer.getCurrentAccount());
		return customer;
	}

	/**
	 * Méthode activant le compte épargne du client et le mettant à jour
	 * 
	 * @param customer
	 *            : client du compte à activer
	 * @param solde
	 *            : Montant de départ du compte
	 * @return Customer : Client après opération pour mettre à jour le bean
	 */
	public Customer savingAccountActivation(Customer customer, Float solde) {
		customer.getSavingAccount().setIsActive(true);
		customer.getSavingAccount().setBalance(solde);
		this.updateSavingAccount(customer.getSavingAccount());
		return customer;
	}
}
