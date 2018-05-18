package fr.gtm.pbsi.domain;

public class CurrentAccount extends Account{

	private Float overdraft;

	public CurrentAccount() {
		super();
	}

	public CurrentAccount(Integer id, String numberCompte, Boolean isActive, String dateCreation, Float balance) {
		super(id, numberCompte, isActive, dateCreation, balance);
	}

	public Float getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(Float overdraft) {
		this.overdraft = overdraft;
	}
	
	
}
