package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBC implements IPizzaDao {

	private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	private static final String URL_H2 = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJDBC.class);

	public PizzaDaoJDBC() {
		try {
			Class.forName(DRIVER_MYSQL);
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public PizzaDaoJDBC(String driver) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> pizzas = new ArrayList<>();

		LOG.debug("Récupération des pizzas...");

		String query = "SELECT * FROM pizza";

		try (Connection conn = DriverManager.getConnection(URL_H2);
				PreparedStatement statement = conn.prepareStatement(query);
				ResultSet res = statement.executeQuery()) {

			while (res.next()) {
				Integer id = res.getInt("id");
				String code = res.getString("code");
				String libelle = res.getString("nom");
				double prix = res.getDouble("prix");
				CategoriePizza cat = CategoriePizza.valueOf(res.getString("categorie"));
				Pizza p = new Pizza(id, code, libelle, prix, cat);
				pizzas.add(p);
			}

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		LOG.debug("...pizzas récupérées.");
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		LOG.debug("Préparation à la sauvegarde d'une nouvelle pizza...");

		List<Pizza> pizzas = findAllPizzas();
		pizzas.stream().filter(p -> p.getCode().equals(pizza.getCode())).findAny().ifPresent(p -> {
			throw new SavePizzaException("Erreur : Le code de la pizza existe déjà. Pizza non sauvée.");
		});

		String query = "INSERT INTO pizza(id, code, nom, prix, categorie) VALUES(NULL,?,?,?,?)";

		try (Connection conn = DriverManager.getConnection(URL_H2);
				PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getNom());
			statement.setString(3, String.valueOf(pizza.getPrix()));
			statement.setString(4, String.valueOf(pizza.getCategorie()));
			statement.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}

		LOG.debug("...pizza sauvegardée");
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		LOG.debug("Préparation à la modification d'une pizza...");

		String updateQuery = "UPDATE pizza SET code=?, nom=?, categorie=? WHERE code=?";
		
		try (Connection conn = DriverManager.getConnection(URL_H2);
			 PreparedStatement statement = conn.prepareStatement(updateQuery)) {
			
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getNom());
			statement.setString(3, String.valueOf(pizza.getCategorie()));
			statement.setString(4, codePizza);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		
		LOG.debug("...pizza modifiée");
	}

	@Override
	public void deletePizza(String codePizza) {
		LOG.debug("Préparation à la suppression d'une pizza...");
		
		String updateQuery = "DELETE FROM pizza WHERE code=?";
		
		try (Connection conn = DriverManager.getConnection(URL_H2);
			 PreparedStatement statement = conn.prepareStatement(updateQuery)) {
			
			statement.setString(1, codePizza);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		
		LOG.debug("...pizza supprimée");
	}
}
