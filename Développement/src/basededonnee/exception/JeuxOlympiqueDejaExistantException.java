package src.basededonnee.exception;

public class JeuxOlympiqueDejaExistantException extends Exception {

    public JeuxOlympiqueDejaExistantException() {
        super("Jeux Olympique déjà existant");
    }

    public JeuxOlympiqueDejaExistantException(int annee) {
        super("Jeux Olympique de l'année " + annee + " déjà existant");
    }
}
