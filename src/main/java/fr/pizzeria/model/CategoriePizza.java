package fr.pizzeria.model;

/**
 * Représente une catégorie de pizza
 * @author Pam
 *
 */
public enum CategoriePizza {
	
	VIANDE("Viande"), FROMAGE("Fromage"), VEGETARIEN("Végétarien");
	
	String libelle;
	
	private CategoriePizza(String libelle) {
		this.libelle = libelle;
	}

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
