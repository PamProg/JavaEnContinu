package fr.pizzeria.ihm.menu.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Permet de sortir du menu
 * @author Pam
 *
 */
public class SortirOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(SortirOptionMenu.class);
	
	public SortirOptionMenu(String libelle) {
		super("99. Sortir");
	}

	/**
	 * Affiche simplement un message de courtoisie indiquant que l'on quitte le menu.
	 * Note : pourra éventuellement être aggrémenté d'autres fonctionnalités plus tard.
	 */
	@Override
	public boolean execute() {
		
		LOG.info("Aurevoir ♪");
		
		return true;
	}

}
