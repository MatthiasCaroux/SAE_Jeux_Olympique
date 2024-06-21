package src.vues;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import src.controlleur.ControleurAjoutDonnees;
import src.modele.jeuxOlympique.Epreuve;
import src.modele.jeuxOlympique.Pays;
import src.modele.jeuxOlympique.Epreuve.TypeSport;
import src.modele.jeuxOlympique.EpreuveCollective;
import src.modele.jeuxOlympique.EpreuveIndividuelle;
import src.modele.jeuxOlympique.Equipe;
import src.basededonnee.Requete;

public class AjouterUneEpreuveScene {

    private Requete requete;
    private ApplicationJeuxOlympique appli;

    public AjouterUneEpreuveScene(Requete requete, ApplicationJeuxOlympique appli) {
        this.requete = requete;
        this.appli = appli;
    }

    public Scene createScene() {
        BorderPane borderPane = new BorderPane();

        VBox mainVBox = new VBox(20);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setStyle("-fx-background-color: #0085C7;");

        Label titleLabel = new Label("Ajouter une épreuve");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: white;");

        VBox formVBox = new VBox(10);
        formVBox.setAlignment(Pos.CENTER);
        formVBox.setPadding(new Insets(20, 50, 20, 50));


        List<Epreuve> listeEpreuves = this.appli.getRequete().getEpreuves(this.appli.getModele());
        List<TypeSport> listeNomsEpreuves = new ArrayList<>();
        for (Epreuve epreuve : listeEpreuves) {
            listeNomsEpreuves.add(epreuve.getSport());
        }
        System.out.println("La liiiiiiiste" + listeNomsEpreuves);

        ComboBox<TypeSport> typeSport = new ComboBox<>();
        typeSport.setPromptText("Nom de l'épreuve");
        typeSport.setEditable(true);
        typeSport.getItems().addAll(listeNomsEpreuves);



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
                Epreuve.TypeSport tp = typeSport.getValue();
                Epreuve.Sexe sexeEpreuve = homme.isSelected() ? Epreuve.Sexe.M : Epreuve.Sexe.F;
                Epreuve epreuve;
                if (tp.getNbParticipantNecessaire() > 1){
                    epreuve = new EpreuveCollective(tp, sexeEpreuve);
                }
                else {
                    epreuve = new EpreuveIndividuelle(tp, sexeEpreuve);
                }
                this.appli.getRequete().ajouteEpreuve(epreuve, this.appli.getModele());
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de l'ajout de l'épreuve");
                alert.setContentText("Veuillez vérifier les informations saisies");
                alert.showAndWait();
                System.out.println(ex.getMessage());
            }
        });

        Button retour = new Button("Retour");
        retour.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;");
        retour.setOnMouseEntered(e -> retour.setStyle("-fx-background-color: #00A1E4; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        retour.setOnMouseExited(e -> retour.setStyle("-fx-background-color: #0085C7; -fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 10px; -fx-background-radius: 5;"));
        retour.setOnAction(new ControleurAjoutDonnees(this.appli));

        buttonHBox.getChildren().addAll(ajouter, retour);

        formVBox.getChildren().addAll(typeSport, sexeHBox, buttonHBox);
        mainVBox.getChildren().addAll(titleLabel, formVBox);

        borderPane.setCenter(mainVBox);

        return new Scene(borderPane, 1000, 650);
    }
}
