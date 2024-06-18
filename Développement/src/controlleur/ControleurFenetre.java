package src.controlleur;
import src.vues.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurFenetre implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;
    private String titre;

    public ControleurFenetre(ApplicationJeuxOlympique applicationJeuxOlympique, String titre) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
        this.titre = titre;
    }

    public void handle(ActionEvent event) {
        if (event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#seConnecter")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneConnexion(), titre, "seConnecter");
            System.out.println("Lançons la fenetre de connexion");
        } else if (event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#sinscrire")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneInscription(), titre, "sinscrire");
            System.out.println("Retour à la fenetre d'inscription");
        } else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#entrer")) {
            String identifiant = this.applicationJeuxOlympique.getSceneConnexion().lookup("#identifiant").toString();
            String motDePasse = this.applicationJeuxOlympique.getSceneConnexion().lookup("#motDePasse").toString();
            if (this.applicationJeuxOlympique.getRequete().dansUtilisateur(identifiant, motDePasse)){//regarde si l'utilisateur est dans la base
                switch (this.applicationJeuxOlympique.getRequete().getRoleUtilisateur(identifiant, motDePasse)){
                    case 'A':
                        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAccueilAdmin(), titre, "entrer");                        
                        break;
                    case 'C':
                        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneClassement(), titre, "entrer");
                        break;
                    case 'O':
                        // this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneOrganisateur(), titre, "entrer");
                        break;                
                    default:
                        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneClassement(), titre, "entrer");
                        break;
                }
            }
            System.out.println("Lançons la fenetre de classement");
        } else if (event.getSource() == this.applicationJeuxOlympique.getSceneInscription().lookup("#boutonRetour")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre, "boutonRetour");
            System.out.println("Retour à la fenetre d'Accueil");
        } else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#boutonRetourConnexion")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre, "boutonRetourConnexion");
            System.out.println("Retour à la fenetre d'Accueil");
        }
    }
}
