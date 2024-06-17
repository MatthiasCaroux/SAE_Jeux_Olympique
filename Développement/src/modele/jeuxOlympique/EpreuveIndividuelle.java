package src.modele.jeuxOlympique;
import src.modele.exceptions.*;

/**
 * Classe EpreuveIndividuelle, héritant de la classe Epreuve
 */
public class EpreuveIndividuelle extends Epreuve {

    /**
     * Constructeur de la classe EpreuveIndividuelle
     * @param sport le sport de l'épreuve
     * @param sexe le sexe de l'épreuve
     */
    public EpreuveIndividuelle(TypeSport sport, Sexe sexe) {
        super(sport, sexe);
    }

    /**
     * Méthode permettant de faire participer un athlète à une épreuve individuelle
     */
    @Override
    public void participer(Participant participant) throws ParticipantDejaPresentException {
        if (participant instanceof Athlete) {
            if (this.participants.contains(participant)) {
                throw new ParticipantDejaPresentException(participant);
            }
            this.participants.add((Athlete) participant);
        }
    }
}
