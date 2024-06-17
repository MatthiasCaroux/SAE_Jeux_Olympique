package src.controlleur;


import src.vues.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ControleurInscription implements EventHandler<ActionEvent>{

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public ControleurInscription(ApplicationJeuxOlympique applicationJeuxOlympique){
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        try{
            String identifiant = this.applicationJeuxOlympique.getIdentifiantInscription();
            String motDePasse = this.applicationJeuxOlympique.getMotDePasseInscription();
            System.out.println(motDePasse);
            String email = this.applicationJeuxOlympique.getEmailInscription();
            System.out.println(email);
            String motDePasseConfirmation = this.applicationJeuxOlympique.getMotDePasseConfirmationInscription();
            System.out.println(motDePasseConfirmation);

            if (identifiant.equals("") || motDePasse.equals("") || email.equals("") || motDePasseConfirmation.equals("")){
                System.out.println("Veuillez remplir tous les champs");
                throw new Exception("Veuillez remplir tous les champs");
            }


            System.out.println("identifiant : " + identifiant);
            System.out.println("mot de passe : " + motDePasse);
            System.out.println("email : " + email);
            System.out.println("mot de passe confirmation : " + motDePasseConfirmation);


            if (motDePasse.equals(motDePasseConfirmation)){
                if (email.contains("@") && email.contains(".")){
                    this.applicationJeuxOlympique.getRequete().inscription(identifiant, email, motDePasse);
                    this.applicationJeuxOlympique.changerFenetre(this.applicationJeuxOlympique.getSceneFenetreAccueil(), "Fenetre d'inscription", "boutonRetour");
                }
            }
            }
            catch (Exception e){
                System.out.println(e.getMessage()); 
            }
    }
}
