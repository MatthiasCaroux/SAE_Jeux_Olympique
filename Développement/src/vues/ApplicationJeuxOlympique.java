package src.vues;

import java.sql.SQLException;

import org.junit.runner.Request;

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
    private FXMLLoader loaderAccueil;
    private FXMLLoader loaderConnexion;
    private FXMLLoader loaderInscription;
    private FXMLLoader loaderClassement;
  
    @Override
    public void init() throws ClassNotFoundException, SQLException, Exception{
        System.out.println("Initialisation de l'application");
        this.requete = new Requete();
        System.out.println(this.requete.idMaxUtilisateur());
        System.out.println(this.requete.connexion("admin", "admin"));
        this.requete.inscription("niksan", "niksan@niksan.niksan.niksan", "niksan");
        this.requete.inscription("matthias", "matthias@matthias.matthias", "matthias");
        this.requete.inscription("alexy", "alexy@alexy.alexy", "alexy");
        // try {
        //     System.out.println("Tentative de connexion à la base de donnée");
        //     this.requete.connecter();
        //     System.out.println("Connexion reussi !");
        // } catch (Exception e) {
        //     System.out.println("Erreur de connexion à la base de donnée");
        // }
        // this.connexionBD.connecter();

        System.out.println("Initialisation de l'application");

        loaderAccueil = new FXMLLoader(this.getClass().getResource("/fxml/accueil.fxml"));
        this.fenetreAccueil = loaderAccueil.load();

        loaderConnexion = new FXMLLoader(this.getClass().getResource("/fxml/connexion.fxml"));
        this.fenetreConnexion = loaderConnexion.load();

        loaderInscription = new FXMLLoader(this.getClass().getResource("/fxml/inscription.fxml"));
        this.fenetreInscription = loaderInscription.load();

        loaderClassement = new FXMLLoader(this.getClass().getResource("/fxml/classement.fxml"));
        this.fenetreClassement = loaderClassement.load();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Lancement de l'application");
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
        

        stage.setScene(sceneFenetreAccueil);
        stage.setTitle("Fenetre d'accueil");
        stage.show();


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
    }

    public void changerFenetre(Scene scene, String titre, String bouton){
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
        else if (titre.equals("Fenetre Accueil") && bouton.equals("boutonRetour")) {
            stage = (Stage) sceneInscription.getWindow();
        }
        else if (titre.equals("Fenetre Accueil") && bouton.equals("boutonRetourConnexion")) {
            stage = (Stage) sceneConnexion.getWindow();
        }
        else if (titre.equals("Fenetre Accueil") && bouton.equals("boutonInscrire")) {
            stage = (Stage) sceneFenetreAccueil.getWindow();
        }
        System.out.println("Changement de fenetre" + scene);
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
        return motDePasse.getText();
    }

    public String getMotDePasseConfirmationInscription() {
        PasswordField motDePasse = (PasswordField) sceneInscription.lookup("#champMDPConfirmation");
        return motDePasse.getText();
    }

    public Requete getRequete() {
        return requete;
    }

    public static void main(String[] args) {
        launch(args);
    }  
}

