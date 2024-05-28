public class Athlete implements Comparable<Athlete>, Participant {

    private String nom;
    private String prenom;
    private char sexe;
    private Pays pays;
    private int force;
    private int endurance;
    private int agilite;
    private int score;

    public Athlete(String nom, String prenom, char sexe, Pays pays, int force, int endurance, int agilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.pays = pays;
        this.force = force;
        this.endurance = endurance;
        this.agilite = agilite;
        this.score = 0;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public int getForce() {
        return this.force;
    }

    public int getEndurance() {
        return this.endurance;
    }

    public int getAgilite() {
        return this.agilite;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Pays getPays() {
        return this.pays;
    }

    public char getSexe() {
        return this.sexe;
    }

    @Override
    public int compareTo(Athlete a) {
        return this.score - a.getScore();
    }

    @Override
    public String toString() {
        return this.nom + " " + this.prenom;
    }

    @Override
    public int getAgilité() {
        return this.agilite;
    }
}