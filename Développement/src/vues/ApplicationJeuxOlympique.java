package src.vues;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;


import src.controlleur.*;
import src.basededonnee.*;

public class ApplicationJeuxOlympique extends Application {
    private Requete requete;
    private Scene sceneFenetreAccueil;
    private Scene sceneConnexion;
    private Scene sceneInscription;
    private Scene sceneClassement;
    private VBox fenetreAccueil;
    private BorderPane fenetreConnexion;
    private BorderPane fenetreInscription;
    private BorderPane fenetreClassement;
    private FXMLLoader loader;
    private FXMLLoader loaderConnexion;
    private FXMLLoader loaderInscription;
    private FXMLLoader loaderClassement;
  
    @Override
    public void init() throws ClassNotFoundException, SQLException{
        System.out.println("Initialisation de l'application");
        this.requete = new Requete();
        System.out.println(this.requete.idMaxUtilisateur());
        System.out.println(this.requete.connexion("admin", "admin"));
        // try {
        //     System.out.println("Tentative de connexion à la base de donnée");
        //     this.requete.connecter();
        //     System.out.println("Connexion reussi !");
        // } catch (Exception e) {
        //     System.out.println("Erreur de connexion à la base de donnée");
        // }
        // this.connexionBD.connecter();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Lancement de l'application");
        loader = new FXMLLoader(this.getClass().getResource("/fxml/accueil.fxml"));
        this.fenetreAccueil = loader.load();

        loaderConnexion = new FXMLLoader(this.getClass().getResource("/fxml/connexion.fxml"));
        this.fenetreConnexion = loaderConnexion.load();

        loaderInscription = new FXMLLoader(this.getClass().getResource("/fxml/inscription.fxml"));
        this.fenetreInscription = loaderInscription.load();

        loaderClassement = new FXMLLoader(this.getClass().getResource("/fxml/classement.fxml"));
        this.fenetreClassement = loaderClassement.load();


        sceneFenetreAccueil = new Scene(fenetreAccueil);
        sceneConnexion = new Scene(fenetreConnexion);
        sceneInscription = new Scene(fenetreInscription);
        sceneClassement = new Scene(fenetreClassement);
        

        stage.setScene(sceneFenetreAccueil);
        stage.setTitle("Fenetre d'accueil");
        stage.show();

        Button boutonConnexion = (Button) sceneFenetreAccueil.lookup("#seConnecter");
        boutonConnexion.setOnAction(new ControleurFenetre(this, sceneConnexion, "Fenetre de connexion"));

        Button boutonSinscrire = (Button) sceneFenetreAccueil.lookup("#sinscrire");
        boutonSinscrire.setOnAction(new ControleurFenetre(this, sceneInscription, "Fenetre d'inscription"));

        Button boutonClassement = (Button) sceneConnexion.lookup("#entrer");
        boutonClassement.setOnAction(new ControleurFenetre(this, sceneClassement, "Fenetre de classement"));

        Button boutonRetour = (Button) sceneInscription.lookup("#boutonRetour");
        boutonRetour.setOnAction(new ControleurFenetre(this, sceneFenetreAccueil, "Fenetre Accueil"));

    }

    public void changerFenetre(Scene scene, String titre){
        Stage stage = null;
        if (titre.equals("Fenetre de connexion")) {
            stage = (Stage) sceneFenetreAccueil.getWindow();
        }
        else if (titre.equals("Fenetre d'inscription")) {
            stage = (Stage) sceneFenetreAccueil.getWindow();
        }
        else if (titre.equals("Fenetre de classement")) {
            stage = (Stage) sceneConnexion.getWindow();
        }
        else if (titre.equals("Fenetre Accueil")) {
            stage = (Stage) sceneInscription.getWindow();
        }
        stage.setScene(scene);
        stage.setTitle(titre);
    }

    public Scene getSceneFenetreAccueil() {
        return sceneFenetreAccueil;
    }

    public Scene getSceneConnexion() {
        return sceneConnexion;
    }

    public Scene getSceneInscription() {
        return sceneInscription;
    }

    public Scene getSceneClassement() {
        return sceneClassement;
    }


    

    public static void main(String[] args) {
        launch(args);
    }  
}

