package src.modele.jeuxOlympique;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant un pays
 */
public class Pays {
    
    /**
     * le nom du pays
     */
    private String nomPays;
    /**
     * le score total du pays
     */
    private int scoreTotal;
    /**
     * le nombre de médailles du pays
     */
    private Map<String, Integer> medailles;

    /**
     * Constructeur de la classe Pays
     * @param nomPays le nom du pays
     */
    public Pays(String nomPays) {
        this.nomPays = nomPays;
        this.scoreTotal = 0;
        this.medailles = new HashMap<>();
        this.medailles.put("Or", 0);
        this.medailles.put("Argent", 0);
        this.medailles.put("Bronze", 0);
    }

    public Pays(String nomPays, Map<String, Integer> medailles) {
        this.nomPays = nomPays;
        this.medailles = medailles;
        this.scoreTotal = this.getScoreTotal();
    }

    /**
     * Méthode permettant de récupérer le nom du pays 
     * @return le nom du pays
     */
    public String getNomPays() {
        return this.nomPays;
    }

    /**
     * Méthode permettant de récupérer le score total du pays
     * @return le score total du pays
     */
    public int getScoreTotal() {
    return this.getMedaille("Or") * 3 + this.getMedaille("Argent") * 2 + this.getMedaille("Bronze");
    }

    /**
     * Méthode permettant de modifier le nom du pays
     * @return une chaîne de caractères représentant le pays
     */
    @Override
    public String toString() {
        return this.nomPays;
    }

    public void setMedailles(Map<String, Integer> medailles) {
        this.medailles = medailles;
    }

    /**
     * Méthode permettant d'ajouter une médaille au pays
     * @param typeMedaille le type de médaille
     */
    public void ajouterMedaille(String typeMedaille) {
        this.medailles.put(typeMedaille, this.medailles.get(typeMedaille) + 1);
        if (typeMedaille.equals("Or")) {
            this.scoreTotal += 3;
        } else if (typeMedaille.equals("Argent")) {
            this.scoreTotal += 2;
        } else {
            this.scoreTotal += 1;
        }
    }

    /**
     * Méthode permettant de récupérer les médailles du pays
     * @return les médailles du pays
     */
    public Map<String, Integer> getMedailles() {
        return this.medailles;
    }

    public int getMedaille(String typeMedaille) {
        return this.medailles.get(typeMedaille);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pays) {
            Pays pays = (Pays) obj;
            return this.nomPays.equals(pays.getNomPays());
        }
        return false;
    }
}
