package fr.pizzeria.dao;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoireTest {

	private PizzaDaoMemoire pizzaDao;
	
	@Before
	public void setUp() {
		pizzaDao = new PizzaDaoMemoire();
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.FROMAGE));
		pizzas.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VEGETARIEN));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGE));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.FROMAGE));
		pizzas.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VEGETARIEN));
		pizzas.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		pizzaDao.initPizzas(pizzas);
	}
	
	@Test
	public void testFindAllPizzas() {
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertThat(pizzas.size()).isEqualTo(8);
	}
	
	@Test
	public void testSaveNewPizza() throws SavePizzaException {
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDao.saveNewPizza(p);
		
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		
		assertThat(pizzas.size()).isEqualTo(9);
	}
	
	@Test(expected = SavePizzaException.class)
	public void testSaveNewPizzaException() throws SavePizzaException {
		Pizza p = new Pizza("FRO", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDao.saveNewPizza(p);
	}
	
	@Test
	public void testUpdatePizza() throws SavePizzaException {
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.FROMAGE);
		String code = "FRO";
		String newCode = p.getCode();
		
		pizzaDao.updatePizza(code, p);
		
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		
		boolean updated = false;
		for(Pizza piz : pizzas) {
			if(newCode.equals(piz.getCode())) {
				updated = true;
			}
		}
		assertThat(updated);
	}
}
