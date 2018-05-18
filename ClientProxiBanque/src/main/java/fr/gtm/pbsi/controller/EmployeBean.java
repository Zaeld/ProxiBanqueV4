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

	//=====================Assesseurs ======================
	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}//===========================================


	public String loginVerification() {
		String forward = null;
		this.employe = serviceEmploye.loginVerification(this.employe);
		//=======================================================
//		this.employe.setTypeFunction(1);
//		this.employe.setId(1);
//		ArrayList<Customer> maList  = new ArrayList<Customer>() ;
//		maList.add(new Customer("toto", "totoFamilly"));
//		maList.add(new Customer("tata", "tataFamilly"));
//		this.employe.setListCustomer(maList);
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
	public String goCustomerListe () {
		this.employe=serviceEmploye.updateEmploye(this.employe);
		return "customerList";
		
	}
}
