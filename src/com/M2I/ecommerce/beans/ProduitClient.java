package com.M2I.ecommerce.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProduitClient")
public class ProduitClient {
	@Id
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idClient", referencedColumnName = "idClient")
	public Client client;
	@Id
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idProduit", referencedColumnName = "idProduit")
	public Produit produit;
	public int quantite;
	public Date dateChoix;
}
