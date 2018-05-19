package fr.gtm.pbsi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.gtm.pbsi.domain.CurrentAccount;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.Employe;
import fr.gtm.pbsi.domain.SavingAccount;
import fr.gtm.pbsi.service.EmployeService;

@ManagedBean(name = "employebean")
@SessionScoped
public class EmployeBean {
	// Déclaration des variables locales
	private Employe employe = new Employe();
	private EmployeService serviceEmploye = new EmployeService();
	private Float savingAccountLimit = 0.0f; 
	private String message = null;
	
	//=====================Assesseurs ======================
	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
		
	}
	
	public Float getSavingAccountLimit() {
		return savingAccountLimit;
	}

	public void setSavingAccountLimit(Float savingAccountLimit) {
		this.savingAccountLimit = savingAccountLimit;
	}	//===========================================


	public String loginVerification() {
		String forward = null;
		//this.employe = serviceEmploye.loginVerification(this.employe);
		//=======================================================
		this.employe.setTypeFunction(1);
		this.employe.setId(1);
		ArrayList<Customer> maList  = new ArrayList<Customer>() ;
		Customer cust1 = new Customer("toto", "totoFamilly");
		cust1.setMyCurrentAccount(new CurrentAccount());
		cust1.setMySavingAccount(new SavingAccount());
		Customer cust2 = (new Customer("tata", "tataFamilly"));
		CurrentAccount accountBad = new CurrentAccount();
		accountBad.setBalance(0.0f);
		cust2.setMyCurrentAccount(accountBad);
		cust2.setMySavingAccount(new SavingAccount());
		maList.add(cust1);
		maList.add(cust2);
		this.employe.setListCustomer(maList);
		System.out.println("cust 1 : " + cust1 );
		System.out.println("cust 2 : " + cust2);
		//=======================================================

		// On test si l'employe a été trouvé en base de donnée
		if (employe.getId() > 0) {
			
			// 0 => gerant; 1 => conseiller
			if (employe.getTypeFunction() == 0) {
				forward = "employeList";
			} else {
				forward = "customerList";
			}
			return forward;
		}
			return "homeLoginBad";
	}
	
	public String logout () {
		this.employe = new Employe();
		return "homeLogin";
	}
	
	public String goCustomerListe () {
		this.employe=serviceEmploye.updateEmploye(this.employe);
		return "customerList";
	}
}
