package fr.pizzeria.ihm.menu.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.ihm.PizzeriaAdminApp;

/**
 * Permet de supprimer une pizza
 * @author Pam
 *
 */
public class SupprimePizzaOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	private static final Logger LOG = LoggerFactory.getLogger(SupprimePizzaOptionMenu.class);
	
	public SupprimePizzaOptionMenu(String libelle) {
		super(libelle);
		dao = new PizzaDaoMemoire();
	}

	/**
	 * Récupère le code entré par l'utilisateur et supprime la pizza qui lui est relié.
	 * Note : ne fait rien si le code entré n'existe pas.
	 */
	@Override
	public boolean execute() {
		
		LOG.info("Veuillez choisir le code de la pizza à supprimer.");
		LOG.info("(99 pour abandonner)");
		
		String codeChosen = PizzeriaAdminApp.getInput().next();
		
		if(!("99").equals(codeChosen)) {
			dao.deletePizza(codeChosen);
		}
		
		return true;
	}

}
