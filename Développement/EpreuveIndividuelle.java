import java.util.ArrayList;
import java.util.List;

public class EpreuveIndividuelle extends Epreuve{
    private List<Athlete> participants;

    public EpreuveIndividuelle(Sport sport, char sexe) {
        super(sport, sexe);
        this.participants = new ArrayList<>();
    }

    public List<Athlete> getParticipants() {
        return participants;
    }

    /**
     * Met un athlete dans la liste des participants
     * @param athlete
     */
    public void ajouterParticipant(Athlete athlete) {
        this.participants.add(athlete);
    }

    @Override
    public void jouer() {
        
    }


    
}
