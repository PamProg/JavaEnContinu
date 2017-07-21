package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.utils.PizzeriaUtil;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Permet de mettre à jour une pizza
 * @author Pam
 *
 */
public class MettreAJourPizzaOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	private Scanner scanner;
	private PizzeriaUtil pizzeriaUtil;
	private static final Logger LOG = LoggerFactory.getLogger(MettreAJourPizzaOptionMenu.class);
	
	public MettreAJourPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner, PizzeriaUtil pizzeriaUtil) {
		super("Mettre à jour une pizza");
		this.dao = pizzaDao;
		this.scanner = scanner;
		this.pizzeriaUtil = pizzeriaUtil;
	}

	/**
	 * Récupère le code de la pizza à modifier puis récupère les informations
	 * de l'utilisateur. Si elles sont correctes, met à jour la pizza.
	 * Note : ne fait rien si le code entré n'existe pas.
	 */
	@Override
	public boolean execute() {
		
		// On liste d'abord les pizzas
		for(Pizza p : dao.findAllPizzas()) {
			LOG.info(p.toString());
		}
		
		LOG.info("Mise à jour d'une pizza");
		LOG.info("Veuillez choisir le code de la pizza à modifier.");
		LOG.info("(99 pour abandonner)");
		
		String codeChosen = scanner.next();
		
		if(!("99").equals(codeChosen)) {
		
			String codeString;
			String nomString;
			String prixString;
			CategoriePizza categorie;
			
			codeString = pizzeriaUtil.askAndCheckCode();
			nomString = pizzeriaUtil.askAndCheckName();
			prixString = pizzeriaUtil.askAndCheckPrice();
			categorie = pizzeriaUtil.askAndCheckCategorie();
			
			Pizza p = new Pizza(codeString, nomString, Double.parseDouble(prixString), categorie);
			dao.updatePizza(codeChosen, p);
		}
		
		return true;
	}
}
