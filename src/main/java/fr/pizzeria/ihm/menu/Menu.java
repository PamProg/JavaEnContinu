package fr.pizzeria.ihm.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.menu.option.ListerPizzasOptionMenu;
import fr.pizzeria.ihm.menu.option.MettreAJourPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.SortirOptionMenu;
import fr.pizzeria.ihm.menu.option.SupprimePizzaOptionMenu;
import fr.pizzeria.ihm.utils.PizzeriaUtil;

/**
 * Classe principale du projet, s'occupe de gérer chaque option du menu.
 * @author ETY15
 *
 */
public class Menu {

	private static final int OPTION_MENU_SORTIE = 99;
	// Déclaration des variables
	private String titre;
	private Map<Integer, OptionMenu> actions = new HashMap<>();
	private Scanner scanner;
	private IPizzaDao pizzaDao;
	private PizzeriaUtil pizzeriaUtil;
	
	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);
	
	public Menu(IPizzaDao pizzaDao, Scanner scanner, PizzeriaUtil pizzeriaUtil, String titre) {
		this.pizzaDao = pizzaDao;
		this.scanner = scanner;
		this.pizzeriaUtil = pizzeriaUtil;
		this.titre = titre;
		
		initActions();
	}
	
	/**
	 * Initialise toutes nos OptionMenu
	 */
	private void initActions() {
		actions.put(1, new ListerPizzasOptionMenu(pizzaDao));
		actions.put(2, new NouvellePizzaOptionMenu(pizzaDao, pizzeriaUtil));
		actions.put(3, new MettreAJourPizzaOptionMenu(pizzaDao, scanner, pizzeriaUtil));
		actions.put(4, new SupprimePizzaOptionMenu(pizzaDao, scanner));
		actions.put(99, new SortirOptionMenu());
	}
	
	/**
	 * Méthode principale, effectue les différents appellent nécessaires au fonctionnement de l'application
	 */
	public void manage() {
		
		
		int input = OPTION_MENU_SORTIE;
		
		// Afficher le menu tant qu'on ne sort pas (ie : response = 99)
		do {
			afficherMenu();
			input = scanner.nextInt();
			actions.get(input).execute();
		} while (input != OPTION_MENU_SORTIE);
		
	}
	

	/**
	 * Affiche le menu principal de la pizzeria
	 */
	public void afficherMenu() {
		LOG.info(titre);
		
		actions.forEach((numero, option) -> LOG.info(numero + ". " + option.getLibelle()));
	}
	
	/**
	 * @return the actions
	 */
	public Map<Integer, OptionMenu> getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(Map<Integer, OptionMenu> actions) {
		this.actions = actions;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	
}
