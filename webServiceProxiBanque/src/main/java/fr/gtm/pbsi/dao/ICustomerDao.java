package fr.gtm.pbsi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.gtm.pbsi.domain.Customer;

/**
 * @author Bovin Blondel Demolis Colbert Sersoub * Interface permettant de
 *         definir un Repository Spring. Nous utilisons ici l'interface
 *         JpaRepository de Spring pour demander au Framework de fabriquer un
 *         DAO evolue qui manipule une entite JPA/Hibernate.
 */
public interface ICustomerDao extends JpaRepository<Customer, Integer> {

	// Methode permettant de recuperer le customer dont l'idEmploye est donne
	List<Customer> findAllByIdEmploye(Integer idEmploye);

}