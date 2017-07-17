package fr.pizzeria.ihm;

/**
 * Permet de sortir du menu
 * @author Pam
 *
 */
public class SortirOptionMenu extends OptionMenu {

	public SortirOptionMenu(String libelle) {
		super(libelle);
	}

	/**
	 * Affiche simplement un message de courtoisie indiquant que l'on quitte le menu.
	 * Note : pourra éventuellement être aggrémenté d'autres fonctionnalités plus tard.
	 */
	@Override
	public boolean execute() {
		
		System.out.println("Aurevoir ♪");
		
		return true;
	}

}
