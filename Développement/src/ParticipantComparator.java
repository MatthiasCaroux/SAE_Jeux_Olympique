import java.util.Comparator;
import java.util.Map;

public class ParticipantComparator implements Comparator<Participant> {

    /**
     * le classement des participants
     */
    private Map<Participant, Double> classement;

    /**
     * Constructeur de la classe ParticipantComparator
     * @param classement le classement des participants
     */
    public ParticipantComparator(Map<Participant, Double> classement) {
        this.classement = classement;
    }

    /**
     * Méthode permettant de comparer deux participants
     */
    @Override
    public int compare(Participant p1, Participant p2) {
        if (this.classement.get(p1) > this.classement.get(p2)) {
            return -1;
        } else if (this.classement.get(p1) < this.classement.get(p2)) {
            return 1;
        } else {
            return 0;
        }
        // if (p1 instanceof Equipe && p2 instanceof Equipe) {
        //     Equipe e1 = (Equipe) p1;
        //     Equipe e2 = (Equipe) p2;
        //     return e1.getNom().compareTo(e2.getNom());
        // } else if (p1 instanceof Athlete && p2 instanceof Athlete) {
        //     Athlete a1 = (Athlete) p1;
        //     Athlete a2 = (Athlete) p2;
        //     return a1.getNom().compareTo(a2.getNom());
        // } else if (p1 instanceof Equipe && p2 instanceof Athlete) {
        //     return -1;
        // } else {
        //     return 1;
        // }
    }
}
