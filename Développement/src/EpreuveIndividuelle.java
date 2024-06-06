public class EpreuveIndividuelle extends Epreuve {

    public EpreuveIndividuelle(TypeSport sport, Sexe sexe) {
        super(sport, sexe);
    }

    @Override
    public void participer(Participant participant) throws ParticipantDejaPresentException {
        if (participant instanceof Athlete) {
            if (this.participants.contains(participant)) {
                throw new ParticipantDejaPresentException(participant);
            }
            this.participants.add((Athlete) participant);
        }
    }
}
