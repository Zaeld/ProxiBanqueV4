package fr.gtm.pbsi.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.Employe;
import fr.gtm.pbsi.service.EmployeService;

@ManagedBean(name = "employebean")
@SessionScoped
public class EmployeBean {
	private Employe employe = new Employe();
	private EmployeService serviceEmploye = new EmployeService();

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public String loginVerification() {
		String forward = null;
System.out.println("début de méthode");
		this.employe=serviceEmploye.loginVerification(this.employe);
		
		// On test si l'employe a été trouvé en base de donnée
		if(employe.getId()>0) {
		
		// 0 => gerant; 1 => conseiller
		if (employe.getTypeFunction() == 0) {
			forward = "employeList";
		} else {
			forward = "customerList";
		}
		}

		return forward;

	}
	
	public List<Customer> showCustomers() {
		List<Customer> customerList = service.findAllCustomers(this.employe);
		return customerList;
	}
}
