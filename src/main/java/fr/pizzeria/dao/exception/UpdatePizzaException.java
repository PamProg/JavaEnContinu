package fr.pizzeria.dao.exception;

@SuppressWarnings("serial")
public class UpdatePizzaException extends PizzaException {

	public UpdatePizzaException() {
		super();
	}

	public UpdatePizzaException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdatePizzaException(String message) {
		super(message);
	}

	public UpdatePizzaException(Throwable cause) {
		super(cause);
	}
	
}
