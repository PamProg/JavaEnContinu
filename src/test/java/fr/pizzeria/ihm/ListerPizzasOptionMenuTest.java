package fr.pizzeria.ihm;

import org.junit.Before;

import fr.pizzeria.ihm.menu.option.ListerPizzasOptionMenu;

public class ListerPizzasOptionMenuTest {
	
	ListerPizzasOptionMenu listerPizzas;
	
	
	@Before
	public void setUp() {
		listerPizzas = new ListerPizzasOptionMenu("test");
	}
	
	
}
