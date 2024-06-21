package src.controlleur;
import src.vues.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurRetourJO implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;
    private String role;

    public ControleurRetourJO(ApplicationJeuxOlympique applicationJeuxOlympique, String role) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
        this.role = role;
    }

    public void handle(ActionEvent event) {
        switch (role) {
            case "admin":
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAccueilAdmin(), "Accueil Admin");
                break;
            case "journaliste":
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAccueilJournaliste(), "Accueil Utilisateur");
                break;
            case "organisateur":
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAccueilOrganisateur(), "Accueil Organisateur");
                break;
            
        
            default:
                break;
        }
        
    }
}
