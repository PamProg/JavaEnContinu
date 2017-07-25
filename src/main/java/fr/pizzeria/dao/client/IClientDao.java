package fr.pizzeria.dao.client;

import java.util.List;

import fr.pizzeria.model.Client;

public interface IClientDao {

	
	List<Client> findAllClient();
	
	void saveNewClient(Client client);
	
	void updateClient(Integer idClient, Client client);
	
	void deleteClient(Integer idClient);
	
	boolean verifyClientRegistred(String email, String motDePasse);
}
