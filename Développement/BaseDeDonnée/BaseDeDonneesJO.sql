DROP TABLE IF EXISTS POSSEDE;
DROP TABLE IF EXISTS PARTICIPE_JO;
DROP TABLE IF EXISTS PARTICIPE_INDIV;
DROP TABLE IF EXISTS PARTICIPE_COLLEC;
DROP TABLE IF EXISTS FAIT_PARTIE;
DROP TABLE IF EXISTS EQUIPE;
DROP TABLE IF EXISTS ATHLETE;
DROP TABLE IF EXISTS PAYS;
DROP TABLE IF EXISTS EPREUVE;
DROP TABLE IF EXISTS JEUXOLYMPIQUE;
-- DROP TABLE IF EXISTS UTILISATEUR;

CREATE TABLE PAYS (
  id_Pays     INT PRIMARY KEY,
  nom_P       VARCHAR(100),
  M_or        INT DEFAULT 0,
  M_argent    INT DEFAULT 0,
  M_bronze    INT DEFAULT 0,
  score_Total INT DEFAULT 0
);

CREATE TABLE ATHLETE (
  id_Athlete INT PRIMARY KEY,
  nom_A      VARCHAR(100),
  prenom_A   VARCHAR(100),
  sexe_A     CHAR(1),
  CHECK (sexe_A IN ('M', 'F')),
  la_force   INT,
  endurance  INT,
  agilite    INT,
  id_Pays    INT NOT NULL,
  FOREIGN KEY (id_Pays) REFERENCES PAYS (id_Pays) ON DELETE CASCADE
);

CREATE TABLE EQUIPE (
  id_Equipe INT PRIMARY KEY,
  nom_E     VARCHAR(100),
  id_Pays   INT NOT NULL,
  FOREIGN KEY (id_Pays) REFERENCES PAYS (id_Pays) ON DELETE CASCADE
);

CREATE TABLE FAIT_PARTIE (
  id_Equipe  INT NOT NULL,
  id_Athlete INT NOT NULL,
  sexe       CHAR(1),
  CHECK (sexe IN ('M', 'F')),
  PRIMARY KEY (id_Equipe, id_Athlete),
  FOREIGN KEY (id_Athlete) REFERENCES ATHLETE (id_Athlete) ON DELETE CASCADE,
  FOREIGN KEY (id_Equipe) REFERENCES EQUIPE (id_Equipe) ON DELETE CASCADE
);

CREATE TABLE JEUXOLYMPIQUE (
  id_JO INT PRIMARY KEY,
  annee INT,
  lieu  VARCHAR(100)
);

CREATE TABLE EPREUVE (
  id_Epreuve INT PRIMARY KEY,
  type_Epreuve VARCHAR(100),
  id_JO INT NOT NULL,
  FOREIGN KEY (id_JO) REFERENCES JEUXOLYMPIQUE (id_JO) ON DELETE CASCADE
);

CREATE TABLE PARTICIPE_COLLEC (
  id_Equipe    INT NOT NULL,
  id_Epreuve   INT NOT NULL,
  type_Epreuve VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_Equipe, id_Epreuve, type_Epreuve),
  FOREIGN KEY (id_Equipe) REFERENCES EQUIPE (id_Equipe) ON DELETE CASCADE, 
  FOREIGN KEY (id_Epreuve) REFERENCES EPREUVE (id_Epreuve) ON DELETE CASCADE
);

CREATE TABLE PARTICIPE_INDIV (
  id_Athlete   INT NOT NULL,
  id_Epreuve   INT NOT NULL,
  type_Epreuve VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_Athlete, id_Epreuve, type_Epreuve),
  FOREIGN KEY (id_Athlete) REFERENCES ATHLETE (id_Athlete) ON DELETE CASCADE,
  FOREIGN KEY (id_Epreuve) REFERENCES EPREUVE (id_Epreuve) ON DELETE CASCADE
);

CREATE TABLE PARTICIPE_JO (
  id_Pays INT NOT NULL,
  id_JO   INT NOT NULL,
  PRIMARY KEY (id_Pays, id_JO),
  FOREIGN KEY (id_JO) REFERENCES JEUXOLYMPIQUE (id_JO) ON DELETE CASCADE,
  FOREIGN KEY (id_Pays) REFERENCES PAYS (id_Pays) ON DELETE CASCADE
);

CREATE TABLE POSSEDE (
  id_Epreuve   INT NOT NULL,
  type_Epreuve VARCHAR(100) NOT NULL,
  id_JO        INT NOT NULL,
  PRIMARY KEY (id_Epreuve, type_Epreuve, id_JO),
  FOREIGN KEY (id_JO) REFERENCES JEUXOLYMPIQUE (id_JO) ON DELETE CASCADE
);

CREATE TABLE UTILISATEUR (
  identifiant    VARCHAR(42) NOT NULL,
  email          VARCHAR(42) NOT NULL UNIQUE,
  mdp            VARCHAR(42),
  rôle           CHAR, 
  CHECK (rôle IN ('A', 'C', 'O')), 
  PRIMARY KEY (identifiant)
);

-- insertions

INSERT INTO UTILISATEUR (identifiant, email, mdp, rôle) VALUES 
('admin', 'admin@admin.com', 'admin', 'A'), 
('user', 'user@user.com', 'user', 'A'), 
('journaliste', 'journaliste@gmail.com', 'journaliste', 'C'), 
('organisateur', 'organisateur@gmail.com', 'organisateur', 'O');

-- INSERT INTO UTILISATEUR (identifiant, email, mdp, rôle) VALUES ('journaliste', 'journaliste@gmail.com', 'journaliste', 'C');




-- Insertion des pays
INSERT INTO PAYS (id_Pays, nom_P) VALUES (1, 'France'), (2, 'USA'), (3, 'China');

-- Insertion des athlètes
INSERT INTO ATHLETE (id_Athlete, nom_A, prenom_A, sexe_A, la_force, endurance, agilite, id_Pays) VALUES 
(1, 'Dupont', 'Jean', 'M', 80, 75, 90, 1), 
(2, 'Smith', 'John','M', 85, 80, 85, 2), 
(3, 'Wang', 'Li','M', 78, 82, 88, 3);

-- Insertion des équipes
INSERT INTO EQUIPE (id_Equipe, nom_E, id_Pays) VALUES 
(1, 'Equipe France', 1), 
(2, 'Team USA', 2), 
(3, 'Team China', 3);

-- Insertion des relations entre équipes et athlètes
INSERT INTO FAIT_PARTIE (id_Equipe, id_Athlete, sexe) VALUES 
(1, 1, 'M'), 
(2, 2, 'M'), 
(3, 3, 'F');

-- Insertion des Jeux Olympiques
INSERT INTO JEUXOLYMPIQUE (id_JO, annee, lieu) VALUES 
(1, 2024, 'Paris'), 
(2, 2028, 'Los Angeles'), 
(3, 2032, 'Brisbane');

-- Insertion des épreuves
INSERT INTO EPREUVE (id_Epreuve, type_Epreuve, id_JO) VALUES 
(101, 'Football', 1), 
(102, 'Basketball', 2), 
(103, 'Volleyball', 3), 
(201, '100m', 1), 
(202, '200m', 2), 
(203, '400m', 3);

-- Insertion des participations aux épreuves collectives
INSERT INTO PARTICIPE_COLLEC (id_Equipe, id_Epreuve, type_Epreuve) VALUES 
(1, 101, 'Football'), 
(2, 102, 'Basketball'), 
(3, 103, 'Volleyball');

-- Insertion des participations aux épreuves individuelles
INSERT INTO PARTICIPE_INDIV (id_Athlete, id_Epreuve, type_Epreuve) VALUES 
(1, 201, '100m'), 
(2, 202, '200m'), 
(3, 203, '400m');

-- Insertion des participations des pays aux JO
INSERT INTO PARTICIPE_JO (id_Pays, id_JO) VALUES 
(1, 1), 
(2, 2), 
(3, 3);

-- Insertion des épreuves dans les JO
INSERT INTO POSSEDE (id_Epreuve, type_Epreuve, id_JO) VALUES 
(101, 'Football', 1), 
(102, 'Basketball', 2), 
(103, 'Volleyball', 3), 
(201, '100m', 1), 
(202, '200m', 2), 
(203, '400m', 3);

-- requetes
SELECT * FROM UTILISATEUR WHERE identifiant = 'admin' AND mdp = 'admin';













  -- requetes
  select * from UTILISATEUR where identifiant = 'admin' and mdp = 'admin';

