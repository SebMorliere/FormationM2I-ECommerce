package com.M2I.ecommerce.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Commande")
public class Commande {
	@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator(name="UUID",  strategy = "uuid")
	@Column(name="idCommande", unique = true)
	public String idCommande;
	public Date dateCreation;
	public Date datePaiement;
	public Date dateLivraison;
	public Date dateCloture;
	public Float total;
	@Column(name="statutCommande")
	public Boolean statut;
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="idClient", referencedColumnName="idClient")
	public Client client;
}
