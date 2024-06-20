package src.controlleur;
import src.vues.*;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ControleurDeconnexion implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurDeconnexion(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Attention");
        confirmationAlert.setContentText("Vous allez vous déconnecter. Confirmez-vous ?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Code to handle OK
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), "Fenetre Accueil");
        }
    }
}
