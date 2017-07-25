package fr.pizzeria.ihm.menu.option.client;

import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.utils.PizzeriaUtil;

public class CommanderPizzaOptionMenu extends OptionMenu {

	private IClientDao dao;
	private PizzeriaUtil pizzeriaUtil;

	public CommanderPizzaOptionMenu(IClientDao dao, PizzeriaUtil pizzeriaUtil) {
		super("Commander une pizza");
		this.dao = dao;
		this.pizzeriaUtil = pizzeriaUtil;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

}
