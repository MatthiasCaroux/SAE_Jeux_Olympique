import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class EpreuveIndividuelle extends Epreuve{
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
        this.getSport().getParticipants().add(athlete);
    }

    @Override
    public void jouer() {
        this.getSport().jouer();
        // this.getClassement();
    }

    public List<Athlete> getClassement() {
        List<Athlete> copy = new ArrayList<>(this.participants);
        Collections.sort(copy);
        Collections.reverse(copy);
        return copy;
    }

    @Override
    public Athlete getVainqueur() {
        return Collections.max(this.participants);
    }

    @Override
    public Pays getPaysVainqueur() {
        return this.getVainqueur().getPays();
    }

    @Override
    public abstract Equipe getEquipeVainqueur();
}
