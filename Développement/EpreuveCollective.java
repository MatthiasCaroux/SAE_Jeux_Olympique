import java.util.ArrayList;
import java.util.List;

public class EpreuveCollective extends Epreuve{
    private List<Equipe> participants;

    public EpreuveCollective(Sport sport, char sexe) {
        super(sport, sexe);
        this.participants = new ArrayList<>();
    }

    public void ajouterParticipant(Equipe equipe) {
        this.participants.add(equipe);
    }

    @Override
    public void jouer() {
        
    }
    
}
