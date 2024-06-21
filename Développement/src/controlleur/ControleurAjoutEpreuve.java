package src.controlleur;
import src.vues.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurAjoutEpreuve implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurAjoutEpreuve(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        System.out.println("Ajout d'une épreuve");
        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAjouterUneEpreuve(), "Ajout des Données");
    }
}