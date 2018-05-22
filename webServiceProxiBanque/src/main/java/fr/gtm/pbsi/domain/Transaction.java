package fr.gtm.pbsi.domain;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@Column(name = "idTransaction")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Date date;
	private Integer typeTransaction;
	private Float value;
	
	@ManyToOne
	@JoinColumn(name = "debitAccount_id", referencedColumnName = "idAccount")
	private Account debitAccount ;
	
	@ManyToOne
	@JoinColumn(name = "creditAccount_id", referencedColumnName = "idAccount")
	private Account creditAccount ;
	
	// CONSTRUCTORS
	public Transaction() {
		super();
	}
	public Transaction(Date date, Integer typeTransaction, Float value, Account debitAccount, Account creditAccount) {
		super();
		this.date = date;
		this.typeTransaction = typeTransaction;
		this.value = value;
		this.debitAccount = debitAccount;
		this.creditAccount = creditAccount;
	}
	public Transaction(Integer id, Date date, Integer typeTransaction, Float value, Account debitAccount,
			Account creditAccount) {
		super();
		this.id = id;
		this.date = date;
		this.typeTransaction = typeTransaction;
		this.value = value;
		this.debitAccount = debitAccount;
		this.creditAccount = creditAccount;
	}
	
	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getTypeTransaction() {
		return typeTransaction;
	}
	public void setTypeTransaction(Integer typeTransaction) {
		this.typeTransaction = typeTransaction;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Account getDebitAccount() {
		return debitAccount;
	}
	public void setDebitAccount(Account debitAccount) {
		this.debitAccount = debitAccount;
	}
	public Account getCreditAccount() {
		return creditAccount;
	}
	public void setCreditAccount(Account creditAccount) {
		this.creditAccount = creditAccount;
	}
	
	// toString
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", typeTransaction=" + typeTransaction + ", value=" + value
				+ ", debitAccount=" + debitAccount + ", creditAccount=" + creditAccount + "]";
	}
	
}
