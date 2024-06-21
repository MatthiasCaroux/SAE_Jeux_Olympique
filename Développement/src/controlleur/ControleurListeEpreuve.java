package src.controlleur;
import src.vues.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurListeEpreuve implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurListeEpreuve(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        System.out.println("ListeEpreuve");
        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneListeEpreuve(), "Liste des épreuves");
    }
}
