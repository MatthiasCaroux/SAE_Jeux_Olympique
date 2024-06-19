package src.vues;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;

import src.controlleur.*;
import src.basededonnee.*;
import src.modele.jeuxOlympique.*;

public class ApplicationJeuxOlympique extends Application {
    private JeuxOlympique modele;
    private Requete requete;
    private Stage primaryStage;  // Ajouter l'attribut stage
    private Scene sceneFenetreAccueil;
    private Scene sceneConnexion;
    private Scene sceneInscription;
    private Scene sceneClassement;
    private Scene sceneAccueilAdmin;
    private Scene sceneAccueilOrganisateur;
    private Scene sceneGestionUtilisateur;
    private FXMLLoader loaderAccueil;
    private FXMLLoader loaderConnexion;
    private FXMLLoader loaderInscription;
    private FXMLLoader loaderClassement;
    private FXMLLoader loaderAccueilAdmin;
    private FXMLLoader loaderAccueilOrganisateur;
    private FXMLLoader loaderGestionUtilisateur;
    // private 
  
    @Override
    public void init() throws ClassNotFoundException, SQLException, IOException {
        this.modele = new JeuxOlympique(2024, "Paris", "Jeux Olympique de Paris 2024");
        try {
            this.requete = new Requete();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }

        System.out.println("Initialisation de l'application");

        this.loadScenes();
        System.out.println("Chargement des scènes terminé");
    }

    private void loadScenes() throws IOException {
        loaderAccueil = new FXMLLoader(this.getClass().getResource("/fxml/accueil.fxml"));
        this.sceneFenetreAccueil = new Scene(loaderAccueil.load());

        loaderConnexion = new FXMLLoader(this.getClass().getResource("/fxml/connexion.fxml"));
        this.sceneConnexion = new Scene(loaderConnexion.load());

        loaderInscription = new FXMLLoader(this.getClass().getResource("/fxml/inscription.fxml"));
        this.sceneInscription = new Scene(loaderInscription.load());

        loaderClassement = new FXMLLoader(this.getClass().getResource("/fxml/classement.fxml"));
        this.sceneClassement = new Scene(loaderClassement.load());

        loaderAccueilAdmin = new FXMLLoader(this.getClass().getResource("/fxml/accueilAdministrateur.fxml"));
        this.sceneAccueilAdmin = new Scene(loaderAccueilAdmin.load());

        // loaderAccueilOrganisateur = new FXMLLoader(this.getClass().getResource("/fxml/accueilOrganisateur.fxml"));
        // this.sceneAccueilOrganisateur = new Scene(loaderAccueilOrganisateur.load());
        // System.out.println("Chargement des scènes ");

        loaderGestionUtilisateur = new FXMLLoader(this.getClass().getResource("/fxml/gestionUtilisateur.fxml"));
        this.sceneGestionUtilisateur = new Scene(loaderGestionUtilisateur.load());
    }
    
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Je suis dans la méthode start");
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

        Button boutonGestionUtilisateur = (Button) sceneAccueilAdmin.lookup("#gestionUtilisateur");
        boutonGestionUtilisateur.setOnAction(new ControleurFenetre(this, "Gestion des utilisateurs"));

        Button boutonJeuxOlympique = (Button) getSceneGestionUtilisateur().lookup("#retourAccueilAdmin");
        boutonJeuxOlympique.setOnAction(new ControleurFenetre(this, "Fenetre d'accueil"));

        Button boutonDeconnexion = (Button) getSceneAccueilAdmin().lookup("#choixDeconnexion");
        // boutonDeconnexion.setOnAction(new ControleurFenetre(this, "Deconnexion"));

        // Button choixMonProfil = (Button) this.getSceneAccueilAdmin().lookup("#choixMonProfil");
        // // choixMonProfil.setOnAction(new ControleurProfil(this, "Profil"));

        // Button choixDeconnexion = (Button) this.getSceneAccueilAdmin().lookup("#choixDeconnexion");
        // // choixDeconnexion.setOnAction(new ControleurProfil(this, "Deconnexion"));
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

    public Scene getSceneAccueilAdmin(){
        return sceneAccueilAdmin;
    }

    public Scene getSceneAccueilOrganisateur(){
        return sceneAccueilOrganisateur;
    }

    public Scene getSceneGestionUtilisateur() {
        VBox vBox = (VBox) sceneGestionUtilisateur.lookup("#vboxPrincipal");
        vBox.getChildren().clear();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setPadding(new javafx.geometry.Insets(20, 50, 20, 50));
    
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");
    
        try {
            List<String> utilisateur = this.requete.getNomUtilisateur();
            for (String user : utilisateur) {
                comboBox.getItems().add(user);
            }
            comboBox.setValue(utilisateur.get(0));
    
            vBox.getChildren().add(comboBox);
    
            VBox vb2 = new VBox();
            vb2.setAlignment(javafx.geometry.Pos.CENTER);
            vb2.setSpacing(20);
    
            HBox hb = new HBox();
            ImageView iv = new ImageView(new Image("images/ath.png"));
            iv.setFitHeight(100);
            iv.setFitWidth(100);
            Label label = new Label(comboBox.getValue());
            label.setStyle("-fx-font-size: 40px;");
            hb.setAlignment(javafx.geometry.Pos.CENTER);
            hb.getChildren().addAll(iv, label);
            hb.setSpacing(20);
            vb2.getChildren().add(hb);
    
            VBox bleu = new VBox();
            bleu.setAlignment(javafx.geometry.Pos.CENTER);
            bleu.setSpacing(40);
            bleu.setStyle("-fx-background-color: #0085C7; -fx-background-radius: 15; -fx-padding: 20px;");
    
            Label changerRole = new Label("CHANGER DE RÔLE");
            changerRole.setStyle("-fx-text-fill: white; -fx-font-size: 35px; -fx-font-weight: bold;");
    
            HBox boutonsRole = new HBox();
            boutonsRole.setSpacing(20);
            boutonsRole.setAlignment(javafx.geometry.Pos.CENTER);
    
            Button administrateur = createRoleButton("Administrateur", 'A', comboBox);
            Button journaliste = createRoleButton("Journaliste", 'C', comboBox);
            Button organisateur = createRoleButton("Organisateur", 'O', comboBox);
    
            boutonsRole.getChildren().addAll(administrateur, journaliste, organisateur);
    
            Button supprimer = new Button("SUPPRIMER L'UTILISATEUR");
            supprimer.setOnAction(new ControleurSupprimerUtilisateur(this, comboBox));
            supprimer.setStyle("-fx-background-color: #BF1010; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;");
            supprimer.setOnMouseEntered(e -> supprimer.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
            supprimer.setOnMouseExited(e -> supprimer.setStyle("-fx-background-color: #BF1010; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
    
            bleu.getChildren().addAll(changerRole, boutonsRole, supprimer);
            vb2.getChildren().add(bleu);
            vBox.getChildren().add(vb2);
    
            comboBox.setOnAction(e -> label.setText(comboBox.getValue()));
    
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des utilisateurs");
            System.err.println(e.getMessage());
        }
    
        return sceneGestionUtilisateur;
    }
    
    private Button createRoleButton(String roleName, char roleCode, ComboBox<String> comboBox) {
        Button button = new Button(roleName);
        button.setOnAction(new ControleurChangerRole(this, roleCode, comboBox));
        String style = "-fx-background-color: #E0E0E0; -fx-text-fill: black; -fx-font-size: 25px; -fx-background-radius: 25; -fx-padding: 10px;";
        button.setStyle(style);
        button.setPrefWidth(300);
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #B0BEC5; -fx-text-fill: black; -fx-font-size: 25px; -fx-background-radius: 25; -fx-padding: 10px;"));
        button.setOnMouseExited(e -> button.setStyle(style));
        return button;
    }
    // public Scene getSceneGestionUtilisateur(){
    //     VBox vBox = (VBox) sceneGestionUtilisateur.lookup("#vboxPrincipal");
    //     GridPane gridPane = new GridPane();
    //     gridPane.setHgap(10);
    //     gridPane.setVgap(10);
    //     List<Athlete> athletes = requete.getAthletes();
    //     while (athletes.hasNext()) {
    //         for (int i = 0; i < 5; i++){
    //             Athlete athlete = athletes.next();
    //             gridPane.add(new Label(athlete.getId()), 0, i);
    //             gridPane.add(new Label(athlete.getNom()), 1, i);
    //             gridPane.add(new Label(athlete.getPrenom()), 2, i);
    //             gridPane.add(new Label(athlete.getSexe()), 3, i);
    //             gridPane.add(new Label(athlete.getNationalite()), 4, i);
    //         }
            
    //     }






    //     return null;
    // }

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
















    public void ajouterPaysALaBase(){
        for (Pays pays : this.modele.getLesPays()){
            try {
                this.requete.ajouterPays(pays.getNomPays());
            } catch (Exception e) {
                System.out.println("le pays");
                System.err.println(e.getMessage());
            }
        }
    }





        //     this.requete.inscription("niksan", "niksan@gmail.com", "niksan");
        //     this.requete.inscription("matthias", "matthias@gmail.com", "matthias");
        //     this.requete.inscription("alexy", "alexy@gmail.com", "alexy");
        //     this.requete.inscription("carrel", "carrel@gmail.com", "carrel");
        // } catch (Exception e) {
        //     // System.err.println(e.getMessage());
        //     System.out.p






















    public static void main(String[] args) {
        System.out.println("Lancement de l'application JavaFX");
        launch(args);
    }  
}
