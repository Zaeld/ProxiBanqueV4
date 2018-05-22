package fr.gtm.pbsi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.service.CustomerService;
import fr.gtm.pbsi.service.EmployeService;

@ManagedBean(name = "customerbean")
@SessionScoped
public class CustomerBean {
	private Customer customer = new Customer();
	private CustomerService serviceCustomer = new CustomerService();
	private EmployeService serviceEmploye = new EmployeService();
	private String message = "test message";
	private List<Account> debitAccount = new ArrayList<Account>();
	private List<Account> creditAccount = new ArrayList<Account>();
	private Float amount = 0.0f;

	
	
	// ============= assesseurs ============
	public Customer getCustomer() {
		return customer;
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

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
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


	public String createCustomer(Integer idEmploye) {
		this.customer.setIdEmploye(idEmploye);
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


	public String goUpdateCustomer(Customer customer) {
		System.out.println("-- goUpdateCustomer Méthode --");
		this.customer = customer;
		return "customerModification";
	}

	public String goTransfert(Customer customer) {
		System.out.println("-- goTransfert Méthode --");
		this.customer = customer;
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
