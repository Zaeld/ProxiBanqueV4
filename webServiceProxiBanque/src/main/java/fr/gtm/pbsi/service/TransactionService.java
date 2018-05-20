package fr.gtm.pbsi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.gtm.pbsi.dao.ITransactionDao;
import fr.gtm.pbsi.domain.Transaction;

/**
 * Classe WebService/Service de Transaction contenant les methodes CRUDs.
 * 
 * @author Stagiaire
 *
 */
@RestController
@RequestMapping("/transaction")
public class TransactionService {

	@Autowired
	private ITransactionDao daoTransaction;

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

	/**
	 * Methode post permettant l'insertion en BDD d'une transaction.
	 * 
	 * @param transaction
	 * @return la transaction creee
	 */
	@PostMapping({ "", "/" })
	Transaction create(@RequestBody Transaction transaction) {
		final Transaction retour = this.daoTransaction.save(transaction);
		TransactionService.LOGGER.info("Création de " + retour + " en BDD.");
		return retour;
	}

	/**
	 * Methode delete permettant la suppression d'une transaction en BDD via son ID.
	 * 
	 * @param transactionId
	 * @return 1 pour signifier que la transaction est bien supprimer en BDD.
	 */
	@DeleteMapping("/{transactionId}")
	Integer delete(@PathVariable Integer transactionId) {
		this.daoTransaction.deleteById(transactionId);
		return 1;
	}

	/**
	 * Methode getAll permettant de recuperer la liste de toutes les transactions
	 * presentes en BDD
	 * 
	 * @return la liste de toutes les transactions
	 */
	@GetMapping({ "", "/" })
	List<Transaction> readAll() {
		return this.daoTransaction.findAll();
	}

	/**
	 * Methode get permettant de recuperer une transaction en BDD via son ID.
	 * 
	 * @param transactionId
	 * @return la transaction demandee
	 */
	@GetMapping("/{transactionId}")
	Transaction read(@PathVariable Integer transactionId) {
		final Optional<Transaction> retour = this.daoTransaction.findById(transactionId);
		if (retour.isPresent()) {
			return retour.get();
		} else {
			final Transaction response = new Transaction();
			response.setId(0);
			return response;
		}
	}

	/**
	 * Methode put permettant de modifier une transaction en BDD grace a son ID et
	 * au nouvel etat de la transaction. Si l'ID donne n'existe pas en BDD, la
	 * methode renvoie une transaction possedant un ID = 0. Sinon elle modifie la
	 * transaction et renvoie la transaction modifiee.
	 * 
	 * @param transactionId
	 * @param transaction
	 * @return la transaction modifiee
	 */
	@PutMapping("/{transactionId}")
	Transaction update(@PathVariable Integer transactionId, @RequestBody Transaction transaction) {
		if (this.daoTransaction.existsById(transactionId)) {
			return this.daoTransaction.save(transaction);
		} else {
			final Transaction response = new Transaction();
			response.setId(0);
			return response;
		}
	}
}
