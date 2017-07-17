package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.model.Pizza;

/**
 * Permet de lister les pizzas
 * @author ETY15
 *
 */
public class ListerPizzasOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);
	
	public ListerPizzasOptionMenu(String libelle) {
		super(libelle);
	}

	/**
	 * Récupère les pizzas via la DAO puis les affiche
	 */
	@Override
	public boolean execute() {
		
		dao = new PizzaDaoMemoire();
		
		for(Pizza p : dao.findAllPizzas()) {
			// Le test n'est théoriquement plus nécessaire
			if(p != null) {
				// voir Pizza.toString() pour plus de précisions
				LOG.info(p.toString());
			}
		}
		return true;
	}
	

}
