package fr.pizzeria.exception;

/**
 * Exception jetée lorsque le nom de la pizza contient au moins un chiffre.
 * {@link : MettreAJourPizzaOptionMenu, NouvellePizzaOptionMenu}
 * @author Pam
 *
 */
@SuppressWarnings("serial")
public class NameDontMatchException extends Exception {

	public NameDontMatchException() {
		super("Le nom ne doit pas contenir de chiffre");
	}
	
	public NameDontMatchException(String message) {
		super(message);
	}
}
