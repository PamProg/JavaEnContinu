package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBCTest {
	
//	protected static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	protected static final String DRIVER_H2 = "org.h2.Driver";
	private static final String URL_H2 = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	private PizzaDaoJDBC pizzaDao = new PizzaDaoJDBC(DRIVER_H2);
	private static Connection conn;
	
	@BeforeClass
	public static void setUpClass() throws ClassNotFoundException, SQLException {
//		Class.forName(DRIVER_MYSQL);
		
		conn = DriverManager.getConnection(URL_H2);
		
		PreparedStatement statement = conn.prepareStatement("CREATE TABLE Pizza ("
				+ "id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY,"
				+ "code VARCHAR(3) NOT NULL,"
				+ "nom VARCHAR(32) NOT NULL,"
				+ "prix DOUBLE(4) NOT NULL,"
				+ "categorie VARCHAR(16)"
				+ ")");
		statement.execute();
	}
	
	@Before
	public void setUp() throws SQLException {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO Pizza(id, code, nom, prix, categorie) "
				  + "VALUES(NULL,?,?,?,?)");
		statement.setString(1, "FRO");
		statement.setString(2, "4 Fromages");
		statement.setString(3, "12.5");
		statement.setString(4, "FROMAGE");
		statement.executeUpdate();
		
		statement.close();
	}
	
	
	@After
	public void setDown() throws SQLException {
		conn = DriverManager.getConnection(URL_H2);
		
		PreparedStatement statement = conn.prepareStatement("TRUNCATE TABLE Pizza");
		statement.execute();
		
		statement.close();
	}
	
	@AfterClass
	public static void setDownClass() throws SQLException {
		conn.close();
	}
	
	
	@Test
	public void testFindAllPizzas() throws SQLException {
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertThat(pizzas.size()).isEqualTo(1);
	}
	
	@Test
	public void testSaveNewPizza() throws SQLException, SavePizzaException {
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDao.saveNewPizza(p);
		
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertThat(pizzas.size()).isEqualTo(2);
	}
	
	@Test(expected = SavePizzaException.class)
	public void testSaveNewPizzaException() throws SQLException, SavePizzaException {
		Pizza p = new Pizza("FRO", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDao.saveNewPizza(p);
	}
	
	@Test
	public void testUpdatePizza() throws SQLException {
		String code = "FRO";
		Pizza p = new Pizza("MOZ", "Mozzarella", 13, CategoriePizza.FROMAGE);
		pizzaDao.updatePizza(code, p);
		
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertThat(pizzas.get(0).getCode()).isEqualTo(p.getCode());
	}
}
