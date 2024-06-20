package src.basededonnee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void ajouterAthlete(Athlete athlete) throws AthleteDejaExistantException {
        try {
            if (! this.dansPays(athlete.getPays().getNomPays())) {
                this.ajouterPays(athlete.getPays().getNomPays());
            }
            if (this.dansAthlete(athlete.getNom(), athlete.getPrenom(), this.getIdPays(athlete.getPays().getNomPays()))) {
                throw new AthleteDejaExistantException(athlete.getNom(), athlete.getPrenom());
            } else {
                if (! this.dansPays(athlete.getPays().getNomPays())) {
                    this.ajouterPays(athlete.getPays().getNomPays());
                }
                // System.out.println(athlete.getNom() + " " + athlete.getPrenom() + " " + athlete.getSexe() + " " + athlete.getForce() + " " + athlete.getEndurance() + " " + athlete.getAgilité() + " " + this.getIdPays(athlete.getPays().getNomPays()));
                int idPays = this.getIdPays(athlete.getPays().getNomPays());
                if (this.dansAthlete(athlete.getNom(), athlete.getPrenom(), getIdPays(athlete.getPays().getNomPays()))) {
                    throw new AthleteDejaExistantException(athlete.getNom(), athlete.getPrenom());
                }
                PreparedStatement requete = this.connexionBD.prepareStatement("Insert into ATHLETE values (?, ?, ?, ?, ?, ?, ?, ?)");
                requete.setInt(1, this.idMaxTable("ATHLETE") + 1);
                requete.setString(2, athlete.getNom());
                requete.setString(3, athlete.getPrenom());
                requete.setString(4, (athlete.getSexe() + "").charAt(0) + "");
                requete.setInt(5, athlete.getForce());
                requete.setInt(6, athlete.getEndurance());
                requete.setInt(7, athlete.getAgilité()); 
                requete.setInt(8, idPays); // Attention
                requete.executeUpdate();
            }         
        } catch (Exception e) {
            throw new AthleteDejaExistantException(athlete.getNom(), athlete.getPrenom());
        }
    }

    public boolean dansAthlete(String nom, String prenom, int idPays) throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from ATHLETE where nom_A = ? and prenom_A = ? and id_Pays = ?");
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

    public int getIdAthlete(String nom, String prenom, char sexe) throws AthleteInexistantException {//throws AthleteInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select id_Athlete from ATHLETE where nom_A = ? and prenom_A = ? and sexe_A = ?");
            requete.setString(1, nom);
            requete.setString(2, prenom);
            requete.setString(3, sexe+"");
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getInt("id_Athlete");
        } catch (Exception e) {
            throw new AthleteInexistantException(nom, prenom);
            // System.out.println("Erreur de connexion à la base de donnée"); // Attention
            // return 0;
        }
    }

    public int getIdAthlete(Athlete athlete) throws AthleteInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select id_Athlete from ATHLETE where nom_A = ? and prenom_A = ? and sexe_A = ? and id_Pays = ?");
            requete.setString(1, athlete.getNom());
            requete.setString(2, athlete.getPrenom());
            requete.setString(3, athlete.getSexe().toString().charAt(0) + "");
            requete.setInt(4, this.getIdPays(athlete.getPays().getNomPays()));
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getInt("id_Athlete");
        } catch (Exception e) {
            throw new AthleteInexistantException(athlete.getNom(), athlete.getPrenom());
        }
    }

    public List<Athlete> getAthletes() throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from ATHLETE NATURAL JOIN PAYS");
            ResultSet resultat = requete.executeQuery();
            List<Athlete> athletes = new ArrayList<>();
            while (resultat.next()) {
                char sexeChar = resultat.getString("sexe_A").charAt(0);
                System.out.println(Epreuve.Sexe.valueOf(sexeChar + ""));
                athletes.add(new Athlete(resultat.getString("nom_A"), resultat.getString("prenom_A"), Epreuve.Sexe.valueOf(sexeChar + ""), new Pays(resultat.getString("nom_P")), resultat.getInt("la_force"), resultat.getInt("endurance"), resultat.getInt("agilite")));
                // if (sexeChar == 'M') {
                //     athletes.add(new Athlete(resultat.getString("nom_A"), resultat.getString("prenom_A"), Epreuve.Sexe.valueOf(sexeChar + ""), new Pays(resultat.getString("nom_P")), resultat.getInt("la_force"), resultat.getInt("endurance"), resultat.getInt("agilite")));
                //     System.out.println("Je suis là");
                // } else {
                //     athletes.add(new Athlete(resultat.getString("nom_A"), resultat.getString("prenom_A"), Epreuve.Sexe.valueOf(sexeChar + ""), new Pays(resultat.getString("nom_P")), resultat.getInt("la_force"), resultat.getInt("endurance"), resultat.getInt("agilite")));
                //     System.out.println("Je suis là");
                // }
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

    public void ajouterEquipe(Equipe equipe) throws BaseDeDonneeInaccessibleException {
        try {
            if (! this.dansPays(equipe.getPays().getNomPays())) {
                this.ajouterPays(equipe.getPays().getNomPays());
            }
            if (this.dansEquipe(equipe)) {
                throw new EquipeDejaExistantException(equipe.getNomEquipe());
            } else {
                PreparedStatement requete = this.connexionBD.prepareStatement("Insert into EQUIPE values (?, ?, ?, ?)"); // ICICICICICI
                requete.setInt(1, this.idMaxTable("EQUIPE") + 1);
                requete.setString(2, equipe.getNomEquipe());
                requete.setInt(3, this.getIdPays(equipe.getPays().getNomPays()));
                requete.setString(4, equipe.getSexeEquipe().toString().charAt(0) + "");
                requete.executeUpdate();
            }
            int idEquipe = this.getIdEquipe(equipe);
            for (Athlete athlete : equipe.getLesAthlètes()) {
                // System.out.println(athlete.getNom() + " " + athlete.getPrenom() + " " + athlete.getSexe());
                try {
                    this.ajouterAthlete(athlete);
                    this.getIdAthlete(athlete.getNom(), athlete.getPrenom(), athlete.getSexe().toString().charAt(0));
                    this.ajouterAthleteDansEquipe(this.getIdAthlete(athlete.getNom(), athlete.getPrenom(), athlete.getSexe().toString().charAt(0)), idEquipe, athlete.getSexe().toString().charAt(0));
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

    public void ajouterAthleteDansEquipe(int idAthlete, int idEquipe, char sexe) throws BaseDeDonneeInaccessibleException {
        try {
            System.out.println(idAthlete + " " + idEquipe + " " + sexe);
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into FAIT_PARTIE values (?, ?, ?)");
            requete.setInt(1, idEquipe);
            requete.setInt(2, idAthlete);
            requete.setString(3, sexe+""); // Attention
            requete.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Erreur de connexion à la base de donnée");
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    // public void ajouterPays(String nomPays) throws BaseDeDonneeInaccessibleException {
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
            // throw new BaseDeDonneeInaccessibleException();
        }
    }

    public List<Pays> getPays() throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from PAYS");
            ResultSet resultat = requete.executeQuery();
            List<Pays> pays = new ArrayList<>();
            while (resultat.next()) {
                pays.add(new Pays(resultat.getString("nom_P")));
            }
            return pays;
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

    public boolean dansEpreuve(Epreuve epreuve) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from EPREUVE where type_Epreuve = ? and sexe_Epreuve = ?");
            requete.setString(1, epreuve.getSport().toString());
            requete.setString(2, epreuve.getSexe().toString().charAt(0) + "");
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public int getIdEpreuve(Epreuve epreuve) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select id_Epreuve from EPREUVE where type_Epreuve = ? and sexe_Epreuve = ?");
            requete.setString(1, epreuve.getSport().toString());
            requete.setString(2, epreuve.getSexe().toString().charAt(0) + "");
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getInt("id_Epreuve");
        } catch (Exception e) {
            return 0;
        }
    }

    public void ajouterAthleteDansEpreuveIndiv(EpreuveIndividuelle epreuve, JeuxOlympique jeuxOlympique) throws BaseDeDonneeInaccessibleException {
        try {
            if (!this.dansEpreuve(epreuve)) {
                this.ajouteEpreuve(epreuve, jeuxOlympique);
            } 

            int idEpreuve = this.getIdEpreuve(epreuve);
            for (Participant participant : epreuve.getParticipants()) {
                Athlete athlete = (Athlete) participant;
                try {
                    this.ajouterAthlete(athlete);
                } catch (Exception e) {
                    // Pass, l'athlète est déjà dans la base de donnée
                }
                
                try {
                    PreparedStatement requete = this.connexionBD.prepareStatement("Insert into PARTICIPE_INDIV (id_Athlete, id_Epreuve, type_Epreuve) values (?, ?, ?)");
                    requete.setInt(1, this.getIdAthlete(athlete.getNom(), athlete.getPrenom(), athlete.getSexe().toString().charAt(0)));
                    requete.setInt(2, idEpreuve);
                    requete.setString(3, epreuve.getSport().toString());
                    requete.executeUpdate();
                } catch (Exception e) {
                    // Pass, l'athlète participe déjà à l'épreuve
                }
            }
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    public void ajouterEquipeDansEpreuveEquipe (EpreuveCollective epreuve, JeuxOlympique jeuxOlympique) throws BaseDeDonneeInaccessibleException {
        try {
            if (!this.dansEpreuve(epreuve)) {
                this.ajouteEpreuve(epreuve, jeuxOlympique);
            }
            int idEpreuve = this.getIdEpreuve(epreuve);
            for (Participant participant : epreuve.getParticipants()) {
                Equipe equipe = (Equipe) participant;
                try {
                    this.ajouterEquipe(equipe);
                } catch (Exception e) {
                    // Pass, l'équipe est déjà dans la base de donnée
                }
                try {
                    PreparedStatement requete = this.connexionBD.prepareStatement("Insert into PARTICIPE_COLLEC (id_Equipe, id_Epreuve, type_Epreuve) values (?, ?, ?)");
                    requete.setInt(1, this.getIdEquipe(equipe));
                    requete.setInt(2, idEpreuve);
                    requete.setString(3, epreuve.getSport().toString());
                    requete.executeUpdate();
                } catch (Exception e) {
                    // Pass, l'équipe participe déjà à l'épreuve
                }
            }
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }
    

    public void ajouteEpreuve(Epreuve epreuve, JeuxOlympique jeuxOlympique) throws EpreuveDejaExistantException {
        try {
            if (! this.dansJO(jeuxOlympique)) {
                this.ajouterJO(jeuxOlympique);
            }
            // if (this.dansEpreuve(epreuve)) {
            //     throw new EpreuveDejaExistantException(epreuve.getSport().toString(), epreuve.getSexe().toString().charAt(0));
            // }
            System.out.println(epreuve.getParticipants().size());
            if (! this.dansEpreuve(epreuve)) {// && epreuve.getParticipants().size() > 0) {
                PreparedStatement requete = this.connexionBD.prepareStatement("insert into EPREUVE (id_Epreuve, type_Epreuve, sexe_Epreuve, id_JO) values (?, ?, ?, ?)");
                requete.setInt(1, this.idMaxTable("EPREUVE") + 1);
                requete.setString(2, epreuve.getSport().toString());
                requete.setString(3, epreuve.getSexe().toString().charAt(0) + "");
                requete.setInt(4, this.getIdJO(jeuxOlympique));
                // requete.setInt(3, epreuve.getNbAthletes());
                // requete.setInt(4, epreuve.getNbEquipes());
                // requete.setInt(5, epreuve.getNbAthletesParEquipe());
                requete.executeUpdate();
            }
            if (epreuve instanceof EpreuveIndividuelle) {
                this.ajouterAthleteDansEpreuveIndiv((EpreuveIndividuelle) epreuve, jeuxOlympique);
            } else {
                this.ajouterEquipeDansEpreuveEquipe((EpreuveCollective) epreuve, jeuxOlympique);
                // if (epreuve.getParticipants().size() > 0) {
                //     this.ajouterEquipeDansEpreuveEquipe((EpreuveCollective) epreuve, jeuxOlympique);
                // }
            }
        } catch (Exception e) {
            throw new EpreuveDejaExistantException(epreuve.getSport().toString(), epreuve.getSexe().toString().charAt(0));
        } 
    }

    public List<EpreuveIndividuelle> getEpreuvesIndiv(JeuxOlympique jeuxOlympique) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("select * from EPREUVE NATURAL JOIN PARTICIPE_INDIV WHERE id_JO = ? GROUP BY type_Epreuve");
            requete.setInt(1, this.getIdJO(jeuxOlympique));
            ResultSet resultat = requete.executeQuery();
            List<EpreuveIndividuelle> epreuves = new ArrayList<>();
            EpreuveIndividuelle epreuve;
            while (resultat.next()) {
                epreuve = new EpreuveIndividuelle(Epreuve.TypeSport.valueOf(resultat.getString("type_Epreuve")), Epreuve.Sexe.valueOf(resultat.getString("sexe_Epreuve")));
                for (Athlete athlete : this.getAthletesDansEpreuvesIndiv(epreuve, jeuxOlympique)) {
                    epreuve.participer(athlete);
                }
                epreuves.add(epreuve);
                
            }
            return epreuves;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<EpreuveCollective> getEpreuveCollec(JeuxOlympique jeuxOlympique) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("select * from EPREUVE NATURAL JOIN PARTICIPE_COLLEC WHERE id_JO = ? GROUP BY type_Epreuve");
            requete.setInt(1, this.getIdJO(jeuxOlympique));
            ResultSet resultat = requete.executeQuery();
            List<EpreuveCollective> epreuves = new ArrayList<>();
            EpreuveCollective epreuve;
            while (resultat.next()) {
                System.out.println("7878787878" + resultat.getBoolean("a_ete_joue"));
                epreuve = new EpreuveCollective(Epreuve.TypeSport.valueOf(resultat.getString("type_Epreuve")), Epreuve.Sexe.valueOf(resultat.getString("sexe_Epreuve")));
                for (Equipe equipe : this.getEquipesDansEpreuves(epreuve, jeuxOlympique)) {
                    try {
                        epreuve.participer(equipe);
                    } catch (Exception e) {
                        // TODO: handle exception
                        // Pass, pas assez de participants
                    }
                }
                jeuxOlympique.ajouteEpreuve(epreuve);
                if (resultat.getBoolean("a_ete_joue")) {
                    jeuxOlympique.lancerUneEpreuve(epreuve);
                }
                epreuves.add(epreuve);
            }
            return epreuves;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void lancerUneEpreuve(Epreuve epreuve, JeuxOlympique jeuxOlympique) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Update EPREUVE set a_ete_joue = true where id_Epreuve = ? and type_Epreuve = ? and sexe_Epreuve = ?");
            requete.setInt(1, this.getIdEpreuve(epreuve));
            requete.setString(2, epreuve.getSport().toString());
            requete.setString(3, epreuve.getSexe().toString().charAt(0) + "");
            requete.executeUpdate();
        } catch (Exception e) {
            // Pass
        }
    }


    // TODO: A tester ; pb de nbr d'épreuves et pas encore ajouter les participants
    public List<Epreuve> getEpreuves(JeuxOlympique jeuxOlympique) {
        try {
            List<Epreuve> epreuves = new ArrayList<>();
            List<EpreuveIndividuelle> epreuvesIndiv = this.getEpreuvesIndiv(jeuxOlympique);
            List<EpreuveCollective> epreuvesCollec = this.getEpreuveCollec(jeuxOlympique);
            epreuves.addAll(epreuvesIndiv);
            epreuves.addAll(epreuvesCollec);
            return epreuves;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Athlete> getAthletesDansEpreuvesIndiv(Epreuve epreuve, JeuxOlympique jeuxOlympique) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from PARTICIPE_INDIV where id_Epreuve = ? and type_Epreuve = ?");
            requete.setInt(1, this.getIdEpreuve(epreuve));
            requete.setString(2, epreuve.getSport().toString());
            ResultSet resultat = requete.executeQuery();
            List<Athlete> athletes = new ArrayList<>();
            while (resultat.next()) {
                athletes.add(this.getAthlete((resultat.getInt("id_Athlete"))));
            }
            return athletes;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Equipe> getEquipesDansEpreuves(Epreuve epreuve, JeuxOlympique jeuxOlympique) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from PARTICIPE_COLLEC where id_Epreuve = ? and type_Epreuve = ?");
            requete.setInt(1, this.getIdEpreuve(epreuve));
            requete.setString(2, epreuve.getSport().toString());
            ResultSet resultat = requete.executeQuery();
            List<Equipe> equipes = new ArrayList<>();
            Equipe equipe;
            while (resultat.next()) {
                PreparedStatement requeteEquipe = this.connexionBD.prepareStatement("Select * from EQUIPE where id_Equipe = ?");
                requeteEquipe.setInt(1, resultat.getInt("id_Equipe"));
                ResultSet resultatEquipe = requeteEquipe.executeQuery();
                resultatEquipe.next();
                equipe = new Equipe(resultatEquipe.getString("nom_E"), new Pays(resultatEquipe.getString("id_Pays")), epreuve.getSexe());
                equipes.add(equipe);
                for (Athlete athlete : this.getAthletesDansEquipe(equipe)) {
                    equipe.ajouterMembre(athlete);;
                }
            }
            return equipes;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }   

    public List<Equipe> getLesEquipes() {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from EQUIPE");
            ResultSet resultat = requete.executeQuery();
            List<Equipe> equipes = new ArrayList<>();
            Equipe equipe;
            while (resultat.next()) {
                equipe = new Equipe(resultat.getString("nom_E"), new Pays(resultat.getString("id_Pays")), Epreuve.Sexe.valueOf(resultat.getString("sexe_Equipe")));
                equipes.add(equipe);
                for (Athlete athlete : this.getAthletesDansEquipe(equipe)) {
                    equipe.ajouterMembre(athlete);
                }
            }
            return equipes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    // To do : A tester
    public List<Athlete> getAthletesDansEquipe(Equipe equipe) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from FAIT_PARTIE where id_Equipe = ? and sexe = ?");
            requete.setInt(1, this.getIdEquipe(equipe));
            requete.setString(2, equipe.getSexeEquipe().toString().charAt(0) + "");
            ResultSet resultat = requete.executeQuery();
            List<Athlete> athletes = new ArrayList<>();
            while (resultat.next()) {
                PreparedStatement requeteAthlete = this.connexionBD.prepareStatement("Select * from ATHLETE where id_Athlete = ?");
                requeteAthlete.setInt(1, resultat.getInt("id_Athlete"));
                ResultSet resultatAthlete = requeteAthlete.executeQuery();
                resultatAthlete.next();
                athletes.add(new Athlete(resultatAthlete.getString("nom_A"), resultatAthlete.getString("prenom_A"), Epreuve.Sexe.valueOf(resultatAthlete.getString("sexe_A")), new Pays(resultatAthlete.getString("id_Pays")), resultatAthlete.getInt("la_force"), resultatAthlete.getInt("endurance"), resultatAthlete.getInt("agilite")));
            }
            return athletes;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Athlete> rechercherAthlete(String chaineDeCarac) {
        try {
            List<Athlete> lesAthetes = new ArrayList<>();
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from ATHLETE where nom_A like ? or prenom_A like ?");
            requete.setString(1, "%" + chaineDeCarac + "%");
            requete.setString(2, "%" + chaineDeCarac + "%");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                lesAthetes.add(new Athlete(resultat.getString("nom_A"), resultat.getString("prenom_A"), Epreuve.Sexe.valueOf(resultat.getString("sexe_A")), new Pays(resultat.getString("id_Pays")), resultat.getInt("la_force"), resultat.getInt("endurance"), resultat.getInt("agilite")));
            }
            return lesAthetes;
        } catch (Exception e) {
            // TODO: handle exception
            return new ArrayList<>();
        }
    }

    public void modifierAthlete(Athlete athlete, int idAthlete) throws AthleteInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Update ATHLETE set nom_A = ?, prenom_A = ?, sexe_A = ?, la_force = ?, endurance = ?, agilite = ?, id_Pays = ? where id_Athlete = ?");
            requete.setString(1, athlete.getNom());
            requete.setString(2, athlete.getPrenom());
            requete.setString(3, athlete.getSexe().toString().charAt(0) + "");
            requete.setInt(4, athlete.getForce());
            requete.setInt(5, athlete.getEndurance());
            requete.setInt(6, athlete.getAgilité());
            requete.setInt(7, this.getIdPays(athlete.getPays().getNomPays())); 
            requete.setInt(8, idAthlete);
            requete.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            throw new AthleteInexistantException(athlete.getNom(), athlete.getPrenom());
        }
    }

    public Athlete getAthlete(int idAthlete) throws AthleteInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from ATHLETE NATURAL JOIN PAYS where id_Athlete = ?");
            requete.setInt(1, idAthlete);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return new Athlete(resultat.getString("nom_A"), resultat.getString("prenom_A"), Epreuve.Sexe.valueOf(resultat.getString("sexe_A")), new Pays(resultat.getString("nom_P")), resultat.getInt("la_force"), resultat.getInt("endurance"), resultat.getInt("agilite"));
        } catch (Exception e) {
            throw new AthleteInexistantException(idAthlete);
        }
    }
}