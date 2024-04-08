import java.util.List;
import java.util.ArrayList;

public class Equipe {
    private String nomEquipe;
    private int nbAthlètes;
    private String nationalité;
    private int forceEquipe;
    private int agilitéEquipe;
    private int enduranceEquipe;
    private List<Athlete> lesAthlètes;

    public Equipe(int forceEquipe, int agilitéEquipe, int enduranceEquipe){
        this.forceEquipe = forceEquipe;
        this.agilitéEquipe = agilitéEquipe;
        this.enduranceEquipe = enduranceEquipe;
        this.lesAthlètes = new ArrayList<>();
    }

    public void ajouteAthlète(Athlete a){
        this.lesAthlètes.add(a);
    }
    
    // public void ParticiperEquipe(EpreuveCollective epreuveCollective){

    // }
}