import java.util.ArrayList;
import java.util.List;

public class JeuxOlympique {
    private int annee;
    private String lieu;
    private String nomJO;
    private List<Epreuve> epreuves;

    public JeuxOlympique(int annee, String lieu, String nomJO) {
        this.annee = annee;
        this.lieu = lieu;
        this.nomJO = nomJO;
        this.epreuves = new ArrayList<>();
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNomJO() {
        return nomJO;
    }

    public void setNomJO(String nomJO) {
        this.nomJO = nomJO;
    }

    public void ajouteEpreuve(Epreuve epreuve) {
        this.epreuves.add(epreuve);
    }

    public Pays vainqueurEpreuve(Epreuve epreuve) {
        return null;
    }

    public Pays vainqueurJeuxOlympique() {
        return null;
    }

    public int getScore(Pays pays){
        return 0;
    }

    public void lancerLesJO(){
        
    }


}
