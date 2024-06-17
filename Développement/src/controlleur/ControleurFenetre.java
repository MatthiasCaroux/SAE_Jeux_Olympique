package src.controlleur;
import src.vues.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;


public class ControleurFenetre implements EventHandler<ActionEvent>{

    private ApplicationJeuxOlympique applicationJeuxOlympique;
    private Scene scene;
    private String titre;

    public ControleurFenetre(ApplicationJeuxOlympique applicationJeuxOlympique, Scene scene, String titre){
        this.applicationJeuxOlympique = applicationJeuxOlympique;
        this.scene = scene;
        this.titre = titre;
        System.out.println(applicationJeuxOlympique + " " +  scene  +  titre);
    }

    public void handle(ActionEvent event) {
        if(event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#seConnecter")){
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneConnexion(), titre, "seConnecter");
            System.out.println("Lançons la fenetre de connexion");
        }
        else if (event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#sinscrire")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneInscription(), titre, "sinscrire");
            System.out.println("Lançons la fenetre d'inscription");
        }
        else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#entrer")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneClassement(), titre, "entrer");

            // if (this.applicationJeuxOlympique.getRequete().connexion(this.applicationJeuxOlympique.getIdentifiant(), this.applicationJeuxOlympique.getMotDePasse())){
            //     this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneClassement(), "Fenetre de classement");

            System.out.println("Lançons la fenetre de classement");
        }
        else if(event.getSource() == this.applicationJeuxOlympique.getSceneInscription().lookup("#boutonRetour")){
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre, "boutonRetour");
            System.out.println("Retour à la fenetre d'Accueil");
        }
        else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#boutonRetourConnexion")) {
            System.out.println("coucouc");
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre, "boutonRetourConnexion");
            System.out.println("Retour à la fenetre d'Accueil");
        }
    }
}
