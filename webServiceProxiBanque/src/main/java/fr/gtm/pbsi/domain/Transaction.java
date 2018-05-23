package fr.gtm.pbsi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@Column(name = "idTransaction")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String date;
	private Integer typeTransaction; // 1 => debit, 2 => credit, 3 => virement CaC
	private Float value;
	private Integer iddebitAccount;
	private Integer idcreditAccount;
	@Transient
	private Account debitAccount;
	@Transient
	private Account creditAccount;

	// CONSTRUCTORS
	public Transaction() {
		super();
	}

	public Transaction(String date, Integer typeTransaction, Float value, Integer iddebitAccount, Account debitAccount, Integer idcreditAccount, Account creditAccount) {
		super();
		this.date = date;
		this.typeTransaction = typeTransaction;
		this.value = value;
		this.iddebitAccount = iddebitAccount;
		this.debitAccount = debitAccount;
		this.idcreditAccount = idcreditAccount;
		this.creditAccount = creditAccount;
	}

	public Transaction(String date, Integer typeTransaction, Float value, Integer iddebitAccount, Integer idcreditAccount) {
		super();
		this.date = date;
		this.typeTransaction = typeTransaction;
		this.value = value;
		this.iddebitAccount = iddebitAccount;
		this.idcreditAccount = idcreditAccount;
	}

	public Transaction(Integer id, String date, Integer typeTransaction, Float value, Integer iddebitAccount, Account debitAccount, Integer idcreditAccount, Account creditAccount) {
		super();
		this.id = id;
		this.date = date;
		this.typeTransaction = typeTransaction;
		this.value = value;
		this.iddebitAccount = iddebitAccount;
		this.debitAccount = debitAccount;
		this.idcreditAccount = idcreditAccount;
		this.creditAccount = creditAccount;
	}

	// GETTERS AND SETTERS
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date2) {
		this.date = date2;
	}

	public Integer getTypeTransaction() {
		return this.typeTransaction;
	}

	public void setTypeTransaction(Integer typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public Float getValue() {
		return this.value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Account getDebitAccount() {
		return this.debitAccount;
	}

	public void setDebitAccount(Account debitAccount) {
		this.debitAccount = debitAccount;
	}

	public Account getCreditAccount() {
		return this.creditAccount;
	}

	public void setCreditAccount(Account creditAccount) {
		this.creditAccount = creditAccount;
	}

	public Integer getIddebitAccount() {
		return this.iddebitAccount;
	}

	public void setIddebitAccount(Integer iddebitAccount) {
		this.iddebitAccount = iddebitAccount;
	}

	public Integer getIdcreditAccount() {
		return this.idcreditAccount;
	}

	public void setIdcreditAccount(Integer idcreditAccount) {
		this.idcreditAccount = idcreditAccount;
	}

	// toString
	@Override
	public String toString() {
		return "Transaction [id=" + this.id + ", date=" + this.date + ", typeTransaction=" + this.typeTransaction + ", value=" + this.value + ", iddebitAccount=" + this.iddebitAccount
				+ ", debitAccount=" + this.debitAccount + ", idcreditAccount=" + this.idcreditAccount + ", creditAccount=" + this.creditAccount + "]";
	}

}
