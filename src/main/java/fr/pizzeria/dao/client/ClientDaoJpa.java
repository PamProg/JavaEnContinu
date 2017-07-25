package fr.pizzeria.dao.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoJpa;
import fr.pizzeria.model.Client;

public class ClientDaoJpa implements IClientDao {

	private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJpa.class);
	
	private EntityManagerFactory emf;

	public ClientDaoJpa(EntityManagerFactory emf) {
		this.emf = emf;
		
		try {
			Class.forName(DRIVER_MYSQL);
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	public ClientDaoJpa(EntityManagerFactory emf, String driver) {
		this.emf = emf;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public List<Client> findAllClient() {
		EntityManager em = emf.createEntityManager();
		
		LOG.debug("Récupération des clients...");
		TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);
		LOG.debug("...clients récupérées.");
		
		List<Client> list = query.getResultList(); 
		em.close();
		return list;
	}

	@Override
	public void saveNewClient(Client client) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		LOG.debug("Préparation à la sauvegarde d'un nouveau client...");
		em.persist(client);
		LOG.debug("...pizza sauvegardée");
		et.commit(); // TODO gérer le rollback
		em.close();
	}

	@Override
	public void updateClient(Integer idClient, Client client) {

	}

	@Override
	public void deleteClient(Integer idClient) {

	}

	@Override
	public boolean verifyClientRegistred(String email, String motDePasse) {
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Client> query = em.createQuery("select c from Client c where c.email=:email", Client.class)
									 .setParameter("email", email);
		Client c = query.getSingleResult();
		
		if(!DigestUtils.sha512Hex(motDePasse).equals(c.getMotDePasse())) {
			return false;
		}
		
		em.close();
		return true;
	}

}
