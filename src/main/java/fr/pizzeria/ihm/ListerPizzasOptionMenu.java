package fr.pizzeria.ihm;

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
				// N'est plus utile depuis que l'on a implémenté le toString() sur Pizza
//				System.out.println(p.getCode() + " -> " 
//								 + p.getNom() + " (" 
//								 + p.getPrix() + " €)");
				System.out.println(p);
			}
		}
		return true;
	}
	

}
