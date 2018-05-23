package fr.gtm.pbsi.service;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.gtm.pbsi.domain.Account;
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

			WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/account/");

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
		Account account = null;
		try {

			WebResource webResource = client
					.resource("http://localhost:8080/webServiceProxiBanque/account/" + idAccount);

			ClientResponse reponse = webResource.accept("application/json").get(ClientResponse.class);

			output = reponse.getEntity(String.class);

			account = mapper.readValue(output, Account.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return account;
	}

	/** Création d'un objet transaction
	 * @param amountTransaction : montant de la transaction
	 * @param idDebitAccount : id du compte débiteur
	 * @param idCreditAccount : id du compte créditeur
	 * @return Transaction : objet envoyé au webService pour le traitement du virement
	 */
	public Transaction createTransaction(Float amountTransaction, Integer idDebitAccount, Integer idCreditAccount) {

		Account debitAccount = this.readAccount(idDebitAccount);
		Account creditAccount = this.readAccount(idCreditAccount);
		Transaction transaction = new Transaction(amountTransaction, debitAccount, creditAccount);
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

			WebResource webResource = client
					.resource("http://localhost:8080/webServiceProxiBanque/customer/transaction");

			ClientResponse reponse = webResource.type("application/json").post(ClientResponse.class, input);

			output = reponse.getEntity(String.class);

			transactionDone = mapper.readValue(output, Transaction.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return transactionDone;
	}

}
