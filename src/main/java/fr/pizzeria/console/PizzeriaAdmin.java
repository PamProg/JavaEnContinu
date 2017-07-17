package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.ihm.Menu;

/**
 * Point d'entrée du programme. Initialise les pizzas et instancie un Scanner
 * qui sera utilisé dans tout le programme.
 * Déclare et appelle ensuite un Menu qui s'occupera de gérer le reste de l'application.
 * @author ETY15
 *
 */
public class PizzeriaAdmin {

	private static Scanner input;
	
	public static void main(String[] args) {
		PizzaDaoMemoire.initPizzas();
		input = new Scanner(System.in);
		Menu m = new Menu("***** Pizzeria Administration *****", 5);
		m.manage();
		input.close();
	}

	/**
	 * @return the input
	 */
	public static Scanner getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public static void setInput(Scanner input) {
		PizzeriaAdmin.input = input;
	}

}
