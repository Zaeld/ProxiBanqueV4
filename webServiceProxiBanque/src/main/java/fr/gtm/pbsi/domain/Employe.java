package fr.gtm.pbsi.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employe")
public class Employe extends People{
	
	@Id
	@Column(name = "idEmploye")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String login;
	
	@Column
	private String password;
	
	@Column
	private Integer typeFunction;	// 0 => gerant; 1 => conseiller
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "employe_id", referencedColumnName = "idEmploye")
	private List<Customer> listCustomer;
	
	// CONSTRUCTORS
	public Employe() {
		super();
	}
	public Employe(String login, String password, Integer typeFunction, List<Customer> listCustomer) {
		super();
		this.login = login;
		this.password = password;
		this.typeFunction = typeFunction;
		this.listCustomer = listCustomer;
	}
	public Employe(Integer id, String login, String password, Integer typeFunction, List<Customer> listCustomer) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.typeFunction = typeFunction;
		this.listCustomer = listCustomer;
	}
	
	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getTypeFunction() {
		return typeFunction;
	}
	public void setTypeFunction(Integer typeFunction) {
		this.typeFunction = typeFunction;
	}
	public List<Customer> getListCustomer() {
		return listCustomer;
	}
	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}
	
	// toString
	@Override
	public String toString() {
		return "Employe [id=" + id + ", login=" + login + ", password=" + password + ", typeFunction=" + typeFunction
				+ ", listCustomer=" + listCustomer + "]";
	}
}
