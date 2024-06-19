package src.basededonnee.exception;

public class EquipeInexistanteException extends Exception {

    public EquipeInexistanteException(String nom) {
        super("L'équipe " + nom + " n'existe pas");
    }

}
