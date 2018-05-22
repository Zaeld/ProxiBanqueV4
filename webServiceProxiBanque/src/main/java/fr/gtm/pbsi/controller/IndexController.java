package fr.gtm.pbsi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import fr.gtm.pbsi.domain.CurrentAccount;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.Employe;
import fr.gtm.pbsi.domain.SavingAccount;

@Controller
public class IndexController {

	private final RestTemplate rt = new RestTemplate();

	private final String urlCreateEmploye = "http://localhost:8080/webServiceProxiBanque/employe/";
	private final String urlCreateCustomer = "http://localhost:8080/webServiceProxiBanque/customer/";
	// private final String urlCreateCA =
	// "http://localhost:8080/webServiceProxiBanque/account/currentAccount";
	// private final String urlCreateSA =
	// "http://localhost:8080/webServiceProxiBanque/account/savingAccount";

	@RequestMapping(path = "/welcome", method = RequestMethod.GET)
	public ModelAndView displayIndex() {
		final ModelAndView mav = new ModelAndView("welcome");

		 this.createBoss();
		 this.createConseiller();
		 this.createCustomerToConseiller1();

		return mav;
	}

	private void createBoss() {
		final Employe boss = new Employe("Cousin", "Olivier", "admin", "admin", 0, null);
		this.rt.postForObject(this.urlCreateEmploye, boss, Employe.class);
	}

	private void createConseiller() {
		final Employe conseiller1 = new Employe("Fournier", "Pascal", "c1", "c1", 1, null);
		this.rt.postForObject(this.urlCreateEmploye, conseiller1, Employe.class);

		final Employe conseiller2 = new Employe("Martin", "Laurine", "c2", "c2", 1, null);
		this.rt.postForObject(this.urlCreateEmploye, conseiller2, Employe.class);
		final Employe conseiller3 = new Employe("Delcroix", "Sarah", "c3", "c3", 1, null);
		this.rt.postForObject(this.urlCreateEmploye, conseiller3, Employe.class);
		final Employe conseiller4 = new Employe("Durand", "Guillaume", "c4", "c4", 1, null);
		this.rt.postForObject(this.urlCreateEmploye, conseiller4, Employe.class);
		final Employe conseiller5 = new Employe("Arnaud", "Claire", "c5", "c5", 1, null);
		this.rt.postForObject(this.urlCreateEmploye, conseiller5, Employe.class);
	}

	private void createCustomerToConseiller1() {
		final boolean active = false;
		final String number = "test";
		final String date = "21-05-2018";
		final float solde = 0;
		final float dec = 0;
		final float taux = 3;
		final CurrentAccount ca = new CurrentAccount(dec);
		ca.setBalance(solde);
		ca.setDateCreation(date);
		ca.setIsActive(active);
		// ca.setNumberCompte(number);
		ca.setNumberAccount(number);
		final SavingAccount sa = new SavingAccount(taux);
		sa.setBalance(solde);
		sa.setDateCreation(date);
		sa.setIsActive(active);
		// sa.setNumberCompte(number);
		sa.setNumberAccount(number);
		System.out.println(ca);
		System.out.println(sa);

		// final CurrentAccount ca1 = this.rt.postForObject(this.urlCreateCA, ca,
		// CurrentAccount.class);
		// final SavingAccount sa1 = this.rt.postForObject(this.urlCreateSA, sa,
		// SavingAccount.class);
		final Customer c1 = new Customer("Bouvier", "Ludivine", 2);
		// final CurrentAccount ca2 = this.rt.postForObject(this.urlCreateCA, ca,
		// CurrentAccount.class);
		// final SavingAccount sa2 = this.rt.postForObject(this.urlCreateSA, sa,
		// SavingAccount.class);
		final Customer c2 = new Customer("Le Guen", "Rébecca", 2);
		// final CurrentAccount ca3 = this.rt.postForObject(this.urlCreateCA, ca,
		// CurrentAccount.class);
		// final SavingAccount sa3 = this.rt.postForObject(this.urlCreateSA, sa,
		// SavingAccount.class);
		final Customer c3 = new Customer("Lebret", "Sylvain", 2);
		// final CurrentAccount ca4 = this.rt.postForObject(this.urlCreateCA, ca,
		// CurrentAccount.class);
		// final SavingAccount sa4 = this.rt.postForObject(this.urlCreateSA, sa,
		// SavingAccount.class);
		final Customer c4 = new Customer("Corbin", "Remy", 2);
		// final CurrentAccount ca5 = this.rt.postForObject(this.urlCreateCA, ca,
		// CurrentAccount.class);
		// final SavingAccount sa5 = this.rt.postForObject(this.urlCreateSA, sa,
		// SavingAccount.class);
		final Customer c5 = new Customer("Besson", "Alphonse", 2);
		this.rt.postForObject(this.urlCreateCustomer, c1, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c2, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c3, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c4, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c5, Customer.class);
	}

}
