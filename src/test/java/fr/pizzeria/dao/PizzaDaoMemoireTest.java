package fr.pizzeria.dao;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoireTest extends PizzaDaoTest {

	@Before
	public void setUp() {
		pizzaDao = new PizzaDaoMemoire();
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGE));
		pizzaDao.initPizzas(pizzas);
	}
}
