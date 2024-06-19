package src.basededonnee.exception;

public class EquipeDejaExistantException extends Exception {

    public EquipeDejaExistantException(String nom) {
        super("L'équipe " + nom + " existe déjà");
    }

}
