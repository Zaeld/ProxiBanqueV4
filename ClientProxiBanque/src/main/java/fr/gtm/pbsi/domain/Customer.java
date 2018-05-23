package fr.gtm.pbsi.domain;

public class Customer extends People {

	private Integer id;
	private String email;
	private String adress;
	private String zipCode;
	private String city;
	private Integer idEmploye;
	private Integer idca;
	private Integer idsa;
	private CurrentAccount currentAccount;
	private SavingAccount savingAccount;

	public Customer() {
		super("", "");
		this.id = 0;
		this.email = null;
		this.adress = "";
		this.city = "";
		this.zipCode = "";
		this.idEmploye = 0;
	}

	public Customer(String name, String firstName) {
		super(name, firstName);
		this.id = 0;
		this.email = "Inconnu";
		this.adress = "Inconnu";
		this.city = "Inconnu";
		this.zipCode = "Inconnu";
		this.idEmploye = 0;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getIdEmploye() {
		return this.idEmploye;
	}

	public void setIdEmploye(Integer idEmploye) {
		this.idEmploye = idEmploye;
	}

	public CurrentAccount getCurrentAccount() {
		return this.currentAccount;
	}

	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
	}

	public SavingAccount getSavingAccount() {
		return this.savingAccount;
	}

	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = savingAccount;
	}

	public Integer getIdsa() {
		return this.idsa;
	}

	public void setIdsa(Integer idsa) {
		this.idsa = idsa;
	}

	public Integer getIdca() {
		return this.idca;
	}

	public void setIdca(Integer idca) {
		this.idca = idca;
	}

	@Override
	public String toString() {
		return "Customer [id=" + this.id + ", email=" + this.email + ", adress=" + this.adress + ", zipCode=" + this.zipCode + ", city=" + this.city + ", idEmploye=" + this.idEmploye + ", idca="
				+ this.idca + ", idsa=" + this.idsa + ", myCurrentAccount=" + this.currentAccount + ", mySavingAccount=" + this.savingAccount + ", getName()=" + this.getName() + ", getFirstName()="
				+ this.getFirstName() + "]";
	}
}
