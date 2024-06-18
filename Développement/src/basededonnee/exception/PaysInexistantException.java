package src.basededonnee.exception;

public class PaysInexistantException extends Exception {

    public PaysInexistantException(String nomPays) {
        super("Le pays " + nomPays + " n'existe pas dans la base de donnée");
    }
}
