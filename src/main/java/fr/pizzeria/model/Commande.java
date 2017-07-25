package fr.pizzeria.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Commande {

	@Id @ GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero_commande")
	private String numeroCommande;
	@Enumerated(EnumType.STRING)
	private Statut statut;
	@Column(name = "date_commande")
	private LocalDateTime dateCommande;
	
	@ManyToOne @JoinColumn(name = "livreur_id")
	private Livreur livreur;
	@ManyToOne @JoinColumn(name = "client_id")
	private Client client;
	
	@ManyToMany
	@JoinTable(name = "commande_pizza",
		joinColumns = @JoinColumn(name = "commande_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id")
	)
	private List<Pizza> pizzas;
	
	public Commande() {
		
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
	 * @return the numeroCommande
	 */
	public String getNumeroCommande() {
		return numeroCommande;
	}
	/**
	 * @param numeroCommande the numeroCommande to set
	 */
	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}
	/**
	 * @return the statut
	 */
	public Statut getStatut() {
		return statut;
	}
	/**
	 * @param statut the statut to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	/**
	 * @return the dateCommande
	 */
	public LocalDateTime getDateCommande() {
		return dateCommande;
	}
	/**
	 * @param dateCommande the dateCommande to set
	 */
	public void setDateCommande(LocalDateTime dateCommande) {
		this.dateCommande = dateCommande;
	}
	/**
	 * @return the livreur
	 */
	public Livreur getLivreur() {
		return livreur;
	}

	/**
	 * @param livreur the livreur to set
	 */
	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

}
