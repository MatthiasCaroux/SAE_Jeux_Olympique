package src.basededonnee.exception;

public class EpreuveDejaExistantException extends Exception {

    public EpreuveDejaExistantException(String nomEpreuve, char sexe) {
        super("L'épreuve " + nomEpreuve + " " + sexe + " existe déjà");
    }

}
