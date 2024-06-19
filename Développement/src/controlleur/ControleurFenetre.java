package src.controlleur;
import src.vues.*;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ControleurFenetre implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;
    private String titre;

    public ControleurFenetre(ApplicationJeuxOlympique applicationJeuxOlympique, String titre) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
        this.titre = titre;
    }

    public void handle(ActionEvent event) {
        Object source = event.getSource();
        try {
            System.out.println("Action sur " + event.getSource() + " : " + event.getEventType());
            if (event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#seConnecter")) {
                System.out.println("Bouton seConnecter cliqué");
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneConnexion(), titre);
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#sinscrire")) {
                System.out.println("Bouton sinscrire cliqué");
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneInscription(), titre);
            }
            else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#entrer")) {
                System.out.println("Bouton entrer cliqué");
                String identifiant = this.applicationJeuxOlympique.getIdentifiantConnexion();
                String motDePasse = this.applicationJeuxOlympique.getMotDePasseConnexion();
                if (!(this.applicationJeuxOlympique.getRequete().dansUtilisateur(identifiant, motDePasse))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Identifiant ou mot de passe incorrect");
                        alert.setContentText("Veuillez vérifier vos informations de connexion.");
                        alert.showAndWait();
                }
                if (this.applicationJeuxOlympique.getRequete().dansUtilisateur(identifiant, motDePasse)){//regarde si l'utilisateur est dans la base
                    System.err.println(this.applicationJeuxOlympique.getRequete().getRoleUtilisateur(identifiant, motDePasse));
                    System.out.println("JSikhgf oliueyrpgoius gru");
                    System.out.println(this.applicationJeuxOlympique.getRequete().getRoleUtilisateur(identifiant, motDePasse));
                    switch (this.applicationJeuxOlympique.getRequete().getRoleUtilisateur(identifiant, motDePasse)){
                        case 'A':
                            System.out.println("role est A");
                            // TextField tfID = (TextField) this.applicationJeuxOlympique.getSceneConnexion().lookup("ID");
                            // tfID.setText("");
                            // TextField tfMDP = (TextField) this.applicationJeuxOlympique.getSceneConnexion().lookup("MDP");
                            // tfMDP.setText("");
                            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAccueilAdmin(), titre);  
                            System.out.println("seroiuhg osig poqpoîpùo3546546");                      
                            break;
                        case 'C':
                            System.out.println("role est C");
                            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getAccueilJournaliste(), titre);
                            break;
                        case 'O':
                            // this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneOrganisateur(), titre, "entrer");
                            break;                
                        default:
                            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneClassement(), titre);
                            break;
                    }
                }
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneInscription().lookup("#boutonRetour")) {
                System.out.println("Bouton boutonRetour cliqué");
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre);
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#boutonRetourConnexion")) {
                System.out.println("Bouton boutonRetourConnexion cliqué");
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre);
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneAccueilAdmin().lookup("#gestionUtilisateur")){
                System.out.println("Bouton gestionUtilisateur cliqué");
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneGestionUtilisateur(), titre);
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneGestionUtilisateur().lookup("#retourAccueilAdmin")) {
                System.out.println("Bouton retourAccueilAdmin cliqué");
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAccueilAdmin(), titre);            
            } else if (source == (Button) this.applicationJeuxOlympique.getSceneAccueilAdmin().lookup("#choixDeconnexion")) {
                System.out.println("Bouton choixDeconnexion cliqué");
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation");
                confirmationAlert.setHeaderText("Attention");
                confirmationAlert.setContentText("Vous allez vous déconnecter. Confirmez-vous ?");
                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Code to handle OK
                    this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre);
                }
            } else if (source == (Button) this.applicationJeuxOlympique.getSceneAccueilAdmin().lookup("#ajoutDesDonnées")){
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAjoutDonnees(), titre);
                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
