package fr.gtm.pbsi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name ="customer")
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
	@PrimaryKeyJoinColumn(referencedColumnName = "employe_id")
	private Integer idEmploye;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name = "currentAccount_id")
	private CurrentAccount myCurrentAccount;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name = "savingAccount_id")
	private SavingAccount mySavingAccount;
	
	// CONSTRUCTOR
	public Customer() {
		super();
	}
	public Customer(String email, String adress, String zipCode, String city, Integer idConseiller, CurrentAccount myCurrentAccount,
			SavingAccount mySavingAccount) {
		super();
		this.email = email;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.idEmploye = idConseiller;
		this.myCurrentAccount = myCurrentAccount;
		this.mySavingAccount = mySavingAccount;
	}
	public Customer(Integer id, String email, String adress, String zipCode, String city, Integer idConseiller,
			CurrentAccount myCurrentAccount, SavingAccount mySavingAccount) {
		super();
		this.id = id;
		this.email = email;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.idEmploye = idConseiller;
		this.myCurrentAccount = myCurrentAccount;
		this.mySavingAccount = mySavingAccount;
	}
	
	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public CurrentAccount getMyCurrentAccount() {
		return myCurrentAccount;
	}
	public Integer getIdemploye() {
		return idEmploye;
	}
	public void setIdEmploye(Integer idEmploye) {
		this.idEmploye = idEmploye;
	}
	public void setMyCurrentAccount(CurrentAccount myCurrentAccount) {
		this.myCurrentAccount = myCurrentAccount;
	}
	public SavingAccount getMySavingAccount() {
		return mySavingAccount;
	}
	public void setMySavingAccount(SavingAccount mySavingAccount) {
		this.mySavingAccount = mySavingAccount;
	}
	
	// toString
	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", adress=" + adress + ", zipCode=" + zipCode + ", city="
				+ city + ", myCurrentAccount=" + myCurrentAccount + ", mySavingAccount=" + mySavingAccount + "]";
	}
}
