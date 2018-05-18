package fr.gtm.pbsi.domain;

public class Customer extends People{

	private Integer id;
	private String email;
	private String adress;
	private String zipCode;
	private String city;
	private Integer idConseiller;
	private CurrentAccount myCurrentAccount;
	private SavingAccount mySavingAccount;
	
	public Customer() {
		super();
	}
	
	
	public Customer(String name, String firstName) {
		super(name, firstName);
	}


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
	public Integer getIdConseiller() {
		return idConseiller;
	}
	public void setIdConseiller(Integer idConseiller) {
		this.idConseiller = idConseiller;
	}
	public CurrentAccount getMyCurrentAccount() {
		return myCurrentAccount;
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
	
	
}
