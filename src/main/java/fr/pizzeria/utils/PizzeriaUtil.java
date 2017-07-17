package fr.pizzeria.utils;

/**
 * Classe contenant plusieurs méthodes utiles au projet.
 * Permet la factorisation d'une partie du code.
 */
import org.apache.commons.text.similarity.LevenshteinDistance;

import fr.pizzeria.console.PizzeriaAdmin;
import fr.pizzeria.exception.CodeDontMatchException;
import fr.pizzeria.exception.NameDontMatchException;
import fr.pizzeria.exception.PriceDontMatchException;
import fr.pizzeria.model.CategoriePizza;

/**
 * Classe permettant d'utiliser des méthodes utiles au divers classes du projet
 * @author ETY15
 *
 */
public class PizzeriaUtil {

	
	/**
	 * Demande la catégorie de la pizza et vérifie qu'elle soit valide
	 * Note : ne vérifie pas encore que la catégorie soit valide.
	 * Cependant, si la valeur entrée est à 2 ou moins de "distance" d'une
	 * des catégories, alors la catégorie la plus proche est sélectionnée.
	 * @return categorieString le prix valide
	 */
	public static CategoriePizza askAndCheckCategorie() {
		String categorieString = null;
		CategoriePizza cat = null;
		
		System.out.println("Veuillez saisir la catégorie");
		
		categorieString = PizzeriaAdmin.getInput().next();
		
		CategoriePizza[] categories = CategoriePizza.values();
		for(CategoriePizza c : categories) {
			if(LevenshteinDistance.getDefaultInstance().apply(categorieString, c.name()) <= 2) {
				cat = c;
			}
		}
		
		return cat;
	}
	
	/**
	 * Demande le prix de la pizza et vérifie qu'il soit valide
	 * @return prixString le prix valide
	 */
	public static String askAndCheckPrice() {
		String prixString = null;
		boolean correct;
		// Boucle tant que le prix entré n'est pas bon
		do {
			correct = true;
			System.out.println("Veuillez saisir le prix");
			try {
				prixString = PizzeriaAdmin.getInput().next();
				
				// Vérifie que le prix soit positif, contienne uniquement des chiffres, 
				// 		et soit séparé par un et un seul point "."
				if(!prixString.matches("[+]?[0-9]+(\\.?[0-9]+)?")) { // On pourrait faire un tout petit peu mieux comme regex...
					throw new PriceDontMatchException();
				}
			} catch (PriceDontMatchException e) {
				System.err.println(e.getMessage());
				correct = false;
			}
		} while (!correct);
		
		return prixString;
	}
	
	/**
	 * Demande le nom de la pizza et vérifie qu'il soit valide
	 * @return nomString le nom valide
	 */
	public static String askAndCheckName() {
		String nomString = null;
		boolean correct;
		// Boucle tant que le nom entré n'est pas bon
		do {
			correct = true;
			System.out.println("Veuillez saisir le nom (sans espace)");
			try {
				nomString = PizzeriaAdmin.getInput().next();
				
				// Vérifie que le nom ne contienne pas de chiffre
				if(!nomString.matches("[^0-9]*")) {
					throw new NameDontMatchException();
				}
			} catch (NameDontMatchException e) {
				System.err.println(e.getMessage());
				correct = false;
			}
		} while (!correct);
		
		return nomString;
	}
	
	/**
	 * Demande le code de la pizza et vérifie qu'il soit valide
	 * @return codeString le code valide
	 */
	public static String askAndCheckCode() {
		String codeString = null;
		boolean correct;
		// Boucle tant que le code entré n'est pas bon
		do {
			correct = true;
			System.out.println("Veuillez saisir le code");
			try {
				codeString = PizzeriaAdmin.getInput().next();
				
				// Vérifie que le code soit composé de 3 lettres majuscules
				if(!codeString.matches("[A-Z]{3}")) {
					throw new CodeDontMatchException();
				}
			} catch (CodeDontMatchException e) {
				System.err.println(e.getMessage());
				correct = false;
			}
		} while (!correct);
		
		return codeString;
	}
}
