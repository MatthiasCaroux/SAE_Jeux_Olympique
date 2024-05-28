import java.util.ArrayList;

public class EpreuveIndividuelle extends Epreuve {

    private ArrayList<Athlete> athletes = new ArrayList<>();

    public EpreuveIndividuelle(Sport sport, char sexe) {
        super(sport, sexe);
        this.athletes = new ArrayList<>();
    }

    @Override
    public Participant getVainqueur() {
        return null;
    }


    @Override
    public Pays getPaysVainqueur() {
        return null;
    }

    @Override
    public void participer(Participant participant) {
        if (participant instanceof Athlete) {
            this.athletes.add((Athlete) participant);
        }
    }
}
