public abstract class Epreuve{
    private char sexe;
    private Sport sport;
    

    public Epreuve(Sport sport, char sexe){
        this.sport = sport;
        this.sexe = sexe;
        
    }

    public void setSport(Sport sport){
        this.sport = sport;
    }

    public void setSexe(char sexe){
        this.sexe = sexe;
    }

    public Sport getSport(){
        return this.sport;
    }

    public char getSexe(){
        return this.sexe;
    }

    public abstract Athlete getVainqueur();

    public abstract Pays getPaysVainqueur();

    public abstract void jouer();

    protected abstract Object getNom();

    protected abstract void add(Athlete athlete);

}


