import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sport{

    private String nomSport;
    private boolean estCollectif;
    private List<Participant> lesParticipants;
    private static Map<String, Integer> lesCoefs;

    public Sport(String nomSport, boolean estCollectif) {
        this.nomSport = nomSport;
        this.estCollectif = estCollectif;
        this.lesParticipants = new ArrayList<>();
        this.lesCoefs = new HashMap<>();
        lesCoefs.put("ATHLETISME", 1);
        lesCoefs.put("NATATION", 2);
        lesCoefs.put("NATATION_RELAI", 3);
        lesCoefs.put("GYMNASTIQUE", 4);
        lesCoefs.put("FOOTBALL", 5);
        lesCoefs.put("BASKETBALL", 6);
        lesCoefs.put("HANDBALL", 7);

    }


    public List<Participant> getParticipants(){
        return this.lesParticipants;
    }

    public Map<String, Integer> getLesCoefs(){
        return this.lesCoefs;
    }

    public boolean estCollectif(){
        return this.estCollectif;
    }

    public String getNomSport(){
        return this.nomSport;
    }



    public enum TypeSport{
        ATHLETISME, 
        NATATION,
        NATATION_RELAI,
        GYMNASTIQUE, 
        FOOTBALL, 
        BASKETBALL, 
        HANDBALL, 
        VOLLEYBALL
    }
}
