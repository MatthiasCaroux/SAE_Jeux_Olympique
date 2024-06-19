package src.controlleur;
import src.vues.*;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

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
