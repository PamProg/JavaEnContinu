package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {

//	private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
//	private static final String DRIVER_H2 = "DRIVER_H2";
//	private static final String URL_H2 = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
//	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoire.class);
	
	private EntityManagerFactory emf;

	public PizzaDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
	}
	

	@Override
	public List<Pizza> findAllPizzas() {
		
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p", Pizza.class);
		return query.getResultList();
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		
	}

	@Override
	public void deletePizza(String codePizza) {
		
	}

}
