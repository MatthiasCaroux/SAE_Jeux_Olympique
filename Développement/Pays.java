public class Pays {
    
    private String nomPays;
    private int scoreTotal;

    public Pays(String nomPays) {
        this.nomPays = nomPays;
        this.scoreTotal = 0;
    }

    public String getNomPays() {
        return this.nomPays;
    }

    public int getScoreTotal() {
        return this.scoreTotal;
    }
}
