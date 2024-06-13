import java.util.HashMap;
import java.util.Map;

public class Pays {
    
    private String nomPays;
    private int scoreTotal;
    private Map<String, Integer> medailles;

    public Pays(String nomPays) {
        this.nomPays = nomPays;
        this.scoreTotal = 0;
        this.medailles = new HashMap<>();
        this.medailles.put("Or", 0);
        this.medailles.put("Argent", 0);
        this.medailles.put("Bronze", 0);
    }

    public String getNomPays() {
        return this.nomPays;
    }

    public int getScoreTotal() {
        return this.scoreTotal;
    }

    @Override
    public String toString() {
        return this.nomPays;
    }

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

    public Map<String, Integer> getMedailles() {
        return this.medailles;
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
