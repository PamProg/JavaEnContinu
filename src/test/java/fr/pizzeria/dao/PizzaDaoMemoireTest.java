package fr.pizzeria.dao;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoireTest {

	private PizzaDaoMemoire pizzaDaoMem;
//	@Rule public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
//	private String logConsole;
	
	@Before
	public void setUp() {
		pizzaDaoMem = new PizzaDaoMemoire();
		PizzaDaoMemoire.initPizzas();
//		logConsole = systemOutRule.getLog();
	}
	
	@Test
	public void testFindAllPizzas() {
		List<Pizza> pizzas = pizzaDaoMem.findAllPizzas();
		assertThat(pizzas.size()).isEqualTo(8);
	}
	
	@Test
	public void testSaveNewPizza() throws SavePizzaException {
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDaoMem.saveNewPizza(p);
		
		List<Pizza> pizzas = pizzaDaoMem.findAllPizzas();
		
		assertThat(pizzas.size()).isEqualTo(9);
	}
	
	@Test(expected = SavePizzaException.class)
	public void testSaveNewPizzaException() throws SavePizzaException {
		Pizza p = new Pizza("FRO", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDaoMem.saveNewPizza(p);
		
//		assertThat(logConsole).contains("Le code de la pizza existe déjà. Pizza non sauvée.");
	}
	
	
	@Test
	public void testUpdatePizza() throws SavePizzaException {
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.FROMAGE);
		String code = "FRO";
		String newCode = p.getCode();
		
		pizzaDaoMem.updatePizza(code, p);
		
		List<Pizza> pizzas = pizzaDaoMem.findAllPizzas();
		
		boolean updated = false;
		for(Pizza piz : pizzas) {
			if(newCode.equals(piz.getCode())) {
				updated = true;
			}
		}
		assertThat(updated);
	}
}
