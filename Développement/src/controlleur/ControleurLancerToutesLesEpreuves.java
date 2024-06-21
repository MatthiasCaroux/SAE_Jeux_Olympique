package src.controlleur;

import src.modele.jeuxOlympique.Epreuve;
import src.modele.jeuxOlympique.JeuxOlympique;
import src.vues.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ControleurLancerToutesLesEpreuves implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurLancerToutesLesEpreuves(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Lancer toutes les épreuves");

        // Create an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Lancement de toutes les épreuves");

        // Create a VBox to hold the ProgressBar and label
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        // Create a ProgressBar
        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);

        // Create a Label for the loading message
        Label loadingLabel = new Label("Chargement...");

        // Add the ProgressBar and Label to the VBox
        vbox.getChildren().addAll(progressBar, loadingLabel);

        // Set the content of the alert to the VBox
        alert.getDialogPane().setContent(vbox);

        // Create a Timeline to simulate the loading
        Timeline timeline = new Timeline();
        double durationSeconds = 5.0; // total duration of the progress
        int frames = 100; // total number of frames for the animation
        double interval = durationSeconds / frames; // duration of each frame in seconds

        for (int i = 0; i <= frames; i++) {
            double progress = (double) i / frames;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * interval), e -> progressBar.setProgress(progress));
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setOnFinished(e -> loadingLabel.setText("Les épreuves vont commencer"));

        timeline.play();

        // Show the alert and wait for it to be closed

        JeuxOlympique jeux = this.applicationJeuxOlympique.getModele();
                
        for (Epreuve epreuve : jeux.getEpreuves()) {
            System.out.println(epreuve + "ççççççççççççççççççç");
            this.applicationJeuxOlympique.getRequete().lancerUneEpreuve(epreuve, jeux);
        }
        System.out.println(jeux.getEpreuves());
        System.out.println(this.applicationJeuxOlympique.getRequete().getEpreuves(jeux));
        try{
            System.out.println("Affichage du classement");
            System.out.println(jeux.getClassementPays());
            System.out.println("le classement des pays");
        }
        catch(Exception ex){
            System.out.println("Erreur lors de l'affichage du classement");
            System.out.println(ex.getMessage());
        }
        alert.showAndWait();
    }
}
