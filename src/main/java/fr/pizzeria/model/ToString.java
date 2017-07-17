package fr.pizzeria.model;

/**
 * Annotation permettant d'afficher ou non les attributs lorsque
 * l'on appelle la méthode toString() sur une instance de la classe.
 */
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ToString {

}
