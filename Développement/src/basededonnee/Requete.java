package src.basededonnee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.basededonnee.exception.*;

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

    public int idMaxUtilisateur() throws SQLException {
        PreparedStatement requete = this.connexionBD.prepareStatement("Select max(id_Utilisateur) as max from UTILISATEUR");
        ResultSet resultat = requete.executeQuery();
        resultat.next();
        return resultat.getInt("max");
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
                PreparedStatement requete = this.connexionBD.prepareStatement("Insert into UTILISATEUR values (?, ?, ?, ?, 'U')");
                requete.setInt(1, this.idMaxUtilisateur() + 1);
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

    public void getEtudiant() {
        try {
            this.connexionBD.connecter();
            System.out.println("Connexion reussi !");
            this.connexionBD.close();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
        // PreparedStatement requete = this.mysql.prepareStatement("Select * from DETUDIANT");
		// ResultSet resultat = requete.executeQuery();
		// while(resultat.next()){
		// 	System.out.println(resultat.getString("prenome"));
		// }
    }
}