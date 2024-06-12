public class ParticipantDejaPresentException extends Exception {

    /**
     * Constructeur de la classe ParticipantDejaPresentException
     * @param participant le participant qui est déjà présent dans l'épreuve
     */
    public ParticipantDejaPresentException(Participant participant) {
        super("Le participant "+ participant + " est déjà présent dans l'épreuve");
    }
}
