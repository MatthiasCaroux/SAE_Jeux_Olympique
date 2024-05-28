import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class EpreuveCollective extends Epreuve{
    private List<Equipe> participants;

    public EpreuveCollective(Sport sport, char sexe) {
        super(sport, sexe);
        this.participants = new ArrayList<>();
    } 

    public List<Equipe> getParticipants() {
        return participants;
    }

    public void ajouterParticipant(Equipe equipe) {
        this.participants.add(equipe);
        this.getSport().getEquipesParticipantes().add(equipe);
    }

    @Override
    public void jouer() {
        
    }

    @Override
    public Equipe getEquipeVainqueur() {
        return Collections.max(this.participants);
    }

    @Override
    public Pays getPaysVainqueur() {
        return this.getEquipeVainqueur().getNationalité();
    }

    @Override
    public abstract Athlete getVainqueur();
}
