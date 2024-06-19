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
    private Scene sceneAjoutDonnees;
    private Scene sceneModificationDonnée;
    private Scene sceneJournalisteAccueil;
    private Scene sceneAthletes;
    private Scene sceneEquipe;
    private FXMLLoader loaderAccueil;
    private FXMLLoader loaderConnexion;
    private FXMLLoader loaderInscription;
    private FXMLLoader loaderClassement;
    private FXMLLoader loaderAccueilAdmin;
    private FXMLLoader loaderAccueilOrganisateur;
    private FXMLLoader loaderGestionUtilisateur;
    private FXMLLoader loaderAjoutDonnees;
    private FXMLLoader loaderModificationDonnée;
    private FXMLLoader loaderJournalisteAccueil;
    private FXMLLoader loaderAthletes;
    private FXMLLoader loaderEquipe;
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

        loaderAjoutDonnees = new FXMLLoader(this.getClass().getResource("/fxml/ajoutDesDonnées.fxml"));
        this.sceneAjoutDonnees = new Scene(loaderAjoutDonnees.load());

        // loaderAccueilOrganisateur = new FXMLLoader(this.getClass().getResource("/fxml/accueilOrganisateur.fxml"));
        // this.sceneAccueilOrganisateur = new Scene(loaderAccueilOrganisateur.load());
        // System.out.println("Chargement des scènes ");

        loaderGestionUtilisateur = new FXMLLoader(this.getClass().getResource("/fxml/gestionUtilisateur.fxml"));
        this.sceneGestionUtilisateur = new Scene(loaderGestionUtilisateur.load());

        loaderModificationDonnée = new FXMLLoader(this.getClass().getResource("/fxml/modificationDeDonnée.fxml"));
        this.sceneModificationDonnée = new Scene(loaderModificationDonnée.load());

        loaderJournalisteAccueil = new FXMLLoader(this.getClass().getResource("/fxml/accueilJournaliste.fxml"));
        this.sceneJournalisteAccueil = new Scene(loaderJournalisteAccueil.load());

        loaderAthletes = new FXMLLoader(this.getClass().getResource("/fxml/athlèteJournaliste.fxml"));
        this.sceneAthletes = new Scene(loaderAthletes.load());

        loaderEquipe = new FXMLLoader(this.getClass().getResource("/fxml/equipesJournaliste.fxml"));
        this.sceneEquipe = new Scene(loaderEquipe.load());
        

        
        
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
        System.out.println("Configuration des actions des boutons");
        Button boutonConnexion = (Button) sceneFenetreAccueil.lookup("#seConnecter");
        if (boutonConnexion != null) {
            boutonConnexion.setOnAction(new ControleurFenetre(this, "Fenetre de connexion"));
            System.out.println("Bouton #seConnecter trouvé et configuré");
        } else {
            System.err.println("Bouton #seConnecter non trouvé dans sceneFenetreAccueil");
        }
    
        Button boutonSinscrire = (Button) sceneFenetreAccueil.lookup("#sinscrire");
        if (boutonSinscrire != null) {
            boutonSinscrire.setOnAction(new ControleurFenetre(this, "Fenetre d'inscription"));
            System.out.println("Bouton #sinscrire trouvé et configuré");
        } else {
            System.err.println("Bouton #sinscrire non trouvé dans sceneFenetreAccueil");
        }
    
        Button boutonClassement = (Button) sceneConnexion.lookup("#entrer");
        if (boutonClassement != null) {
            boutonClassement.setOnAction(new ControleurFenetre(this, "Fenetre de classement"));
            System.out.println("Bouton #entrer trouvé et configuré");
        } else {
            System.err.println("Bouton #entrer non trouvé dans sceneConnexion");
        }
    
        Button boutonRetour = (Button) sceneInscription.lookup("#boutonRetour");
        if (boutonRetour != null) {
            boutonRetour.setOnAction(new ControleurFenetre(this, "Fenetre Accueil"));
            System.out.println("Bouton #boutonRetour trouvé et configuré");
        } else {
            System.err.println("Bouton #boutonRetour non trouvé dans sceneInscription");
        }
    
        Button boutonRetourConnexion = (Button) sceneConnexion.lookup("#boutonRetourConnexion");
        if (boutonRetourConnexion != null) {
            boutonRetourConnexion.setOnAction(new ControleurFenetre(this, "Fenetre Accueil"));
            System.out.println("Bouton #boutonRetourConnexion trouvé et configuré");
        } else {
            System.err.println("Bouton #boutonRetourConnexion non trouvé dans sceneConnexion");
        }
    
        Button boutonInscription = (Button) sceneInscription.lookup("#estInscrit");
        if (boutonInscription != null) {
            boutonInscription.setOnAction(new ControleurInscription(this));
            System.out.println("Bouton #estInscrit trouvé et configuré");
        } else {
            System.err.println("Bouton #estInscrit non trouvé dans sceneInscription");
        }
    
        Button boutonGestionUtilisateur = (Button) sceneAccueilAdmin.lookup("#gestionUtilisateur");
        if (boutonGestionUtilisateur != null) {
            boutonGestionUtilisateur.setOnAction(new ControleurFenetre(this, "Gestion des utilisateurs"));
            System.out.println("Bouton #gestionUtilisateur trouvé et configuré");
        } else {
            System.err.println("Bouton #gestionUtilisateur non trouvé dans sceneAccueilAdmin");
        }
    
        Button boutonJeuxOlympique = (Button) getSceneGestionUtilisateur().lookup("#retourAccueilAdmin");
        if (boutonJeuxOlympique != null) {
            boutonJeuxOlympique.setOnAction(new ControleurFenetre(this, "Fenetre d'accueil"));
            System.out.println("Bouton #retourAccueilAdmin trouvé et configuré");
        } else {
            System.err.println("Bouton #retourAccueilAdmin non trouvé dans sceneGestionUtilisateur");
        }
    
        System.out.println("coucou");
        System.out.println("La scene est " + sceneAccueilAdmin);
        Button boutonDeconnexion = (Button) sceneAccueilAdmin.lookup("#choixDeconnexion");
        System.out.println(boutonDeconnexion);
        if (boutonDeconnexion != null) {
            boutonDeconnexion.setOnAction(new ControleurDeconnexion(this));
            System.out.println("Bouton #choixDeconnexion trouvé et configuré");
        } else {
            System.out.println("Bouton #choixDeconnexion non trouvé dans sceneAccueilAdmin");
        }

        Button boutonAjoutDesDonnées = (Button) sceneAccueilAdmin.lookup("#ajoutDesDonnées");
        if (boutonAjoutDesDonnées != null) {
            boutonAjoutDesDonnées.setOnAction(new ControleurFenetre(this, "Ajout des données"));
            System.out.println("Bouton #ajoutDesDonnées trouvé et configuré");
        } else {
            System.out.println("Bouton #ajoutDesDonnées non trouvé dans sceneAccueilAdmin");
        }

        Button boutonDecoDepuisAjoutDonnees = (Button) sceneAjoutDonnees.lookup("#choixDeconnexion");
        if (boutonDecoDepuisAjoutDonnees != null) {
            boutonDecoDepuisAjoutDonnees.setOnAction(new ControleurDeconnexion(this));
            System.out.println("Bouton #deconnexion trouvé et configuré");
        } else {
            System.out.println("Bouton #deconnexion non trouvé dans sceneAjoutDonnees");
        }

        Button boutonLogoJODepuisGestionUtilisitateur = (Button) sceneGestionUtilisateur.lookup("#logoJO");
        if (boutonLogoJODepuisGestionUtilisitateur != null) {
            boutonLogoJODepuisGestionUtilisitateur.setOnAction(new ControleurRetourJO(this));
            System.out.println("Bouton #logoJO trouvé et configuré");
        } else {
            System.out.println("Bouton #logoJO non trouvé dans sceneGestionUtilisateur");
        }
        
        Button boutonLogoJODepuisAjoutDeDonnees = (Button) sceneAjoutDonnees.lookup("#logoJO");
        if (boutonLogoJODepuisAjoutDeDonnees != null) {
            boutonLogoJODepuisAjoutDeDonnees.setOnAction(new ControleurRetourJO(this));
            System.out.println("Bouton #logoJO trouvé et configuré");
        } else {
            System.out.println("Bouton #logoJO non trouvé dans sceneAjoutDeDonnees");
        }

        Button boutonLogoJOModifierDonnees = (Button) sceneModificationDonnée.lookup("#logoJO");
        if (boutonLogoJOModifierDonnees != null) {
            boutonLogoJOModifierDonnees.setOnAction(new ControleurRetourJO(this));
            System.out.println("Bouton #logoJO trouvé et configuré");
        } else {
            System.out.println("Bouton #logoJO non trouvé dans sceneModifierDonnées");
        }

        Button boutonJeuxOlympiqueDepuisGestionUtilisateur = (Button) sceneGestionUtilisateur.lookup("#jeuxOlympique");
        if (boutonJeuxOlympiqueDepuisGestionUtilisateur != null) {
            boutonJeuxOlympiqueDepuisGestionUtilisateur.setOnAction(new ControleurRetourJO(this));
            System.out.println("Bouton #jeuxOlympique trouvé et configuré");
        } else {
            System.out.println("Bouton #jeuxOlympique non trouvé dans sceneGestionUtilisateur");
        }

        Button boutonAjoutDesDonneesDepuisGestionUtiliButton = (Button) sceneGestionUtilisateur.lookup("#ajoutDesDonnées");
        if (boutonAjoutDesDonneesDepuisGestionUtiliButton != null) {
            boutonAjoutDesDonneesDepuisGestionUtiliButton.setOnAction(new ControleurAjoutDonnees(this));
            System.out.println("Bouton #ajoutDesDonnées trouvé et configuré");
        } else {
            System.out.println("Bouton #ajoutDesDonnées non trouvé dans sceneGestionUtilisateur");
        }

        Button modificationDesDonnéeDepuisGestionUtiliButton = (Button) sceneGestionUtilisateur.lookup("#modificationDonnées");
        if (modificationDesDonnéeDepuisGestionUtiliButton != null) {
            modificationDesDonnéeDepuisGestionUtiliButton.setOnAction(new ControleurModificationDonnée(this));
            System.out.println("Bouton #ajoutDesDonnées trouvé et configuré");
        } else {
            System.out.println("Bouton #ajoutDesDonnées non trouvé dans sceneGestionUtilisateur");
        }

        Button boutonDeconnexionDepuisGestionUtilisateur = (Button) sceneGestionUtilisateur.lookup("#choixDeconnexion");
        if (boutonDeconnexionDepuisGestionUtilisateur != null) {
            boutonDeconnexionDepuisGestionUtilisateur.setOnAction(new ControleurDeconnexion(this));
            System.out.println("Bouton #choixDeconnexion trouvé et configuré");
        } else {
            System.out.println("Bouton #choixDeconnexion non trouvé dans sceneGestionUtilisateur");
        }

        Button boutonModificationDesDonnéeDepuis = (Button) sceneAccueilAdmin.lookup("#modificationDonnées");
        if (boutonModificationDesDonnéeDepuis != null) {
            boutonModificationDesDonnéeDepuis.setOnAction(new ControleurModificationDonnée(this));
            System.out.println("Bouton #modificationDonnées trouvé et configuré");
        } else {
            System.out.println("Bouton #modificationDonnées non trouvé dans sceneAccueilAdmin");
        }

        Button boutonModificationDonnées = (Button) sceneAjoutDonnees.lookup("#modificationDonnées");
        if (boutonModificationDonnées != null) {
            boutonModificationDonnées.setOnAction(new ControleurModificationDonnée(this));
            System.out.println("Bouton #modificationDonnées trouvé et configuré");
        } else {
            System.out.println("Bouton #modificationDonnées non trouvé dans sceneAjoutDonnees");
        }

        Button boutonGestionUtilisateurDepuisAjoutDonnees = (Button) sceneAjoutDonnees.lookup("#gestionUtilisateur");
        if (boutonGestionUtilisateurDepuisAjoutDonnees != null) {
            boutonGestionUtilisateurDepuisAjoutDonnees.setOnAction(new ControleurGestionUtilisateur(this));
            System.out.println("Bouton #gestionUtilisateur trouvé et configuré");
        } else {
            System.out.println("Bouton #gestionUtilisateur non trouvé dans sceneAjoutDonnees");
        }

        Button boutonJODepuisModificationDonnée = (Button) sceneModificationDonnée.lookup("#jeuxOlympique");
        if (boutonJODepuisModificationDonnée != null) {
            boutonJODepuisModificationDonnée.setOnAction(new ControleurRetourJO(this));
            System.out.println("Bouton #jeuxOlympique trouvé et configuré");
        } else {
            System.out.println("Bouton #jeuxOlympique non trouvé dans sceneModificationDonnée");
        }

        Button boutonJODepuisAjoutDeDonnées = (Button) sceneAjoutDonnees.lookup("#jeuxOlympique");
        if (boutonJODepuisAjoutDeDonnées != null) {
            boutonJODepuisAjoutDeDonnées.setOnAction(new ControleurRetourJO(this));
            System.out.println("Bouton #jeuxOlympique trouvé et configuré");
        } else {
            System.out.println("Bouton #jeuxOlympique non trouvé dans sceneModificationDonnée");
        }

        Button boutonDeconnexionDepuisModificationDonnée = (Button) sceneModificationDonnée.lookup("#choixDeconnexion");
        if (boutonDeconnexionDepuisModificationDonnée != null) {
            boutonDeconnexionDepuisModificationDonnée.setOnAction(new ControleurDeconnexion(this));
            System.out.println("Bouton #choixDeconnexion trouvé et configuré");
        } else {
            System.out.println("Bouton #choixDeconnexion non trouvé dans sceneModificationDonnée");
        }
        
        Button boutonGestionDesUtilisateurDepuisModificationDonnée = (Button) sceneModificationDonnée.lookup("#gestionUtilisateur");
        if (boutonGestionDesUtilisateurDepuisModificationDonnée != null) {
            boutonGestionDesUtilisateurDepuisModificationDonnée.setOnAction(new ControleurGestionUtilisateur(this));
            System.out.println("Bouton #gestionUtilisateur trouvé et configuré");
        } else {
            System.out.println("Bouton #gestionUtilisateur non trouvé dans sceneModificationDonnée");
        }

        Button boutonAjoutDesDonnéeDepuisModificationDonnée = (Button) sceneModificationDonnée.lookup("#ajoutDesDonnées");
        if (boutonAjoutDesDonnéeDepuisModificationDonnée != null) {
            boutonAjoutDesDonnéeDepuisModificationDonnée.setOnAction(new ControleurAjoutDonnees(this));
            System.out.println("Bouton #ajoutDesDonnées trouvé et configuré");
        } else {
            System.out.println("Bouton #ajoutDesDonnées non trouvé dans sceneModificationDonnée");
        }

        Button boutonAthletesDepuisAccueilJournaliste = (Button) sceneJournalisteAccueil.lookup("#Athlètes");
        if (boutonAthletesDepuisAccueilJournaliste != null) {
            boutonAthletesDepuisAccueilJournaliste.setOnAction(new ControleurAthletes(this));
            System.out.println("Bouton #athletes trouvé et configuré");
        } else {
            System.out.println("Bouton #athletes non trouvé dans sceneJournalisteAccueil");
        }

        Button boutonEquipeDepuisAccueilJournaliste = (Button) sceneJournalisteAccueil.lookup("#Equipes");
        if (boutonEquipeDepuisAccueilJournaliste != null) {
            boutonEquipeDepuisAccueilJournaliste.setOnAction(new ControleurEquipe(this));
            System.out.println("Bouton #equipe trouvé et configuré");
        } else {
            System.out.println("Bouton #equipe non trouvé dans sceneJournalisteAccueil");
        }

        Button boutonClassementDepuisAccueilJournaliste = (Button) sceneJournalisteAccueil.lookup("#Classement");
        if (boutonClassementDepuisAccueilJournaliste != null) {
            boutonClassementDepuisAccueilJournaliste.setOnAction(new ControleurClassement(this));
            System.out.println("Bouton #Classement trouvé et configuré");
        } else {
            System.out.println("Bouton #Classement non trouvé dans sceneJournalisteAccueil");
        }

        Button boutonDeconnexionDepuisAccueilJournaliste = (Button) sceneJournalisteAccueil.lookup("#choixDeconnexion");
        if (boutonDeconnexionDepuisAccueilJournaliste != null) {
            boutonDeconnexionDepuisAccueilJournaliste.setOnAction(new ControleurDeconnexion(this));
            System.out.println("Bouton #choixDeconnexion trouvé et configuré");
        } else {
            System.out.println("Bouton #choixDeconnexion non trouvé dans sceneJournalisteAccueil");
        }
    }
    

    public void changerFenetre(Scene scene, String titre) {
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

    public Scene getSceneAjoutDonnees(){
        return sceneAjoutDonnees;
    }

    public Scene getSceneModificationDonnée(){
        return sceneModificationDonnée;
    }

    public Scene getAccueilJournaliste(){
        return sceneJournalisteAccueil;
    }

    public Scene getSceneJournalisteAccueil(){
        return sceneJournalisteAccueil;
    }

    public Scene getSceneAthletes(){
        return sceneAthletes;
    }

    public Scene getSceneEquipe(){
        return sceneEquipe;
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
        return this.requete;
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

    public static void main(String[] args) {
        System.out.println("Lancement de l'application JavaFX");
        launch(args);
    }  
}
