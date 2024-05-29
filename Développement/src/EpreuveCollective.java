import java.util.ArrayList;

public class EpreuveCollective extends Epreuve{

    private ArrayList<Equipe> equipes = new ArrayList<>();
    private Sport sport;

    public EpreuveCollective(Sport sport, char sexe) {
        super(sport, sexe);
        this.equipes = new ArrayList<>();
    }

    public ArrayList<Equipe> getEquipes() {
        return this.equipes;
    }

    @Override
    public Participant getVainqueur() {
        int scoreMax = 0;
        Equipe equipeGagnante = this.equipes.get(0);
        for (Equipe equipe : equipes) {
            int scoreEquipe = equipe.getAgilité() + equipe.getEndurance();
            if (scoreEquipe > scoreMax) {
                scoreMax = scoreEquipe;
                equipeGagnante = equipe;
            }
        }
        return equipeGagnante;
    }


    @Override
    public Pays getPaysVainqueur() {
        return getVainqueur().getPays();
    }

    @Override
    public void participer(Participant participant) {
        if(participant instanceof Equipe){
            this.equipes.add((Equipe) participant);
        }
    }
    
}