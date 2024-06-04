import java.util.ArrayList;

public class EpreuveIndividuelle extends Epreuve {

    private ArrayList<Athlete> athletes = new ArrayList<>();

    public EpreuveIndividuelle(TypeSport sport, Sexe sexe) {
        super(sport, sexe);
        this.athletes = new ArrayList<>();
    }

    @Override
    public Participant getVainqueur() {
        // int scoreMax = 0;
        // Athlete athleteGagnant = this.athletes.get(0);
        // for (Athlete athlete : this.athletes) {
        //     int scoreAthlete = // mettre ici le calcul du score de l'athlete
        //     if (scoreAthlete > scoreMax) {
        //         scoreMax = scoreAthlete;
        //         athleteGagnant = athlete;
        //     }
        // }
        // return athleteGagnant;
        return null;
    }


    @Override
    public Pays getPaysVainqueur() {
        return getVainqueur().getPays();
    }

    @Override
    public void participer(Participant participant) {
        if (participant instanceof Athlete) {
            this.athletes.add((Athlete) participant);
        }
    }
}
