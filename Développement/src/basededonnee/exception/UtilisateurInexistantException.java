package src.basededonnee.exception;

public class UtilisateurInexistantException extends Exception {

    public UtilisateurInexistantException(String identifiant) {
        super("L'utilisateur " + identifiant + " n'existe pas");
    }

}
