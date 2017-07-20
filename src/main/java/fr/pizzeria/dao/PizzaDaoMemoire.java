package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Permet de lister, sauvegarder, modifier ou supprimer les pizzas en mémoire.
 * {@link IPizzaDao}
 * @author ETY15
 *
 */
public class PizzaDaoMemoire implements IPizzaDao {

	private List<Pizza> pizzas = new ArrayList<>();
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoire.class);
	
	/**
	 * Initialise les pizzas originelles
	 */
	@Override
	public void initPizzas(List<Pizza> pizzas) {
		
		LOG.info("Initialisation des pizzas...");
		this.pizzas = pizzas;
		LOG.info("...pizzas initialisées");
	}
	
	/**
	 * {@link IPizzaDao}
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		LOG.debug("Récupération des pizzas");
		return new ArrayList<Pizza>(pizzas);
	}

	/**
	 * Sauve la pizza en mémoire si le code entré n'existe pas encore.
	 * Renvoie une exception sinon.
	 * {@link IPizzaDao}
	 */
	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		
		LOG.debug("Préparation à la sauvegarde d'une nouvelle pizza...");
		
		for(Pizza p : pizzas) {
			// On vérifie que le code n'existe pas déjà parmi les pizzas
			if(p.getCode().equals(pizza.getCode())) {
				throw new SavePizzaException(("Erreur : Le code de la pizza existe déjà. Pizza non sauvée."));
			}
		}
		
		pizzas.add(pizza);
		LOG.debug("...pizza sauvegardée");
	}

	/**
	 * Met à jour la pizza de code codePizza
	 * {@link IPizzaDao}
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		
		LOG.debug("Préparation à la mise à jour d'une pizza...");
		
		for(Pizza p : pizzas) {
			if(p.getCode().equals(codePizza)) {
				p.setCode(pizza.getCode());
				p.setNom(pizza.getNom());
				p.setPrix(pizza.getPrix());
				p.setCategorie(pizza.getCategorie());
			}
		}
		
		LOG.debug("...pizza mise à jour");
	}

	/**
	 * Supprime la pizza de code codePizza.
	 * Note : rien ne se passe si le code entré n'existe pas.
	 * {@link IPizzaDao}
	 */
	@Override
	public void deletePizza(String codePizza) {
		
		LOG.debug("Préparation à la suppression d'une pizza...");
		
		for(Pizza p : pizzas) {
			if(p.getCode().equals(codePizza)) {
				pizzas.remove(p);
			}
		}
		
		LOG.debug("...pizza supprimée");
	}
}
