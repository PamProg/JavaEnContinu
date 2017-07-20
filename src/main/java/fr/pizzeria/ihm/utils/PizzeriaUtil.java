package fr.pizzeria.ihm.utils;

/**
 * Classe contenant plusieurs méthodes utiles au projet.
 * Permet la factorisation d'une partie du code.
 */
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.CodeDontMatchException;
import fr.pizzeria.dao.exception.NameDontMatchException;
import fr.pizzeria.dao.exception.PriceDontMatchException;
import fr.pizzeria.ihm.PizzeriaAdminApp;
import fr.pizzeria.model.CategoriePizza;

/**
 * Classe permettant d'utiliser des méthodes utiles aux divers classes du projet
 * @author ETY15
 *
 */
public class PizzeriaUtil {

	private static final Logger LOG = LoggerFactory.getLogger(PizzeriaUtil.class);
	private static final Logger LOGERROR = LoggerFactory.getLogger("error-log");
	
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
		
		LOG.info("Veuillez saisir la catégorie");
		
		categorieString = PizzeriaAdminApp.getInput().next();
		
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
			LOG.info("Veuillez saisir le prix");
			try {
				prixString = PizzeriaAdminApp.getInput().next();
				
				// Vérifie que le prix soit positif, contienne uniquement des chiffres, 
				// 		et soit séparé par un et un seul point "."
				if(!prixString.matches("[+]?[0-9]+(\\.?[0-9]+)?")) { // On pourrait faire un tout petit peu mieux comme regex...
					throw new PriceDontMatchException();
				}
			} catch (PriceDontMatchException e) {
				LOG.warn(e.getMessage());
				LOGERROR.error("Erreur Price", e);
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
			LOG.info("Veuillez saisir le nom (sans espace)");
			try {
				nomString = PizzeriaAdminApp.getInput().next();
				
				// Vérifie que le nom ne contienne pas de chiffre
				if(!nomString.matches("[^0-9]*")) {
					throw new NameDontMatchException();
				}
			} catch (NameDontMatchException e) {
				LOG.warn(e.getMessage());
				LOGERROR.error("Erreur Name", e);
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
			LOG.info("Veuillez saisir le code");
			try {
				codeString = PizzeriaAdminApp.getInput().next();
				
				// Vérifie que le code soit composé de 3 lettres majuscules
				if(!codeString.matches("[A-Z]{3}")) {
					throw new CodeDontMatchException();
				}
			} catch (CodeDontMatchException e) {
				LOG.warn(e.getMessage());
				LOGERROR.error("Erreur Code", e);
				correct = false;
			}
		} while (!correct);
		
		return codeString;
	}
}
