import java.util.ArrayList;
import java.util.List;

public abstract class Sport {
    private String nomSport;
    private boolean estCollectif;
    private List<Athlete> lesAthletes;

    public Sport(String nomSport, boolean estCollectif) {
        this.nomSport = nomSport;
        this.estCollectif = estCollectif;
        this.lesAthletes = new ArrayList<>();
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

    public abstract void jouer(); 
}
