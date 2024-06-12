import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Epreuve{

    public enum TypeSport {
        NatationBrasse(),
        NatationRelais(4),
        Handball(7),
        Volley(6),
        Escrimefleuret(),
        EscrimeÉpée(),
        AthlétismeHaie(),
        AthlétismeRelais(4);

        private final int nbParticipantNecessaire;

        TypeSport(int nbParticipantNecessaire) {
            this.nbParticipantNecessaire = nbParticipantNecessaire;
        }

        TypeSport() {
            this.nbParticipantNecessaire = 1;
        }

        public int getNbParticipantNecessaire() {
            return this.nbParticipantNecessaire;
        }
    }

    public enum Sexe {
        M {
            public String toString() {
                return "Masculin";
            }
        },
        F {
            public String toString() {
                return "Féminin";
            }
        }
    }

    protected TypeSport sport;
    protected Sexe sexe;
    protected List<Participant> participants;
    protected Map<Participant, Double> classement;
    

    public Epreuve(TypeSport sport, Sexe sexe){
        this.sport = sport;
        this.sexe = sexe;
        this.participants = new ArrayList<>();
        this.classement = null;
    }

    public List<Participant> getParticipants(){
        return this.participants;
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

    public Map<Participant, Double> getClassement() throws EpreuveNonCommenceeException{
        if (this.classement == null) {
            throw new EpreuveNonCommenceeException(this);
        }
        return this.classement;
    }

    public void jouerEpreuve() throws EpreuveDejaJoueeException {
        if (this.classement == null) {
            this.classement = new HashMap<>();
            for (Participant participant : this.getParticipants()) {
                this.classement.put(participant, this.calculeScore(participant));
            }
            this.majClassement();
        } else {
            throw new EpreuveDejaJoueeException(this);
        }
    }

    public void majClassement() {
        Collections.sort(this.getParticipants(), new ParticipantComparator(this.classement));
        for (int i = 0; i < this.getParticipants().size(); ++i) {
            if (i == 0) {
                this.getParticipants().get(i).getPays().ajouterMedaille("Or");
            } else if (i == 1) {
                this.getParticipants().get(i).getPays().ajouterMedaille("Argent");
            } else if (i == 2) {
                this.getParticipants().get(i).getPays().ajouterMedaille("Bronze");
            }
        }
    }

    public Participant getVainqueur() throws EpreuveNonCommenceeException {
        if (this.classement == null) {
            throw new EpreuveNonCommenceeException(this);
        }
        return Collections.max(this.classement.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public Pays getPaysVainqueur() throws EpreuveNonCommenceeException {
        return this.getVainqueur().getPays();
    }

    public abstract void participer(Participant participant) throws Exception;

    public double calculeScore(Participant participant){
        switch (this.getSport()) {
            case Handball:
                return (participant.getAgilité()*0.3 + participant.getEndurance()*0.4 + participant.getForce()*0.3);
            case Volley:
                return (participant.getAgilité()*0.3 + participant.getEndurance()*0.5 + participant.getForce()*0.2);
            case NatationBrasse:
                return (participant.getAgilité()*0.1 + participant.getEndurance()*0.6 + participant.getForce()*0.3);
            case NatationRelais:
                return (participant.getAgilité()*0.1 + participant.getEndurance()*0.4 + participant.getForce()*0.5);
            case Escrimefleuret:
                return (participant.getAgilité()*0.4 + participant.getEndurance()*0.2 + participant.getForce()*0.4);
            case EscrimeÉpée:
                return (participant.getAgilité()*0.4 + participant.getEndurance()*0.1 + participant.getForce()*0.5);
            case AthlétismeHaie:
                return (participant.getAgilité()*0.4 + participant.getEndurance()*0.6 + participant.getForce()*0.0);
            case AthlétismeRelais:
                return (participant.getAgilité()*0.4 + participant.getEndurance()*0.6 + participant.getForce()*0.0);
            default:
                return 0;
        }
    }

    public String toString(){
        return this.getSport() + " " + this.getSexe();
    }

    public String rapport() throws EpreuveNonCommenceeException {
        if (this.classement == null) {
            throw new EpreuveNonCommenceeException(this);
        }
        String rapport = "Rapport de l'épreuve " + this + "\n";
        switch (this.getSport()) {
            case Handball:
                rapport += "Nombre de buts marqués par équipe : \n";
                for (Participant participant : this.getParticipants()) {
                    rapport += participant + " : " + Math.round(this.classement.get(participant)%32) + "\n";
                }
                return rapport;
            case Volley:
                rapport += "Nombre de sets gagnés par équipe : \n";
                for (Participant participant : this.getParticipants()) {
                    rapport += participant + " : " + Math.round(this.classement.get(participant)%this.getParticipants().size()) + "\n";
                }
                return rapport;
            case NatationRelais:
                rapport += "Temps de chaque équipe : \n";
                for (Participant participant : this.getParticipants()) {
                    rapport += participant + " : " + (1000 - this.classement.get(participant))/8 + " secondes\n";
                }
                return rapport;
            case AthlétismeRelais:
                rapport += "Temps de chaque équipe : \n";
                for (Participant participant : this.getParticipants()) {
                    rapport += participant + " : " + (1000 - this.classement.get(participant))/8 + " secondes\n";
                }
                return rapport;
            case NatationBrasse:
                rapport += "Temps de chaque nageur : \n";
                for (Participant participant : this.getParticipants()) {
                    rapport += participant + " : " + (1000 - this.classement.get(participant))/10.4 + " secondes\n";
                }
                return rapport;
            case Escrimefleuret:
                rapport += "Nombre de touches par escrimeur : \n";
                for (Participant participant : this.getParticipants()) {
                    rapport += participant + " : " + Math.round(this.classement.get(participant)%15) + "\n";
                }
                return rapport;
            case EscrimeÉpée:
                rapport += "Nombre de touches par escrimeur : \n";
                for (Participant participant : this.getParticipants()) {
                    rapport += participant + " : " + Math.round(this.classement.get(participant)%15) + "\n";
                }
                return rapport;
            case AthlétismeHaie:
                rapport += "Temps de chaque coureur : \n";
                for (Participant participant : this.getParticipants()) {
                    rapport += participant + " : " + (1000 - this.classement.get(participant))/16.7 + " secondes\n";
                }
                return rapport;
            default:
                return "Pas de rapport disponible pour cette épreuve";
        }
    }
}


