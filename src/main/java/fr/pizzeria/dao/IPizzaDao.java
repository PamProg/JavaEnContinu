package fr.pizzeria.dao;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * Interface qui lie le projet à une potentielle base de données.
 * Va faire la transition entre les "modèles" et le "stockage".
 * @author ETY15
 *
 */
public interface IPizzaDao {

	/**
	 * Récupère l'ensemble des pizzas
	 * @return la liste des pizzas stockées
	 */
	List<Pizza> findAllPizzas();
	
	/**
	 * Sauve la pizza (selon la classe qui implémente l'interface : mémoire, fichier, base de données...)
	 * @param pizza la pizza à stocker
	 * @throws SavePizzaException 
	 */
	void saveNewPizza(Pizza pizza) throws SavePizzaException;
	
	/**
	 * Met à jour une pizza qui existe déjà
	 * @param codePizza le code de la pizza à mettre à jour
	 * @param pizza
	 */
	void updatePizza(String codePizza, Pizza pizza);
	
	/**
	 * Supprime une pizza
	 * @param codePizza le code de la pizza à supprimer
	 */
	void deletePizza(String codePizza);
	
	
	default void initPizza(List<Pizza> pizzas) {
		throw new NotImplementedException("Initialisation des pizzas non implémentée");
	}
}
