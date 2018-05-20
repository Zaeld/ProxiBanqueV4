package fr.gtm.pbsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.gtm.pbsi.domain.Employe;

/**
 * @author Bovin Blondel Demolis Colbert Sersoub * Interface permettant de
 *         definir un Repository Spring. Nous utilisons ici l'interface
 *         JpaRepository de Spring pour demander au Framework de fabriquer un
 *         DAO evolue qui manipule une entite JPA/Hibernate.
 */
public interface IEmployeDao extends JpaRepository<Employe, Integer> {

	// Methode permettant de recuperer l'employe possedant le login et le password
	// donne
	Employe findByLoginAndPassword(String login, String password);

	// Methode pour recuperer tous les conseillers sans le gerants
	List<Employe> findAllByTypeFunction(Integer typeFunction);

}
