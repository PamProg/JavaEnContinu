package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class PizzaDaoJDBCTest extends PizzaDaoTest {
	
	private static final String URL_H2 = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	private static Connection conn;
	
	@BeforeClass
	public static void setUpClass() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_H2);
		
		conn = DriverManager.getConnection(URL_H2);
		
		PreparedStatement statement = conn.prepareStatement("CREATE TABLE pizza ("
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
		pizzaDao = new PizzaDaoJDBC(DRIVER_H2);
		
		PreparedStatement statement = conn.prepareStatement("INSERT INTO pizza(id, code, nom, prix, categorie) "
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
		Statement statement = conn.createStatement();
		statement.execute("TRUNCATE TABLE pizza");
		statement.close();
	}
	
	@AfterClass
	public static void setDownClass() throws SQLException {
		Statement statement = conn.createStatement();
		statement.execute("DROP TABLE pizza IF EXISTS");
		statement.close();
		
		conn.close();
	}
}
