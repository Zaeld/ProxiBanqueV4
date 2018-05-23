package fr.gtm.pbsi.domain;

public class Transaction {

	private Integer id;

	private String date;
	private Integer typeTransaction;
	private Float value;
	private Integer iddebitAccount;
	private Integer idcreditAccount;
	private Account debitAccount;
	private Account creditAccount;

	// CONSTRUCTORS
	public Transaction() {
		super();
	}

	public Transaction(Float value, Integer iddebitAccount, Integer idcreditAccount) {
		super();
		this.value = value;
		this.iddebitAccount = iddebitAccount;
		this.idcreditAccount = idcreditAccount;
	}

	public Transaction(String date, Integer typeTransaction, Float value, Account debitAccount, Account creditAccount) {
		super();
		this.date = date;
		this.typeTransaction = typeTransaction;
		this.value = value;
		this.debitAccount = debitAccount;
		this.creditAccount = creditAccount;
	}

	public Transaction(Integer id, String date, Integer typeTransaction, Float value, Account debitAccount, Account creditAccount) {
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
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
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
		return "Transaction [id=" + this.id + ", date=" + this.date + ", typeTransaction=" + this.typeTransaction + ", value=" + this.value + ", debitAccount=" + this.debitAccount + ", creditAccount="
				+ this.creditAccount + "]";
	}
}
