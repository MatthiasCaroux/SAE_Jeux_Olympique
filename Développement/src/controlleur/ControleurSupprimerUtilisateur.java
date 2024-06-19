package src.controlleur;

import src.vues.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import java.util.Optional;

public class ControleurSupprimerUtilisateur implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;
    private ComboBox<String> combo;

    public ControleurSupprimerUtilisateur(ApplicationJeuxOlympique applicationJeuxOlympique, ComboBox<String> combo){
        this.applicationJeuxOlympique = applicationJeuxOlympique;
        this.combo = combo;     
    }

    public void handle(ActionEvent event) {
        try {
            String utilisateur = (String) this.combo.getValue();
            if (utilisateur != null) {
                // Créer une alerte de confirmation
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment supprimer l'utilisateur : " + utilisateur + " ?");

                // Afficher l'alerte et attendre la réponse de l'utilisateur
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // L'utilisateur a confirmé la suppression
                    this.applicationJeuxOlympique.getRequete().supprimerUtilisateur(utilisateur);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}