package com.M2I.ecommerce.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.M2I.ecommerce.beans.Client;
import com.M2I.ecommerce.services.ClientService;

@Path("/client")
public class ClientServiceREST {

	@GET
	@Path("/lister")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Client> ListerClient() {
		List<Client> clients = ClientService.get().lister(false);
		return clients;
	}

//	@POST
//	@Path("/nouveau")
//	@Produces(MediaType.APPLICATION_JSON)
//	public void NouveauClient(Client client) {
//
//		try {
//			ClientService.get().creerClient(client.nom, client.prenom, client.email, client.motDePasse, client.codePostal,
//					client.pays, client.ville, client.nrue, client.rue, client.appartement, client.telephone);
//		} catch (InvalidArgumentException | MetierException e) {
//			e.printStackTrace();
//		}
//	}

}
