import java.util.List;
import java.util.ArrayList;

public class Equipe implements Comparable<Equipe> {
    private String nomEquipe;
    private int nbAthlètes;
    private Pays nationalité;
    private int forceEquipe;
    private int agilitéEquipe;
    private int enduranceEquipe;
    private List<Athlete> lesAthlètes;
    private int score;

    public Equipe(String nomEquipe, int nbAthlètes, Pays pays){
        this.nomEquipe = nomEquipe;
        this.nbAthlètes = nbAthlètes;
        this.nationalité = pays;
        this.lesAthlètes = new ArrayList<>();
        this.score = 0;
    }

    /**
     * Met a jour les attributs de l'équipe en fonction des attributs des athlètes
     */
    public void miseAJourAttributsEquipe(){
        int force = 0;
        int agilité = 0;
        int endurance = 0;
        for (Athlete a : this.lesAthlètes){
            force += a.getForce();
            agilité += a.getAgilite();
            endurance += a.getEndurance();
        }
        this.forceEquipe = force;
        this.agilitéEquipe = agilité;
        this.enduranceEquipe = endurance;
    }

    public void ajouterMembre(Athlete a){
        this.lesAthlètes.add(a);
    }

    public String getNomEquipe(){
        return this.nomEquipe;
    }

    public int getNbAthlètes(){
        return this.nbAthlètes;
    }

    public Pays getNationalité() {
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

    @Override
    public int compareTo(Equipe o) {
        return this.score - o.score;
    }
}