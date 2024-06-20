package src.basededonnee.exception;

public class EpreuveInexistanteException extends Exception {
    public EpreuveInexistanteException(String nomPays, char sexe) {
        super("L'épreuve " + nomPays + " n'existe pas pour le sexe " + sexe);
    }

}
