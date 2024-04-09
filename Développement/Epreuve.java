import java.util.ArrayList;
import java.util.List;

public abstract class Epreuve{
    private char sexe;
    private Sport sport;
    

    public Epreuve(Sport sport, char sexe){
        this.sport = sport;
        this.sexe = sexe;
        
    }

    public void setSport(){
        this.sport = sport;
    }

    public void setSexe(){
        this.sexe = sexe;
    }

    public Sport getSport(){
        return this.sport;
    }

    public char getSexe(){
        return this.sexe;
    }

    public abstract void jouer();

}


