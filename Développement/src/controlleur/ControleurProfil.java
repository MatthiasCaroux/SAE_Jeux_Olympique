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


public class ControleurProfil implements EventHandler<ActionEvent>{


    private ApplicationJeuxOlympique applicationJeuxOlympique ;
    private String titre;

    @FXML
    private MenuItem choixMonProfil;

    @FXML
    private MenuItem choixDeconnexion;

    public ControleurProfil(ApplicationJeuxOlympique applicationJeuxOlympique, String titre){
        System.out.println("ControleurProfil");
        this.applicationJeuxOlympique = applicationJeuxOlympique;
        this.titre = titre;
    }
    

    private void handleChoixDeconnexion(ActionEvent event) {
        System.out.println("Déconnexion a été sélectionné !");
        Alert alert = new Alert(AlertType.INFORMATION, "Etes-vous sûr de vouloir vous déconnecter ?");
        alert.setTitle("Déconnexion");
        alert.setHeaderText(null);
        alert.setContentText("Déconnexion a été sélectionné !");
        alert.showAndWait();
    }

    @Override
    public void handle(ActionEvent arg0) {
        if (arg0.getSource() == this.applicationJeuxOlympique.getSceneAccueilAdmin().lookup("#choixMonProfil")) {
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneProfil(), "Profil", "choixMonProfil");
            System.out.println("Lançons la fenetre de connexion");
        } else if (arg0.getSource() == this.applicationJeuxOlympique.getSceneAccueilAdmin().lookup("#choixDeconnexion")) {
            handleChoixDeconnexion(arg0);
            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), "Fenetre Accueil", "choixDeconnexion"); //mettre qui si il dit ok
        }
    }
}