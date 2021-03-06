package fr.pizzeria.ihm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoJpa;
import fr.pizzeria.ihm.menu.Menu;
import fr.pizzeria.ihm.utils.PizzeriaUtil;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Point d'entrée du programme. Initialise les pizzas et instancie un Scanner
 * qui sera utilisé dans tout le programme.
 * Déclare et appelle ensuite un Menu qui s'occupera de gérer le reste de l'application.
 * @author ETY15
 *
 */
public class PizzeriaAdminApp {

	public static void main(String[] args) {
		
		List<Pizza> pizzas = new ArrayList<>();
		initPizzasMemoire(pizzas);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzeria-console-objet-annotation");
		IPizzaDao pizzaDao = new PizzaDaoJpa(emf);
		pizzaDao.initPizzas(pizzas);
		
		try (Scanner scanner = new Scanner(System.in)) {
			PizzeriaUtil pizzeriaUtil = new PizzeriaUtil(scanner);
			Menu m = new Menu(pizzaDao, scanner, pizzeriaUtil, "***** Pizzeria Administration *****");
			m.manage();
		}
	}

	/**
	 * Permet d'initialiser une liste des pizzas avec un jeu de données
	 * @param pizzas
	 */
	private static void initPizzasMemoire(List<Pizza> pizzas) {
		
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.FROMAGE));
		pizzas.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VEGETARIEN));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGE));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.FROMAGE));
		pizzas.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VEGETARIEN));
		pizzas.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
	}

}
