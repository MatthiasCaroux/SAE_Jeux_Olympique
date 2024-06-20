package src.controlleur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import src.modele.jeuxOlympique.Epreuve;
import src.vues.ApplicationJeuxOlympique;

public class ControleurLancerEpreuve implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique application;

    public ControleurLancerEpreuve(ApplicationJeuxOlympique appli) {
        this.application = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Lancer une épreuve");

        // Event and Category section
        VBox eventCategorySection = new VBox(20);
        eventCategorySection.setAlignment(Pos.CENTER);
        eventCategorySection.setPadding(new Insets(20));

        // Event section
        Label eventLabel = new Label("ÉPREUVE");
        ToggleGroup eventGroup = new ToggleGroup();
        RadioButton athleRelai = new RadioButton("4×100m Relais");
        athleRelai.setToggleGroup(eventGroup);
        RadioButton haies = new RadioButton("110m Haies");
        haies.setToggleGroup(eventGroup);
        RadioButton brasse = new RadioButton("100m Brasse");
        brasse.setToggleGroup(eventGroup);
        RadioButton nageRelai = new RadioButton("4x100m Nage libre");
        nageRelai.setToggleGroup(eventGroup);
        RadioButton volley = new RadioButton("Volleyball");
        volley.setToggleGroup(eventGroup);
        RadioButton epe = new RadioButton("Escrime épée");
        epe.setToggleGroup(eventGroup);
        RadioButton fleuret = new RadioButton("Fleuret");
        fleuret.setToggleGroup(eventGroup);
        RadioButton hand = new RadioButton("Handball");
        hand.setToggleGroup(eventGroup);

        VBox eventBox = new VBox(10, eventLabel, athleRelai, haies, brasse, nageRelai, volley, epe, fleuret, hand);
        eventBox.setStyle("-fx-background-color: lightgray; -fx-padding: 10;");
        eventBox.setPadding(new Insets(10));
        VBox.setVgrow(eventBox, Priority.ALWAYS);
        VBox.setMargin(eventLabel, new Insets(0, 0, 10, 0));

        // Load and add images
        VBox imageBox = createImageBox();

        // Category section
        Label categoryLabel = new Label("CATÉGORIE");
        ToggleGroup categoryGroup = new ToggleGroup();
        RadioButton womenButton = new RadioButton("Femmes");
        womenButton.setToggleGroup(categoryGroup);
        RadioButton menButton = new RadioButton("Homme");
        menButton.setToggleGroup(categoryGroup);

        VBox categoryBox = new VBox(10, categoryLabel, womenButton, menButton);
        categoryBox.setStyle("-fx-background-color: lightgray; -fx-padding: 10;");
        categoryBox.setPadding(new Insets(10));
        VBox.setMargin(categoryLabel, new Insets(0, 0, 10, 0));

        // Set min width for both VBox to ensure same size
        eventBox.setMinWidth(200);
        categoryBox.setMinWidth(200);

        // HBox to align eventBox, imageBox, and categoryBox horizontally
        HBox selectionBox = new HBox(20);
        selectionBox.setAlignment(Pos.CENTER);
        selectionBox.getChildren().addAll(eventBox, imageBox, categoryBox);

        // Start button
        Button startButton = new Button("LANCER L'ÉPREUVE");
        startButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        startButton.setPadding(new Insets(10, 20, 10, 20));
        startButton.setCursor(javafx.scene.Cursor.HAND);
        startButton.setOnAction(e -> {
            Epreuve.TypeSport typeSport = null;
            RadioButton selectedRadioButton = (RadioButton) eventGroup.getSelectedToggle();
            RadioButton selectedRadioButton2 = (RadioButton) categoryGroup.getSelectedToggle();
            System.out.println("Bouton 1 :" + selectedRadioButton.getText());
            switch (selectedRadioButton.getText()) {
                case "4×100m Relais":
                    typeSport = Epreuve.TypeSport.AthlétismeRelais;
                    break;
                case "110m Haies":
                    typeSport = Epreuve.TypeSport.AthlétismeHaie;
                    break;
                case "100m Brasse":
                    typeSport = Epreuve.TypeSport.NatationBrasse;
                    break;
                case "4x100m Nage libre":
                    typeSport = Epreuve.TypeSport.NatationRelais;
                    break;
                case "Volleyball":
                    typeSport = Epreuve.TypeSport.Volley;
                    break;
                case "Escrime épée":
                    typeSport = Epreuve.TypeSport.EscrimeÉpée;
                    break;
                case "Fleuret":
                    typeSport = Epreuve.TypeSport.Escrimefleuret;
                    break;
                case "Handball":
                    typeSport = Epreuve.TypeSport.Handball;
                    break;
            
                default:
                    break;
            }
            Epreuve.Sexe sexe = null;
            System.out.println("Bouton2 : " + selectedRadioButton2.getText());
            switch (selectedRadioButton2.getText()) {
                case "Femmes":
                    sexe = Epreuve.Sexe.F;
                    break;
                case "Homme":
                    sexe = Epreuve.Sexe.M;
                    break;
            
                default:
                    break;
            }
            try{
                System.out.println("Lancement de l'épreuve");
                System.out.println(this.application.getModele().getEpreuves());
                System.out.println("1");
                System.out.println("Type de sport : " + typeSport);
                System.out.println("Sexe : " + sexe);
                System.out.println(this.application.getRequete().getEpreuve(typeSport, sexe));
                // System.out.println(this.application.getRequete().getEpreuve(Epreuve.TypeSport.AthlétismeRelais, Epreuve.Sexe.F));
                System.err.println("1,5");
                Epreuve epreuve = this.application.getRequete().getEpreuve(typeSport, sexe);
                // Epreuve epreuve = this.application.getRequete().getEpreuve(typeSport, sexe);
                System.out.println("2");
                this.application.getRequete().lancerUneEpreuve(epreuve, this.application.getModele());
                System.out.println("3");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Lancer une épreuve");
                alert.setHeaderText("L'épreuve a été lancée");
                alert.showAndWait();
            }
            catch(Exception ex){
                System.out.println("Erreur lors du lancement de l'épreuve");
            }
            
        });

        eventCategorySection.getChildren().addAll(selectionBox, startButton);

        // BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(eventCategorySection);

        // Show the content in an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Lancer une épreuve");
        alert.setHeaderText("Sélectionnez une épreuve et une catégorie");
        alert.getDialogPane().setContent(borderPane);
        alert.showAndWait();
    }

    private VBox createImageBox() {
        VBox imageBox = new VBox(10);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(10));

        // Load and add images
        for (int i = 1; i <= 5; i++) {
            System.out.println("L'image est : " + getClass().getResourceAsStream("/images/sport/image" + i + ".png"));
            Image image = new Image(getClass().getResourceAsStream("/images/sport/image" + i + ".png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(119);
            imageView.setFitHeight(67);
            imageBox.getChildren().add(imageView);
            System.out.println("Image ajoutée");
        }

        return imageBox;
    }
}