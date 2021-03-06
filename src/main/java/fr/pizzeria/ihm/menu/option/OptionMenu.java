package fr.pizzeria.ihm.menu.option;

/**
 * Classe abstraite représentant une option du menu.
 * @author Pam
 *
 */
public abstract class OptionMenu {

	private String libelle;
	
	public OptionMenu(String libelle) {
		this.libelle = libelle;
	}
	
	public abstract boolean execute();

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
