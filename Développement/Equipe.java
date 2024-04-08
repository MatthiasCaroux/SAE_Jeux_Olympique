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

    public Equipe(String nomEquipe, int nbAthlètes, String nationalité, int forceEquipe, int agilitéEquipe, int enduranceEquipe){
        this.nomEquipe = nomEquipe;
        this.nbAthlètes = nbAthlètes;
        this.nationalité = nationalité;
        this.forceEquipe = forceEquipe;
        this.agilitéEquipe = agilitéEquipe;
        this.enduranceEquipe = enduranceEquipe;
        this.lesAthlètes = new ArrayList<>();
    }

    public void ajouteAthlète(Athlete a){
        this.lesAthlètes.add(a);
    }

    public String getNomEquipe(){
        return this.nomEquipe;
    }

    public int getNbAthlètes(){
        return this.nbAthlètes;
    }

    public String getNationalité() {
        return this.nationalité;
    }

    public int getForceEquipe() {
        return this.forceEquipe;
    }

    public int getAgilitéEquipe() {
        return this.agilitéEquipe;
    }

    public int getEnduranceEquipe() {
        return this.enduranceEquipe;
    }

    public List<Athlete> getLesAthlètes() {
        return this.lesAthlètes;
    }

    
    // public void ParticiperEquipe(EpreuveCollective epreuveCollective){

    // }
}