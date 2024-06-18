package src.basededonnee.exception;

public class BaseDeDonneeInaccessibleException extends Exception {

    public BaseDeDonneeInaccessibleException() {
        super("La base de donnée est inaccessible");
    }
}
