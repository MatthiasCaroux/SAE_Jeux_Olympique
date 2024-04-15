import java.util.List;

public class Volley extends Sport{
    

    public Volley() {
        super("Volley", true);
    }

    public void ajouteAthlete(Athlete athlete, Epreuve epreuve) {
        
    }


    @Override
    public List<Athlete> getAthletes() {
        return null;
    }


    @Override
    public void classement() {
        // a modif
    }

    @Override
    public Pays getPaysVainqueur() {
        return null; // a modif
    }

}
