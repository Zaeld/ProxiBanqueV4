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
@DiscriminatorColumn(name = "typeAccount", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

	@Id
	@Column(name = "idAccount")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	protected String numberAccount;
	protected String dateCreation;
	protected Boolean isActive;
	protected Float balance;

	// CONSTRUCTORS
	public Account() {
		super();
	}

	public Account(String numberAccount, Boolean isActive, String dateCreation, Float balance) {
		this.numberAccount = numberAccount;
		this.isActive = isActive;
		this.dateCreation = dateCreation;
		this.balance = balance;
	}

	public Account(Integer id, String numberAccount, Boolean isActive, String dateCreation, Float balance) {
		this.id = id;
		this.numberAccount = numberAccount;
		this.isActive = isActive;
		this.dateCreation = dateCreation;
		this.balance = balance;
	}

	// GETTERS AND SETTERS
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumberAccount() {
		return this.numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Float getBalance() {
		return this.balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	// toString
	@Override
	public String toString() {
		return "Account [id=" + this.id + ", numberCompte=" + this.numberAccount + ", isActive=" + this.isActive + ", dateCreation=" + this.dateCreation + ", balance=" + this.balance + "]";
	}
}
