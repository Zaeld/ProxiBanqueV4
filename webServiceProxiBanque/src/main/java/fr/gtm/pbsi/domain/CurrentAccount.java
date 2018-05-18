package fr.gtm.pbsi.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("currentAccount")
public class CurrentAccount extends Account {
	
	@Column
	private Float overdraft;
	
	// CONSTRUCTOR
	public CurrentAccount() {
		super();
	}
	public CurrentAccount(Float overdraft) {
		super();
		this.overdraft = overdraft;
	}
	
	// GETTERS AND SETTERS
	public Float getOverdraft() {
		return overdraft;
	}
	public void setOverdraft(Float overdraft) {
		this.overdraft = overdraft;
	}
	
	// toString
	@Override
	public String toString() {
		return "CurrentAccount [overdraft=" + overdraft + "]";
	}
}
