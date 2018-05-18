package fr.gtm.pbsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.gtm.pbsi.domain.Employe;

/**
 * @author Bovin Blondel Demolis Colbert Sersoub
 *  * Interface permettant de définir un Repository Spring. Nous utilisons ici
 * l'interface JpaRepository de Spring pour demander au Framework de fabriquer
 * un DAO évolué qui manipule une entité JPA/Hibernate.
 */
public interface IEmployeDao  extends JpaRepository<Employe,Integer> {
	
	List<Employe> findAllByLoginAndPassword(String login,String password);

}
