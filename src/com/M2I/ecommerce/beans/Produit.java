package com.M2I.ecommerce.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Produit")
public class Produit {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@Column(name = "idProduit", unique = true)
	public String idProduit;
	@Column(name = "nomProduit")
	public String nom;
	@Column(name = "descriptionProduit")
	public String description;
	public Float prixUnitaire;
	public Integer promo;
	public String descriptionPromo;
	@Column(name = "statutProduit")
	public Boolean statut;
	public Integer stockMagasin;
	public Integer stockHangar;
	public Date dateDispo;
	public String etat;
	public String couleur;
	public String garantie;
	public String image;

	@Override
	public String toString() {
		return "{idProduit = " + idProduit + "\nnom = " + nom + "\ndescription = " 
				+ description + "\nP.U.=" + prixUnitaire + "}";
	}
}
