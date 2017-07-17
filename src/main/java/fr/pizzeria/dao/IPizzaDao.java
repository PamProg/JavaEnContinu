package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exception.SavePizzaException;
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
	 * @return true si le stockage s'est bien passé, false sinon
	 * @throws SavePizzaException 
	 */
	boolean saveNewPizza(Pizza pizza) throws SavePizzaException;
	
	/**
	 * Met à jour une pizza qui existe déjà
	 * @param codePizza le code de la pizza à mettre à jour
	 * @param pizza
	 * @return true si la modification s'est bien passé, false sinon
	 */
	boolean updatePizza(String codePizza, Pizza pizza);
	
	/**
	 * Supprime une pizza
	 * @param codePizza le code de la pizza à supprimer
	 * @return true si la suppression s'est bien passé, false sinon
	 */
	boolean deletePizza(String codePizza);
}
