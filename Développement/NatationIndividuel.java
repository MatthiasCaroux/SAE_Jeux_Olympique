public class NatationIndividuel extends Natation{

    public NatationIndividuel(String nomSport, boolean estCollectif) {
        super("NatationIndividuel", false);
    }

    public Pays getPaysVainqueur(){
        return null; // a modif
    }

    public void classement(){
        // a modif
    }

    @Override
    public void jouer(){
        double score = 0;
        for (Athlete athlete : this.getAthletes()) {
            score = athlete.getAgilite() * 0.5 + athlete.getEndurance() * 0.35 + athlete.getForce() * 0.15;
            athlete.setScore((int) score);
        }
    }
}
