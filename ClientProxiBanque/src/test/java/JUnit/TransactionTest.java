package JUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import org.junit.Test;

import fr.gtm.pbsi.domain.Account;
import fr.gtm.pbsi.domain.CurrentAccount;
import fr.gtm.pbsi.domain.Transaction;
import fr.gtm.pbsi.service.AccountService;


public class TransactionTest {
	
	//////////////////////SCENARIOS//////////////////////////////////////////////////////////////////////////////
	// => 1) test si le montant du virement n'exède pas le solde du compte debiteur ou sont decouvert autorisé
	//				2 tests (Epargne et courant)
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// => 2)  test si le montant du virement et que le debit sont bien égale 
	//				1 test
	//	CurrentCompte ccD = serviceAccount.readAccount();
	//	CurrentCompte ccC = serviceAccount.readAccount();
	//  transaction = new Transaction(ccD.getId, ccC.getId,500);
	//
	//	ccD.setSolde(1000);
	//	ccD.setSolde(200);
	//	serviceAccount.transfert(c1, c2, 500);
	// 
	//Assert.assertEquals(500, ccD.getSolde());
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// => 3 test si le montant du virement et le credit sont bien égale
	//	CurrentCompte ccD = serviceAccount.readAccount();
	//	CurrentCompte ccC = serviceAccount.readAccount();
	//  transaction = new Transaction(ccD.getId, ccC.getId,500);
	//
	//	ccD.setSolde(1000);
	//	ccD.setSolde(200);
	//	serviceAccount.transfert(c1, c2, 500);
	// 
	//Assert.assertEquals(700, ccC.getSolde());
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	

	
	
	
	
	//////////////////////////////////////////////////////////////
	

}
