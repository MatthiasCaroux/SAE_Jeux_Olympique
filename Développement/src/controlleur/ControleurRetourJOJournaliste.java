package src.controlleur;
import src.vues.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurRetourJOJournaliste implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurRetourJOJournaliste(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneJournalisteAccueil(), "Accueil Journaliste");
    }
}