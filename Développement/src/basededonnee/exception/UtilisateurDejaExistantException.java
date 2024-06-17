package src.basededonnee.exception;

public class UtilisateurDejaExistantException extends Exception{

    public UtilisateurDejaExistantException() {
        super("L'utilisateur existe déjà");
    }
}
