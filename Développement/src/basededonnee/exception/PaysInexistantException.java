package src.basededonnee.exception;

public class PaysInexistantException extends Exception {

    public PaysInexistantException(String nomPays) {
        super("Le pays " + nomPays + " n'existe pas dans la base de donnée");
    }

    public PaysInexistantException(int idPays) {
        super("Le pays d'id " + idPays + " n'existe pas dans la base de donnée");
    }
}
