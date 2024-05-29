import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Sport{

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

    private TypeSport nomSport;
    private List<Participant> lesParticipants;
    private static Map<String, Integer> lesCoefs;

    public Sport(TypeSport nomSport) {
        this.nomSport = nomSport;
        this.lesParticipants = new ArrayList<>();
        this.lesCoefs = new HashMap<>();
        lesCoefs.put("ATHLETISME", 1);
        lesCoefs.put("NATATION", 2);
        lesCoefs.put("NATATION_RELAI", 3);
        lesCoefs.put("GYMNASTIQUE", 4);
        lesCoefs.put("FOOTBALL", 5);
        lesCoefs.put("BASKETBALL", 6);
        lesCoefs.put("HANDBALL", 7);
        lesCoefs.put("VOLLEYBALL", 8);
        lesCoefs.put("ESCRIME", 9);
        


    }


    public List<Participant> getParticipants(){
        return this.lesParticipants;
    }

    public Map<String, Integer> getLesCoefs(){
        return this.lesCoefs;
    }

    public TypeSport getNomSport(){
        return this.nomSport;
    }

    
}
