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

    public Requete() throws ClassNotFoundException {
        this.connexionBD = new ConnexionBD();
        try {
            this.connexionBD.connecter();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
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
            System.out.println("moiuyerg moiuerz gmoiusghyog");
            return resultat.getInt("max");
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean connexion(String identifiant, String motDePasse) {
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
            System.out.println("Erreur de connexion à la base de donnée");
            return false;
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

    public char getRoleUtilisateur(String identifiant, String motDePasse) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select rôle from UTILISATEUR where identifiant = ? and mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, motDePasse);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getString("rôle").charAt(0);
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
            return 'C';
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
            System.out.println("Erreur de connexion à la base de donnée");
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    // public void supprimerUtilisateur() {
    //     PreparedStatement requete = this?connexionBD.prepareStatement("delete from UTILISATEUR where mdp = ?");
    // }

    public void ajouterAthlete(String nom, String prenom, int force, int endurance, int agilite, char sexe, int idPays) {
        try {
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
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    public void ajouterAthlete(Athlete athlete) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into ATHLETE values (?, ?, ?, ?, ?, ?, ?)");
            requete.setInt(1, this.idMaxTable("ATHLETE") + 1);
            requete.setString(2, athlete.getNom());
            requete.setString(3, athlete.getPrenom());
            requete.setInt(4, athlete.getForce());
            requete.setInt(5, athlete.getEndurance());
            requete.setInt(6, athlete.getAgilité());
            requete.setString(7, athlete.getSexe()+""); // Attention
            requete.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    public boolean dansAthlete(String nom, String prenom, int idPays) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from ATHLETE where nom = ? and prenom = ? and id_Pays = ?");
            requete.setString(1, nom);
            requete.setString(2, prenom);
            requete.setInt(3, idPays);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
            return false;
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

    // TO DO IMPORTANT
    // public List<Athlete> getAthletes() {
    //     try {
    //         PreparedStatement requete = this.connexionBD.prepareStatement("Select * from ATHLETE NATURAL JOIN PAYS");
    //         ResultSet resultat = requete.executeQuery();
    //         List<Athlete> athletes = new ArrayList<>();
    //         while (resultat.next()) {
    //             char sexeChar = (resultat.getCharacterStream("sexe")+"").charAt(0);
    //             if (sexeChar == 'M') {
    //                 athletes.add(new Athlete(resultat.getString("nom"), resultat.getString("prenom"), Epreuve.Sexe.M, resultat.getInt("force"), resultat.getInt("endurance"), resultat.getInt("agilite"));
    //             } else {
    //                 athletes.add(new Athlete(resultat.getString("nom"), resultat.getString("prenom"), resultat.getString("sexe").charAt(0), resultat.getInt("force"), resultat.getInt("endurance"), resultat.getInt("agilite"));
    //             }
    //         }
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //     }
    // }


    public void supprimerAthlete(int idAthlete) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("delete from ATHLETE where id_Athlete = ?");
            requete.setInt(1, idAthlete);
            requete.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    // TO DO
    // public Athlete getAthlete(int idAthlete) {
    //     try {
    //         PreparedStatement requete = this.connexionBD.prepareStatement("Select * from ATHLETE where id_Athlete = ?");
    //         requete.setInt(1, idAthlete);
    //         ResultSet resultat = requete.executeQuery();
    //         resultat.next();
    //         char sexeChar = (resultat.getCharacterStream("sexe")+"").charAt(0);
    //         if (sexeChar == 'M') {
    //             return new Athlete(resultat.getString("nom"), resultat.getString("prenom"), Epreuve.Sexe.M, resultat.getInt("force"), resultat.getInt("endurance"), resultat.getInt("agilite"));
    //         }
    //         return new Athlete(resultat.getString("nom"), resultat.getString("prenom"), resultat.getString("sexe").charAt(0), resultat.getInt("force"), resultat.getInt("endurance"), resultat.getInt("agilite"));
    //     } catch (Exception e) {
    //         System.out.println("Erreur de connexion à la base de donnée");
    //         return null;
    //     }
    // }

    public void ajouterEquipe(String nomEquipe, int idPays) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into EQUIPE values (?, ?, ?)");
            requete.setInt(1, this.idMaxTable("EQUIPE") + 1);
            requete.setString(2, nomEquipe);
            requete.setInt(3, idPays);
            requete.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
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

    public void ajouterPays(String nomPays) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("INSERT INTO PAYS (id_Pays, nom_P) VALUES (?, ?)");
            requete.setInt(1, this.idMaxTable("PAYS") + 1);
            System.out.println("Pays ajouté");
            requete.setString(2, nomPays);
            System.out.println("Pays ajouté");
            requete.executeUpdate();
            System.out.println("Pays ajouté");
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
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

    

    // Incorrecte
    // public void ajouterAthleteDansEpreuve(String nomEpreuve, String typeSport, String sexe) {
    //     try {
    //         PreparedStatement requete = this.connexionBD.prepareStatement("Insert into PARTICIPE_INDIV values (?, ?, ?, ?)");
    //         requete.setInt(1, this.idMaxTable("EPREUVE") + 1);
    //         requete.setString(2, nomEpreuve);
    //         requete.setString(3, typeSport);
    //         requete.setString(4, sexe);
    //         requete.executeUpdate();
    //     } catch (Exception e) {
    //         System.out.println("Erreur de connexion à la base de donnée");
    //     }
    // }
}