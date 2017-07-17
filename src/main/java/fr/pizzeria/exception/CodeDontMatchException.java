package fr.pizzeria.exception;

/**
 * Exception jetée lorsque le code de pizza ne contient pas exactement 3 lettres majuscules.
 * {@link : MettreAJourPizzaOptionMenu, NouvellePizzaOptionMenu}
 * @author Pam
 *
 */
@SuppressWarnings("serial")
public class CodeDontMatchException extends Exception {
	
	public CodeDontMatchException() {
		super("Le code doit contenir exactement 3 lettres majuscules");
	}
	
	public CodeDontMatchException(String message) {
		super(message);
	}

}
