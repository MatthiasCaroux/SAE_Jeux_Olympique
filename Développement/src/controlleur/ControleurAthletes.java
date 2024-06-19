package src.controlleur;
import src.vues.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurAthletes implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurAthletes(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAthletes(), "Gestion des Athletes");
    }
}