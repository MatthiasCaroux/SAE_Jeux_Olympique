public class ParticipantDejaPresentException extends Exception {

    public ParticipantDejaPresentException(Participant participant) {
        super("Le participant "+ participant + " est déjà présent dans l'épreuve");
    }
}
