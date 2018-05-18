package fr.gtm.pbsi.domain;

public class SavingAccount extends Account{
	private Float rate;

	public SavingAccount() {
		super();
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
	
	
	
	
}

