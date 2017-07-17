package fr.pizzeria.exception;

/**
 * Exception jetée lorsque le prix de la pizza ne correspond pas à un double.
 * {@link : MettreAJourPizzaOptionMenu, NouvellePizzaOptionMenu}
 * @author Pam
 *
 */
@SuppressWarnings("serial")
public class PriceDontMatchException extends Exception {

	public PriceDontMatchException() {
		super("Le prix doit être positif et doit contenir uniquement des chiffres, séparés par un et un seul point \".\"");
	}
	
	public PriceDontMatchException(String message) {
		super(message);
	}
}
