package fr.pizzeria.exception;

/**
 * Exception jetée lorsqu'il y a une erreur dans la sauvegarde d'une nouvelle pizza.
 * @author Pam
 *
 */
@SuppressWarnings("serial")
public class SavePizzaException extends Exception {

	public SavePizzaException() {
		super("Erreur lors de la sauvegarde de la pizza. Pizza non sauvée.");
	}
	
	public SavePizzaException(String message) {
		super(message);
	}
}
