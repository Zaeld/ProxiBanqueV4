package fr.gtm.pbsi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.gtm.pbsi.dao.IAccountDao;

/**
 * Classe WebService/Service de Account contenant les methodes CRUDs.
 * 
 * @author Stagiaire
 *
 */
@RestController
@RequestMapping("/account")
public class AccountService {
	@Autowired
	private IAccountDao daocurrent;

}
