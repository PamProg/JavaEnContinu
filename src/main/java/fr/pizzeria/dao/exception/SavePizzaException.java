package fr.pizzeria.dao.exception;

/**
 * Exception jetée lorsqu'il y a une erreur dans la sauvegarde d'une nouvelle pizza.
 * @author Pam
 *
 */
@SuppressWarnings("serial")
public class SavePizzaException extends PizzaException {

	public SavePizzaException() {
		super("Erreur lors de la sauvegarde de la pizza. Pizza non sauvée.");
	}

	public SavePizzaException(String message, Throwable cause) {
		super(message, cause);
	}

	public SavePizzaException(String message) {
		super(message);
	}

	public SavePizzaException(Throwable cause) {
		super(cause);
	}

}
