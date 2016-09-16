package com.M2I.ecommerce.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.M2I.ecommerce.beans.Client;
import com.M2I.ecommerce.exceptions.InvalidArgumentException;
import com.M2I.ecommerce.exceptions.MetierException;
import com.M2I.ecommerce.hibernate.HibernateUtils;
import com.M2I.ecommerce.hibernate.InitDBTest;
import com.M2I.ecommerce.services.ClientService;

public class ClientServiceTest {

	@BeforeClass
	public static void avantClass() {
		HibernateUtils.HibernateTest();
		InitDBTest.clientVide();
		System.out.println("Lancement des tests ClientServiceTest\n");
	}

	@Before
	public void avantTest() {
		ClientService.get().clear();
		System.out.println("base de donnée effacée !\n");
	}

	@AfterClass
	public static void apresClasse() {
		ClientService.get().clear();
	}

	@Test
	public void validerCreationEmailNullorEmptyTest() {
		String email = null;
		String motDePasse = "yolotest";
		try {
			Client client = ClientService.get().validerCreation(email, motDePasse);
			fail("fail - la création a été autorisée avec email vide");
		} catch (InvalidArgumentException e) {
			assertTrue(e.getRealMessage().contains("L'email ne peut être null ou vide"));
		} catch (MetierException e) {
			fail("fail - l'erreur métier a été levée\n -> e : " + e);
		}

	}

	@Test
	public void validerCreationEmailWrongFormatTest() {
		String email = "0123";
		String motDePasse = "yolotest";
		try {
			Client client = ClientService.get().validerCreation(email, motDePasse);
			fail("fail - la création a été autorisée avec email invalide");
		} catch (InvalidArgumentException e) {
			assertTrue(e.getRealMessage().contains("Le format de l'email est invalide"));
		} catch (MetierException e) {
			fail("fail - l'erreur métier a été levée\n -> e : " + e);
		}

	}

	@Test
	public void validerCreationMotDePasseNullorEmptyTest() {
		String email = "test@test.fr";
		String motDePasse = "";
		try {
			Client client = ClientService.get().validerCreation(email, motDePasse);
			fail("fail - la création a été autorisée avec mdp null ou vide");
		} catch (InvalidArgumentException e) {
			assertTrue(e.getRealMessage().contains("Le mot de passe ne peut être null ou vide"));
		} catch (MetierException e) {
			fail("fail - l'erreur métier a été levée\n -> e : " + e);
		}

	}

	@Test
	public void validerCreationEmailExistTest() {
		String email = "test@test.fr";
		String motDePasse = "123456";
		try {
			Client client1 = ClientService.get().creerClient("client1", null, email, motDePasse, null, null, null, null,
					null, null, null);
			Client client2 = ClientService.get().creerClient("client2", null, email, motDePasse, null, null, null, null,
					null, null, null);
			fail("fail - l'enregistrement en bdd a été autorisée alors que l'email est en doublon");
		} catch (InvalidArgumentException e) {
			fail("fail - l'erreur InvalidArgumentException a été levée\n -> e : " + e);
		} catch (MetierException e) {
			assertTrue(e.getMessage().contains("Cet email est déjà utilisé"));
		}
	}

	@Test
	public void validerCreationOK() {
		String email = "test@test.fr";
		String motDePasse = "123456";
		try {
			Client client = ClientService.get().validerCreation(email, motDePasse);
			assertEquals(email, client.email);
		} catch (InvalidArgumentException | MetierException e) {
			fail();
		}
	}

	@Test
	public void modifierClientNullTest() {
		Client client = null;
		try {
			client = ClientService.get().modifier(client, null, null, null, null, null, null, null, null, null, null);
			fail("fail - le client ne peut être null");
		} catch (InvalidArgumentException e) {
			assertTrue(e.getRealMessage().contains("Le client ne peut être null"));
		}
	}

	@Test
	public void modifierOKTest() {
		String email = "test@test.fr";
		String motDePasse = "123456";
		String prenom = "test";
		Client client;
		try {
			client = ClientService.get().validerCreation(email, motDePasse);
			client = ClientService.get().modifier(client, null, prenom, null, null, null, null, null, null, null, null);
			assertEquals(prenom, client.prenom.toString());
		} catch (InvalidArgumentException | MetierException e) {
			fail("fail - la modif a échoué");
		}
	}

	@Test
	public void authenticateEmailNullorEmptyTest() {
		String email = "";
		String motDePasse = "123456";
		try {
			ClientService.get().authenticate(email, motDePasse);
			fail("fail - l'email ne peut être vide ou null");
		} catch (InvalidArgumentException e) {
			assertTrue(e.getRealMessage().contains("L'email ne peut être null ou vide"));
		}
	}

	@Test
	public void authenticateMDPNullorEmptyTest() {
		String email = "test@test.fr";
		String motDePasse = null;
		try {
			ClientService.get().authenticate(email, motDePasse);
			fail("fail - le mdp ne peut être vide ou null");
		} catch (InvalidArgumentException e) {
			assertTrue(e.getRealMessage().contains("Le motDePasse ne peut être null ou vide"));
		}
	}

	@Test
	public void authenticateOKTest() {
		String email = "test@test.fr";
		String motDePasse = "123456";
		String nom = "yolo";
		String prenom = "test";
		Client client;
		try {
			client = ClientService.get().creerClient(prenom, prenom, email, motDePasse, null, null, null, null, null,
					null, null);
			assertTrue(ClientService.get().authenticate(email, motDePasse));
		} catch (InvalidArgumentException | MetierException e) {
			fail("fail - echec de l'authentification en phase de creation du client\n -> e : " + e);
		}
	}

	@Test
	public void authenticateWrongEmailTest() {
		String email = "test@test.fr";
		String motDePasse = "123456";
		String nom = "yolo";
		String prenom = "test";
		Client client;
		try {
			client = ClientService.get().creerClient(prenom, prenom, email, motDePasse, null, null, null, null, null,
					null, null);
			assertFalse(ClientService.get().authenticate("azerty@azerty.com", motDePasse));
		} catch (InvalidArgumentException | MetierException e) {
			fail("fail - echec de l'authentification en phase de creation du client\n -> e : " + e);
		}
	}

	@Test
	public void authenticateWrongMDPTest() {
		String email = "test@test.fr";
		String motDePasse = "123456";
		String nom = "yolo";
		String prenom = "test";
		Client client;
		try {
			client = ClientService.get().creerClient(nom, prenom, email, motDePasse, null, null, null, null, null, null,
					null);
			Boolean b = ClientService.get().authenticate(email, "azerty");
			assertFalse(b);
		} catch (InvalidArgumentException | MetierException e) {
			fail("fail - echec de l'authentification en phase de creation du client\n -> e : " + e);
		}
	}

	@Test
	public void listerClientsTest() {
		String email1 = "test@test.fr";
		String motDePasse1 = "123456";
		String nom1 = "yolo";
		String prenom1 = "test";
		Client client1;

		String email2 = "super@chien.fr";
		String motDePasse2 = "987654";
		String nom2 = "super";
		String prenom2 = "chien";
		Client client2;

		Boolean isSupprime = false;

		try {
			client1 = ClientService.get().creerClient(nom1, prenom1, email1, motDePasse1, null, null, null, null, null,
					null, null);
			client2 = ClientService.get().creerClient(nom2, prenom2, email2, motDePasse2, null, null, null, null, null,
					null, null);
			List<Client> clients = ClientService.get().lister(isSupprime);
			assertTrue(clients.size() == 2);
		} catch (InvalidArgumentException | MetierException e) {
			fail();
		}
	}

	@Test
	public void SupprimerTest() {
		String email1 = "test@test.fr";
		String motDePasse1 = "123456";
		String nom1 = "yolo";
		String prenom1 = "test";
		Client client1;

		String email2 = "super@chien.fr";
		String motDePasse2 = "987654";
		String nom2 = "super";
		String prenom2 = "chien";
		Client client2;

		Boolean isSupprime = true;

		try {
			client1 = ClientService.get().creerClient(nom1, prenom1, email1, motDePasse1, null, null, null, null, null,
					null, null);
			client2 = ClientService.get().creerClient(nom2, prenom2, email2, motDePasse2, null, null, null, null, null,
					null, null);
			ClientService.get().supprimer(client2);
			List<Client> clients = ClientService.get().lister(isSupprime);
			assertEquals(1, clients.size());
		} catch (InvalidArgumentException | MetierException e) {
			fail();
		}
	}

	@Test
	public void listerZeroClientTest() {
		List<Client> clients = ClientService.get().lister(false);
		assertEquals(0, clients.size());
	}

	@Test
	public void getClientTest(){
		String email = "test@test.fr";
		String motDePasse = "123456";
		String nom = "yolo";
		String prenom = "test";
		Client client;
		try {
			client = ClientService.get().creerClient(nom, prenom, email, motDePasse, null, null, null, null, null, null, null);
			List<Client> clients = ClientService.get().lister(false);
			String idClient = clients.get(0).idClient;
			Client client1 = ClientService.get().getClient(idClient);
			assertEquals(client.nom, client1.nom);
			assertEquals(client.prenom, client1.prenom);
			assertEquals(client.email, client1.email);
			assertEquals(client.motDePasse, client1.motDePasse);
		} catch (InvalidArgumentException | MetierException e) {
			fail();
		}
		
	}
	
	@Test
	public void getClientNullTest(){
		String email = "test@test.fr";
		String motDePasse = "123456";
		String nom = "yolo";
		String prenom = "test";
		Client client;
		try {
			client = ClientService.get().creerClient(nom, prenom, email, motDePasse, null, null, null, null, null, null, null);
			List<Client> clients = ClientService.get().lister(false);
			Client client1 = ClientService.get().getClient(null);
			fail();
		} catch (InvalidArgumentException e) {
			assertTrue(e.getRealMessage().contains("L'id client ne peut être null ou vide"));;
		} catch (MetierException e) {
			fail();
		}
		
	}
	
	@Test
	public void getClientByEmailTest(){
		String email = "test@test.fr";
		String motDePasse = "123456";
		String nom = "yolo";
		String prenom = "test";
		Client client;
		try {
			client = ClientService.get().creerClient(nom, prenom, email, motDePasse, null, null, null, null, null, null, null);
			List<Client> clients = ClientService.get().lister(false);
			Client client1 = ClientService.get().getClientByEmail(email);
			assertEquals(client.nom, client1.nom);
			assertEquals(client.prenom, client1.prenom);
			assertEquals(client.email, client1.email);
			assertEquals(client.motDePasse, client1.motDePasse);
		} catch (InvalidArgumentException | MetierException e) {
			fail();
		}
		
	}
	
	@Test
	public void getClientByEmailNullTest(){
		String email = "test@test.fr";
		String motDePasse = "123456";
		String nom = "yolo";
		String prenom = "test";
		Client client;
		try {
			client = ClientService.get().creerClient(nom, prenom, email, motDePasse, null, null, null, null, null, null, null);
			Client client1 = ClientService.get().getClientByEmail(null);
			fail();
		} catch (InvalidArgumentException e) {
			assertTrue(e.getRealMessage().contains("L'email ne peut être null ou vide"));;
		} catch (MetierException e) {
			fail();
		}
		
	}
	
	
}
