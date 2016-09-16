package com.M2I.ecommerce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.M2I.ecommerce.types.EUserRole;
import com.M2I.ecommerce.types.StatutClient;

@Entity
@Table(name = "Client")
public class Client {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@Column(name = "idClient", unique = true)
	public String idClient;
	public String nom;
	public String prenom;
	@Column(name = "email", unique = true)
	public String email;
	public String email2;
	public String motDePasse;
	@Enumerated(EnumType.STRING)
	public StatutClient statutClient;
	public String codePostal;
	public String pays;
	public String ville;
	public String nrue;
	public String rue;
	public String appartement;
	public String telephone;
	@Enumerated(EnumType.STRING)
	public EUserRole role;
	public Boolean isSupprime;
}
