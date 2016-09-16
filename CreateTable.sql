#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------
DROP TABLE IF EXISTS Client;
CREATE TABLE Client(.
        idClient     Varchar (255) NOT NULL ,
        nom          Varchar (45) NOT NULL ,
        prenom       Varchar (45) ,
        email        Varchar (45) NOT NULL ,
        email2       Varchar (45) ,
        motDePasse   Varchar (255) ,
        statutClient Varchar (25) ,
        codePostal   Varchar (25) ,
        pays         Varchar (25) ,
        ville        Varchar (45) ,
        nrue         Varchar (10) ,
        rue          Varchar (45) ,
        appartement  Varchar (45) ,
        telephone    Varchar (15) ,
        role         varchar (25) NOT NULL,
        isSupprime   boolean ,
        
        PRIMARY KEY (idClient ) ,
        UNIQUE (email )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Produit
#------------------------------------------------------------
DROP TABLE IF EXISTS Produit;
CREATE TABLE Produit(
        idProduit          Varchar (255) NOT NULL ,
        nomProduit         Varchar (45) NOT NULL ,
        descriptionProduit Varchar (255) NOT NULL ,
        prixUnitaire       Float ,
        promo              Int ,
        descriptionPromo   Varchar (255) ,
        statutProduit      Bool ,
        stockMagasin       Int ,
        stockHangar        Int ,
        dateDispo          Date ,
        etat               Varchar (25) ,
        couleur            Varchar (25) ,
        garantie           Varchar (255) ,
        PRIMARY KEY (idProduit )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Commande
#------------------------------------------------------------
DROP TABLE IF EXISTS Commande;
CREATE TABLE Commande(
        idCommande     Varchar (255) NOT NULL ,
        dateCreation        Date NOT NULL ,
        datePaiement        Date NOT NULL ,
        dateLivraison       Date NOT NULL ,
        dateCloture         Date NOT NULL ,
        descriptionCommande Varchar (255) ,
        total               Float NOT NULL ,
        statutCommande      Bool NOT NULL ,
        numeroCommande      Numeric NOT NULL ,
        idClient            Varchar (255) NOT NULL ,
        PRIMARY KEY (idCommande )
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: CB
#------------------------------------------------------------
DROP TABLE IF EXISTS CB;
CREATE TABLE CB(
        numeroCommande Numeric NOT NULL ,
        nomCB          Varchar (45) NOT NULL ,
        codeSecu       Numeric ,
        dateValid Date ,
        PRIMARY KEY (numeroCommande )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Choisir
#------------------------------------------------------------
DROP TABLE IF EXISTS Choisir;
CREATE TABLE Choisir(
        IDClient      Varchar (255) NOT NULL ,
        IDProduit     Varchar (255) NOT NULL ,
        quantite      Numeric NOT NULL ,
        dateSelection Date NOT NULL ,
        PRIMARY KEY (IDClient ,IDProduit )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Composer
#------------------------------------------------------------
DROP TABLE IF EXISTS Composer;
CREATE TABLE Composer(
        idCommande Varchar (255) NOT NULL ,
        idProduit  Varchar (255) NOT NULL ,
        Quantite   Int NOT NULL ,
        PRIMARY KEY (idCommande ,idProduit )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Utiliser
#------------------------------------------------------------
DROP TABLE IF EXISTS Utiliser;
CREATE TABLE Utiliser(
        IdClient       Varchar (255) NOT NULL ,
        numeroCommande Numeric NOT NULL ,
        PRIMARY KEY (IDClient ,numeroCommande )
)ENGINE=InnoDB;

