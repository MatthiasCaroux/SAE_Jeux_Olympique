package src.modele;

/**
 * Classe représentant un athlète
 */
public class Athlete implements Comparable<Athlete>, Participant {

    /**
     * le nom de l'athlète
     */
    private String nom;
    /**
     * le prénom de l'athlète
     */
    private String prenom;
    /**
     * le sexe de l'athlète
     */
    private Epreuve.Sexe sexe;
    /**
     * le pays de l'athlète
     */
    private Pays pays;
    /**
     * la force de l'athlète
     */
    private int force;
    /**
     * l'endurance de l'athlète
     */
    private int endurance;
    /**
     * l'agilité de l'athlète
     */
    private int agilite;
    /**
     * le score de l'athlète
     */
    private int score;

    /**
     * Constructeur de la classe Athlete
     * @param nom le nom de l'athlète
     * @param prenom le prénom de l'athlète
     * @param sexe le sexe de l'athlète
     * @param pays le pays de l'athlète
     * @param force la force de l'athlète
     * @param endurance l'endurance de l'athlète
     * @param agilite l'agilité de l'athlète
     */
    public Athlete(String nom, String prenom, Epreuve.Sexe sexe, Pays pays, int force, int endurance, int agilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.pays = pays;
        this.force = force;
        this.endurance = endurance;
        this.agilite = agilite;
        this.score = 0;
    }
    
    /**
     * Méthode permettant de modifier le nom de l'athlète
     * @param nom le nom de l'athlète
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant d'obtenir le nom de l'athlète
     * @return le nom de l'athlète
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode permettant de modifier le prénom de l'athlète
     * @param prenom le prénom de l'athlète
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Méthode permettant d'obtenir le prénom de l'athlète
     * @return le prénom de l'athlète
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Méthode permettant d'obtenir la force de l'athlète
     * @return la force de l'athlète
     */
    @Override
    public int getForce() {
        return this.force;
    }

    /**
     * Méthode permettant d'obtenir l'endurance de l'athlète
     * @return l'endurance de l'athlète
     */
    @Override
    public int getEndurance() {
        return this.endurance;
    }

    /**
     * Méthode permettant d'obtenir l'agilité de l'athlète
     * @return l'agilité de l'athlète
     */
    @Override
    public int getAgilité() {
        return this.agilite;
    }

    /**
     * Méthode permettant d'obtenir le score de l'athlète
     * @return le score de l'athlète
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Méthode permettant de modifier le score de l'athlète
     * @param score le score de l'athlète
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Méthode permettant d'ajouter un score à l'athlète
     * @param score le score à ajouter
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Méthode permettant de modifier le pays de l'athlète
     * @param pays le pays de l'athlète
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * Méthode permettant d'obtenir le pays de l'athlète
     * @return le pays de l'athlète
     */
    @Override
    public Pays getPays() {
        return this.pays;
    }

    /**
     * Méthode permettant d'obtenir le sexe de l'athlète
     * @return le sexe de l'athlète
     */
    public Epreuve.Sexe getSexe() {
        return this.sexe;
    }

    /**
     * Méthode permettant de comparer deux athlètes
     * @param a l'athlète à comparer
     * @return un entier négatif si l'athlète a un score inférieur, un entier positif si l'athlète a un score supérieur, 0 si les deux athlètes ont le même score
     */
    @Override
    public int compareTo(Athlete a) {
        return this.score - a.getScore();
    }

    /**
     * Méthode permettant d'obtenir une représentation textuelle de l'athlète
     * @return une chaîne de caractères représentant l'athlète
     */
    @Override
    public String toString() {
        return this.nom + " " + this.prenom;
    }
}