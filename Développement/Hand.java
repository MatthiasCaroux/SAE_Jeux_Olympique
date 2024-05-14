public class Hand extends Sport{

    public Hand() {
        super("Hand-Balle", true);
    }

    @Override // Refaire TODO pour val
    public void jouer() {
        double score = 0;
        for (Equipe equipe : this.getEquipesParticipantes()) {
            score = equipe.getAgilitéEquipe() * 0.30 + equipe.getEnduranceEquipe() * 0.05 + equipe.getForceEquipe() * 0.65;
            equipe.setScore((int) score);
        }
    }
    
}
