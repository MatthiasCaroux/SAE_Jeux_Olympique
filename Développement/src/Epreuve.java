public abstract class Epreuve{

    public enum TypeSport{
        NatationBrasse,
        NatationRelais,
        Handball,
        Volley,
        Escrimefleuret,
        EscrimeÉpée,
        AthlétismeHaie,
        AthlétismeRelais
    }

    public enum Sexe {
        M,
        F
    }

    private TypeSport sport;
    private Sexe sexe;
    

    public Epreuve(TypeSport sport, Sexe sexe){
        this.sport = sport;
        this.sexe = sexe;
    }

    public void setSport(TypeSport sport){
        this.sport = sport;
    }


    public TypeSport getSport(){
        return this.sport;
    }

    public Sexe getSexe(){
        return this.sexe;
    }

    public abstract Participant getVainqueur();

    public abstract Pays getPaysVainqueur();

    public abstract void participer(Participant participant) throws Exception;

    public double calculeScore(Participant participant){
        switch (this.getSport()) {
            case Handball:
                return (participant.getAgilité()*0.3 + participant.getEndurance()*0.4 + participant.getForce()*0.3);
            case Volley:
                return (participant.getAgilité()*0.3 + participant.getEndurance()*0.5 + participant.getForce()*0.2);
            case NatationBrasse:
                return (participant.getAgilité()*0.1 + participant.getEndurance()*0.6 + participant.getForce()*0.3);
            case NatationRelais:
                return (participant.getAgilité()*0.1 + participant.getEndurance()*0.4 + participant.getForce()*0.5);
            case Escrimefleuret:
                return (participant.getAgilité()*0.4 + participant.getEndurance()*0.2 + participant.getForce()*0.4);
            case EscrimeÉpée:
                return (participant.getAgilité()*0.4 + participant.getEndurance()*0.1 + participant.getForce()*0.5);
            case AthlétismeHaie:
                return (participant.getAgilité()*0.4 + participant.getEndurance()*0.6 + participant.getForce()*0.0);
            case AthlétismeRelais:
                return (participant.getAgilité()*0.4 + participant.getEndurance()*0.6 + participant.getForce()*0.0);
            default:
                return 0;
        }
    }
}


