import java.util.List;
import java.util.ArrayList;

public class Equipe implements Comparable<Equipe>, Participant {
    private String nomEquipe;
    private Pays nationalité;
    private List<Athlete> lesAthlètes;
    private int score;

    public Equipe(String nomEquipe, Pays pays){
        this.lesAthlètes = new ArrayList<>();
        this.nomEquipe = nomEquipe;
        this.score = 0;
        this.nationalité = pays;
    }

    @Override
    public Pays getPays(){
        return this.nationalité;
    }


    public List<Athlete> getLesAthlètes() {
        return this.lesAthlètes;
    }


    @Override
    public int getForce() {
        int forceEquipe = 0;
        for (Athlete athlete : lesAthlètes) {
            forceEquipe += athlete.getForce();
        }
        return forceEquipe;
    }

    @Override
    public int getEndurance() {
        int enduranceEquipe = 0;
        for (Athlete athlete : lesAthlètes) {
            enduranceEquipe += athlete.getEndurance();
        }
        return enduranceEquipe;
    }

    @Override
    public int getAgilité() {
        int agilitéEquipe = 0;
        for (Athlete athlete : lesAthlètes) {
            agilitéEquipe += athlete.getAgilité();
        }
        return agilitéEquipe;
    }
    

    public void ajouterMembre(Athlete a){
        if (a.getPays().equals(this.nationalité)){
            this.lesAthlètes.add(a);
        }
        else{
            System.out.println("L'athlète" + a +" n'a pas la même nationalité que l'équipe");
        }
    }

    public String getNomEquipe(){
        return this.nomEquipe;
    }

    public int getNbAthlètes(){
        return this.lesAthlètes.size();
    }

    public Pays getNationalité() {
        return this.nationalité;
    }


    public void setScore(int score){
        this.score = score;
    }

    @Override
    public int compareTo(Equipe o) {
        return this.score - o.score;
    }

    @Override
    public String toString() {
        return this.nomEquipe;
    }

    public void removeAthlete(Athlete a){
        this.lesAthlètes.remove(a);
    }

    public void removeAthlete(int index){
        this.lesAthlètes.remove(index);
    }

}