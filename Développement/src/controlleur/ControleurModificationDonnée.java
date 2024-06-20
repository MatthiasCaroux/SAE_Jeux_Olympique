package src.controlleur;
import src.vues.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurModificationDonnée implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurModificationDonnée(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        System.out.println("Modification Donnée");
        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneModificationDonnée(), "Modification Donnée");
    }
}
