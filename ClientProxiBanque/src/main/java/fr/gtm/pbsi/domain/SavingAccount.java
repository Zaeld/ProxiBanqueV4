package fr.gtm.pbsi.domain;

import fr.gtm.pbsi.service.numberGenerator;

public class SavingAccount extends Account{
	private Float rate;

	public SavingAccount() {
		super();
		this.setNumberAccount("S" + numberGenerator.generate(15));
		this.rate = 0.0f;
	}

	public SavingAccount(Integer id, String numberCompte, Boolean isActive, String dateCreation, Float balance) {
		super(id, numberCompte, isActive, dateCreation, balance);
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return " Compte épargne -- [ numéro : " + getNumberAccount() + ", solde : " + getBalance() + "]";
	}
	
	
	
	
}

