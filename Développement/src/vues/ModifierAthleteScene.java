package src.vues;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import src.modele.jeuxOlympique.*;
import src.basededonnee.Requete;
import src.controlleur.*;

import java.util.List;

public class ModifierAthleteScene {

    private Requete requete;
    private ApplicationJeuxOlympique appli;

    public ModifierAthleteScene(Requete requete, ApplicationJeuxOlympique appli) {
        this.requete = requete;
        this.appli = appli;
    }

    /**
     * Permet de créer la scène de modification des athlètes
     * @return la scène de modification des athlètes
     */
    public Scene createScene() {
        BorderPane borderPaneInterieur = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.TOP_CENTER);

        try {
            List<Athlete> athletes = this.requete.getAthletes();
            for (int i = 0; i < athletes.size(); i++) {
                Athlete athlete = athletes.get(i);
                VBox vb = new VBox();
                vb.setAlignment(Pos.CENTER);
                String prenom = athlete.getPrenom();
                String nom = athlete.getNom();
                ImageView imageView;
                if (athlete.getSexe() == Epreuve.Sexe.M) {
                    imageView = new ImageView(new Image("images/hommeAth.png"));
                } else {
                    imageView = new ImageView(new Image("images/femmeAth.png"));
                }
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                Label label = new Label(prenom + " " + nom);
                label.setStyle("-fx-font-size: 24px;");
                vb.getChildren().addAll(imageView, label);

                vb.setOnMouseClicked(event -> editAthlete(athlete));

                gridPane.add(vb, i % 5, i / 5);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des athlètes");
            System.err.println(e.getMessage());
        }

        scrollPane.setContent(gridPane);
        borderPaneInterieur.setCenter(scrollPane);

        TextField recherche = new TextField();
        recherche.setPromptText("Rechercher un athlète");
        recherche.setStyle("-fx-font-size: 20px; -fx-padding: 20px; -fx-background-color: #f0f0f0;");
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                List<Athlete> athletes = this.requete.getAthletes();
                gridPane.getChildren().clear();
                for (int i = 0; i < athletes.size(); i++) {
                    Athlete athlete = athletes.get(i);
                    if (athlete.getPrenom().toLowerCase().contains(newValue.toLowerCase()) || athlete.getNom().toLowerCase().contains(newValue.toLowerCase())) {
                        VBox vb = new VBox();
                        vb.setAlignment(Pos.CENTER);
                        String prenom = athlete.getPrenom();
                        String nom = athlete.getNom();
                        ImageView imageView;
                        if (athlete.getSexe() == Epreuve.Sexe.M) {
                            imageView = new ImageView(new Image("images/hommeAth.png"));
                        } else {
                            imageView = new ImageView(new Image("images/femmeAth.png"));
                        }
                        imageView.setFitHeight(100);
                        imageView.setFitWidth(100);
                        Label label = new Label(prenom + " " + nom);
                        label.setStyle("-fx-font-size: 24px;");
                        vb.getChildren().addAll(imageView, label);

                        vb.setOnMouseClicked(event -> editAthlete(athlete));

                        gridPane.add(vb, i % 5, i / 5);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la récupération des athlètes");
                System.err.println(ex.getMessage());
            }
        });

        borderPaneInterieur.setTop(recherche);

        Button retour = new Button("Retour");
        retour.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;");
        retour.setOnAction(new ControleurModificationDonnée(appli));

        borderPaneInterieur.setBottom(retour);

        return new Scene(borderPaneInterieur, 1000, 650);
    }

    /**
     * Permet de modifier un athlète
     * @param athlete l'athlète à modifier
     */
    private void editAthlete(Athlete athlete) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        TextField prenom = new TextField(athlete.getPrenom());
        TextField nom = new TextField(athlete.getNom());
        TextField pays = new TextField(athlete.getPays().getNomPays());
        TextField sexe = new TextField(athlete.getSexe().toString());
        TextField agilite = new TextField(Integer.toString(athlete.getAgilité()));
        TextField force = new TextField(Integer.toString(athlete.getForce()));
        TextField endurance = new TextField(Integer.toString(athlete.getEndurance()));

        VBox vbox = new VBox(8, prenom, nom, pays, sexe, agilite, force, endurance);
        Button modifier = new Button("Modifier");

        Button supprimer = new Button("Supprimer");
        supprimer.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;");
        supprimer.setOnAction(e -> {
            try {
                int id = this.requete.getIdAthlete(athlete);
                this.requete.supprimerAthlete(id);
                alert.close();
            } catch (Exception ex) {
                System.out.println("Erreur lors de la suppression de l'athlète");
                System.err.println(ex.getMessage());
                Alert alertErreur = new Alert(Alert.AlertType.ERROR);
                alertErreur.setTitle("Suppression de l'athlète");
                alertErreur.setHeaderText("Erreur lors de la suppression de l'athlète");
                alertErreur.setContentText("Une erreur est survenue lors de la suppression de l'athlète");
                alertErreur.showAndWait();
            }
        });

        modifier.setOnAction(e -> {
            try {
                int id = this.requete.getIdAthlete(athlete);
                athlete.setPrenom(prenom.getText());
                athlete.setNom(nom.getText());
                athlete.setPays(new Pays(pays.getText()));
                athlete.setSexe(sexe.getText().equalsIgnoreCase("M") ? Epreuve.Sexe.M : Epreuve.Sexe.F);
                athlete.setAgilité(Integer.parseInt(agilite.getText()));
                athlete.setForce(Integer.parseInt(force.getText()));
                athlete.setEndurance(Integer.parseInt(endurance.getText()));
                this.requete.modifierAthlete(athlete, id);
                alert.close();
            } catch (Exception ex) {
                System.out.println("Erreur lors de la modification de l'athlète");
                System.err.println(ex.getMessage());
                Alert alertErreur = new Alert(Alert.AlertType.ERROR);
                alertErreur.setTitle("Modification de l'athlète");
                alertErreur.setHeaderText("Erreur lors de la modification de l'athlète");
                alertErreur.setContentText("Une erreur est survenue lors de la modification de l'athlète");
                alertErreur.showAndWait();
            }
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(modifier, supprimer);
        VBox mainVBox = new VBox(10, vbox, hbox);

        alert.getDialogPane().setContent(mainVBox);
        alert.setTitle("Modification de l'athlète");
        alert.setHeaderText(athlete.getPrenom() + " " + athlete.getNom());
        alert.setContentText("Prénom: " + athlete.getPrenom() + "\n" +
                             "Nom: " + athlete.getNom() + "\n" +
                             "Pays: " + athlete.getPays().getNomPays() + "\n" +
                             "Sexe: " + athlete.getSexe() + "\n" +
                             "Agilité: " + athlete.getAgilité() + "\n" +
                             "Force: " + athlete.getForce() + "\n" +
                             "Endurance: " + athlete.getEndurance());

        alert.showAndWait();
    }
}
