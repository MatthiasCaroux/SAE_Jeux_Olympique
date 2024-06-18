package src.controlleur;


import src.vues.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;      


public class ControleurInscription implements EventHandler<ActionEvent>{

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurInscription(ApplicationJeuxOlympique applicationJeuxOlympique){
        System.out.println("ControleurInscription");
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        System.out.println("Inscription");
        try{
            String identifiant = this.applicationJeuxOlympique.getIdentifiantInscription();
            String motDePasse = this.applicationJeuxOlympique.getMotDePasseInscription();
            System.out.println(motDePasse);
            String email = this.applicationJeuxOlympique.getEmailInscription();
            System.out.println(email);
            String motDePasseConfirmation = this.applicationJeuxOlympique.getMotDePasseConfirmationInscription();
            System.out.println(motDePasseConfirmation);

            if (identifiant.equals("") || motDePasse.equals("") || email.equals("") || motDePasseConfirmation.equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Il y a eu un problème lors de l'inscription");
                alert.setHeaderText("Veuillez remplir tous les champs");
                alert.showAndWait();
                return;
            }


            System.out.println("identifiant : " + identifiant);
            System.out.println("mot de passe : " + motDePasse);
            System.out.println("email : " + email);
            System.out.println("mot de passe confirmation : " + motDePasseConfirmation);


            if (motDePasse.equals(motDePasseConfirmation)){
                if (email.contains("@") && email.contains(".")){
                    if (this.applicationJeuxOlympique.getRequete().dansUtilisateur(identifiant, email, motDePasse)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Il y a eu un problème lors de l'inscription");
                        alert.setHeaderText("Cet identifiant ou cette adresse mail est déjà utilisé");
                        alert.showAndWait();
                        return;
                    } else {
                        this.applicationJeuxOlympique.getRequete().inscription(identifiant, email, motDePasse);
                        if (this.applicationJeuxOlympique.getRequete().dansUtilisateur(identifiant, email, motDePasse)) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Inscription réussie");
                            alert.setHeaderText("Votre inscription a bien été prise en compte");
                            alert.showAndWait();
                            this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), "Fenetre d'inscription", "boutonRetour");// On revient à la fenêtre d'accueil
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Il y a eu un problème lors de l'inscription");
                            alert.setHeaderText("Votre inscription n'a pas été prise en compte");
                            alert.showAndWait();
                        }
                    }
                    this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), "Fenetre d'inscription", "boutonRetour");// On revient à la fenêtre d'accueil
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Il y a eu un problème lors de l'inscription");
                    alert.setHeaderText("Veuillez entrer une adresse mail valide");
                    alert.showAndWait();
                
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Il y a eu un problème lors de l'inscription");
                alert.setHeaderText("Les mots de passe ne correspondent pas");
                alert.showAndWait();
            }
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Il y a eu un problème lors de l'inscription");
                System.out.println(e.getMessage()); 
            }
    }
}
