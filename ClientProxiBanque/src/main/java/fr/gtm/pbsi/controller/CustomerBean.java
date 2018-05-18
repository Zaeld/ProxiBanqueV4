package fr.gtm.pbsi.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.service.CustomerService;

@ManagedBean(name = "customerbean")
@SessionScoped
public class CustomerBean {
	private Customer customer = new Customer();
	private CustomerService serviceCustomer = new CustomerService();

	public CustomerBean() {
		super();
	}

	public CustomerBean(Customer customer) {
		super();
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
public String createCustomer() {
	String forward = null;
//	this.customer=serviceCustomer.createCustomer(this.customer);
	if(customer.getId()>0) 
		forward="";
		else forward="";
		return forward;
	}
}

