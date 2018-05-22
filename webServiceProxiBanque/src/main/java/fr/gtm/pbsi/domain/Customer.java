package fr.gtm.pbsi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "customer")
public class Customer extends People {

	@Id
	@Column(name = "idCustomer")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;
	private String adress;
	private String zipCode;
	private String city;
	private Integer idEmploye;
	@Transient
	private Employe employe;
	private Integer idCurrentAccount;
	@Transient
	private CurrentAccount currentAccount;
	private Integer idSavingAccount;
	@Transient
	private SavingAccount savingAccount;

	// CONSTRUCTOR
	public Customer() {
		super();
	}

	public Customer(String email, String adress, String zipCode, String city, Integer idEmploye, Employe employe, Integer idCurrentAccount, CurrentAccount currentAccount, Integer idSavingAccount,
			SavingAccount savingAccount) {
		super();
		this.email = email;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.idEmploye = idEmploye;
		this.employe = employe;
		this.idCurrentAccount = idCurrentAccount;
		this.currentAccount = currentAccount;
		this.idSavingAccount = idSavingAccount;
		this.savingAccount = savingAccount;
	}

	public Customer(Integer id, String email, String adress, String zipCode, String city, Integer idEmploye, Employe employe, Integer idCurrentAccount, CurrentAccount currentAccount,
			Integer idSavingAccount, SavingAccount savingAccount) {
		super();
		this.id = id;
		this.email = email;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.idEmploye = idEmploye;
		this.employe = employe;
		this.idCurrentAccount = idCurrentAccount;
		this.currentAccount = currentAccount;
		this.idSavingAccount = idSavingAccount;
		this.savingAccount = savingAccount;
	}

	public Customer(String name, String firstName, Integer idEmploye) {
		super();
		this.setName(name);
		this.setFirstName(firstName);
		this.idEmploye = idEmploye;
	}

	// GETTERS AND SETTERS
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

	public Integer getIdCurrentAccount() {
		return this.idCurrentAccount;
	}

	public void setIdCurrentAccount(Integer idCurrentAccount) {
		this.idCurrentAccount = idCurrentAccount;
	}

	public Integer getIdSavingAccount() {
		return this.idSavingAccount;
	}

	public void setIdSavingAccount(Integer idSavingAccount) {
		this.idSavingAccount = idSavingAccount;
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

	public Employe getEmploye() {
		return this.employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	// toString
	@Override
	public String toString() {
		return "Customer [id=" + this.id + ", email=" + this.email + ", adress=" + this.adress + ", zipCode=" + this.zipCode + ", city=" + this.city + ", idEmploye=" + this.idEmploye + ", employe="
				+ this.employe + ", idCurrentAccount=" + this.idCurrentAccount + ", currentAccount=" + this.currentAccount + ", idSavingAccount=" + this.idSavingAccount + ", savingAccount="
				+ this.savingAccount + "]";
	}

}
