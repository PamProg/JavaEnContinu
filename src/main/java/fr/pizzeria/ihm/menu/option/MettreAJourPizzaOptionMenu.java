package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.CodeDontMatchException;
import fr.pizzeria.dao.exception.NameDontMatchException;
import fr.pizzeria.dao.exception.PriceDontMatchException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Permet de mettre à jour une pizza
 * @author Pam
 *
 */
public class MettreAJourPizzaOptionMenu extends OptionMenu {

	private IPizzaDao dao;
	private Scanner scanner;
	private static final Logger LOG = LoggerFactory.getLogger(MettreAJourPizzaOptionMenu.class);
	private static final Logger LOGERROR = LoggerFactory.getLogger("error-log");
	
	public MettreAJourPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scanner) {
		super("Mettre à jour une pizza");
		dao = pizzaDao;
		this.scanner = scanner;
	}

	/**
	 * Récupère le code de la pizza à modifier puis récupère les informations
	 * de l'utilisateur. Si elles sont correctes, met à jour la pizza.
	 * Note : ne fait rien si le code entré n'existe pas.
	 */
	@Override
	public boolean execute() {
		
		// On liste d'abord les pizzas
		for(Pizza p : dao.findAllPizzas()) {
			LOG.info(p.toString());
		}
		
		LOG.info("Mise à jour d'une pizza");
		LOG.info("Veuillez choisir le code de la pizza à modifier.");
		LOG.info("(99 pour abandonner)");
		
		String codeChosen = scanner.next();
		
		if(!("99").equals(codeChosen)) {
		
			String codeString;
			String nomString;
			String prixString;
			CategoriePizza categorie;
			
			codeString = askAndCheckCode();
			nomString = askAndCheckName();
			prixString = askAndCheckPrice();
			categorie = askAndCheckCategorie();
			
			Pizza p = new Pizza(codeString, nomString, Double.parseDouble(prixString), categorie);
			dao.updatePizza(codeChosen, p);
		}
		
		return true;
	}
	
	/**
	 * Demande le code de la pizza et vérifie qu'il soit valide
	 * @return codeString le code valide
	 */
	public String askAndCheckCode() {
		String codeString = null;
		boolean correct;
		// Boucle tant que le code entré n'est pas bon
		do {
			correct = true;
			LOG.info("Veuillez saisir le code");
			try {
				codeString = scanner.next();
				
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
	
	/**
	 * Demande le nom de la pizza et vérifie qu'il soit valide
	 * @return nomString le nom valide
	 */
	public String askAndCheckName() {
		String nomString = null;
		boolean correct;
		// Boucle tant que le nom entré n'est pas bon
		do {
			correct = true;
			LOG.info("Veuillez saisir le nom (sans espace)");
			try {
				nomString = scanner.next();
				
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
	 * Demande le prix de la pizza et vérifie qu'il soit valide
	 * @return prixString le prix valide
	 */
	public String askAndCheckPrice() {
		String prixString = null;
		boolean correct;
		// Boucle tant que le prix entré n'est pas bon
		do {
			correct = true;
			LOG.info("Veuillez saisir le prix");
			try {
				prixString = scanner.next();
				
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
	 * Demande la catégorie de la pizza et vérifie qu'elle soit valide
	 * Note : ne vérifie pas encore que la catégorie soit valide.
	 * Cependant, si la valeur entrée est à 2 ou moins de "distance" d'une
	 * des catégories, alors la catégorie la plus proche est sélectionnée.
	 * @return categorieString le prix valide
	 */
	public CategoriePizza askAndCheckCategorie() {
		String categorieString = null;
		CategoriePizza cat = null;
		
		LOG.info("Veuillez saisir la catégorie");
		
		categorieString = scanner.next();
		
		CategoriePizza[] categories = CategoriePizza.values();
		for(CategoriePizza c : categories) {
			if(LevenshteinDistance.getDefaultInstance().apply(categorieString, c.name()) <= 2) {
				cat = c;
			}
		}
		return cat;
	}
}
