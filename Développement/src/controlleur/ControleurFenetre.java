package src.controlleur;
import src.vues.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class ControleurFenetre implements EventHandler<ActionEvent> {

    private ApplicationJeuxOlympique applicationJeuxOlympique;
    private String titre;

    public ControleurFenetre(ApplicationJeuxOlympique applicationJeuxOlympique, String titre) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
        this.titre = titre;
    }

    public void handle(ActionEvent event) {
        try {
            System.out.println("Action sur " + event.getSource() + " : " + event.getEventType());
            if (event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#seConnecter")) {
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneConnexion(), titre, "seConnecter");
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneFenetreAccueil().lookup("#sinscrire")) {
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneInscription(), titre, "sinscrire");
            }
            else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#entrer")) {
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
                            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAccueilAdmin(), titre, "entrer");  
                            System.out.println("seroiuhg osig poqpoîpùo3546546");                      
                            break;
                        case 'C':
                            System.out.println("role est C");
                            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneClassement(), titre, "entrer");
                            break;
                        case 'O':
                            // this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneOrganisateur(), titre, "entrer");
                            break;                
                        default:
                            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneClassement(), titre, "entrer");
                            break;
                    }
                }
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneInscription().lookup("#boutonRetour")) {
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre, "boutonRetour");
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneConnexion().lookup("#boutonRetourConnexion")) {
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre, "boutonRetourConnexion");
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneAccueilAdmin().lookup("#gestionUtilisateur")){
                System.out.println("gestionUtilisateur");
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneGestionUtilisateur(), titre, "gestionUtilisateur");
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneGestionUtilisateur().lookup("#retourAccueilAdmin")) {
                System.out.println("retourAccueilAdmin");
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneAccueilAdmin(), titre, "retourAccueilAdmin");            
            } else if (event.getSource() == this.applicationJeuxOlympique.getSceneGestionUtilisateur().lookup("#choixDeconnexion")) {
                System.out.println("choixDeconnexion");
                this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), titre, "choixDeconnexion");                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
