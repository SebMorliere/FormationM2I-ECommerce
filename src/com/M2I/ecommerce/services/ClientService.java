package com.M2I.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import com.M2I.ecommerce.beans.Client;
import com.M2I.ecommerce.exceptions.InvalidArgumentException;
import com.M2I.ecommerce.exceptions.MetierException;
import com.M2I.ecommerce.hibernate.HibernateUtils;
import com.M2I.ecommerce.types.EUserRole;
import com.M2I.ecommerce.types.StatutClient;
import com.M2I.ecommerce.validators.EmailValidator;
import com.google.common.base.Strings;

public class ClientService {

	private static int BCRYPT_WORKLOAD = 12;
	private static Session session = null;

	/**
	 * Singleton
	 */
	private static ClientService instance;

	/**
	 * Constructeur privé = personne ne peut faire de new ClientService()
	 */
	private ClientService() {
	}

	/**
	 * Seule méthode pour récupérer une instance (toujours la même) de
	 * ClientService
	 *
	 * @return toujours la même instance de ClientService
	 */
	public static ClientService get() {
		if (instance == null) {
			instance = new ClientService();
		}
		return instance;
	}

	public Client creerClient(String nom, String prenom, String email, String motDePasse, String codePostal,
			String pays, String ville, String nrue, String rue, String appartement, String telephone)
					throws InvalidArgumentException, MetierException {
		Client client = new Client();
		client = ClientService.get().validerCreation(email, motDePasse);
		client = ClientService.get().modifier(client, nom, prenom, email, codePostal, pays, ville, nrue, rue,
				appartement, telephone);

		session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		session.save(client);
		t.commit();
		session.close();

		return client;
	}

	public Client validerCreation(String email, String motDePasse) throws InvalidArgumentException, MetierException {

		List<String> validationMessages = new ArrayList<>();
		if (Strings.isNullOrEmpty(email)) {
			validationMessages.add("L'email ne peut être null ou vide");
		} else {
			if (!EmailValidator.validate(email)) {
				validationMessages.add("Le format de l'email est invalide");
			}
		}

		if (Strings.isNullOrEmpty(motDePasse)) {
			validationMessages.add("Le mot de passe ne peut être null ou vide");
		}

		if (validationMessages.size() > 0) {
			throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
		}
		session = HibernateUtils.getSession();
		Query query = session.createQuery("from Client where email =:email");
		query.setString("email", email);
		List<Client> clients = query.list();
		if (clients.size() != 0) {
			throw new MetierException("Cet email est déjà utilisé");
		}
		session.close();

		Client client = new Client();
		client.email = email;
		client.motDePasse = encodePassword(motDePasse);
		client.statutClient = StatutClient.PART;
		client.role = EUserRole.CLIENT;
		client.isSupprime = false;
		return client;
	}

	public Client modifier(Client client, String nom, String prenom, String email, String codePostal, String pays,
			String ville, String nrue, String rue, String appartement, String telephone)
					throws InvalidArgumentException {

		List<String> validationMessages = new ArrayList<>();
		if (client == null) {
			validationMessages.add("Le client ne peut être null");
		}
		if (validationMessages.size() > 0) {
			throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
		}

		if (client.nom != nom) {
			client.nom = nom;
		}
		if (client.prenom != prenom) {
			client.prenom = prenom;
		}
		if (client.email != email) {
			client.email = email;
		}
		if (client.codePostal != codePostal) {
			client.codePostal = codePostal;
		}
		if (client.pays != pays) {
			client.pays = pays;
		}
		if (client.ville != ville) {
			client.ville = ville;
		}
		if (client.nrue != nrue) {
			client.nrue = nrue;
		}
		if (client.rue != rue) {
			client.rue = rue;
		}
		if (client.appartement != appartement) {
			client.appartement = appartement;
		}
		if (client.telephone != telephone) {
			client.telephone = telephone;
		}
		return client;
	}

	private String encodePassword(String password) {
		String salt = BCrypt.gensalt(BCRYPT_WORKLOAD);
		return BCrypt.hashpw(password, salt);
	}

	public boolean authenticate(String email, String motDePasse) throws InvalidArgumentException {

		List<String> validationMessages = new ArrayList<>();
		if (Strings.isNullOrEmpty(email)) {
			validationMessages.add("L'email ne peut être null ou vide");
		}
		if (Strings.isNullOrEmpty(motDePasse)) {
			validationMessages.add("Le motDePasse ne peut être null ou vide");
		}
		if (validationMessages.size() > 0) {
			throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
		}
		session = HibernateUtils.getSession();
		Query query = session.createQuery("from Client where email =:email");
		query.setString("email", email);
		Client client = (Client) query.uniqueResult();
		if (client == null) {
			return false;
		}
		session.close();
		if (BCrypt.checkpw(motDePasse, client.motDePasse)) {
			return true;
		}
		return false;
	}

	public List<Client> lister(Boolean isSupprime) {
		List<Client> clients = new ArrayList<>();
		session = HibernateUtils.getSession();
		Query query = session.createQuery("from Client where isSupprime =:isSupprime");
		query.setBoolean("isSupprime", isSupprime);
		clients = query.list();
		session.close();
		return clients;
	}

	public void enregistrerModifClient(Client client) throws InvalidArgumentException {

		List<String> validationMessages = new ArrayList<>();
		if (client == null) {
			validationMessages.add("Le client ne peut être null ou vide");
		}
		if (validationMessages.size() > 0) {
			throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
		}

		session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		session.update(client);
		t.commit();
		session.close();
	}

	public void supprimer(Client client) throws InvalidArgumentException {
		if (client == null) {
			throw new InvalidArgumentException(new String[] { "Le client ne peut être null" });
		}
		client.isSupprime = true;
		session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		session.update(client);
		t.commit();
		session.close();
	}

	public Client getClient(String idClient) throws InvalidArgumentException {
		List<String> validationMessages = new ArrayList<>();
		if (Strings.isNullOrEmpty(idClient)) {
			validationMessages.add("L'id client ne peut être null ou vide");
		}
		if (validationMessages.size() > 0) {
			throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
		}
		session = HibernateUtils.getSession();
		Query query = session.createQuery("from Client where idClient =:idClient");
		query.setString("idClient", idClient);
		Client client = (Client) query.uniqueResult();
		session.close();
		return client;
	}

	public Client getClientByEmail(String email) throws InvalidArgumentException {
		List<String> validationMessages = new ArrayList<>();
		if (Strings.isNullOrEmpty(email)) {
			validationMessages.add("L'email ne peut être null ou vide");
		}
		if (validationMessages.size() > 0) {
			throw new InvalidArgumentException((String[]) validationMessages.toArray(new String[0]));
		}

		session = HibernateUtils.getSession();
		Query query = session.createQuery("from Client where email =:email");
		query.setString("email", email);
		Client client = (Client) query.uniqueResult();
		session.close();
		return client;
	}

	public void clear() {
		session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		session.createQuery("delete Client").executeUpdate();
		t.commit();
		session.close();
	}

}
