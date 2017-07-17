package fr.pizzeria.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Objet principal du projet. Contient toutes les informations relatives à une pizza.
 * @author ETY15
 *
 */
public class Pizza {

	private Integer id;
	@ToString
	private String code;
	@ToString
	private String nom;
	@ToString
	private double prix;
	private CategoriePizza categorie;
	
	private static Integer idGenerator = 0;
	
	
	/**
	 * Créé une instance de pizza. Génère un id grâce à l'attribut d'instance idGenerator.
	 * @param code
	 * @param nom
	 * @param prix
	 */
	public Pizza(String code, String nom, double prix, CategoriePizza categorie) {
		this.id = idGenerator++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}


	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}


	/**
	 * @return the categorie
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}


	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * Permet d'afficher la valeur des attributs de classe.
	 * Note : ici, n'affiche que les attributs ayant l'annotation ToString
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
//		for(Field field : this.getClass().getDeclaredFields()) {
//			if(field.getDeclaredAnnotation(ToString.class) != null) {
//				try {
//					Object obj = field.get(this);
//					sb.append(obj.toString());
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		// Pour chaque attribut
		for(Field field : this.getClass().getDeclaredFields()) {
			// Pour chaque annotation (de chaque attribut)
			for(Annotation annot : field.getDeclaredAnnotations()) {
				// Si l'annotation est "ToString"...
				if(annot instanceof ToString) {
					Object obj;
					try {
						obj = field.get(this);
						// ...ajouter le nom de l'attribut (pour l'afficher)
						sb.append(obj.toString());
						sb.append(" ");
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
		
//		return "Pizza [id=" + id + ", code=" + code + ", nom=" + nom + ", prix=" + prix + ", categorie=" + categorie + "]";
		// Renvoie le StringBuilder contenant tout les noms d'attributs.
		return sb.toString();
	}
	
	
	
	
}
