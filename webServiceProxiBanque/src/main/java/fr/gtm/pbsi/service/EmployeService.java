package fr.gtm.pbsi.service;

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
	private EmployeDao daoEmploye;

	/**
	 * Methode post permettant l'insertion en BDD de l'employe.
	 * 
	 * @param employe
	 * @return
	 */
	@PostMapping({ "", "/" })
	Employe create(@RequestBody Employe employe) {
		return this.daoEmploye.save(employe);
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
		final Employe retour = this.daoEmploye.authentification(employe.getLogin(), employe.getPassword());
		if (retour.equals(null)) {
			final Employe employeVide = new Employe();
			employeVide.setId(0);
			retour = employeVide;
		}
		return retour;
	}

	/**
	 * Methode delete permettant le suppression d'un employe en BDD via son ID.
	 * 
	 * @param employeId
	 */
	@DeleteMapping("/{conseillerId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer employeId) {
		this.daoEmploye.deleteById(employeId);
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
	 * Classe get permettant de recuperer un employe en BDD via son ID.
	 * 
	 * @param employeId
	 * @return
	 */
	@GetMapping("/{conseillerId}")
	Employe read(@PathVariable Integer employeId) {
		final Optional<Employe> retour = this.daoEmploye.findById(employeId);
		if (retour.isPresent()) {
			return retour.get();
		} else {
			return null;
		}
	}

	/**
	 * Methode put permettant de modifier un employe en BDD grace a son ID et au
	 * nouvel etat de l'employe.
	 * 
	 * @param employeId
	 * @param employe
	 * @return
	 */
	@PutMapping("/{conseillerId}")
	Employe update(@PathVariable Integer employeId, @RequestBody Employe employe) {
		if (this.daoEmploye.existsById(employeId)) {
			return this.daoEmploye.save(employe);
		} else {
			return null;
		}
	}

}
