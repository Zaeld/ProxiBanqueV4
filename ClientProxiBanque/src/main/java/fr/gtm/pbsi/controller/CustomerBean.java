package fr.gtm.pbsi.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.Transaction;
import fr.gtm.pbsi.service.AccountService;
import fr.gtm.pbsi.service.CustomerService;
import fr.gtm.pbsi.service.EmployeService;

@ManagedBean(name = "customerbean")
@SessionScoped
public class CustomerBean {
	// Déclaration des variables locales
	private Customer customer = new Customer();
	private CustomerService serviceCustomer = new CustomerService();
	private EmployeService serviceEmploye = new EmployeService();
	private AccountService serviceAccount = new AccountService();
	private String message = "test message";
	private Float amountTransaction = 0.0f;
	private Transaction transaction = new Transaction();
	private Integer idDebitAccount;
	private Integer idCreditAccount;
	private List<Account> customerAccountList = new ArrayList<Account>();
	private List<Account> accountList = null;
	private Float solde = 0.0f;

	// =====================Assesseurs ======================

	public Customer getCustomer() {
		return customer;
	}

	public Integer getIdDebitAccount() {
		return idDebitAccount;
	}

	public void setIdDebitAccount(Integer idDebitAccount) {
		this.idDebitAccount = idDebitAccount;
	}

	public Integer getIdCreditAccount() {
		return idCreditAccount;
	}

	public void setIdCreditAccount(Integer idCreditAccount) {
		this.idCreditAccount = idCreditAccount;
	}

	public Float getAmountTransaction() {
		return amountTransaction;
	}

	public void setAmountTransaction(Float amountTransaction) {
		this.amountTransaction = amountTransaction;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<Account> getCustomerAccountList() {
		return customerAccountList;
	}

	public void setCustomerAccountList(List<Account> customerAccountList) {
		this.customerAccountList = customerAccountList;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AccountService getServiceAccount() {
		return serviceAccount;
	}

	public void setServiceAccount(AccountService serviceAccount) {
		this.serviceAccount = serviceAccount;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Float getSolde() {
		return solde;
	}

	public void setSolde(Float solde) {
		this.solde = solde;
	}
	// ==================================================

	// ===================== Methode ======================

	/**
	 * Méthode permettant la création d'un client
	 * 
	 * @param idEmploye
	 *            : id du conseiller qui sera le conseiller du futur client
	 * @return String : page à afficher, page de succès si la création a bien eu
	 *         lieu et page d'échec dans le cas contraire
	 */
	public String createCustomer(int idEmploye) {
		// Set de l'idée employée dans l'attribut client avec un parse
		this.customer.setIdEmploye((Integer) idEmploye);
		String forward = null;
		this.customer = serviceCustomer.createCustomer(this.customer);
		// Si le client a bien été créé, le client retourné par le service possède un id
		// assigné par la base de donnée non null

		this.message = " 'creation du client' ";
		if (customer.getId() > 0) {
			forward = "success";
		} else
			forward = "fail";
		return forward;
	}

	/**
	 * Méthode permettant la modification d'un objet client en bdd
	 * 
	 * @param customer
	 *            : Objet déjà présent en bdd et devant être modifié
	 * @return String : page à afficher, page de succès si la création a bien eu
	 *         lieu et page d'échec dans le cas contraire
	 */
	public String updateCustomer(Customer customer) {
		String forward = null;
		this.customer = serviceCustomer.updateCustomer(this.customer);
		// Si la modification a bien eu lieu, le service retourne bien un client
		this.message = " 'mise à jour du client' ";
		if (this.customer == null) {
			forward = "fail";
		} else
			forward = "success";
		return forward;
	}

	/**
	 * Méthode permettant de créer et d'envoyer l'objet transaction nécessaire au
	 * traitement de l'opération bancaire
	 * 
	 * @return String : page à afficher, page de succès si la création a bien eu
	 *         lieu et page d'échec dans le cas contraire
	 */
	public String transfert() {
		String forward = null;
		this.transaction = serviceAccount.createTransaction(this.amountTransaction, this.idDebitAccount, this.idCreditAccount);
		this.transaction = serviceAccount.transfert(this.transaction);
		// On met à jour les informations du client après la transaction
		this.customer = serviceCustomer.updateCustomer(this.customer);
		// Si l'opération bancaire a bien été réalisée, le service retourne un objet
		// transaction non null
		this.message = " virement compte à compte ";
		if (this.transaction == null) {
			forward = "fail";

		} else
			forward = "success";
		return forward;
	}

	/**
	 * Méthode permettant d'activer(créer) le compte courant du client affichée dans
	 * la page
	 */
	public String currentAccountActivation() {
		System.out.println("currentAccountActivation méthode");
		this.customer = this.serviceAccount.currentAccountActivation(this.customer, this.solde);
		System.out.println(this.customer + "   " + this.customer.getCurrentAccount());
		return "accountsList";
	}

	/**
	 * Méthode permettant d'activer(créer) le compte épargne du client affichée dans
	 * la page
	 */
	public String savingAccountActivation() {
		System.out.println("savingAccountActivation méthode");
		this.customer = this.serviceAccount.savingAccountActivation(this.customer, this.solde);
		return "accountsList";
	}

	/**
	 * Méthode dirigeant vers la page de modification du client et mettant à jour la
	 * propriété customer du bean pour appliquer ses valeurs à la vue
	 * 
	 * @param customer
	 *            : Client dont les valeurs vont être utilisé pour la vue
	 * @return String : page à afficher
	 */
	public String goUpdateCustomer(Customer customer) {
		System.out.println("-- goUpdateCustomer Méthode --");
		this.customer = customer;
		return "customerModification";
	}

	/**
	 * Méthode dirigeant vers la page de virement compte à compte et mettant à jour
	 * la propriété customer du bean pour appliquer ses valeurs à la vue
	 * 
	 * @param customer
	 *            : Client dont les valeurs vont être utilisé pour la vue
	 * @return String : page à afficher
	 */
	public String goTransfert(Customer customer) {
		System.out.println("-- goTransfert Méthode --");
		this.customer = customer;
		// Liste des comptes du client
		this.customerAccountList = serviceCustomer.accountList(customer);
		// Liste de tous les comptes de la banque
		this.accountList = serviceAccount.getAllAccount();
		return "transfert";
	}

	/**
	 * Méthode dirigeant vers la page d'information du client et mettant à jour la
	 * propriété customer du bean pour appliquer ses valeurs à la vue
	 * 
	 * @param customer
	 *            : Client dont les valeurs vont être utilisé pour la vue
	 * @return String : page à afficher
	 */
	public String goAccountsList(Customer customer) {
		System.out.println("-- goAccountsList Méthode --");
		this.customer = customer;
		return "accountsList";
	}

	/**
	 * Méthode dirigeant vers la page de création du client et effaçant les valeurs
	 * de la propriété customer de la vue
	 * 
	 * @return String : page à afficher
	 */
	public String goCreateCustomerPage() {
		System.out.println("-- showCreateCustomerPage Méthode --");
		this.customer = new Customer();
		return "customerCreation";
	}

}
