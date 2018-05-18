package fr.gtm.pbsi.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.gtm.pbsi.domain.Employe;
import fr.gtm.pbsi.service.EmployeService;

@ManagedBean(name = "employebean")
@SessionScoped
public class EmployeBean {
	private Employe employe = new Employe();
	private EmployeService service = new EmployeService();

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public String loginVerification() {
		
		// service.loginVerification(Employe employe);
		String forward = null;
		employe.setTypeFunction(0);
		
		// 0 => gerant; 1 => conseiller
		if (employe.getTypeFunction() == 0) {
			forward = "employeList";
		} else {
			forward = "customerList";
		}

		return forward;

	}
}
