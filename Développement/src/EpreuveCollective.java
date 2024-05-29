import java.util.ArrayList;

public class EpreuveCollective extends Epreuve{

    private ArrayList<Equipe> equipes = new ArrayList<>();

    public EpreuveCollective(TypeSport sport, Sexe sexe) {
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
    public void participer(Participant participant) throws PasViableException{
        if(participant instanceof Equipe) {
            if (this.estViable((Equipe) participant)) {
                this.equipes.add((Equipe) participant);
            }
            else{
                throw new PasViableException();
            }
        }
    }

    public boolean estViable(Equipe equipe){
        switch (this.getSport()) {
            case Handball:
                if (equipe.getNbAthlètes() == 7) {
                    return true;
                }
                return false;
            case Volley:
                if (equipe.getNbAthlètes() == 6) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}