package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpaTest extends PizzaDaoJDBCTest{

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzeria-console-objet-annotation");
	private PizzaDaoJpa pizzaDao = new PizzaDaoJpa(emf);
	
	@Test
	public void testFindAllPizzas() throws SQLException {
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertThat(pizzas.size()).isEqualTo(1);
	}
	
	@Test
	public void testSaveNewPizza() throws SQLException, SavePizzaException {
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDao.saveNewPizza(p);
		
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertThat(pizzas.size()).isEqualTo(2);
	}
	
	@Test(expected = SavePizzaException.class)
	public void testSaveNewPizzaException() throws SQLException, SavePizzaException {
		Pizza p = new Pizza("FRO", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDao.saveNewPizza(p);
	}
}
