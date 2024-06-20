package src.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import src.controlleur.ControleurAjoutDonnees;
import src.modele.jeuxOlympique.Epreuve;
import src.modele.jeuxOlympique.Pays;
import src.modele.jeuxOlympique.Equipe;
import src.basededonnee.Requete;

public class AjouterUneEquipeScene {

    private Requete requete;
    private ApplicationJeuxOlympique appli;

    public AjouterUneEquipeScene(Requete requete, ApplicationJeuxOlympique appli) {
        this.requete = requete;
        this.appli = appli;
    }

    public Scene createScene() {
        BorderPane borderPane = new BorderPane();

        VBox mainVBox = new VBox(20);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setStyle("-fx-background-color: #0085C7;");

        Label titleLabel = new Label("Ajouter une équipe");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: white;");

        VBox formVBox = new VBox(10);
        formVBox.setAlignment(Pos.CENTER);
        formVBox.setPadding(new Insets(20, 50, 20, 50));

        TextField nom = new TextField();
        nom.setPromptText("Nom de l'équipe");
        nom.setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");

        TextField pays = new TextField();
        pays.setPromptText("Pays de l'équipe");
        pays.setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #f0f0f0;");

        ToggleGroup sexe = new ToggleGroup();
        HBox sexeHBox = new HBox(10);
        sexeHBox.setAlignment(Pos.CENTER);
        RadioButton homme = new RadioButton("Homme");
        homme.setToggleGroup(sexe);
        RadioButton femme = new RadioButton("Femme");
        femme.setToggleGroup(sexe);
        sexeHBox.getChildren().addAll(homme, femme);

        HBox buttonHBox = new HBox(10);
        buttonHBox.setAlignment(Pos.CENTER);
        Button ajouter = new Button("Ajouter");
        ajouter.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;");
        ajouter.setOnMouseEntered(e -> ajouter.setStyle("-fx-background-color: #00A1E4; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        ajouter.setOnMouseExited(e -> ajouter.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        ajouter.setOnAction(e -> {
            try {
                String nomEquipe = nom.getText();
                Pays paysEquipe = new Pays(pays.getText());
                String sexeEquipe = ((RadioButton) sexe.getSelectedToggle()).getText();
                Epreuve.Sexe sex = sexeEquipe.equals("Homme") ? Epreuve.Sexe.M : Epreuve.Sexe.F;
                Equipe equipe = new Equipe(nomEquipe, paysEquipe, sex);
                this.requete.ajouterEquipe(equipe);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout d'une équipe");
                alert.setHeaderText("Équipe ajoutée");
                alert.setContentText("L'équipe " + nomEquipe + " a bien été ajoutée");
                alert.showAndWait();
            } catch (Exception ex) {
                System.out.println("Erreur lors de l'ajout de l'équipe");
                System.err.println(ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajout d'une équipe");
                alert.setHeaderText("Erreur lors de l'ajout de l'équipe");
                alert.setContentText("Une erreur est survenue lors de l'ajout de l'équipe");
                alert.showAndWait();
            }
        });

        Button retour = new Button("Retour");
        retour.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;");
        retour.setOnMouseEntered(e -> retour.setStyle("-fx-background-color: #00A1E4; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        retour.setOnMouseExited(e -> retour.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        retour.setOnAction(new ControleurAjoutDonnees(this.appli));

        buttonHBox.getChildren().addAll(ajouter, retour);

        formVBox.getChildren().addAll(nom, pays, sexeHBox, buttonHBox);
        mainVBox.getChildren().addAll(titleLabel, formVBox);

        borderPane.setCenter(mainVBox);

        return new Scene(borderPane, 1000, 650);
    }
}
