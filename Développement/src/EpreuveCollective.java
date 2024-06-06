public class EpreuveCollective extends Epreuve{

    public EpreuveCollective(TypeSport sport, Sexe sexe) {
        super(sport, sexe);
    }

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
            case NatationRelais:
                if (equipe.getNbAthlètes() == 4) {
                    return true;
                }
                return false;
            case AthlétismeRelais:
                if (equipe.getNbAthlètes() == 4) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}