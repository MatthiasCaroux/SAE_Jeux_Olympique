public class EpreuveCollective extends Epreuve{

    /**
     * Constructeur de la classe EpreuveCollective
     * @param sport le sport de l'épreuve
     * @param sexe le sexe de l'épreuve
     */
    public EpreuveCollective(TypeSport sport, Sexe sexe) {
        super(sport, sexe);
    }

    /**
     * Méthode permettant de faire participer une équipe à une épreuve collective
     */
    @Override
    public void participer(Participant participant) throws TropDeJoueursException, PasAssezDeJoueursException, PasUneEquipeException, EquipeDejaParticipanteException {
        if(participant instanceof Equipe) {
            Equipe equipe = (Equipe) participant;
            if (this.participants.contains(participant)) {
                throw new EquipeDejaParticipanteException();
            }
            switch (this.getSport()) {
                case Handball:
                    if (equipe.getNbAthlètes() > 7) {
                        throw new TropDeJoueursException(7);
                    } else if (equipe.getNbAthlètes() < 7) {
                        throw new PasAssezDeJoueursException(7);
                    } else {
                        this.participants.add(equipe);
                    }
                    break;
                case Volley:
                    if (equipe.getNbAthlètes() > 6) {
                        throw new TropDeJoueursException(6);
                    } else if (equipe.getNbAthlètes() < 6) {
                        throw new PasAssezDeJoueursException(6);
                    } else {
                        this.participants.add(equipe);
                    }
                    break;
                case NatationRelais:
                    if (equipe.getNbAthlètes() > 4) {
                        throw new TropDeJoueursException(4);
                    } else if (equipe.getNbAthlètes() < 4) {
                        throw new PasAssezDeJoueursException(4);
                    } else {
                        this.participants.add(equipe);
                    }
                    break;
                case AthlétismeRelais:
                    if (equipe.getNbAthlètes() > 4) {
                        throw new TropDeJoueursException(4);
                    } else if (equipe.getNbAthlètes() < 4) {
                        throw new PasAssezDeJoueursException(4);
                    } else {
                        this.participants.add(equipe);
                    }
                    break;
                default:
                    break;
            }
        } else {
            throw new PasUneEquipeException();
        }
    }
}