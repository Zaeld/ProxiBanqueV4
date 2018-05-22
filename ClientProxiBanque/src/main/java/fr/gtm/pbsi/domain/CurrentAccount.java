package fr.gtm.pbsi.domain;

import fr.gtm.pbsi.service.numberGenerator;

public class CurrentAccount extends Account{

	private Float overdraft;

	public CurrentAccount() {
		super();
		this.overdraft=0.0f;
		this.setNumberAccount("C" + numberGenerator.generate(15));
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

	@Override
	public String toString() {
		return "CurrentAccount [overdraft=" + overdraft + ", getId()=" + getId() + ", getIsActive()=" + getIsActive()
				+ ", getBalance()=" + getBalance() + "]";
	}

}
