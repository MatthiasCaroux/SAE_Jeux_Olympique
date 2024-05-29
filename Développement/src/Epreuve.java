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
}


