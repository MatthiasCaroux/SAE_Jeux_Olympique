package src.controlleur;
import src.vues.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;


public class ControleurFenetre implements EventHandler<ActionEvent>{

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurFenetre(ApplicationJeuxOlympique applicationJeuxOlympique, Scene scene, String titre){
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        if(event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#seConnecter")){

            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneConnexion(), "Fenetre de connexion", "seConnecter");
            System.out.println("Lançons la fenetre de connexion");

        }
        else if (event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#sinscrire")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneInscription(), "Fenetre d'inscription", "sinscrire");
            System.out.println("Lançons la fenetre d'inscription");
        }
        else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#entrer")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneClassement(), "Fenetre de classement", "entrer");

            // if (this.applicationJeuxOlympique.getRequete().connexion(this.applicationJeuxOlympique.getIdentifiant(), this.applicationJeuxOlympique.getMotDePasse())){
            //     this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneClassement(), "Fenetre de classement");

            System.out.println("Lançons la fenetre de classement");
        }
        else if(event.getSource() == this.applicationJeuxOlympique.getSceneInscription().lookup("#boutonRetour")){
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), "Fenetre Accueil", "boutonRetour");
            System.out.println("Retour à la fenetre d'Accueil'");
        }
        else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#boutonRetourConnexion")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), "Fenetre Accueil", "boutonRetourConnexion");
            System.out.println("Retour à la fenetre d'Accueil'");
        }
    }
}
