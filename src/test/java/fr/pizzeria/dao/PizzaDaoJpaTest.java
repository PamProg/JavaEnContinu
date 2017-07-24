package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.pizzeria.model.Pizza;

public class PizzaDaoJpaTest extends PizzaDaoJDBCTest{

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzeria-console-objet-annotation");
	private PizzaDaoJpa pizzaDao = new PizzaDaoJpa(emf);
	
	@Test
	public void testFindAllPizzas() throws SQLException {
		
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertThat(pizzas.size()).isEqualTo(1);
	}
}
