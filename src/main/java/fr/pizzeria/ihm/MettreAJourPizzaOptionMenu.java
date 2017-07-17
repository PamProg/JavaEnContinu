package fr.pizzeria.ihm;

import fr.pizzeria.console.PizzeriaAdmin;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.PizzeriaUtil;

/**
 * Permet de mettre à jour une pizza
 * @author Pam
 *
 */
public class MettreAJourPizzaOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	
	public MettreAJourPizzaOptionMenu(String libelle) {
		super(libelle);
	}

	/**
	 * Récupère le code de la pizza à modifier puis récupère les informations
	 * de l'utilisateur. Si elles sont correctes, met à jour la pizza.
	 * Note : ne fait rien si le code entré n'existe pas.
	 */
	@Override
	public boolean execute() {
		
		dao = new PizzaDaoMemoire();
		
		System.out.println("Veuillez choisir le code de la pizza à modifier.");
		System.out.println("(99 pour abandonner)");
		
		String codeChosen = PizzeriaAdmin.getInput().next();
		
		if(!codeChosen.equals("99")) {
		
			String codeString;
			String nomString;
			String prixString;
			CategoriePizza categorie;
			
			codeString = PizzeriaUtil.askAndCheckCode();
			nomString = PizzeriaUtil.askAndCheckName();
			prixString = PizzeriaUtil.askAndCheckPrice();
			categorie = PizzeriaUtil.askAndCheckCategorie();
			
			Pizza p = new Pizza(codeString, nomString, Double.parseDouble(prixString), categorie);
			dao.updatePizza(codeChosen, p);
		}
		
		return true;
	}

}
