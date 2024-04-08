import java.util.List;

public abstract class Sport {
    private String nomSport;
    private boolean estCollectif;

    public void ajouteAthlete(Athlete athlete, Epreuve epreuve) {

    }

    public Pays getPaysVainqueur(){
        return null;
    }

    public List<Athlete> getAthletes(){
        return null;
    }

    public List<Epreuve> getEpreuves(){
        return null;
    }

    public void classement(){
        
    }

    public Athlete getVainqueur(Epreuve epreuve){
        return null;
    }
    
}
