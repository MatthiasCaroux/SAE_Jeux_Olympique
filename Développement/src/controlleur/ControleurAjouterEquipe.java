package src.controlleur;
import src.vues.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurAjouterEquipe implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurAjouterEquipe(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAjouterUneEquipe(), "Ajout des Données");
    }
}