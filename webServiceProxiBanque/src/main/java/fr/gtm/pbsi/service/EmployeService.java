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

import fr.gtm.pbsi.dao.IEmployeDao;
import fr.gtm.pbsi.domain.Employe;

/**
 * Classe WebService/Service de Employe contenant les methodes CRUDs et la
 * methode d'authentification des employes.
 * 
 * @author Stagiaire
 *
 */
@RestController
@RequestMapping("/employe")
public class EmployeService {

	@Autowired
	private IEmployeDao daoEmploye;

	/**
	 * Methode post permettant l'insertion en BDD de l'employe. La methode verifie
	 * qu'aucun autre employe ne possede le même mot de passe et/ou login. Si oui
	 * retourne un employe vide d'ID 0.
	 * 
	 * @param employe
	 * @return
	 */
	@PostMapping({ "", "/" })
	Employe create(@RequestBody Employe employe) {
		final Employe retour = this.daoEmploye.findByLoginAndPassword(employe.getLogin(), employe.getPassword());
		if (retour == null) {
			return this.daoEmploye.save(employe);
		} else {
			retour.setId(0);
			return retour;
		}
	}

	/**
	 * Methode post permettant de verifier le login et le password de l'employe
	 * essayant de se connecter a l'application. Si le login et/ou le password sont
	 * mauvais, l'employe retourne possede
	 * 
	 * @param employe
	 * @return
	 */
	@PostMapping("/authentification")
	Employe authentification(@RequestBody Employe employe) {
		Employe retour = this.daoEmploye.findByLoginAndPassword(employe.getLogin(), employe.getPassword());
		if (retour == null) {
			final Employe employeVide = new Employe();
			employeVide.setId(0);
			retour = employeVide;
		}
		return retour;
	}

	/**
	 * Methode delete permettant la suppression d'un employe en BDD via son ID. Si
	 * l'ID donne n'existe pas la methode renvoie 0. De même, si l'employe
	 * correspondant existe mais possede encore des clients, la methode renvoie 0.
	 * Si l'employe existe et ne possede plus de client alors la methode le supprime
	 * et renvoie 1.
	 * 
	 * @param employeId
	 */
	@DeleteMapping("/{employeId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	Integer delete(@PathVariable Integer employeId) {
		if (this.daoEmploye.existsById(employeId)) {
			final Optional<Employe> retour = this.daoEmploye.findById(employeId);
			final Employe employe = retour.get();
			if (employe.getListCustomer() == null) {
				this.daoEmploye.deleteById(employeId);
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Methode getAll permettant la liste de tous les employes presents en BDD.
	 * 
	 * @return
	 */
	@GetMapping({ "", "/" })
	List<Employe> readAll() {
		return this.daoEmploye.findAll();
	}

	/**
	 * Methode get permettant de recuperer un employe en BDD via son ID.
	 * 
	 * @param employeId
	 * @return
	 */
	@GetMapping("/{employeId}")
	Employe read(@PathVariable Integer employeId) {
		final Optional<Employe> retour = this.daoEmploye.findById(employeId);
		if (retour.isPresent()) {
			return retour.get();
		} else {
			final Employe response = new Employe();
			response.setId(0);
			return response;
		}
	}

	/**
	 * Methode permettant de recuperer tous les conseillers present en BDD.
	 * 
	 * @return
	 */
	@GetMapping("/adviser")
	List<Employe> readAdviser() {
		return this.daoEmploye.findAllByTypeFunction(1);
	}

	/**
	 * Methode put permettant de modifier un employe en BDD grace a son ID et au
	 * nouvel etat de l'employe. Si l'ID donne n'existe pas en BDD, la methode
	 * renvoie un employe possedant un ID = 0. Sinon elle modifie l'employe et
	 * renvoie l'employe modifie.
	 * 
	 * @param employeId
	 * @param employe
	 * @return
	 */
	@PutMapping("/{employeId}")
	Employe update(@PathVariable Integer employeId, @RequestBody Employe employe) {
		if (this.daoEmploye.existsById(employeId)) {
			return this.daoEmploye.save(employe);
		} else {
			final Employe response = new Employe();
			response.setId(0);
			return response;
		}
	}

}
