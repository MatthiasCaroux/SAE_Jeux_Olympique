package src.controlleur;
import src.vues.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurGererEpreuve implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurGererEpreuve(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        System.out.println("4");
        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneGestionEpreuve(), "Gestion des Utilisateurs");
    }
}