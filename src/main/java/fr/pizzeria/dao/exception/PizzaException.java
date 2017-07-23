package fr.pizzeria.dao.exception;

@SuppressWarnings("serial")
public class PizzaException extends RuntimeException {

	public PizzaException() {
		super();
	}

	public PizzaException(String message, Throwable cause) {
		super(message, cause);
	}

	public PizzaException(String message) {
		super(message);
	}

	public PizzaException(Throwable cause) {
		super(cause);
	}

}
