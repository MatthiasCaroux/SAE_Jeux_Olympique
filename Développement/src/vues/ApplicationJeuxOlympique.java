package src.vues;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private Scene sceneAjouterUnAthlete;
    private Scene sceneAjouterEquipe;
    private Scene sceneModificationDonnéeAthlete;
    private Scene sceneGestionEpreuve;
    private Scene sceneAjouterEpreuve;
    private Scene sceneListeEpreuve;
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
    private FXMLLoader loaderAjouterUnAthlete;
    private FXMLLoader loaderGererEpreuve;
    // private 
  
    @Override
    /**
     * Méthode d'initialisation de l'application
     */
    public void init() throws ClassNotFoundException, SQLException, IOException {
        this.modele = new JeuxOlympique(2024, "Paris", "Jeux Olympique de Paris 2024");



        this.constructionRequete();
        this.loadScenes();
        this.constructionEpreuve();
    }

    /**Métode de construction de la requête */
    private void constructionRequete(){
        try {
            Map<Epreuve, List<Participant>> map = this.modele.getParticipantsParEpreuve("./donnees.csv");
            this.requete = new Requete();
            for (Epreuve epreuve : map.keySet()) {
                this.requete.ajouteEpreuve(epreuve, modele);
            }
            this.requete.getEpreuves(this.modele);            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //*Méthode de construction des épreuves */
    private void constructionEpreuve(){
        for (Epreuve epreuve : requete.getEpreuves(modele)) {
            try{
                this.modele.ajouteEpreuve(epreuve);
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
            
        }
    }
    //*Méthode de chargement des scènes */
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

        loaderAccueilOrganisateur = new FXMLLoader(this.getClass().getResource("/fxml/accueilOrganisateur.fxml"));
        this.sceneAccueilOrganisateur = new Scene(loaderAccueilOrganisateur.load());

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

        loaderAjouterUnAthlete = new FXMLLoader(this.getClass().getResource("/fxml/ajouterUnAthlete.fxml"));
        this.sceneAjouterUnAthlete = new Scene(loaderAjouterUnAthlete.load());

        loaderGererEpreuve = new FXMLLoader(this.getClass().getResource("/fxml/gererEpreuves.fxml"));
        this.sceneGestionEpreuve = new Scene(loaderGererEpreuve.load());

    }
    
    @Override
    //*Méthode de démarrage de l'application */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;  // Initialiser l'attribut stage
        try {
            configureButtonActions();
            primaryStage.setScene(sceneFenetreAccueil);
            primaryStage.setTitle("Fenetre d'accueil");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du démarrage de l'application : " + e.getMessage());
        }
    }

    //*Méthode de configuration des actions des boutons */
    private void configureButtonActions() {
        configureButton(sceneFenetreAccueil, "#seConnecter", new ControleurFenetre(this, "Fenetre de connexion"));
        configureButton(sceneFenetreAccueil, "#sinscrire", new ControleurFenetre(this, "Fenetre d'inscription"));
        configureButton(sceneConnexion, "#entrer", new ControleurFenetre(this, "Fenetre de classement"));
        configureButton(sceneInscription, "#boutonRetour", new ControleurFenetre(this, "Fenetre Accueil"));
        configureButton(sceneConnexion, "#boutonRetourConnexion", new ControleurFenetre(this, "Fenetre Accueil"));
        configureButton(sceneInscription, "#estInscrit", new ControleurInscription(this));
        configureButton(sceneAccueilAdmin, "#gestionUtilisateur", new ControleurFenetre(this, "Gestion des utilisateurs"));
        configureButton(getSceneGestionUtilisateur(), "#retourAccueilAdmin", new ControleurFenetre(this, "Fenetre d'accueil"));
        configureButton(sceneAccueilAdmin, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneAccueilAdmin, "#ajoutDesDonnées", new ControleurFenetre(this, "Ajout des données"));
        configureButton(sceneAjoutDonnees, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneGestionUtilisateur, "#logoJO", new ControleurRetourJO(this, "admin"));
        configureButton(sceneAjoutDonnees, "#logoJO", new ControleurRetourJO(this, "admin"));
        configureButton(sceneModificationDonnée, "#logoJO", new ControleurRetourJO(this, "admin"));
        configureButton(sceneGestionUtilisateur, "#jeuxOlympique", new ControleurRetourJO(this, "admin"));
        configureButton(sceneGestionUtilisateur, "#ajoutDesDonnées", new ControleurAjoutDonnees(this));
        configureButton(sceneGestionUtilisateur, "#modificationDonnées", new ControleurModificationDonnée(this));
        configureButton(sceneGestionUtilisateur, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneAccueilAdmin, "#modificationDonnées", new ControleurModificationDonnée(this));
        configureButton(sceneAjoutDonnees, "#modificationDonnées", new ControleurModificationDonnée(this));
        configureButton(sceneAjoutDonnees, "#gestionUtilisateur", new ControleurGestionUtilisateur(this));
        configureButton(sceneAjoutDonnees, "", new ControleurAjoutEpreuve(this));
        configureButton(sceneModificationDonnée, "#jeuxOlympique", new ControleurRetourJO(this, "admin"));
        configureButton(sceneModificationDonnée, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneModificationDonnée, "#gestionUtilisateur", new ControleurGestionUtilisateur(this));
        configureButton(sceneModificationDonnée, "#ajoutDesDonnées", new ControleurAjoutDonnees(this));
        configureButton(sceneJournalisteAccueil, "#Athlètes", new ControleurAthletes(this));
        configureButton(sceneJournalisteAccueil, "#Equipes", new ControleurEquipe(this));
        configureButton(sceneJournalisteAccueil, "#Classement", new ControleurClassement(this));
        configureButton(sceneJournalisteAccueil, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneJournalisteAccueil, "#listeEpreuve", new ControleurListeEpreuve(this));
        configureButton(sceneAthletes, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneAthletes, "#Actus", new ControleurActus(this));
        configureButton(sceneAthletes, "#Equipes", new ControleurEquipe(this));
        configureButton(sceneAthletes, "#Classement", new ControleurClassement(this));
        configureButton(sceneAjoutDonnees, "#ajouterUnAthletes", new ControleurAjouterUnAthlete(this));
        configureButton(sceneAjouterUnAthlete, "#Actus", new ControleurActus(this));
        configureButton(sceneAjouterUnAthlete, "#Equipes", new ControleurEquipe(this));
        configureButton(sceneAjouterUnAthlete, "#Classement", new ControleurClassement(this));
        configureButton(sceneAjouterUnAthlete, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneAjoutDonnees, "#ajouterUneEquipe", new ControleurAjouterEquipe(this));
        configureButton(sceneAjoutDonnees, "#ajouterUneEpreuve", new ControleurAjoutEpreuve(this));
        configureButton(sceneAjoutDonnees, "#Actus", new ControleurRetourJO(this, "admin"));

        configureButton(sceneAthletes, "#boutonJO", new ControleurRetourJOJournaliste(this));
        configureButton(sceneClassement, "#boutonJO", new ControleurRetourJOJournaliste(this));
        configureButton(sceneClassement, "#Actus", new ControleurActus(this));
        configureButton(sceneClassement, "#Athlètes", new ControleurAthletes(this));
        configureButton(sceneClassement, "#Equipes", new ControleurEquipe(this));
        configureButton(sceneClassement, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneEquipe, "#logoJO", new ControleurRetourJOJournaliste(this));
        configureButton(sceneEquipe, "#Actus", new ControleurRetourJOJournaliste(this));
        configureButton(sceneEquipe, "#Athlètes", new ControleurAthletes(this));
        configureButton(sceneEquipe, "#Classement", new ControleurClassement(this));
        configureButton(sceneEquipe, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneModificationDonnée, "#athletes", new ControleurModifierAthlete(this));
        configureButton(sceneAccueilOrganisateur, "#gererEpreuve", new ControleurGererEpreuve(this));
        configureButton(sceneAccueilOrganisateur, "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(sceneAccueilOrganisateur, "#Classement", new ControleurClassement(this));
        configureButton(this.getSceneGestionEpreuve(), "#lancerUneEpreuve", new ControleurLancerEpreuve(this));
        configureButton(this.getSceneGestionEpreuve(), "#lancerToutesLesEpreuves", new ControleurLancerToutesLesEpreuves(this));
        configureButton(this.getSceneGestionEpreuve(), "#Classement", new ControleurClassement(this));
        configureButton(this.getSceneGestionEpreuve(), "#choixDeconnexion", new ControleurDeconnexion(this));
        configureButton(this.getSceneGestionEpreuve(), "#boutonJO", new ControleurRetourJO(this, "organisateur"));
        configureButton(this.getSceneGestionEpreuve(), "#Actus", new ControleurRetourJO(this, "organisateur"));
        
    }

    //*Méthode de récupération du modèle */
    public JeuxOlympique getModele() {
        return modele;
    }

    //*Méthode de récupération de la requête */
    private void configureButton(Scene scene, String buttonId, EventHandler<ActionEvent> handler) {
        Button button = (Button) scene.lookup(buttonId);
        if (button != null) {
            button.setOnAction(handler);
            //System.out.println("Bouton " + buttonId + " trouvé et configuré");
        } //else {
            //System.out.println("Bouton " + buttonId + " non trouvé dans " + scene);
        //}
        //System.out.println("Bouton configuré");
    }


    public void changerFenetre(Scene scene, String titre) {
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

    /*Méthode de récupération de la scène de classement */
    public Scene getSceneClassement() {
        VBox vBoxPricpale = (VBox) sceneClassement.lookup("#vboxPrincipal");
        vBoxPricpale.getChildren().clear();
        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        vBox.getChildren().clear();
        int i = 1;
        try {
            for (Pays pays : this.requete.getPays()) {

                BorderPane borderPane = new BorderPane();
                borderPane.setPadding(new Insets(10, 50, 10, 50));
                if (i%2 == 1){
                    borderPane.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-color: white;");

                } else{
                    borderPane.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-color: #f1f1f1;");
                }
                BorderPane.setMargin(scrollPane, new Insets(0,0,0,20));
                HBox hb1 = new HBox(10);
                hb1.setAlignment(Pos.CENTER_LEFT);
                Label indice = new Label("" + i);
                i++;
                ImageView imageview = new ImageView(new Image("images/drapeaux/fr.png"));
                switch (pays.getNomPays()) {
                    case "France":
                        imageview = new ImageView(new Image("images/drapeaux/fr.png"));
                        break;
                    case "Allemagne":
                        imageview = new ImageView(new Image("images/drapeaux/de.png"));
                        break;
                    case "Italie":
                        imageview = new ImageView(new Image("images/drapeaux/it.png"));
                        break;
                    case "Espagne":
                        imageview = new ImageView(new Image("images/drapeaux/es.png"));
                        break;
                    case "Royaume-Uni":
                        imageview = new ImageView(new Image("images/drapeaux/uk.png"));
                        break;
                    case "USA":
                        imageview = new ImageView(new Image("images/drapeaux/us.png"));
                        break;
                    case "Chine":
                        imageview = new ImageView(new Image("images/drapeaux/cn.png"));
                        break;
                    case "CANADA":
                        imageview = new ImageView(new Image("images/drapeaux/ca.png"));
                        break;
                    case "Japon":
                        imageview = new ImageView(new Image("images/drapeaux/jp.png"));
                        break;
                    case "Kenya":
                        imageview = new ImageView(new Image("images/drapeaux/ke.png"));
                        break;
                    case "Turquie":
                        imageview = new ImageView(new Image("images/drapeaux/tr.png"));
                        break;
                    case "Congo":
                        imageview = new ImageView(new Image("images/drapeaux/cd.png"));
                        break;
                    case "Brésil":
                        imageview = new ImageView(new Image("images/drapeaux/br.png"));
                        break;
                    case "Maroc":
                        imageview = new ImageView(new Image("images/drapeaux/ma.png"));
                        break;
                    case "Australie":
                        imageview = new ImageView(new Image("images/drapeaux/au.png"));
                        break;
                    default:
                        break;
                }
    
                if (imageview != null) {
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(50);
                    imageview.setPreserveRatio(true);
                }
    
                Label label = new Label(pays.getNomPays());
                HBox.setHgrow(label, Priority.ALWAYS);
    
                hb1.getChildren().addAll(indice, imageview, label);
                hb1.setPadding(new Insets(0, 64, 0, 64));
                VBox vb2 = new VBox();
                vb2.setAlignment(Pos.CENTER);
                HBox hb2 = new HBox(10);
                hb2.setAlignment(Pos.CENTER);
    
    
                ImageView medailleOr = new ImageView(new Image("images/médailles/medaille_or.png"));
                medailleOr.setFitHeight(50);
                medailleOr.setFitWidth(50);
    
    
                Label or = new Label(pays.getMedailles().get("Or").toString());
                HBox.setHgrow(or, Priority.ALWAYS);
    
                ImageView medailleArgent = new ImageView(new Image("images/médailles/medaille_argent.png"));
                medailleArgent.setFitHeight(50);
                medailleArgent.setFitWidth(50);
    
                Label argent = new Label(pays.getMedailles().get("Argent").toString());
                HBox.setHgrow(argent, Priority.ALWAYS);
    
                ImageView medailleBronze = new ImageView(new Image("images/médailles/medaille_bronze.png"));
                medailleBronze.setFitHeight(50);
                medailleBronze.setFitWidth(50);
    
                Label bronze = new Label(pays.getMedailles().get("Bronze").toString());
                HBox.setHgrow(bronze, Priority.ALWAYS);
    
                hb2.getChildren().addAll(medailleOr, or, medailleArgent, argent, medailleBronze, bronze);
                hb2.setPadding(new Insets(0, 64, 0, 64));

                HBox hb3 = new HBox();
                hb3.setAlignment(Pos.CENTER_RIGHT);
    
                Label total = new Label("Total : " + pays.getScoreTotal());
                HBox.setHgrow(total, Priority.ALWAYS);
    
                hb3.getChildren().add(total);
                hb3.setPadding(new Insets(0, 64, 0, 64));
                borderPane.setLeft(hb1);
                vb2.getChildren().add(hb2);
                borderPane.setCenter(vb2);
                borderPane.setRight(hb3);
    
    
                vBox.getChildren().add(borderPane);
                vBox.setAlignment(Pos.CENTER);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des pays");
            System.err.println(e.getMessage());
        }
    
        vBoxPricpale.getChildren().add(scrollPane);
        vBoxPricpale.setAlignment(Pos.CENTER);
    
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    
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

    public Scene getSceneOrganisateur(){
        return sceneAccueilOrganisateur;
    }


    public Scene getSceneJournalisteAccueil(){
        return sceneJournalisteAccueil;
    }

    /*Méthode de récupération de la scène d'ajout d'un athlète */
    public Scene getSceneAjouterUnAthlete() {
        BorderPane borderPane = (BorderPane) sceneAjouterUnAthlete.lookup("#borderPane");
    
        VBox mainVBox = new VBox(20);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setStyle("-fx-background-color: #0085C7;");
    
        Label titleLabel = new Label("Ajouter un athlète");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: white;");
    
        VBox formVBox = new VBox(10);
        formVBox.setAlignment(Pos.CENTER);
        formVBox.setPadding(new Insets(20, 50, 20, 50));
    
        TextField nom = new TextField();
        nom.setPromptText("Nom");
        nom.setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");
    
        TextField prenom = new TextField();
        prenom.setPromptText("Prénom");
        prenom.setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");
    
        TextField force = new TextField();
        force.setPromptText("Force");
        force.setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");
    
        TextField endurance = new TextField();
        endurance.setPromptText("Endurance");
        endurance.setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");
    
        TextField agilite = new TextField();
        agilite.setPromptText("Agilité");
        agilite.setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");
    
        ToggleGroup sexe = new ToggleGroup();
        HBox sexeHBox = new HBox(10);
        sexeHBox.setAlignment(Pos.CENTER);
        RadioButton homme = new RadioButton("Homme");
        homme.setToggleGroup(sexe);
        RadioButton femme = new RadioButton("Femme");
        femme.setToggleGroup(sexe);
        sexeHBox.getChildren().addAll(homme, femme);
    
        HBox paysHBox = new HBox(10);
        paysHBox.setAlignment(Pos.CENTER);
        Label paysLabel = new Label("Choix du pays");
        paysLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        ComboBox<String> pays = new ComboBox<>();
        pays.setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");
        
        List<String> paysList = new ArrayList<>();
        try {
            for (Pays pay : this.requete.getPays()) {
                paysList.add(pay.getNomPays());
            }
            pays.getItems().addAll(paysList);
            pays.setValue(paysList.get(0));
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des pays");
            System.err.println(e.getMessage());
        }
    
        paysHBox.getChildren().addAll(paysLabel, pays);
    
        HBox buttonHBox = new HBox(10);
        buttonHBox.setAlignment(Pos.CENTER);
        Button ajouter = new Button("Ajouter");
        ajouter.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;");
        ajouter.setOnMouseEntered(e -> ajouter.setStyle("-fx-background-color: #00A1E4; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        ajouter.setOnMouseExited(e -> ajouter.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        ajouter.setOnAction(e -> {
            try {
                String nomAthlete = nom.getText();
                String prenomAthlete = prenom.getText();
                String sexeAthlete = ((RadioButton) sexe.getSelectedToggle()).getText();
                Epreuve.Sexe sex = sexeAthlete.equals("Homme") ? Epreuve.Sexe.M : Epreuve.Sexe.F;
                Pays paysAthlete = new Pays(pays.getValue());
                int forceAthlete = Integer.parseInt(force.getText());
                int enduranceAthlete = Integer.parseInt(endurance.getText());
                int agiliteAthlete = Integer.parseInt(agilite.getText());
                Athlete athlete = new Athlete(nomAthlete, prenomAthlete, sex, paysAthlete, forceAthlete, enduranceAthlete, agiliteAthlete);
                this.requete.ajouterAthlete(athlete);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajout d'un athlète");
                alert.setHeaderText("Athlète ajouté");
                alert.setContentText("L'athlète " + prenomAthlete + " " + nomAthlete + " a bien été ajouté");
                alert.showAndWait();
            } catch (Exception ex) {
                System.out.println("Erreur lors de l'ajout de l'athlète");
                System.err.println(ex.getMessage());
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ajout d'un athlète");
                alert.setHeaderText("Erreur lors de l'ajout de l'athlète");
                alert.setContentText("Une erreur est survenue lors de l'ajout de l'athlète");
                alert.showAndWait();
            }
        });
    
        Button retour = new Button("Retour");
        retour.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;");
        retour.setOnMouseEntered(e -> retour.setStyle("-fx-background-color: #00A1E4; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        retour.setOnMouseExited(e -> retour.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        retour.setOnAction(new ControleurAjoutDonnees(this));
    
        buttonHBox.getChildren().addAll(ajouter, retour);
    
        formVBox.getChildren().addAll(nom, prenom, force, endurance, agilite, sexeHBox, paysHBox, buttonHBox);
    
        mainVBox.getChildren().addAll(titleLabel, formVBox);
    
        borderPane.setCenter(mainVBox);
    
        borderPane.setTop(null);
        return sceneAjouterUnAthlete;
    }

    /*Méthode de récupération de la scène d'ajout d'une équipe */
    public Scene getSceneModifierAthlete(){
        ModifierAthleteScene modifierAthleteScene = new ModifierAthleteScene(requete, this);
        this.sceneModificationDonnéeAthlete = modifierAthleteScene.createScene();
        return this.sceneModificationDonnéeAthlete;
    }

    /*Méthode de récupération de la scène d'ajout d'une équipe */
    public Scene getSceneAthletes(){// Permet de faire une fenetre avec tous les athletes
        SceneAthletes sceneAthletes = new SceneAthletes(requete, this);
        this.sceneAthletes = sceneAthletes.createScene();
        return this.sceneAthletes;

}

    /*Méthode de récupération de la scène d'ajout d'une équipe */
    public Scene getSceneAjouterUneEquipe(){
        AjouterUneEquipeScene ajouterUneEquipeScene = new AjouterUneEquipeScene(requete, this);
        this.sceneAjouterEquipe = ajouterUneEquipeScene.createScene();
        return this.sceneAjouterEquipe;
    }

    public Scene getSceneAjouterUneEpreuve(){
        AjouterUneEpreuveScene ajouterUneEpreuveScene = new AjouterUneEpreuveScene(this);
        this.sceneAjouterEpreuve = ajouterUneEpreuveScene.createScene();
        return this.sceneAjouterEpreuve;
    }

    public Scene getSceneListeEpreuve(){
        SceneListeEpreuve listeEpreuveScene = new SceneListeEpreuve(requete, this);
        this.sceneListeEpreuve = listeEpreuveScene.createScene();
        return this.sceneListeEpreuve;
    }



    /*Méthode de récupération de la scène d'ajout d'une équipe */
    public Scene getSceneEquipe(){// permet de lister toutes les equipes
        BorderPane borderPane = (BorderPane) sceneEquipe.lookup("#borderPane");


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true); // Ajuster le scroll pane à la largeur de la fenêtre

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.TOP_CENTER); // Centrer le GridPane horizontalement

        try{
            List<Equipe> equipes = this.requete.getLesEquipes();
            for (int i = 0; i < equipes.size(); i++) {
                Equipe equipe = equipes.get(i);
                VBox vb = new VBox();
                vb.setAlignment(Pos.CENTER); // Centrer le contenu de chaque VBox horizontalement
                String nom = equipe.getNomEquipe();
                ImageView imageView = new ImageView(new Image("images/logo-équipe.jpg"));
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                Label label = new Label(nom);
                label.setStyle("-fx-font-size: 24px;"); // Augmenter la taille du texte
                vb.getChildren().addAll(imageView, label);
                
                // Ajouter un event handler pour afficher une popup avec les attributs de l'athlète
                vb.setOnMouseClicked(event -> {
                    showEquipeDetails(equipe);
                });
                gridPane.add(vb, i % 5, i / 5);
            }
        }
        catch (Exception e){
            System.out.println("Erreur lors de la récupération des équipes");
            System.err.println(e.getMessage());
        }

        scrollPane.setContent(gridPane);
        borderPane.setCenter(scrollPane);
        

        return sceneEquipe;
    }

    /*Méthode de récupération de la scène de gestion des épreuves */
    private void showEquipeDetails(Equipe equipe) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Détails de l'équipe");
        alert.setHeaderText("Détails de l'équipe " + equipe.getNomEquipe());
        alert.setContentText("Pays : " + equipe.getPays().getNomPays() + "\n" +
                             "Nombre d'athlètes : " + equipe.getLesAthlètes().size());
        alert.showAndWait();
    }



    public Scene getSceneAccueilJournaliste(){
        return sceneJournalisteAccueil;
    }

    public Scene getSceneGestionEpreuve(){
        return this.sceneGestionEpreuve;
    }

    /*Méthode de récupération de la scène de gestion des utilisateurs */
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
    
    /*Méthode de création d'un bouton pour changer de rôle */
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
        return motDePasse.getText();
    }

    public String getMotDePasseConfirmationInscription() {
        PasswordField motDePasse = (PasswordField) sceneInscription.lookup("#champMDPConfirme");
        return motDePasse.getText();
    }

    public Requete getRequete() {
        return this.requete;
    }


    /*Méthode de récupération de la scène de classement */
    public void ajouterPaysALaBase(){
        for (Pays pays : this.modele.getLesPays()){
            try {
                this.requete.ajouterPays(pays.getNomPays());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }  
}
