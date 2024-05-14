public class AthletismeHaie extends Athletisme{

    public AthletismeHaie() {
        super("Athlétisme Haie", false);
    }

    @Override
    public void jouer() {
        double score;
        for (Athlete athlete : this.getParticipants()) {
            score = athlete.getAgilite() * 0.30 + athlete.getEndurance() * 0.05 + athlete.getForce() * 0.65;
            athlete.setScore((int) score);
        }
    }
}
