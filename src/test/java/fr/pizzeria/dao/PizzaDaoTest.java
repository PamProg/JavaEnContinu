package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public abstract class PizzaDaoTest {

	protected static final String DRIVER_H2 = "org.h2.Driver";
	protected IPizzaDao pizzaDao;
	
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
	
	@Test
	public void testUpdatePizza() throws SQLException {
		String code = "FRO";
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDao.updatePizza(code, p);
		
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertThat(pizzas.get(0).getCode()).isEqualTo(p.getCode());
	}
	
	@Test
	public void testDeletePizza() {
		String code = "FRO";
		pizzaDao.deletePizza(code);
		
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		
		assertThat(pizzas.size()).isEqualTo(0);
	}
}
