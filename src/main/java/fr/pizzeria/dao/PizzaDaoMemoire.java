package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Permet de lister, sauvegarder, modifier ou supprimer les pizzas en mémoire.
 * {@link IPizzaDao}
 * @author ETY15
 *
 */
public class PizzaDaoMemoire implements IPizzaDao {

	private static List<Pizza> pizzas;
	private static final Logger LOGEXEC = LoggerFactory.getLogger("execution-log");
	
	/**
	 * Initialise les pizzas originelles
	 */
	public static void initPizzas() {
		
		pizzas = new ArrayList<>();
		
		LOGEXEC.debug("Initialisation des pizzas...");
		
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.FROMAGE));
		pizzas.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VEGETARIEN));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGE));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.FROMAGE));
		pizzas.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VEGETARIEN));
		pizzas.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		
		LOGEXEC.debug("...pizzas initialisées");
	}
	
	/**
	 * {@link IPizzaDao}
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		LOGEXEC.debug("Récupération des pizzas");
		return pizzas;
	}

	/**
	 * Sauve la pizza en mémoire si le code entré n'existe pas encore.
	 * Renvoie une exception sinon.
	 * {@link IPizzaDao}
	 */
	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {
		
		LOGEXEC.debug("Préparation à la sauvegarde d'une nouvelle pizza...");
		
		for(Pizza p : pizzas) {
			// On vérifie que le code n'existe pas déjà parmi les pizzas
			if(pizzas.get(p.getId()).getCode().equals(pizza.getCode())) {
				throw new SavePizzaException(("Erreur : Le code de la pizza existe déjà. Pizza non sauvée"));
			}
		}
		
		pizzas.add(pizza);
		LOGEXEC.debug("...pizza sauvegardée");
		return true;
	}

	/**
	 * Met à jour la pizza de code codePizza
	 * {@link IPizzaDao}
	 */
	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		
		LOGEXEC.debug("Préparation à la mise à jour d'une pizza...");
		
		for(Pizza p : pizzas) {
			if(p.getCode().equals(codePizza)) {
				pizzas.get(p.getId()).setCode(pizza.getCode());
				pizzas.get(p.getId()).setNom(pizza.getNom());
				pizzas.get(p.getId()).setPrix(pizza.getPrix());
				pizzas.get(p.getId()).setCategorie(pizza.getCategorie());
			}
		}
		
		LOGEXEC.debug("...pizza mise à jour");
		return true;
	}

	/**
	 * Supprime la pizza de code codePizza.
	 * Note : rien ne se passe si le code entré n'existe pas.
	 * {@link IPizzaDao}
	 */
	@Override
	public boolean deletePizza(String codePizza) {
		
		LOGEXEC.debug("Préparation à la suppression d'une pizza...");
		
		for(Pizza p : pizzas) {
			if(pizzas.get(p.getId()).getCode().equals(codePizza)) {
				pizzas.remove(p);
			}
		}
		
		LOGEXEC.debug("...pizza supprimée");
		return true;
	}
}
