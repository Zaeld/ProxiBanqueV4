package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import org.junit.Test;

import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.CurrentAccount;
import fr.gtm.pbsi.service.AccountService;

public class AccountServiceTest {
	private static AccountService serviceAccount = new AccountService();
	private Account account;
	
	/**
	 * test qui verifie que la la methode readAccount reussi bien a 
	 * recuperer le bon compte par sont id la condition etant que
	 * le numero de compte attendu et bien celui recuperer.   
	 * 
	 */

	@Test
	public void testGetAccount() {
		
		Account account = new CurrentAccount();
		account=serviceAccount.readAccount(1);
		assertEquals("E523", account.getNumberAccount());
	}
	@Test
	public void testUpdateAccount() {
		Account account = new CurrentAccount();
		Account accountUpdate= new CurrentAccount();
		account=serviceAccount.readAccount(1);
		account.setNumberAccount("E500");
		accountUpdate=serviceAccount.updateAccount(account);
		assertEquals("E523", accountUpdate.getNumberAccount());
	}
	@Test
	public void testDeleteAccount() {
		Account account = new CurrentAccount();
		account=serviceAccount.readAccount(1);
		serviceAccount.deleteAccount(account);
		assertNull(account);
	}
	
	public void testListAccount() {
		assertEquals(12, serviceAccount.getAllAccount().size());
	}

}
