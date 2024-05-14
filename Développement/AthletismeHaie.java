public class AthletismeHaie extends Athletisme{

    public AthletismeHaie() {
        super("Athlétisme Haie", false);
    }

    @Override
    public void jouer() {
        for (Athlete athlete : this.getAthletes()) {
            double score = athlete.getAgilite() * 0.30 + athlete.getEndurance() * 0.05 + athlete.getForce() * 0.65;
            athlete.setScore((int) score);
        }
    }
}
