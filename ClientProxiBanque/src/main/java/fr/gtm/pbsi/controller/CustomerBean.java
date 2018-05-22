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
	private Customer customer = new Customer();
	private CustomerService serviceCustomer = new CustomerService();
	private EmployeService serviceEmploye = new EmployeService();
	private AccountService serviceAccount = new AccountService();
	private String message = "test message";
	private List<Account> debitAccount = new ArrayList<Account>();
	private List<Account> creditAccount = new ArrayList<Account>();
	private Float amountTransaction = 0.0f;
	private Transaction transaction = new Transaction();
	private Integer idDebitAccount;
	private Integer idCreditAccount;
	private List<Account> customerAccountList = new ArrayList<Account>();
	private List<Account> accountList=null;
	
	
	// ============= assesseurs ============
	
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

	public List<Account> getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(List<Account> debitAccount) {
		this.debitAccount = debitAccount;
	}

	public List<Account> getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccounts(List<Account> creditAccount) {
		this.creditAccount = creditAccount;
	}	// ==================================================

	// ===================== Methode ======================


	public String createCustomer(int idEmploye) {
		// Set de l'idée employée dans l'attribut client avec un parse
		this.customer.setIdEmploye((Integer)idEmploye);
		String forward = null;
		this.customer = serviceCustomer.createCustomer(this.customer);
		if (customer.getId() > 0) {
			forward = "success";
		} else
			forward = "fail";
		return forward;
	}

	public String updateCustomer(Customer customer) {
		String forward = null;
		this.customer = serviceCustomer.updateCustomer(this.customer);
		if (this.customer==null) {
			forward = "fail";

		} else
			forward = "success";
		return forward;
	}
	
	public String transfert() {
		String forward = null;
		this.transaction = serviceAccount.createTransaction(amountTransaction, idDebitAccount, idCreditAccount);
		this.transaction = serviceAccount.transfert(this.transaction);
		if (this.transaction==null) {
			forward = "fail";

		} else
			forward = "success";
		return forward;
	}
	public String goUpdateCustomer(Customer customer) {
		System.out.println("-- goUpdateCustomer Méthode --");
		this.customer = customer;
		return "customerModification";
	}

	public String goTransfert(Customer customer) {
		System.out.println("-- goTransfert Méthode --");
		this.customer = customer;
		this.customerAccountList=serviceCustomer.AccountList(customer);
		this.accountList=serviceAccount.getAllAccount();
		return "transfert";
	}

	public String goAccountsList(Customer customer) {
		System.out.println("-- goAccountsList Méthode --");
		this.customer = customer;
		return "accountsList";
	}

	public String goCreateCustomerPage() {
		System.out.println("-- showCreateCustomerPage Méthode --");
		this.customer = new Customer();
		return "customerCreation";
	}

}
