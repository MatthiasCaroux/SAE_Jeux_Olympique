public abstract class Epreuve{
    


    private Sport sport;
    private char sexe;
    

    public Epreuve(Sport sport, char sexe){
        this.sport = sport;
        this.sexe = sexe;
    }

    public void setSport(Sport sport){
        this.sport = sport;
    }


    public Sport getSport(){
        return this.sport;
    }

    public abstract Participant getVainqueur();

    public abstract Pays getPaysVainqueur();

    public abstract void participer(Participant participant);
}


