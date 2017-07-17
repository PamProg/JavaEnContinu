package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.PizzeriaUtil;

/**
 * Permet d'ajouter une nouvelle pizza.
 * @author Pam
 *
 */
public class NouvellePizzaOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	private static final Logger LOG = LoggerFactory.getLogger(NouvellePizzaOptionMenu.class);
	
	public NouvellePizzaOptionMenu(String libelle) {
		super(libelle);
		dao = new PizzaDaoMemoire();
	}

	/**
	 * Récupère les informations l'utilisateur entre dans la console
	 * puis si elles sont correctes, ajoute une nouvelle pizza via la DAO.
	 */
	@Override
	public boolean execute() {
		String codeString;
		String nomString;
		String prixString;
		CategoriePizza categorie;
		
		codeString = PizzeriaUtil.askAndCheckCode();
		nomString = PizzeriaUtil.askAndCheckName();
		prixString = PizzeriaUtil.askAndCheckPrice();
		categorie = PizzeriaUtil.askAndCheckCategorie();
		
		Pizza p = new Pizza(codeString, nomString, Double.parseDouble(prixString), categorie);
		try {
			dao.saveNewPizza(p);
		} catch (SavePizzaException e) {
			LOG.warn(e.getMessage());
		}
		
		return true;
	}



}
