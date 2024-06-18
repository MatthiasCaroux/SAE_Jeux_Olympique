package src.vues;

import java.io.IOException;
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
    private Stage primaryStage;  // Ajouter l'attribut stage
    private Scene sceneFenetreAccueil;
    private Scene sceneConnexion;
    private Scene sceneInscription;
    private Scene sceneClassement;
    private VBox fenetreAccueil;
    private BorderPane fenetreConnexion;
    private BorderPane fenetreInscription;
    private BorderPane fenetreClassement;
    private FXMLLoader loaderAccueil;
    private FXMLLoader loaderConnexion;
    private FXMLLoader loaderInscription;
    private FXMLLoader loaderClassement;
  
    @Override
    public void init() throws ClassNotFoundException, SQLException, IOException {
        System.out.println("Initialisation de l'application");
        this.requete = new Requete();
        // System.out.println(this.requete.idMaxTable("UTILISATEUR"));
        // System.out.println(this.requete.connexion("admin", "admin"));

        System.out.println("Initialisation de l'application");

        loadScenes();
    }

    private void loadScenes() throws IOException {
        loaderAccueil = new FXMLLoader(this.getClass().getResource("/fxml/accueil.fxml"));
        this.fenetreAccueil = loaderAccueil.load();

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
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;  // Initialiser l'attribut stage
        try {
            System.out.println("Lancement de l'application");

            // Configuration des boutons et de leurs actions
            configureButtonActions();

            primaryStage.setScene(sceneFenetreAccueil);
            primaryStage.setTitle("Fenetre d'accueil");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du démarrage de l'application : " + e.getMessage());
        }
    }

    private void configureButtonActions() {
        Button boutonConnexion = (Button) sceneFenetreAccueil.lookup("#seConnecter");
        boutonConnexion.setOnAction(new ControleurFenetre(this, "Fenetre de connexion"));

        Button boutonSinscrire = (Button) sceneFenetreAccueil.lookup("#sinscrire");
        boutonSinscrire.setOnAction(new ControleurFenetre(this, "Fenetre d'inscription"));

        Button boutonClassement = (Button) sceneConnexion.lookup("#entrer");
        boutonClassement.setOnAction(new ControleurFenetre(this, "Fenetre de classement"));

        Button boutonRetour = (Button) sceneInscription.lookup("#boutonRetour");
        boutonRetour.setOnAction(new ControleurFenetre(this, "Fenetre Accueil"));

        Button boutonRetourConnexion = (Button) sceneConnexion.lookup("#boutonRetourConnexion");
        boutonRetourConnexion.setOnAction(new ControleurFenetre(this, "Fenetre Accueil"));

        Button boutonInscription = (Button) sceneInscription.lookup("#estInscrit");
        boutonInscription.setOnAction(new ControleurInscription(this));
    }

    public void changerFenetre(Scene scene, String titre, String bouton) {
        System.out.println("Changement de fenetre : " + scene);
        primaryStage.setScene(scene);
        primaryStage.setTitle(titre);
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

    public String getIdentifiantConnexion() {
        TextField identifiant = (TextField) sceneConnexion.lookup("#ID");
        return identifiant.getText();
    }

    public String getMotDePasseConnexion() {
        PasswordField motDePasse = (PasswordField) sceneConnexion.lookup("#MDP");
        return motDePasse.getText();
    }

    public String getIdentifiantInscription() {
        TextField identifiant = (TextField) sceneInscription.lookup("#champID");
        return identifiant.getText();
    }

    public String getEmailInscription() {
        TextField email = (TextField) sceneInscription.lookup("#champEmail");
        return email.getText();
    }

    public String getMotDePasseInscription() {
        PasswordField motDePasse = (PasswordField) sceneInscription.lookup("#champMDP");
        System.out.println(motDePasse.getText());
        return motDePasse.getText();
    }

    public String getMotDePasseConfirmationInscription() {
        PasswordField motDePasse = (PasswordField) sceneInscription.lookup("#champMDPConfirme");
        return motDePasse.getText();
    }

    public Requete getRequete() {
        return requete;
    }

    public static void main(String[] args) {
        System.out.println("Lancement de l'application JavaFX");
        launch(args);
    }  
}
