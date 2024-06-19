package src.controlleur;

import src.vues.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import java.util.Optional;

public class ControleurChangerRole implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;
    private char role;
    private ComboBox<String> identifiants;

    public ControleurChangerRole(ApplicationJeuxOlympique applicationJeuxOlympique, char role, ComboBox<String> identifiants){
        this.applicationJeuxOlympique = applicationJeuxOlympique;
        this.role = role;
        this.identifiants = identifiants;
    }

    public void handle(ActionEvent event) {
        try {
            String utilisateur = identifiants.getValue();
            if (utilisateur != null) {
                // Créer une alerte de confirmation
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de changement de rôle");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment changer le rôle de l'utilisateur : " + utilisateur + " en '" + role + "' ?");

                // Afficher l'alerte et attendre la réponse de l'utilisateur
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // L'utilisateur a confirmé le changement de rôle
                    this.applicationJeuxOlympique.getRequete().changerRoleUtilisateur(utilisateur, role);
                    
                    // Créer une alerte de confirmation de réussite
                    Alert successAlert = new Alert(AlertType.INFORMATION);
                    successAlert.setTitle("Changement de rôle réussi");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Le rôle de l'utilisateur : " + utilisateur + " a été changé en '" + role + "' avec succès.");
                    successAlert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
