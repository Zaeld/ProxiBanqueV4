package fr.gtm.pbsi.domain;

public abstract class Account {

	private Integer id;
	private String numberCompte;
	private Boolean isActive;
	private String dateCreation;
	private Float balance;
	
	public Account() {
		super();
	}
	public Account(Integer id, String numberCompte, Boolean isActive, String dateCreation, Float balance) {
		super();
		this.id = id;
		this.numberCompte = numberCompte;
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
	
	
}
