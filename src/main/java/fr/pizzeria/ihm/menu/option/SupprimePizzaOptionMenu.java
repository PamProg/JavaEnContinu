package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Permet de supprimer une pizza
 * @author Pam
 *
 */
public class SupprimePizzaOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	private Scanner scanner;
	private static final Logger LOG = LoggerFactory.getLogger(SupprimePizzaOptionMenu.class);
	
	public SupprimePizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		super("Supprimer une pizza");
		dao = pizzaDao;
		this.scanner = scanner;
	}

	/**
	 * Récupère le code entré par l'utilisateur et supprime la pizza qui lui est relié.
	 * Note : ne fait rien si le code entré n'existe pas.
	 */
	@Override
	public boolean execute() {
		
		// On liste d'abord les pizzas
		for(Pizza p : dao.findAllPizzas()) {
			LOG.info(p.toString());
		}
		
		LOG.info("Suppression d'une pizza");
		LOG.info("Veuillez choisir le code de la pizza à supprimer.");
		LOG.info("(99 pour abandonner)");
		
		String codeChosen = scanner.next();
		
		if(!("99").equals(codeChosen)) {
			dao.deletePizza(codeChosen);
		}
		
		return true;
	}

}
