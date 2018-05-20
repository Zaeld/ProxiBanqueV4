package fr.gtm.pbsi.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeAccount",discriminatorType = DiscriminatorType.STRING)
public abstract class Account {
	
	@Id
	@Column(name = "idAccount")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String numberCompte;
	
	@Column
	private Boolean isActive;
	
	@Column
	private String dateCreation;
	
	@Column
	private Float balance;
	
	// CONSTRUCTORS
	public Account() {
		super();
	}
	public Account(String numberCompte, Boolean isActive, String dateCreation, Float balance) {
		super();
		this.numberCompte = numberCompte;
		this.isActive = isActive;
		this.dateCreation = dateCreation;
		this.balance = balance;
	}
	public Account(Integer id, String numberCompte, Boolean isActive, String dateCreation, Float balance) {
		super();
		this.id = id;
		this.numberCompte = numberCompte;
		this.isActive = isActive;
		this.dateCreation = dateCreation;
		this.balance = balance;
	}
	
	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumberCompte() {
		return numberCompte;
	}
	public void setNumberCompte(String numberCompte) {
		this.numberCompte = numberCompte;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	
	// toString
	@Override
	public String toString() {
		return "Account [id=" + id + ", numberCompte=" + numberCompte + ", isActive=" + isActive + ", dateCreation="
				+ dateCreation + ", balance=" + balance + "]";
	}
}
