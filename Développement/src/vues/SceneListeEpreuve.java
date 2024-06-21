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
import src.modele.jeuxOlympique.EpreuveCollective;
import src.modele.jeuxOlympique.EpreuveIndividuelle;
import src.basededonnee.Requete;

import java.util.List;

public class SceneListeEpreuve {

    private Requete requete;
    private Scene sceneAthletes;
    private ApplicationJeuxOlympique appli;

    public SceneListeEpreuve(Requete requete, ApplicationJeuxOlympique appli) {
        this.requete = requete;
        this.appli = appli;
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
            List<Epreuve> epreuves = this.requete.getEpreuves(this.appli.getModele());
            for (int i = 0; i < epreuves.size(); i++) {
                Epreuve epreuve;
                if (epreuves.get(i) instanceof EpreuveCollective) {
                    epreuve = (EpreuveCollective) epreuves.get(i);
                } else {
                    epreuve = (EpreuveIndividuelle) epreuves.get(i);
                }
                VBox vb = new VBox();
                vb.setAlignment(Pos.CENTER);
                String nomEpreuve = epreuve.getSport().toString();
                ImageView imageView;
                if (epreuve.getSport().getNbParticipantNecessaire() > 1) {
                    imageView = new ImageView(new Image("images/equipe.png"));                    
                }
                else{
                    imageView = new ImageView(new Image("images/homme.png"));
                }
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                // vb.setOnMouseClicked(event -> showEpreuveRapport(epreuve));

                gridPane.add(vb, i % 5, i / 5);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des athlètes");
            System.err.println(e.getMessage());
        }

        scrollPane.setContent(gridPane);
        borderPaneInterieur.setCenter(scrollPane);

    


        Button boutonretour = new Button();
        boutonretour.setStyle("-fx-font-size: 20px; -fx-padding: 20px; -fx-background-color: #f0f0f0;");
        boutonretour.setText("Retour");
        boutonretour.setOnAction(event -> {
            this.appli.changerFenetre(this.appli.getSceneAccueilJournaliste(), "Accueil journaliste");
        });

        borderPaneInterieur.setBottom(boutonretour);
        return new Scene(borderPaneInterieur, 1000, 650);
    }

    /**
     * Affiche les détails d'une épreuve
     * @param athlete
     */
    private void showEpreuveRapport(Athlete athlete) {
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
