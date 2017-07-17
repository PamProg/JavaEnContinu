package fr.pizzeria.ihm;

import fr.pizzeria.console.PizzeriaAdmin;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoMemoire;

/**
 * Permet de supprimer une pizza
 * @author Pam
 *
 */
public class SupprimePizzaOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	
	public SupprimePizzaOptionMenu(String libelle) {
		super(libelle);
	}

	/**
	 * Récupère le code entré par l'utilisateur et supprime la pizza qui lui est relié.
	 * Note : ne fait rien si le code entré n'existe pas.
	 */
	@Override
	public boolean execute() {
		
		dao = new PizzaDaoMemoire();
		
		System.out.println("Veuillez choisir le code de la pizza à supprimer.");
		System.out.println("(99 pour abandonner)");
		
		String codeChosen = PizzeriaAdmin.getInput().next();
		
		if(!codeChosen.equals("99")) {
			dao.deletePizza(codeChosen);
		}
		
		return true;
	}

}
