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

	@Column
	private String email;
	private String adress;
	private String zipCode;
	private String city;
	private Integer idEmploye;
	@Transient
	private Employe employe;
	private Integer idca;
	@Transient
	private CurrentAccount currentAccount;
	private Integer idsa;
	@Transient
	private SavingAccount savingAccount;

	// CONSTRUCTOR
	public Customer() {
		super();
	}

	public Customer(Integer id, String email, String adress, String zipCode, String city, Integer idEmploye, Employe employe, Integer idca, CurrentAccount currentAccount, Integer idsa,
			SavingAccount savingAccount) {
		super();
		this.id = id;
		this.email = email;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.idEmploye = idEmploye;
		this.employe = employe;
		this.idca = idca;
		this.currentAccount = currentAccount;
		this.idsa = idsa;
		this.savingAccount = savingAccount;
	}

	public Customer(String name, String firstName, Integer idEmploye) {
		super(name, firstName);
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

	public Employe getEmploye() {
		return this.employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Integer getIdca() {
		return this.idca;
	}

	public void setIdca(Integer idca) {
		this.idca = idca;
	}

	public CurrentAccount getCurrentAccount() {
		return this.currentAccount;
	}

	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
	}

	public Integer getIdsa() {
		return this.idsa;
	}

	public void setIdsa(Integer idsa) {
		this.idsa = idsa;
	}

	public SavingAccount getSavingAccount() {
		return this.savingAccount;
	}

	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = savingAccount;
	}

	// toString
	@Override
	public String toString() {
		return "Customer [id=" + this.id + ", email=" + this.email + ", adress=" + this.adress + ", zipCode=" + this.zipCode + ", city=" + this.city + ", idEmploye=" + this.idEmploye + ", employe="
				+ this.employe + ", idca=" + this.idca + ", myCurrentAccount=" + this.currentAccount + ", idsa=" + this.idsa + ", mySavingAccount=" + this.savingAccount + "]";
	}

}
