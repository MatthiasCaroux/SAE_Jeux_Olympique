import java.util.ArrayList;
import java.util.List;

public abstract class Sport{
    private String nomSport;
    private boolean estCollectif;
    private List<Athlete> lesParticipants;
    private List<Equipe> lesEquipesParticipantes;

    public Sport(String nomSport, boolean estCollectif) {
        this.nomSport = nomSport;
        this.estCollectif = estCollectif;
        if (estCollectif){
            this.lesParticipants = null;
            this.lesEquipesParticipantes = new ArrayList<>();
        }
        else{
            this.lesParticipants = new ArrayList<>();
            this.lesEquipesParticipantes = null;
        }

    }

    public List<Athlete> getParticipants(){
        return this.lesParticipants;
    }

    public boolean estCollectif(){
        return this.estCollectif;
    }

    public String getNomSport(){
        return this.nomSport;
    }

    public List<Equipe> getEquipesParticipantes(){
        return this.lesEquipesParticipantes;
    }

    public abstract void jouer(); 
}
