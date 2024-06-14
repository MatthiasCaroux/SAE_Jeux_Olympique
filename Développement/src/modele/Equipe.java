package src.modele;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe représentant une équipe
 */
public class Equipe implements Comparable<Equipe>, Participant {
    /**
     * le nom de l'équipe
     */
    private String nomEquipe;
    /**
     * la nationalité de l'équipe
     */
    private Pays nationalité;
    /**
     * la liste des athlètes de l'équipe
     */
    private List<Athlete> lesAthlètes;
    /**
     * le score de l'équipe
     */
    private int score;
    /**
     * le sexe de l'équipe
     */
    private Epreuve.Sexe sexe;

    /**
     * Constructeur de la classe Equipe
     * @param nomEquipe le nom de l'équipe
     * @param pays la nationalité de l'équipe
     * @param sexe le sexe de l'équipe
     */
    public Equipe(String nomEquipe, Pays pays, Epreuve.Sexe sexe){
        this.lesAthlètes = new ArrayList<>();
        this.nomEquipe = nomEquipe;
        this.score = 0;
        this.nationalité = pays;
        this.sexe = sexe;
    }

    /**
     * Méthode permettant de récupérer le pays de l'équipe
     */
    @Override
    public Pays getPays(){
        return this.nationalité;
    }

    /**
     * Méthode permettant de récupérer la liste des athlètes de l'équipe
     */
    public List<Athlete> getLesAthlètes() {
        return this.lesAthlètes;
    }

    /**
     * Méthode permettant de récupérer la force de l'équipe
     */
    @Override
    public int getForce() {
        int forceEquipe = 0;
        for (Athlete athlete : lesAthlètes) {
            forceEquipe += athlete.getForce();
        }
        return forceEquipe;
    }

    /**
     * Méthode permettant de récupérer l'endurance de l'équipe
     */
    @Override
    public int getEndurance() {
        int enduranceEquipe = 0;
        for (Athlete athlete : lesAthlètes) {
            enduranceEquipe += athlete.getEndurance();
        }
        return enduranceEquipe;
    }

    /**
     * Méthode permettant de récupérer l'agilité de l'équipe
     */
    @Override
    public int getAgilité() {
        int agilitéEquipe = 0;
        for (Athlete athlete : lesAthlètes) {
            agilitéEquipe += athlete.getAgilité();
        }
        return agilitéEquipe;
    }
    
    /**
     * Methode permettant d'ajouter un athlète à l'équipe
     * @param a l'athlète à ajouter
     */
    public void ajouterMembre(Athlete a){
        if (a.getPays().equals(this.nationalité)){
            if (a.getSexe() == this.sexe){
                this.lesAthlètes.add(a);
            }
            else{
                System.out.println("L'athlète " + a +" n'a pas le même sexe que l'équipe");
            }
        }
        else{
            System.out.println("L'athlète " + a +" n'a pas la même nationalité que l'équipe");
        }
    }

    /**
     * Méthode permettant de récupérer le nom de l'équipe
     * @return le nom de l'équipe
     */
    public String getNomEquipe(){
        return this.nomEquipe;
    }

    /**
     * Méthode permettant de récupérer le nombre d'athlètes de l'équipe
     * @return le nombre d'athlètes de l'équipe
     */
    public int getNbAthlètes(){
        return this.lesAthlètes.size();
    }

    /**
     * Méthode permettant de récupérer la nationalité de l'équipe
     * @return la nationalité de l'équipe
     */
    public Pays getNationalité() {
        return this.nationalité;
    }

    /**
     * Méthode permettant de récupérer le sexe de l'équipe
     * @return le sexe de l'équipe
     */
    public Epreuve.Sexe getSexeEquipe() {
        return this.sexe;
    }
    
    /**
     * Méthode permettant de modifier le score de l'équipe
     * @param score le score de l'équipe
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * Méthode permettant de comparer deux équipes
     * @param o l'équipe à comparer
     * @return un entier négatif si l'équipe a un score inférieur, un entier positif si l'équipe a un score supérieur, 0 si les deux équipes ont le même score
     */
    @Override
    public int compareTo(Equipe o) {
        return this.score - o.score;
    }

    /**
     * Méthode permettant d'obtenir une représentation textuelle de l'équipe
     * @return une chaîne de caractères représentant l'équipe
     */
    @Override
    public String toString() {
        return this.nomEquipe;
    }

    /**
     * Méthode permettant de supprimer un athlète de l'équipe
     * @param a l'athlète à supprimer
     */
    public void removeAthlete(Athlete a){
        this.lesAthlètes.remove(a);
    }

    /**
     * Méthode permettant de supprimer un athlète de l'équipe
     * @param index l'index de l'athlète à supprimer
     */
    public void removeAthlete(int index){
        this.lesAthlètes.remove(index);
    }
}