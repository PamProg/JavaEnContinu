package fr.pizzeria.ihm.menu.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.ihm.utils.PizzeriaUtil;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Permet d'ajouter une nouvelle pizza.
 * @author Pam
 *
 */
public class NouvellePizzaOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	private PizzeriaUtil pizzeriaUtil;
	
	private static final Logger LOG = LoggerFactory.getLogger(NouvellePizzaOptionMenu.class);
	private static final Logger LOGERROR = LoggerFactory.getLogger("error-log");
	
	public NouvellePizzaOptionMenu(IPizzaDao pizzaDao, PizzeriaUtil pizzeriaUtil) {
		super("Ajouter une nouvelle pizza");
		this.dao = pizzaDao;
		this.pizzeriaUtil = pizzeriaUtil;
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
		
		LOG.info("Ajout d'une nouvelle pizza");
		
		codeString = pizzeriaUtil.askAndCheckCode();
		nomString = pizzeriaUtil.askAndCheckName();
		prixString = pizzeriaUtil.askAndCheckPrice();
		categorie = pizzeriaUtil.askAndCheckCategorie();
		
		Pizza p = new Pizza(codeString, nomString, Double.parseDouble(prixString), categorie);
		try {
			dao.saveNewPizza(p);
		} catch (SavePizzaException e) {
			LOG.warn(e.getMessage());
			LOGERROR.error("Erreur", e);
		}
		
		return true;
	}
}
