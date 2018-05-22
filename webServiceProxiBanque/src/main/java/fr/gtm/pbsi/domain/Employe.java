package fr.gtm.pbsi.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "employe")
public class Employe extends People {

	@Id
	@Column(name = "idEmploye")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String login;
	private String password;
	private Integer typeFunction; // 0 => gerant; 1 => conseiller
	@Transient
	private Collection<Customer> listCustomer;
	
	private Integer numberCustommers;

	// ================ CONSTRUCTORS =====================
	public Employe() {
		super();
	}

	public Employe(String login, String password, Integer typeFunction, Collection<Customer> listCustomer) {
		super();
		this.login = login;
		this.password = password;
		this.typeFunction = typeFunction;
		this.listCustomer = listCustomer;
	}

	public Employe(Integer id, String login, String password, Integer typeFunction, Collection<Customer> listCustomer) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.typeFunction = typeFunction;
		this.listCustomer = listCustomer;
	}

	public Employe(String name, String firstName, String login, String password, Integer typeFunction, Collection<Customer> listCustomer) {
		this.setName(name);
		this.setFirstName(firstName);
		this.login = login;
		this.password = password;
		this.typeFunction = typeFunction;
		this.listCustomer = listCustomer;
	}

	// GETTERS AND SETTERS
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

	public Collection<Customer> getListCustomer() {
		return this.listCustomer;
	}

	public void setListCustomer(Collection<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}
	
	public Integer getNumberCustommers() {
		return numberCustommers;
	}

	public void setNumberCustommers(Integer numberCustommers) {
		this.numberCustommers = numberCustommers;
	}

	// toString
	@Override
	public String toString() {
		return "Employe [id=" + this.id + ", login=" + this.login + ", password=" + this.password + ", typeFunction=" + this.typeFunction + ", listCustomer=" + this.listCustomer + "]";
	}
}
