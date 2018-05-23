package fr.gtm.pbsi.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import fr.gtm.pbsi.domain.CurrentAccount;
import fr.gtm.pbsi.domain.Customer;
import fr.gtm.pbsi.domain.Employe;
import fr.gtm.pbsi.domain.Transaction;

@Controller
public class IndexController {

	private final RestTemplate rt = new RestTemplate();

	private final String urlCreateEmploye = "http://localhost:8080/webServiceProxiBanque/employe/";
	private final String urlCreateCustomer = "http://localhost:8080/webServiceProxiBanque/customer/";
	private final String urlUpdateAccount = "http://localhost:8080/webServiceProxiBanque/account/";
	// private final String urlCreateSA =
	// "http://localhost:8080/webServiceProxiBanque/account/savingAccount";

	@RequestMapping(path = "/welcome", method = RequestMethod.GET)
	public ModelAndView displayIndex() {
		final ModelAndView mav = new ModelAndView("welcome");

		Employe emp = new Employe();
		emp = this.rt.getForObject(this.urlCreateEmploye + "1", Employe.class);

		if (emp.getId() == 0) {
			this.createBoss();
			this.createConseiller();
			this.createCustomersToEmployes();
			// this.updateAccountToActive();
			this.createTransaction();
		}
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

	private void createCustomersToEmployes() {
		final Customer c1 = new Customer("Bouvier", "Ludivine", "lud.bouvier@test.com", "55, rue Banaudon", "69001", "LYON", 2);
		final Customer c2 = new Customer("Le Guen", "Rébecca", "reb.leguen@test.com", "89, rue Marguerite", "69100", "VILLEURBANNE", 2);
		final Customer c3 = new Customer("Lebret", "Sylvain", "syl.lebret@test.com", "32, rue Pierre Motte", "69110", "SAINTE-FOY-LÈS-LYON", 2);
		final Customer c4 = new Customer("Corbin", "Remy", "remycorbin@test.com", "54, rue Pierre Motte", "69110", "SAINTE-FOY-LÈS-LYON", 2);
		final Customer c5 = new Customer("Besson", "Alphonse", "al.besson@test.com", "65, rue de la République", "69003", "LYON", 2);
		final Customer c6 = new Customer("Tang", "Daniel", "dtang@test.com", "15, rue Marguerite", "69100", "VILLEURBANNE", 3);
		final Customer c7 = new Customer("Beranger", "Juliette", "julietteberanger@test.com", "51, rue Marguerite", "69100", "VILLEURBANNE", 3);
		final Customer c8 = new Customer("Bernard", "Édouard", "ebernard@test.com", "85, Rue Marie De Médicis", "69300", "CALUIRE-ET-CUIRE", 3);
		final Customer c9 = new Customer("Delattre", "Roger", "rogerdelattre@test.com", "87, rue Grande Fusterie", "69500", "BRON", 4);
		final Customer c10 = new Customer("Lucas", "Laure", "laurelucas@test.com", "65, rue Banaudon", "69009", "LYON", 4);
		final Customer c11 = new Customer("Hery", "Vladimir", "vhery@test.com", "8, rue Marguerite", "69100", "VILLEURBANNE", 4);
		final Customer c12 = new Customer("Dumoulin", "Nicolas", "nicdumoulin@test.com", "1, rue Pierre Motte", "69110", "SAINTE-FOY-LÈS-LYON", 4);
		final Customer c13 = new Customer("Durand", "Nicole", "nicoledurand@test.com", "76, Cours Marechal-Joffre", "69150", "DÉCINES-CHARPIEU", 4);
		final Customer c14 = new Customer("Parmentier", "Mireille ", "mireilleparmentier@test.com", "24, rue Banaudon", "69007", "LYON", 4);
		final Customer c15 = new Customer("Courty", "Nathalie ", "ncourty@test.com", "45, rue Gustave Eiffel", "69140", "RILLIEUX-LA-PAPE", 4);
		final Customer c16 = new Customer("Aubry", "Romain ", "romainaubry@test.com", "1, Rue Marie De Médicis", "69300", "CALUIRE-ET-CUIRE", 5);
		final Customer c17 = new Customer("Lorenzo", "Hélène", "hlorenzo@test.com", "14, rue de la Hulotais", "69800", "SAINT-PRIEST", 5);
		final Customer c18 = new Customer("Cohen", "Caroline ", "carocohen@test.com", "76, boulevard Amiral Courbet", "69600", "OULLINS", 5);
		final Customer c19 = new Customer("Crepin", "Madeleine ", "madeleinecrepin@test.com", "85, rue de la Hulotais", "69800", "SAINT-PRIEST", 5);
		final Customer c20 = new Customer("Prat", "Véronique ", "veroprat@test.com", "30, rue Banaudon", "69009", "LYON", 5);
		this.rt.postForObject(this.urlCreateCustomer, c1, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c2, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c3, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c4, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c5, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c6, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c7, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c8, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c9, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c10, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c11, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c12, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c13, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c14, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c15, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c16, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c17, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c18, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c19, Customer.class);
		this.rt.postForObject(this.urlCreateCustomer, c20, Customer.class);
	}

	private void updateAccountToActive() {
		Customer cus = new Customer();
		CurrentAccount ca = new CurrentAccount();
		SavingAccount sa = new SavingAccount();
		// customer 1
		cus = this.rt.getForObject(this.urlCreateCustomer + "1", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(577f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(11000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 2
		cus = this.rt.getForObject(this.urlCreateCustomer + "2", Customer.class);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(50000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 3
		cus = this.rt.getForObject(this.urlCreateCustomer + "3", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(-58f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(3000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 4
		cus = this.rt.getForObject(this.urlCreateCustomer + "4", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(-500f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		// customer 5
		cus = this.rt.getForObject(this.urlCreateCustomer + "5", Customer.class);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(48000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 6
		cus = this.rt.getForObject(this.urlCreateCustomer + "6", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(321f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		// customer 7
		cus = this.rt.getForObject(this.urlCreateCustomer + "7", Customer.class);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(7000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 8
		cus = this.rt.getForObject(this.urlCreateCustomer + "8", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(100f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(5000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 9
		cus = this.rt.getForObject(this.urlCreateCustomer + "9", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(984f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(152000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 10
		cus = this.rt.getForObject(this.urlCreateCustomer + "10", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(-502f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		// customer 11
		cus = this.rt.getForObject(this.urlCreateCustomer + "11", Customer.class);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(1000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 12
		cus = this.rt.getForObject(this.urlCreateCustomer + "12", Customer.class);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(9000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 13
		cus = this.rt.getForObject(this.urlCreateCustomer + "13", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(3000f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(2000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 14
		cus = this.rt.getForObject(this.urlCreateCustomer + "14", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(-600f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		// customer 15
		cus = this.rt.getForObject(this.urlCreateCustomer + "15", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(2f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(5000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 16
		cus = this.rt.getForObject(this.urlCreateCustomer + "16", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(31f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		// customer 17
		cus = this.rt.getForObject(this.urlCreateCustomer + "17", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(541f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(6200f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 18
		cus = this.rt.getForObject(this.urlCreateCustomer + "18", Customer.class);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		sa.setBalance(42000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 19
		cus = this.rt.getForObject(this.urlCreateCustomer + "19", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(800f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);
		sa = cus.getSavingAccount();
		sa.setIsActive(true);
		ca.setBalance(2000f);
		this.rt.put(this.urlUpdateAccount + "updatesavingaccount/" + sa.getId(), sa);
		// customer 20
		cus = this.rt.getForObject(this.urlCreateCustomer + "20", Customer.class);
		ca = cus.getCurrentAccount();
		ca.setIsActive(true);
		ca.setBalance(951f);
		this.rt.put(this.urlUpdateAccount + "updatecurrentaccount/" + ca.getId(), ca);

	}

	private void createTransaction() {
		@SuppressWarnings("deprecation")
		final Date d1 = new Date(14, 02, 24);
		@SuppressWarnings("deprecation")
		final Date d2 = new Date(17, 10, 10);
		@SuppressWarnings("deprecation")
		final Date d3 = new Date(18, 01, 05);
		@SuppressWarnings("deprecation")
		final Date d4 = new Date(18, 04, 27);
		@SuppressWarnings("deprecation")
		final Date d5 = new Date(18, 03, 11);

		final Transaction t1 = new Transaction(d1, 3, 500f, 1, 3);
		final Transaction t2 = new Transaction(d2, 3, 1000f, 5, 2);
		final Transaction t3 = new Transaction(d3, 3, 387f, 3, 2);
		final Transaction t4 = new Transaction(d4, 3, 2145f, 4, 6);
		final Transaction t5 = new Transaction(d5, 3, 974f, 1, 7);

		System.out.println(t1);

		this.rt.postForObject("http://localhost:8080/webServiceProxiBanque/transaction/", t1, Transaction.class);
		this.rt.postForObject("http://localhost:8080/webServiceProxiBanque/transaction/", t2, Transaction.class);
		this.rt.postForObject("http://localhost:8080/webServiceProxiBanque/transaction/", t3, Transaction.class);
		this.rt.postForObject("http://localhost:8080/webServiceProxiBanque/transaction/", t4, Transaction.class);
		this.rt.postForObject("http://localhost:8080/webServiceProxiBanque/transaction/", t5, Transaction.class);
	}

}
