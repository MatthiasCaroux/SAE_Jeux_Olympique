public class Epee extends Escrime{

    public Epee() {
        super("Escrime à l'épée", false);
    }

    @Override
    public void jouer() {
        for (Athlete athlete : this.getAthletes()) {
            double score = athlete.getAgilite() * 0.20 + athlete.getEndurance() * 0.40 + athlete.getForce() * 0.40;
            athlete.setScore((int) score);
        }
    }
}
