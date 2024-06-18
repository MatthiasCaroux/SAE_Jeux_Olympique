package src.basededonnee.exception;

public class PasDeIdDansUtilisateurException extends Exception {

    public PasDeIdDansUtilisateurException() {
        super("Pas de id dans la table Utilisateur");
    }

}
