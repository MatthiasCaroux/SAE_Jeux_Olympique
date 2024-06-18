package src.basededonnee.exception;

public class AthleteInexistantException extends Exception{

    public AthleteInexistantException(String nom, String prenom) {
        super("L'athlète " + nom + " " + prenom + " n'existe pas");
    }
}
