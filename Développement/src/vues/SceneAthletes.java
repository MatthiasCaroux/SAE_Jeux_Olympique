package src.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import src.modele.jeuxOlympique.Athlete;
import src.modele.jeuxOlympique.Epreuve;
import src.basededonnee.Requete;

import java.util.List;

public class SceneAthletes {

    private Requete requete;
    private Scene sceneAthletes;

    public SceneAthletes(Requete requete) {
        this.requete = requete;
    }

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

                vb.setOnMouseClicked(event -> showAthleteDetails(athlete));

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

                        vb.setOnMouseClicked(event -> showAthleteDetails(athlete));

                        gridPane.add(vb, i % 5, i / 5);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Erreur lors de la récupération des athlètes");
                System.err.println(ex.getMessage());
            }
        });

        borderPaneInterieur.setTop(recherche);
        return new Scene(borderPaneInterieur, 1000, 650);
    }

    private void showAthleteDetails(Athlete athlete) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails de l'athlète");
        alert.setHeaderText(athlete.getPrenom() + " " + athlete.getNom());
        alert.setContentText("Prénom: " + athlete.getPrenom() + "\n" +
                             "Nom: " + athlete.getNom() + "\n" +
                             "Pays: " + athlete.getPays() + "\n" +
                             "Sexe: " + athlete.getSexe() + "\n" +
                             "Agilité: " + athlete.getAgilité() + "\n" +
                             "Force: " + athlete.getForce() + "\n" +
                             "Endurance: " + athlete.getEndurance());
        alert.showAndWait();
    }
}
