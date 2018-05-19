package JUnit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import fr.gtm.pbsi.domain.Employe;
import fr.gtm.pbsi.service.EmployeService;

import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class LoginVerificationTest {

	// Declaration
	private static EmployeService serviceEmploye = new EmployeService();
	private static Employe advisor = new Employe("Paul", "Robert", "lapatate", "147852", 1);
	private static Employe gerand = new Employe("Jean", "Patrick", "trety", "258741", 0);
	private Employe employeTest = new Employe();
	private String login;
	private String password;
	private String nameResult;
	private Integer typeFunctionResult;
	private Integer idResult;

	public LoginVerificationTest(String login, String password, String nameResult, Integer typeFunctionResult,
			Integer idResult) {
		super();
		this.login = login;
		this.password = password;
		this.nameResult = nameResult;
		this.typeFunctionResult = typeFunctionResult;
		this.idResult = idResult;
	}

	@Parameters
	static public List<Object[]> getParameters() {
		Object[][] parameters = {
				// On test que si le login et le password ne sont pas connus en bdd, l'id de
				// l'employé retourné est bien de 0
				{ "mauvaisLogin", "mauvaisPassword", null, null, 0 },

				// On test que si le login et le password correspondent à un conseiller, le
				// conseiller est bien récupéré depuis la bdd
				{ "lapatate", "147852", "Paul", 1, 1 },

				// On test que si le login et le password correspondent à un gerand, le gerand
				// est bien récupéré depuis la bdd
				{ "trety", "258741", "Jean", 0, 2 } };
		return Arrays.asList(parameters);
	}

	@BeforeClass
	public static void CreationEmploye() {
		// Creation des objets en base de donnee au debut des tests
		serviceEmploye.createEmploye(advisor);
		serviceEmploye.createEmploye(gerand);
	}

	@Before
	public void before() {
		// Affectation des attributs des objets avec les parametres du test avant chaque
		// tests
		System.out.println("Debut du test");
		employeTest.setLogin(login);
		employeTest.setPassword(password);
	}

	// Test de la methode virementCourantAEpargne
	@Test
	public void TestVirementCourantAEpargne() {

		employeTest=serviceEmploye.loginVerification(employeTest);

		assertThat(employeTest.getName(), IsEqual.equalTo(nameResult));
		assertThat(employeTest.getTypeFunction(), IsEqual.equalTo(typeFunctionResult));
		if(employeTest.getId()==0)
		assertThat(employeTest.getId(), IsEqual.equalTo(idResult));

	}

	@After
	public void after() {
		System.out.println("Fin du test");
	

	}

	// Suppression des objets en base de donnee
	@AfterClass
	public static void SupressionCourant() {
		serviceEmploye.deleteEmploye(advisor);
		serviceEmploye.deleteEmploye(gerand);
	}

}
