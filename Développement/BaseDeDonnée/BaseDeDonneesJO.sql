DROP TABLE IF EXISTS POSSEDE;
DROP TABLE IF EXISTS PARTICIPE_JO;
DROP TABLE IF EXISTS PARTICIPE_INDIV;
DROP TABLE IF EXISTS PARTICIPE_COLLEC;
DROP TABLE IF EXISTS JEUXOLYMPIQUE;
DROP TABLE IF EXISTS FAIT_PARTIE;
DROP TABLE IF EXISTS EQUIPE;
DROP TABLE IF EXISTS ATHLETE;
DROP TABLE IF EXISTS PAYS;
DROP TABLE IF EXISTS UTILISATEUR;

CREATE TABLE PAYS (
  id_Pays     INT PRIMARY KEY,
  nom_P       VARCHAR(100),
  M_or          INT DEFAULT 0,
  M_argent      INT DEFAULT 0,
  M_bronze      INT DEFAULT 0,
  score_Total INT DEFAULT 0
);

CREATE TABLE ATHLETE (
  id_Athlete INT PRIMARY KEY,
  nom_A      VARCHAR(100),
  prenom_A   VARCHAR(100),
  la_force   INT,
  endurance  INT,
  agilite    INT,
  id_Pays    INT NOT NULL,
  FOREIGN KEY (id_Pays) REFERENCES PAYS (id_Pays)
);

CREATE TABLE EQUIPE (
  id_Equipe INT PRIMARY KEY,
  nom_E     VARCHAR(100),
  id_Pays   INT NOT NULL,
  FOREIGN KEY (id_Pays) REFERENCES PAYS (id_Pays)
);

CREATE TABLE FAIT_PARTIE (
  id_Equipe  INT NOT NULL,
  id_Athlete INT NOT NULL,
  sexe       CHAR(1),
  CHECK (sexe IN ('M', 'F')),
  PRIMARY KEY (id_Equipe, id_Athlete),
  FOREIGN KEY (id_Athlete) REFERENCES ATHLETE (id_Athlete),
  FOREIGN KEY (id_Equipe) REFERENCES EQUIPE (id_Equipe)
);

CREATE TABLE JEUXOLYMPIQUE (
  id_JO INT PRIMARY KEY,
  annee YEAR,
  lieu  VARCHAR(100)
);

CREATE TABLE PARTICIPE_COLLEC (
  id_Equipe    INT NOT NULL,
  id_Epreuve   INT NOT NULL,
  type_Epreuve VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_Equipe, id_Epreuve, type_Epreuve),
  FOREIGN KEY (id_Equipe) REFERENCES EQUIPE (id_Equipe)
);

CREATE TABLE PARTICIPE_INDIV (
  id_Athlete   INT NOT NULL,
  id_Epreuve   INT NOT NULL,
  type_Epreuve VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_Athlete, id_Epreuve, type_Epreuve),
  FOREIGN KEY (id_Athlete) REFERENCES ATHLETE (id_Athlete)
);

CREATE TABLE PARTICIPE_JO (
  id_Pays INT NOT NULL,
  id_JO   INT NOT NULL,
  PRIMARY KEY (id_Pays, id_JO),
  FOREIGN KEY (id_JO) REFERENCES JEUXOLYMPIQUE (id_JO),
  FOREIGN KEY (id_Pays) REFERENCES PAYS (id_Pays)
);

CREATE TABLE POSSEDE (
  id_Epreuve   INT NOT NULL,
  type_Epreuve VARCHAR(100) NOT NULL,
  id_JO        INT NOT NULL,
  PRIMARY KEY (id_Epreuve, type_Epreuve, id_JO),
  FOREIGN KEY (id_JO) REFERENCES JEUXOLYMPIQUE (id_JO)
);

CREATE TABLE UTILISATEUR (
  identifiant    VARCHAR(42) NOT NULL,
  email          VARCHAR(42) NOT NULL,
  mdp            VARCHAR(42),
  rôle           CHAR, 
  CHECK (rôle IN ('A', 'C', 'O')), 
  PRIMARY KEY (identifiant, email)
);

-- insertions
INSERT INTO UTILISATEUR VALUES ('1', 'admin', 'admin@admin.com', 'admin', 'A');
INSERT INTO UTILISATEUR VALUES ('2', 'user', 'user@user.com', 'user', 'A');

INSERT INTO UTILISATEUR VALUES ('3', 'journaliste', 'journaliste@gmail.com', 'journaliste', 'C');

INSERT INTO UTILISATEUR VALUES ('4', 'organisateur', 'organisateur@gmail.com', 'organisateur', 'O');

-- requetes
select * from UTILISATEUR where identifiant = 'admin' and mdp = 'admin';

