
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

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeService.class);

	/**
	 * Methode post permettant l'insertion en BDD de l'employe. La methode verifie
	 * qu'aucun autre employe ne possede le même mot de passe et/ou login. Si oui
	 * retourne un employe vide d'ID 0.
	 * 
	 * @param employe
	 * @return l'employe cree
	 */
	@PostMapping({ "", "/" })
	Employe create(@RequestBody Employe employe) {
		Employe retour = this.daoEmploye.findByLoginAndPassword(employe.getLogin(), employe.getPassword());
		if (retour == null) {
			retour = this.daoEmploye.save(employe);
			EmployeService.LOGGER.info("Création de " + retour + " en BDD.");
			return retour;
		} else {
			EmployeService.LOGGER.info("Impossible de créer l'employe en BDD car le login et mot de passe existe déjà pour un autre employe.");
			retour.setId(0);
			return retour;
		}
	}

	/**
	 * Methode post permettant de verifier le login et le password de l'employe
	 * essayant de se connecter a l'application. Si le login et/ou le password sont
	 * mauvais, l'employe retourne possede un ID = 0.
	 * 
	 * @param employe
	 * @return l'employe qui se connecte
	 */
	@PostMapping("/authentification")
	Employe authentification(@RequestBody Employe employe) {
		final Employe retour = this.daoEmploye.findByLoginAndPassword(employe.getLogin(), employe.getPassword());
		if (retour == null) {
			final Employe employeVide = new Employe();
			employeVide.setId(0);
			EmployeService.LOGGER.info("Tentative de connection à l'application échouée due à un mauvais login ou password.");
			return employeVide;
		} else {
			if (retour.getTypeFunction() == 0) {
				EmployeService.LOGGER.info("Connection du gérant " + retour.getName() + " " + retour.getFirstName() + " à l'application.");
			} else {
				EmployeService.LOGGER.info("Connection du conseiller " + retour.getName() + " " + retour.getFirstName() + " à l'application.");
			}
			return retour;
		}
	}

	/**
	 * Methode delete permettant la suppression d'un employe en BDD via son ID. Si
	 * l'ID donne n'existe pas la methode renvoie 0. De même, si l'employe
	 * correspondant existe mais possede encore des clients, la methode renvoie 0.
	 * Si l'employe existe et ne possede plus de client alors la methode le supprime
	 * et renvoie 1.
	 * 
	 * @param employeId
	 * @return 0 si l'employe n'existe pas ou possede encore des clients, 1 si la
	 *         suppression s'est bien effectuee
	 */
	@DeleteMapping("/{employeId}")
	Integer delete(@PathVariable Integer employeId) {
		if (this.daoEmploye.existsById(employeId)) {
			final Optional<Employe> retour = this.daoEmploye.findById(employeId);
			final Employe employe = retour.get();
			if (employe.getListCustomer() == null) {
				this.daoEmploye.deleteById(employeId);
				EmployeService.LOGGER.info("Suppression de " + employe + " de la BDD.");
				return 1;
			} else {
				EmployeService.LOGGER.error("Tentative de suppression d'un employe échouée car l'ID donné n'est pas trouvable dans la BDD.");
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Methode getAll permettant de recuperer la liste de tous les employes presents
	 * en BDD.
	 * 
	 * @return la liste des employes
	 */
	@GetMapping({ "", "/" })
	List<Employe> readAll() {
		EmployeService.LOGGER.info("Récupération de la liste des employes de ProxiBanque.");
		return this.daoEmploye.findAll();
	}

	/**
	 * Methode get permettant de recuperer un employe en BDD via son ID.
	 * 
	 * @param employeId
	 * @return l'employe demande
	 */
	@GetMapping("/{employeId}")
	Employe read(@PathVariable Integer employeId) {
		final Optional<Employe> retour = this.daoEmploye.findById(employeId);
		if (retour.isPresent()) {
			EmployeService.LOGGER.info("Récupération de " + retour + ".");
			return retour.get();
		} else {
			EmployeService.LOGGER.error("Tentative de récupération d'un employe échouée car l'ID donné n'a aucune correspondance en BDD.");
			final Employe response = new Employe();
			response.setId(0);
			return response;
		}
	}

	/**
	 * Methode permettant de recuperer tous les conseillers present en BDD.
	 * 
	 * @return la liste des conseillers
	 */
	@GetMapping("/adviser")
	List<Employe> readAdviser() {
		EmployeService.LOGGER.info("Récupération de la liste des conseiller de ProxiBanque.");
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
	 * @return l'employe modifie
	 */
	@PutMapping("/{employeId}")
	Employe update(@PathVariable Integer employeId, @RequestBody Employe employe) {
		if (this.daoEmploye.existsById(employeId)) {
			final Employe retour = this.daoEmploye.save(employe);
			EmployeService.LOGGER.info("Modification de " + employe + " en " + retour + " dans la BDD.");
			return retour;
		} else {
			EmployeService.LOGGER.error("Tentative de modification de " + employe + " échouée car cet employe n'existe pas en BDD ou l'ID de la requête n'a pas de correspondance en BDD.");
			final Employe response = new Employe();
			response.setId(0);
			return response;
		}
	}
}
