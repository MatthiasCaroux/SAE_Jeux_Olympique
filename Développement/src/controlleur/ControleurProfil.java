package src.controlleur;


import src.vues.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;      


public class ControleurProfil implements EventHandler<ActionEvent>{


    private ApplicationJeuxOlympique applicationJeuxOlympique ;
    private String titre;

    @FXML
    private Button choixMonProfil;

    @FXML
    private Button choixDeconnexion;

    public ControleurProfil(ApplicationJeuxOlympique applicationJeuxOlympique, String titre){
        System.out.println("ControleurProfil");
        this.applicationJeuxOlympique = applicationJeuxOlympique;
        this.titre = titre;
    }

    private Alert handleChoixDeconnexion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Etes-vous sûr de vouloir vous déconnecter ?");
        alert.setTitle("Partie Perdue");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinWidth(400);
        dialogPane.setMinHeight(200);
        alert.showAndWait();
        return alert;
    }

    @Override
    public void handle(ActionEvent arg0) {
        if (arg0.getSource() == this.applicationJeuxOlympique.getSceneAccueilAdmin().lookup("#choixMonProfil")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneProfil(), "Profil", "choixMonProfil");
            System.out.println("Votre profil");
        } else if (arg0.getSource() == this.applicationJeuxOlympique.getSceneAccueilAdmin().lookup("#choixDeconnexion")) {
            Alert alert = handleChoixDeconnexion(arg0);
            if (alert.getResult() == ButtonType.OK) {
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), "Fenetre Accueil", "choixDeconnexion"); //mettre qui si il dit ok
            }
        }
    }
}