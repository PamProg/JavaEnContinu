package fr.pizzeria.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpaTest extends PizzaDaoTest{

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzeria-console-objet-annotation");
	
	@Before
	public void setUp() throws SQLException {
		pizzaDao = new PizzaDaoJpa(emf, DRIVER_H2);
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGE));
		et.commit();
		
		em.close();
	}
}
