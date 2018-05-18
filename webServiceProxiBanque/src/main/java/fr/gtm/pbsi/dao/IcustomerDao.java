package fr.gtm.pbsi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.gtm.pbsi.domain.Customer;


/**
 * @author Bovin Blondel Demolis Colbert Sersoub
 *  * Interface permettant de définir un Repository Spring. Nous utilisons ici
 * l'interface JpaRepository de Spring pour demander au Framework de fabriquer
 * un DAO évolué qui manipule une entité JPA/Hibernate.
 */
public interface IcustomerDao extends JpaRepository<Customer,Integer> {

}
