package src.basededonnee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.basededonnee.exception.*;
import src.modele.jeuxOlympique.*;

public class Requete {
    
    private ConnexionBD connexionBD;

    public Requete() throws BaseDeDonneeInaccessibleException, ClassNotFoundException {
        this.connexionBD = new ConnexionBD();
        try {
            this.connexionBD.connecter();
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public String getColumName(String nomTable) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from " + nomTable);
            ResultSet resultat = requete.executeQuery();
            ResultSetMetaData metaData = resultat.getMetaData();
            return metaData.getColumnName(1);
        } catch (Exception e) {
            return "";
        }
    }

    public int idMaxTable(String nomTable) throws SQLException, PasDeIdDansUtilisateurException {
        if (nomTable.equals("UTILISATEUR")) {
            throw new PasDeIdDansUtilisateurException();
        }
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select max(" + this.getColumName(nomTable) + ") as max from " + nomTable);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getInt("max");
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean connexion(String identifiant, String motDePasse) throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from UTILISATEUR where identifiant = ? and mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, motDePasse);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public boolean dansUtilisateur(String identifiant, String mail, String motDePasse) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from UTILISATEUR where identifiant = ? and email = ? and mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, mail);
            requete.setString(3, motDePasse);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Connexion défaillante");
            return false;
        }
    }

    public boolean dansUtilisateur(String identifiant, String motDePasse) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from UTILISATEUR where identifiant = ? and mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, motDePasse);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Connexion défaillante");
            return false;
        }
    }

    public List<String> getNomUtilisateur() throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select identifiant from UTILISATEUR");
            ResultSet resultat = requete.executeQuery();
            List<String> noms = new ArrayList<>();
            while (resultat.next()) {
                noms.add(resultat.getString("identifiant"));
            }
            return noms;
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public char getRoleUtilisateur(String identifiant, String motDePasse) throws BaseDeDonneeInaccessibleException {
        try {
            if (! this.dansUtilisateur(identifiant, motDePasse)) {
                throw new UtilisateurInexistantException(identifiant);
            }
            PreparedStatement requete = this.connexionBD.prepareStatement("Select rôle from UTILISATEUR where identifiant = ? and mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, motDePasse);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getString("rôle").charAt(0);
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public void changerRoleUtilisateur(String identifiant, char role) throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Update UTILISATEUR set rôle = ? where identifiant = ?");
            requete.setString(1, role+"");
            requete.setString(2, identifiant);
            requete.executeUpdate();
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public void inscription(String identifiant, String mail, String motDePasse) throws BaseDeDonneeInaccessibleException {
        try {
            if (! this.dansUtilisateur(identifiant, mail)) {
                PreparedStatement requete = this.connexionBD.prepareStatement("Insert into UTILISATEUR values ( ?, ?, ?, 'C')");
                requete.setString(1, identifiant);
                requete.setString(2, mail);
                requete.setString(3, motDePasse);
                requete.executeUpdate();
            } else {
                System.out.println("Utilisateur déjà existant");
                throw new UtilisateurDejaExistantException();
            }
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public void supprimerUtilisateur(String identifiant) throws UtilisateurInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("delete from UTILISATEUR where identifiant = ?");
            requete.setString(1, identifiant);
            requete.executeUpdate();
        } catch (Exception e) {
            throw new UtilisateurInexistantException(identifiant);
        }
    }

    public void ajouterAthlete(String nom, String prenom, int force, int endurance, int agilite, char sexe, int idPays) throws BaseDeDonneeInaccessibleException {
        try {
            // if (! this.dansPays(idPays)) {
            //     this.ajouterPays(idPays);
            // }
            if (this.dansAthlete(nom, prenom, idPays)) {
                throw new AthleteDejaExistantException(nom, prenom);
            }
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into ATHLETE values (?, ?, ?, ?, ?, ?, ?)");
            requete.setInt(1, this.idMaxTable("ATHLETE") + 1);
            requete.setString(2, nom);
            requete.setString(3, prenom);
            requete.setInt(4, force);
            requete.setInt(5, endurance);
            requete.setInt(6, agilite);
            requete.setString(7, sexe+""); // Attention
            requete.executeUpdate();
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public void ajouterAthlete(Athlete athlete) throws BaseDeDonneeInaccessibleException {
        try {
            System.out.println("1");
            if (! this.dansPays(athlete.getPays().getNomPays())) {
                this.ajouterPays(athlete.getPays().getNomPays());
            }
            if (this.dansAthlete(athlete.getNom(), athlete.getPrenom(), this.getIdPays(athlete.getPays().getNomPays()))) {
                System.out.println("2");
                throw new AthleteDejaExistantException(athlete.getNom(), athlete.getPrenom());
            } else {
                System.out.println("3");
                if (! this.dansPays(athlete.getPays().getNomPays())) {
                    this.ajouterPays(athlete.getPays().getNomPays());
                }
                System.out.println(athlete.getNom() + " " + athlete.getPrenom() + " " + athlete.getSexe() + " " + athlete.getForce() + " " + athlete.getEndurance() + " " + athlete.getAgilité() + " " + this.getIdPays(athlete.getPays().getNomPays()));
                System.out.println("je suis là");
                int idPays = this.getIdPays(athlete.getPays().getNomPays());
                System.out.println("HLELEOEOK");
                if (this.dansAthlete(athlete.getNom(), athlete.getPrenom(), getIdPays(athlete.getPays().getNomPays()))) {
                    System.out.println("je pass par là");
                    throw new AthleteDejaExistantException(athlete.getNom(), athlete.getPrenom());
                }
                System.out.println("AU REVOIZSPEIUG PSIDUG");
                PreparedStatement requete = this.connexionBD.prepareStatement("Insert into ATHLETE values (?, ?, ?, ?, ?, ?, ?, ?)");
                requete.setInt(1, this.idMaxTable("ATHLETE") + 1);
                requete.setString(2, athlete.getNom());
                requete.setString(3, athlete.getPrenom());
                requete.setString(4, (athlete.getSexe() + "").charAt(0) + "");
                System.out.println(athlete.getSexe());
                requete.setInt(5, athlete.getForce());
                requete.setInt(6, athlete.getEndurance());
                requete.setInt(7, athlete.getAgilité()); 
                requete.setInt(8, idPays); // Attention
                requete.executeUpdate();
            }         
        } catch (Exception e) {
            System.out.println("5641320.132564564");
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public boolean dansAthlete(String nom, String prenom, int idPays) throws BaseDeDonneeInaccessibleException {
        try {
            System.out.println(idPays);
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from ATHLETE where nom = ? and prenom = ? and id_Pays = ?");
            requete.setString(1, nom);
            requete.setString(2, prenom);
            requete.setInt(3, idPays);
            try {
                ResultSet resultat = requete.executeQuery();
                if (resultat.next()) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public int getIdAthlete(String nom, String prenom) throws AthleteInexistantException {//throws AthleteInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select id_Athlete from ATHLETE where nom = ? and prenom = ?");
            requete.setString(1, nom);
            requete.setString(2, prenom);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getInt("id_Athlete");
        } catch (Exception e) {
            throw new AthleteInexistantException(nom, prenom);
            // System.out.println("Erreur de connexion à la base de donnée"); // Attention
            // return 0;
        }
    }

    public List<Athlete> getAthletes() throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from ATHLETE NATURAL JOIN PAYS");
            ResultSet resultat = requete.executeQuery();
            List<Athlete> athletes = new ArrayList<>();
            while (resultat.next()) {
                System.out.println(resultat.getString("sexe"));
                char sexeChar = resultat.getString("sexe").charAt(0);
                System.out.println(sexeChar);
                if (sexeChar == 'M') {
                    athletes.add(new Athlete(resultat.getString("nom"), resultat.getString("prenom"), Epreuve.Sexe.M, new Pays(resultat.getString("nom_P")), resultat.getInt("force"), resultat.getInt("endurance"), resultat.getInt("agilite")));
                } else {
                    athletes.add(new Athlete(resultat.getString("nom"), resultat.getString("prenom"), Epreuve.Sexe.F, new Pays(resultat.getString("nom_P")), resultat.getInt("force"), resultat.getInt("endurance"), resultat.getInt("agilite")));
                }
            }
            return athletes;
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public void supprimerAthlete(int idAthlete) throws AthleteInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("delete from ATHLETE where id_Athlete = ?");
            requete.setInt(1, idAthlete);
            requete.executeUpdate();
        } catch (Exception e) {
            throw new AthleteInexistantException(idAthlete);
        }
    }

    public boolean dansEquipe(Equipe equipe) throws EquipeInexistanteException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from EQUIPE where nom_E = ?");
            requete.setString(1, equipe.getNomEquipe());
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new EquipeInexistanteException(equipe.getNomEquipe());
        }

    }

    public int getIdEquipe(Equipe equipe) throws EquipeInexistanteException {
        try {
            if (this.dansEquipe(equipe)) {
                PreparedStatement requete = this.connexionBD.prepareStatement("Select id_Equipe from EQUIPE where nom_E = ?");
                requete.setString(1, equipe.getNomEquipe());
                ResultSet resultat = requete.executeQuery();
                resultat.next();
                return resultat.getInt("id_Equipe");
            } else {
                throw new EquipeInexistanteException(equipe.getNomEquipe());
            }
        } catch (Exception e) {
            throw new EquipeInexistanteException(equipe.getNomEquipe());
        }
    }

    /// TO DO CICICICICICICIICICICICI
    public void ajouterEquipe(Equipe equipe) throws BaseDeDonneeInaccessibleException {
        try {
            if (! this.dansPays(equipe.getPays().getNomPays())) {
                this.ajouterPays(equipe.getPays().getNomPays());
            }
            if (this.dansEquipe(equipe)) {
                throw new EquipeDejaExistantException(equipe.getNomEquipe());
            } else {
                PreparedStatement requete = this.connexionBD.prepareStatement("Insert into EQUIPE values (?, ?, ?)"); // ICICICICICI
                requete.setInt(1, this.idMaxTable("EQUIPE") + 1);
                requete.setString(2, equipe.getNomEquipe());
                requete.setInt(3, this.getIdPays(equipe.getPays().getNomPays()));
                requete.executeUpdate();
            }
            int idEquipe = this.getIdEquipe(equipe);
            for (Athlete athlete : equipe.getLesAthlètes()) {
                System.out.println(athlete.getNom() + " " + athlete.getPrenom() + " " + athlete.getSexe());
                try {
                    System.out.println("BONJOUR");
                    this.ajouterAthlete(athlete);
                    System.out.println("1");
                    this.getIdAthlete(athlete.getNom(), athlete.getPrenom());
                    System.out.println("2");
                    this.ajouterAthleteDansEquipe(this.getIdAthlete(athlete.getNom(), athlete.getPrenom()), idEquipe, athlete.getSexe().toString().charAt(0));
                    System.out.println("3");
                } catch (Exception e) {
                    // TODO: handle exception
                    // Pass, l'athlète est déjà dans la base de donnée
                }
            }
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
        // try {
        //     PreparedStatement requete = this.connexionBD.prepareStatement("Insert into EQUIPE values (?, ?)");
        //     requete.setInt(1, this.idMaxTable("EQUIPE") + 1);
        //     for (Athlete athlete : equipe.getLesAthlètes()) {
        //         this.ajouterAthlete(athlete);
        //     }
        // } catch (Exception e) {
        //     System.out.println("Erreur de connexion à la base de donnée");
        // }
    }

    public void ajouterAthleteDansEquipe(int idAthlete, int idEquipe, char sexe) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into FAIT_PARTIE values (?, ?, ?)");
            requete.setInt(1, idAthlete);
            requete.setInt(2, idEquipe);
            requete.setString(3, sexe+""); // Attention
            requete.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    public void ajouterPays(String nomPays) throws BaseDeDonneeInaccessibleException {
        try {
            if (this.dansPays(nomPays)) {
                throw new PaysDejaExistantException(nomPays);
            }
            PreparedStatement requete = this.connexionBD.prepareStatement("INSERT INTO PAYS (id_Pays, nom_P) VALUES (?, ?)");
            requete.setInt(1, this.idMaxTable("PAYS") + 1);
            requete.setString(2, nomPays);
            requete.executeUpdate();
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }   

    public boolean dansPays(String nomPays) throws PaysInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from PAYS where nom_P = ?");
            requete.setString(1, nomPays);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new PaysInexistantException(nomPays);
        }
    }

    public boolean dansPays(int idPays) throws PaysInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from PAYS where id_Pays = ?");
            requete.setInt(1, idPays);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new PaysInexistantException(idPays);
        }
    }

    public int getIdPays(String nomPays) throws SQLException, PaysInexistantException {
        try {
            if (this.dansPays(nomPays)) {
                PreparedStatement requete = this.connexionBD.prepareStatement("Select id_Pays from PAYS where nom_P = ?");
                requete.setString(1, nomPays);
                ResultSet resultat = requete.executeQuery();
                resultat.next();
                System.out.println(resultat.getInt("id_Pays"));
                return resultat.getInt("id_Pays");
            } else {
                throw new PaysInexistantException(nomPays);
            }
        } catch (PaysInexistantException e) {
            throw new PaysInexistantException(nomPays);
        } 
    }

    public boolean dansJO(JeuxOlympique jeuxOlympique) throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from JEUXOLYMPIQUE where annee = ?");
            requete.setInt(1, jeuxOlympique.getAnnee());
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public void ajouterJO(JeuxOlympique jeuxOlympique) throws BaseDeDonneeInaccessibleException {
        try {
            System.out.println("1");
            if (this.dansJO(jeuxOlympique)) {
                throw new JeuxOlympiqueDejaExistantException(jeuxOlympique.getAnnee());
            }
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into JEUXOLYMPIQUE values (?, ?, ?)");
            requete.setInt(1, this.idMaxTable("JEUXOLYMPIQUE") + 1);
            requete.setInt(2, jeuxOlympique.getAnnee());
            requete.setString(3, jeuxOlympique.getLieu());
            requete.executeUpdate();
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public int getIdJO(JeuxOlympique jeuxOlympique) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select id_JO from JEUXOLYMPIQUE where annee = ?");
            requete.setInt(1, jeuxOlympique.getAnnee());
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getInt("id_JO");
        } catch (Exception e) {
            return 0;
        }
    }

    public void ajouteEpreuve(Epreuve epreuve, JeuxOlympique jeuxOlympique) {
        try {
            if (! this.dansJO(jeuxOlympique)) {
                this.ajouterJO(jeuxOlympique);
            }
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into EPREUVE values (?, ?, ?, ?)");
            requete.setInt(1, this.idMaxTable("EPREUVE") + 1);
            requete.setString(2, epreuve.getSport().toString());
            requete.setInt(3, epreuve.getSexe().toString().charAt(0));
            requete.setInt(4, this.getIdJO(jeuxOlympique));
            // requete.setInt(3, epreuve.getNbAthletes());
            // requete.setInt(4, epreuve.getNbEquipes());
            // requete.setInt(5, epreuve.getNbAthletesParEquipe());
            System.out.println(this.idMaxTable("EPREUVE") + 1);
            System.out.println(epreuve.getSport().toString());
            System.out.println(epreuve.getSexe().toString().charAt(0));
            System.out.println(this.getIdJO(jeuxOlympique));
            System.out.println("7410");
            requete.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        } 
    }
}