package fr.gtm.pbsi.domain;

import java.util.List;

public class Employe extends People {

	private Integer id;
	private String login;
	private String password;
	private Integer typeFunction;
	private List<Customer> listCustomer;

	public Employe() {
		super();
		this.login = "";
		this.password = "";
	}

	public Employe(String name, String firstName, String login, String password, Integer typeFunction) {
		super(name, firstName);
		this.typeFunction = typeFunction;
		this.login = login;
		this.password = password;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTypeFunction() {
		return this.typeFunction;
	}

	public void setTypeFunction(Integer typeFunction) {
		this.typeFunction = typeFunction;
	}

	public List<Customer> getListCustomer() {
		return this.listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	@Override
	public String toString() {
		return "Employe [id=" + this.id + ", login=" + this.login + ", password=" + this.password + ", typeFunction=" + this.typeFunction + ", listCustomer=" + this.listCustomer + "]";
	}

}
