public class Fleuret extends Escrime{

    public Fleuret() {
        super("Fleuret", false);
    }

    @Override
    public void jouer() {
        for (Athlete athlete : this.getParticipants()) {
            double score = athlete.getAgilite() * 0.40 + athlete.getEndurance() * 0.40 + athlete.getForce() * 0.20;
            athlete.setScore((int) score);
        }
    }
}
