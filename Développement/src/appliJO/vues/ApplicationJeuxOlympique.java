package src.appliJO.vues;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;

public class ApplicationJeuxOlympique extends Application {
    Scene sceneFenetreAccueil;
    Scene sceneConnexion;
    Scene sceneInscription;
    Scene sceneClassement;
    Pane fenetreCourante;
    VBox fenetreAccueil;
    BorderPane fenetreConnexion;
    BorderPane fenetreInscription;
    BorderPane fenetreClassement;
    FXMLLoader loader;
    FXMLLoader loaderConnexion;
    FXMLLoader loaderInscription;
    FXMLLoader loaderClassement;
  
    @Override
    public void init(){    
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        loader = new FXMLLoader(this.getClass().getResource("accueil.fxml"));
        this.fenetreAccueil = loader.load();

        loaderConnexion = new FXMLLoader(this.getClass().getResource("connexion.fxml"));
        this.fenetreConnexion = loaderConnexion.load();

        loaderInscription = new FXMLLoader(this.getClass().getResource("inscription.fxml"));
        this.fenetreInscription = loaderInscription.load();

        loaderClassement = new FXMLLoader(this.getClass().getResource("classement.fxml"));
        this.fenetreClassement = loaderClassement.load();


        sceneFenetreAccueil = new Scene(fenetreAccueil);
        sceneConnexion = new Scene(fenetreConnexion);
        sceneInscription = new Scene(fenetreInscription);
        sceneClassement = new Scene(fenetreClassement);
        

        stage.setScene(sceneFenetreAccueil);
        stage.setTitle("Fenetre d'acceuil");
        stage.show();

        Button boutonConnexion = (Button) sceneFenetreAccueil.lookup("#seConnecter");
        boutonConnexion.setOnAction(e -> {
            stage.setScene(sceneConnexion);
            stage.setTitle("Fenetre de connexion");
        });

        Button boutonSinscrire = (Button) sceneFenetreAccueil.lookup("#sinscrire");
        boutonSinscrire.setOnAction(e -> {
            stage.setScene(sceneInscription);
            stage.setTitle("Fenetre d'inscription");
        });

        Button boutonClassement = (Button) sceneConnexion.lookup("#entrer");
        boutonClassement.setOnAction(e -> {
            stage.setScene(sceneClassement);
            stage.setTitle("Fenetre de classement");
        });

    }

    public static void main(String[] args) {
        launch(args);
    }  
}

