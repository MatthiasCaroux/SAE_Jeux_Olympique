public abstract class Epreuve{
    

    public enum Sport{
        ATHLETISME, 
        NATATION,
        NATATION_RELAI,
        GYMNASTIQUE, 
        FOOTBALL, 
        ASKETBALL, 
        HANDBALL, 
        VOLLEYBALL
    }


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

    public abstract Equipe getEquipeVainqueur();

    public abstract Pays getPaysVainqueur();

    public abstract void participer(Participant participant);

}


