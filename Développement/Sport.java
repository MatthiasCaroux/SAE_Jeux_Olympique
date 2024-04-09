import java.util.List;

public abstract class Sport {
    private String nomSport;
    private boolean estCollectif;
    private List<Athlete> lesAthletes;

    public Sport(String nomSport, boolean estCollectif) {
        this.nomSport = nomSport;
        this.estCollectif = estCollectif;
    }


    public Pays getPaysVainqueur(){
        return null;
    }

    public List<Athlete> getAthletes(){
        return this.lesAthletes;
    }

    public boolean estCollectif(){
        return this.estCollectif;
    }

    public String getNomSport(){
        return this.nomSport;
    }



    public void classement(){
        
    }

    
}
