package fr.pizzeria.ihm.menu.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Permet de lister les pizzas
 * @author ETY15
 *
 */
public class ListerPizzasOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);
	
	public ListerPizzasOptionMenu(IPizzaDao pizzaDao) {
		super("Lister les pizzas");
		this.dao = pizzaDao;
	}

	/**
	 * Récupère les pizzas via la DAO puis les affiche
	 */
	@Override
	public boolean execute() {
		
		LOG.info("Liste des pizzas");
		
		for(Pizza p : dao.findAllPizzas()) {
			// voir Pizza.toString() pour plus de précisions
			LOG.info(p.toString());
		}
		return true;
	}
	

}
