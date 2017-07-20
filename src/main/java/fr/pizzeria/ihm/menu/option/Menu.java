package fr.pizzeria.ihm.menu.option;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.ihm.PizzeriaAdminApp;

/**
 * Classe principale du projet, s'occupe de gérer chaque option du menu.
 * @author ETY15
 *
 */
public class Menu {

	// Déclaration des variables
	private String titre;
	private Map<Integer, OptionMenu> actions;
	
	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);
	
	public Menu(String titre) {
		this.titre = titre;
		actions = new HashMap<>();
	}
	
	/**
	 * Méthode principale, effectue les différents appellent nécessaires au fonctionnement de l'application
	 */
	public void manage() {
		initActions();
		
		int response = -1;
		
		// Afficher le menu tant qu'on ne sort pas (ie : response = 99)
		do {
			afficherMenu();
			
			response = PizzeriaAdminApp.getInput().nextInt();
			
			// TODO ne plus mettre un switch, simplement récupérer et ensuite gérer l'action par rapport à la saisie
			switch (response) {
				case 1: 
					LOG.info("Liste des pizzas");
					actions.get(0).execute();
					break; 
				case 2: 
					LOG.info("Ajout d'une nouvelle pizza");
					actions.get(1).execute();
					break; 
				case 3: 
					actions.get(0).execute();
					LOG.info("Mise à jour d'une pizza");
					actions.get(2).execute();
					break; 
				case 4: 
					actions.get(0).execute();
					LOG.info("Suppression d'une pizza");
					actions.get(3).execute();
					break; 
				case 99: 
					actions.get(4).execute();
					break; 
				default:
					LOG.warn("Saisie incorrecte");
					break;
			}
		} while (response != 99);
		
	}
	
	/**
	 * Initialise toutes nos OptionMenu
	 */
	private void initActions() {
		actions.put(0, new ListerPizzasOptionMenu("1. Lister les pizzas"));
		actions.put(1, new NouvellePizzaOptionMenu("2. Ajouter une nouvelle pizza"));
		actions.put(2, new MettreAJourPizzaOptionMenu("3. Mettre à jour une pizza"));
		actions.put(3, new SupprimePizzaOptionMenu("4. Supprimer une pizza"));
		actions.put(4, new SortirOptionMenu("99. Sortir"));
	}

	/**
	 * Affiche le menu principal de la pizzeria
	 */
	public void afficherMenu() {
		LOG.info(titre);
		
		for(OptionMenu o : actions.values()) {
			LOG.info(o.getLibelle());
		}
	}
	
	/**
	 * @return the actions
	 */
	public Map<Integer, OptionMenu> getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(Map<Integer, OptionMenu> actions) {
		this.actions = actions;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	
}
