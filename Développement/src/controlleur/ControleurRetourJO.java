package src.controlleur;
import src.vues.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurRetourJO implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurRetourJO(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAccueilAdmin(), "Accueil Admin");
    }
}
