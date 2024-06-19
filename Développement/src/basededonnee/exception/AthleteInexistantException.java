package src.basededonnee.exception;

public class AthleteInexistantException extends Exception{

    public AthleteInexistantException(String nom, String prenom) {
        super("L'athlète " + nom + " " + prenom + " n'existe pas");
    }

    public AthleteInexistantException(int id) {
        super("L'athlète avec id " + id + " n'existe pas");
    }
}
