package fr.gtm.pbsi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.gtm.pbsi.dao.ICustomerDao;
import fr.gtm.pbsi.domain.Customer;

/**
 * Classe WebService/Service de Customer contenant les methodes CRUDs.
 * 
 * @author Stagiaire
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerService {
	// TODO ajouter les sécuritées pour la modification, créations, et suppression.

	@Autowired
	private ICustomerDao daocustomer;

	/**
	 * Methode post permettant l'insertion en BDD d'un client.
	 * 
	 * @param employe
	 * @return
	 */
	@PostMapping({ "", "/" })
	Customer create(@RequestBody Customer customer) {
		return this.daocustomer.save(customer);
	}

	/**
	 * Methode delete permettant la suppression d'un client en BDD via son ID.
	 * 
	 * @param employeId
	 */
	@DeleteMapping("/{customerId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer customerId) {
		this.daocustomer.deleteById(customerId);
	}

	/**
	 * Methode getAll permettant la liste de tous les clients presents en BDD.
	 * 
	 * @return
	 */
	@GetMapping({ "", "/" })
	List<Customer> readAll() {
		return this.daocustomer.findAll();
	}

	/**
	 * Classe get permettant de recuperer un employe en BDD via son ID.
	 * 
	 * @param employeId
	 * @return
	 */
	@GetMapping("/{customerId}")
	Customer read(@PathVariable Integer customerId) {
		final Optional<Customer> retour = this.daocustomer.findById(customerId);
		if (retour.isPresent()) {
			return retour.get();
		} else {
			return null;
		}
	}

	/**
	 * Methode put permettant de modifier un client en BDD grace a son ID et au
	 * nouvel etat de l'employe.
	 * 
	 * @param employeId
	 * @param employe
	 * @return
	 */
	@PutMapping("/{customerId}")
	Customer update(@PathVariable Integer customerId, @RequestBody Customer customer) {
		if (this.daocustomer.existsById(customerId)) {
			return this.daocustomer.save(customer);
		} else {
			return null;
		}
	}
}
