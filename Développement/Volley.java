import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Volley extends Sport {
    

    public Volley() {
        super("Volley", true);
    }

    public void ajouteAthlete(Athlete athlete, Epreuve epreuve) {
        // a modif
    }

    @Override
    public List<Athlete> getAthletes() {
        return null;
    }

    @Override
    public void classement() {
        Collections.sort(getAthletes());
    }

    @Override
    public Pays getPaysVainqueur() {
        return null; // a modif
    }
}
