package src.basededonnee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void inscription(String identifiant, String mail, String motDePasse) {
        try {
            this.connexionBD.connecter();
            System.out.println("Connexion reussi !");
            this.connexionBD.close();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

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