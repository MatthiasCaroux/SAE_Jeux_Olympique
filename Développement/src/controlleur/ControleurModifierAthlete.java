package src.controlleur;
import src.vues.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurModifierAthlete implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurModifierAthlete(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        System.out.println("Modification Donnée");
        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneModifierAthlete(), "Modification Athlete");
    }
}
