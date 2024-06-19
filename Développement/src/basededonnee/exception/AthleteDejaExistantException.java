package src.basededonnee.exception;

public class AthleteDejaExistantException extends Exception {

    public AthleteDejaExistantException(String nomAthlete, String prenomAthlete) {
        super("L'athlète " + nomAthlete + " " + prenomAthlete + " existe déjà");
    }

}
