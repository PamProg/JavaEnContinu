package fr.pizzeria.dao;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoireTest {

	private PizzaDaoMemoire pizzaDaoMem;
	
	@Before
	public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		pizzaDaoMem = new PizzaDaoMemoire();
		PizzaDaoMemoire.initPizzas();
		
//		Field pizzasField = PizzaDaoMemoire.class.getDeclaredField("pizzas");
//		pizzasField.setAccessible(true);
//		@SuppressWarnings("unchecked")
//		List<Pizza> pizzas = (List<Pizza>) pizzasField.get(pizzaDaoMem);
	}
	
	@Test
	public void testFindAllPizzas() {
		List<Pizza> pizzas = pizzaDaoMem.findAllPizzas();
		
		assertThat(pizzas.size()).isEqualTo(8);
	}
	
	@Test
	public void testSaveNewPizza() throws SavePizzaException {
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.VIANDE);
		pizzaDaoMem.saveNewPizza(p);
		
		List<Pizza> pizzas = new ArrayList<>(); 
		pizzas = pizzaDaoMem.findAllPizzas();
		
		assertThat(pizzas.size()).isEqualTo(9);
	}
	@Test
	public void testSaveNewPizzaException() throws SavePizzaException {
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.VIANDE);
		pizzaDaoMem.saveNewPizza(p);
		
		List<Pizza> pizzas = new ArrayList<>(); 
		pizzas = pizzaDaoMem.findAllPizzas();
		
		assertThat(pizzas.size()).isEqualTo(9);
	}
	
	
	@Test // TODO changer
	public void testUpdatePizza() throws SavePizzaException {
		PizzaDaoMemoire.initPizzas();
		
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.VIANDE);
		pizzaDaoMem.saveNewPizza(p);
		
		List<Pizza> pizzas = new ArrayList<>(); 
		pizzas = pizzaDaoMem.findAllPizzas();
		
		assertThat(pizzas.size()).isEqualTo(9);
	}
	
	
	
}
