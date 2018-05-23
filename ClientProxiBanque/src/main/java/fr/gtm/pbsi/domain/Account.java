package fr.gtm.pbsi.domain;

public abstract class Account {

	private Integer id;
	private String numberAccount;
	private Boolean isActive;
	private String dateCreation;
	private Float balance;
	
	public Account() {
		super();
		this.id=0;
		this.numberAccount="Inconnu";
		this.dateCreation = "Inconnu";
		this.isActive = false;
		this.balance = 0.0f;
	}
	public Account(Integer id, String numberCompte, Boolean isActive, String dateCreation, Float balance) {
		super();
		this.id = id;
		this.numberAccount = numberCompte;
		this.isActive = isActive;
		this.dateCreation = dateCreation;
		this.balance = balance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumberAccount() {
		return numberAccount;
	}
	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
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
	
	@Override
	public String toString() {
		return " Compte bancaire -- [ numéro : " + getNumberAccount() + ", solde : " + getBalance() + "]";
	}
}
