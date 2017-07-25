package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {

//	private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
//	private static final String DRIVER_H2 = "DRIVER_H2";
//	private static final String URL_H2 = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJpa.class);
	
	private EntityManagerFactory emf;

	public PizzaDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public void initPizzas(List<Pizza> pizzas) {
		
		// TODO
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		EntityManager em = emf.createEntityManager();
		
		LOG.debug("Récupération des pizzas...");
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p", Pizza.class);
		LOG.debug("...pizzas récupérées.");
		
		List<Pizza> list = query.getResultList(); 
		em.close();
		return list;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		LOG.debug("Préparation à la sauvegarde d'une nouvelle pizza...");
		
		List<Pizza> pizzas = findAllPizzas();
		pizzas.stream().filter(p -> p.getCode().equals(pizza.getCode())).findAny().ifPresent(p -> {
			throw new SavePizzaException("Erreur : Le code de la pizza existe déjà. Pizza non sauvée.");
		});
		
		em.persist(pizza);
		LOG.debug("...pizza sauvegardée");
		et.commit(); // TODO gérer le rollback
		em.close();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		LOG.debug("Préparation à la modification d'une pizza...");

		// On récupère la pizza de code codePizza
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where p.code=:code", Pizza.class)
									.setParameter("code", codePizza);
		Pizza p = query.getSingleResult();
		
		// Si elle existe...
		if(p != null) {
			// ...on remplace son id par l'id de la "nouvelle" pizza...
			pizza.setId(p.getId());
			// ...puis on "écrase" les données de la nouvelle pizza dans celle en base
			em.merge(pizza);
		} // TODO gérer le else{}
		
		LOG.debug("...pizza modifiée");
		et.commit(); // TODO gérer le rollback
		em.close();
	}

	@Override
	public void deletePizza(String codePizza) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		LOG.debug("Préparation à la suppression d'une pizza...");
		
		// On récupère la pizza de code codePizza
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where p.code=:code", Pizza.class)
									.setParameter("code", codePizza);
		Pizza p = query.getSingleResult();
		
		// Si elle existe...
		if(p != null) {
			// ...on la supprime de la base de données
			em.remove(p);
		} // TODO gérer le else{}
		
		LOG.debug("...pizza supprimée");
		et.commit(); // TODO gérer le rollback
		em.close();
	}

}
