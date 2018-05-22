package fr.gtm.pbsi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.Employe;
import fr.gtm.pbsi.service.EmployeService;

@ManagedBean(name = "employebean")
@SessionScoped
public class EmployeBean {
	// Déclaration des variables locales
	private Employe employe = new Employe();
	private EmployeService serviceEmploye = new EmployeService();
	private Float savingAccountLimit = 0.0f;
	private String message = null;
	private List<Employe> advisorList = new ArrayList<Employe>();
	private List<Customer> customerListOfAdvisor = new ArrayList<Customer>();

	// =====================Assesseurs ======================

	public Employe getEmploye() {
		return employe;
	}

	public List<Customer> getCustomerListOfAdvisor() {
		return customerListOfAdvisor;
	}

	public void setCustomerListOfAdvisor(List<Customer> customerListOfAdvisor) {
		this.customerListOfAdvisor = customerListOfAdvisor;
	}

	public List<Employe> getAdvisorList() {
		return advisorList;
	}

	public void setAdvisorList(List<Employe> advisorList) {
		this.advisorList = advisorList;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Float getSavingAccountLimit() {
		return savingAccountLimit;
	}

	public void setSavingAccountLimit(Float savingAccountLimit) {
		this.savingAccountLimit = savingAccountLimit;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} // ===========================================

	/**
	 * Méthode permettant à un employé de s'identifier sur la page de connexion du
	 * site et de récupérer la liste des clients ou des conseillers selon le type de
	 * fonction de l'employé connecté
	 * 
	 * @return String : Retoune la page d'acceuil correspondant au type de fonction
	 *         de l'employé connecté ou vers la page d'erreur si la connexion a échouée
	 */
	public String loginVerification() {
		String forward = null;
		System.out.println("employe envoyé :" + employe);
		this.employe = serviceEmploye.loginVerification(this.employe);
		// =======================================================
		// this.employe.setTypeFunction(1);
		// this.employe.setId(1);
		// ArrayList<Customer> maList = new ArrayList<Customer>() ;
		// Customer cust1 = new Customer( "totoFamilly", "toto");
		// cust1.setMyCurrentAccount(new CurrentAccount());
		// cust1.setMySavingAccount(new SavingAccount());
		// Customer cust2 = (new Customer("tataFamilly", "tata"));
		// CurrentAccount accountBad = new CurrentAccount();
		// accountBad.setIsActive(true);
		// accountBad.setBalance(-100.0f);
		// cust2.setMyCurrentAccount(accountBad);
		// cust2.setMySavingAccount(new SavingAccount());
		// maList.add(cust1);
		// maList.add(cust2);
		// this.employe.setListCustomer(maList);
		// System.out.println("cust 1 : " + cust1 );
		// System.out.println("cust 2 : " + cust2);
		// =======================================================
		System.out.println("employe reçu :" + employe);
		// On test si l'employe a été trouvé en base de donnée
		if (employe.getId() > 0) {

			// 0 => gerant; 1 => conseiller
			if (employe.getTypeFunction() == 0) {
				this.advisorList = serviceEmploye.getAllAdvisor();
				forward = "employeList";
			} else {
				this.customerListOfAdvisor = serviceEmploye.getAllCustomerOfAdvisor(employe);
				forward = "customerList";
			}
			return forward;
		}
		return "homeLoginBad";
	}

	/** Méthode permettant de retourner à la page de Login et d'identifier un nouvel employé
	 * @return String : page de redirection
	 */
	public String logout() {
		this.employe = new Employe();
		return "homeLogin";
	}

	/** Méthode appelée lors d'une redirection vers la liste de client du conseiller permettant sa mise à jour
	 * @return String : page contenant la liste des clients
	 */
	public String goCustomerListe() {
		System.out.println("-- goCustomerListe methode --");
		this.employe = serviceEmploye.updateEmploye(this.employe);
		return "customerList";
	}

}
