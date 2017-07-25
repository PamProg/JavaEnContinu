package fr.pizzeria.ihm.menu.option.client;

import java.util.HashMap;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.ihm.menu.Menu;
import fr.pizzeria.ihm.menu.option.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.SortirOptionMenu;
import fr.pizzeria.ihm.utils.PizzeriaUtil;

public class ConnexionOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(NouvellePizzaOptionMenu.class);
	
	private IClientDao dao;
	private Scanner scanner;
	private PizzeriaUtil pizzeriaUtil;
	
	private Menu menu;
	
	public ConnexionOptionMenu(IClientDao clientDao, PizzeriaUtil pizzeriaUtil) {
		super("Se connecter");
		this.dao = clientDao;
		this.pizzeriaUtil = pizzeriaUtil;
	}
	
	@Override
	public boolean execute() {

		String email;
		String motDePasse;
		Boolean connexionValide;
		
		LOG.info("Inscription");
		
		email = pizzeriaUtil.askAndCheckEmail();
		motDePasse = pizzeriaUtil.askAndCheckPassword();
		
		connexionValide = dao.verifyClientRegistred(email, motDePasse);
		
		if(!connexionValide) {
			return false;
		}
		
		HashMap<Integer, OptionMenu> actions = new HashMap<>();
		actions.put(1, new CommanderPizzaOptionMenu(dao, pizzeriaUtil));
		actions.put(2, new ListerCommandesOptionMenu(dao, pizzeriaUtil));
		actions.put(99, new SortirOptionMenu());
		
		menu = new Menu(dao, scanner, pizzeriaUtil, "***** Pizzeria Client *****");
		menu.setActions(actions);
		menu.manage();
		
		return true;
	}

}
