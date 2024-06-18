package src.basededonnee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.basededonnee.exception.*;
import src.modele.jeuxOlympique.Athlete;
import src.modele.jeuxOlympique.Epreuve;

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

    public int idMaxUtilisateur(String nomTable) throws SQLException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select max(id_Utilisateur) as max from " + nomTable);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
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

    public void inscription(String identifiant, String mail, String motDePasse) {
        try {
            if (! this.dansUtilisateur(identifiant, mail, motDePasse)) {
                PreparedStatement requete = this.connexionBD.prepareStatement("Insert into UTILISATEUR values (?, ?, ?, ?, 'C')");
                requete.setInt(1, this.idMaxUtilisateur("UTILISATEUR") + 1);
                requete.setString(2, identifiant);
                requete.setString(3, mail);
                requete.setString(4, motDePasse);
                requete.executeUpdate();
            } else {
                System.out.println("Utilisateur déjà existant");
                throw new UtilisateurDejaExistantException();
            }
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    // public void supprimerUtilisateur() {
    //     PreparedStatement requete = this?connexionBD.prepareStatement("delete from UTILISATEUR where mdp = ?");
    // }

    public void ajouterAthlete(String nom, String prenom, int force, int endurance, int agilite, char sexe, int idPays) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into ATHLETE values (?, ?, ?, ?, ?, ?, ?)");
            requete.setInt(1, this.idMaxUtilisateur("ATHLETE") + 1);
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
            requete.setInt(1, this.idMaxUtilisateur("ATHLETE") + 1);
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
            requete.setInt(1, this.idMaxUtilisateur("EQUIPE") + 1);
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
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into PAYS values (?, ?)");
            requete.setInt(1, this.idMaxUtilisateur("PAYS") + 1);
            requete.setString(2, nomPays);
            requete.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }   

    // Incorrecte
    // public void ajouterAthleteDansEpreuve(String nomEpreuve, String typeSport, String sexe) {
    //     try {
    //         PreparedStatement requete = this.connexionBD.prepareStatement("Insert into PARTICIPE_INDIV values (?, ?, ?, ?)");
    //         requete.setInt(1, this.idMaxUtilisateur("EPREUVE") + 1);
    //         requete.setString(2, nomEpreuve);
    //         requete.setString(3, typeSport);
    //         requete.setString(4, sexe);
    //         requete.executeUpdate();
    //     } catch (Exception e) {
    //         System.out.println("Erreur de connexion à la base de donnée");
    //     }
    // }
}