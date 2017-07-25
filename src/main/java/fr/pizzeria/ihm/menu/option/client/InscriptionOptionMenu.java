package fr.pizzeria.ihm.menu.option.client;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.ihm.menu.option.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.utils.PizzeriaUtil;
import fr.pizzeria.model.Client;

public class InscriptionOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(NouvellePizzaOptionMenu.class);
	
	private IClientDao dao;
	private PizzeriaUtil pizzeriaUtil;

	public InscriptionOptionMenu(IClientDao clientDao, PizzeriaUtil pizzeriaUtil) {
		super("S'inscrire");
		this.dao = clientDao;
		this.pizzeriaUtil = pizzeriaUtil;
	}

	@Override
	public boolean execute() {

		String nom;
		String prenom;
		String email;
		String motDePasse;
		
		LOG.info("Inscription");
		
		nom = pizzeriaUtil.askAndCheckName();
		prenom = pizzeriaUtil.askAndCheckFirstName();
		email = pizzeriaUtil.askAndCheckEmail();
		motDePasse = pizzeriaUtil.askAndCheckPassword();
		
		Client c = new Client(nom, prenom, email, DigestUtils.sha512Hex(motDePasse));
		
		dao.saveNewClient(c);
		
		return false;
	}

}
