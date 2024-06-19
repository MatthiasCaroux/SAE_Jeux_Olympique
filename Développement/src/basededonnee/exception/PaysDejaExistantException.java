package src.basededonnee.exception;

public class PaysDejaExistantException extends Exception {

    public PaysDejaExistantException(String nomPays) {
        super("Le pays " + nomPays + " existe déjà");
    }

}
