package fr.pizzeria.ihm.client;

import java.util.HashMap;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.dao.client.ClientDaoJpa;
import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.ihm.menu.Menu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.SortirOptionMenu;
import fr.pizzeria.ihm.menu.option.client.ConnexionOptionMenu;
import fr.pizzeria.ihm.menu.option.client.InscriptionOptionMenu;
import fr.pizzeria.ihm.utils.PizzeriaUtil;

public class PizzeriaClientApp {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzeria-console-objet-annotation");
		IClientDao clientDao = new ClientDaoJpa(emf);
		
		try (Scanner scanner = new Scanner(System.in)) {
			PizzeriaUtil pizzeriaUtil = new PizzeriaUtil(scanner);
			
			HashMap<Integer, OptionMenu> actions = new HashMap<>();
			actions.put(1, new InscriptionOptionMenu(clientDao, pizzeriaUtil));
			actions.put(2, new ConnexionOptionMenu(clientDao, pizzeriaUtil));
			actions.put(99, new SortirOptionMenu());
			
			Menu m = new Menu(clientDao, scanner, pizzeriaUtil, "***** Pizzeria Client *****");
			m.setActions(actions);
			m.manage();
		}
	}

}
