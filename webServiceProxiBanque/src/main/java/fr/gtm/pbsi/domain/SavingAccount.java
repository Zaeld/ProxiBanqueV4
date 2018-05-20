package fr.gtm.pbsi.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("savingAccount")
public class SavingAccount extends Account {
	
	@Column
	private Float rate;
	
	// CONSTRUCTOR
	public SavingAccount() {
		super();
	}
	public SavingAccount(Float rate) {
		super();
		this.rate = rate;
	}
	
	// GETTERS AND SETTERS
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	
	// toString
	@Override
	public String toString() {
		return "SavingAccount [rate=" + rate + "]";
	}
}
