import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Epreuve{

    /**
     * Enumération des différents types de sport
     */
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

    /**
     * Enumération des sexes
     */
    public enum Sexe {
        M {
            /**
             * Méthode permettant d'obtenir le sexe masculin
             */
            public String toString() {
                return "Masculin";
            }
        },
        F {
            /**
             * Méthode permettant d'obtenir le sexe féminin
             */
            public String toString() {
                return "Féminin";
            }
        }
    }

    /**
     * le type de sport de l'épreuve
     */
    protected TypeSport sport;
    /**
     * le sexe de l'épreuve
     */
    protected Sexe sexe;
    /**
     * la liste des participants
     */
    protected List<Participant> participants;
    /**
     * le classement des participants
     */
    protected Map<Participant, Double> classement;
    
    /**
     * Constructeur de la classe Epreuve
     * @param sport le type de sport de l'épreuve
     * @param sexe le sexe de l'épreuve
     */
    public Epreuve(TypeSport sport, Sexe sexe){
        this.sport = sport;
        this.sexe = sexe;
        this.participants = new ArrayList<>();
        this.classement = null;
    }

    /**
     * Méthode permettant d'obtenir la liste des participants
     * @return
     */
    public List<Participant> getParticipants(){
        return this.participants;
    }

    /**
     * Méthode permettant de modifier le type de sport de l'épreuve
     * @param sport le type de sport de l'épreuve
     */
    public void setSport(TypeSport sport){
        this.sport = sport;
    }

    /**
     * Méthode permettant d'obtenir le type de sport de l'épreuve
     * @return le type de sport de l'épreuve
     */
    public TypeSport getSport(){
        return this.sport;
    }

    /**
     * Méthode permettant d'obtenir le sexe de l'épreuve
     * @return le sexe de l'épreuve
     */
    public Sexe getSexe(){
        return this.sexe;
    }

    /**
     * Méthode permettant d'obtenir le classement des participants
     * @return le classement des participants
     * @throws EpreuveNonCommenceeException si l'épreuve n'a pas encore été jouée
     */
    public Map<Participant, Double> getClassement() throws EpreuveNonCommenceeException{
        if (this.classement == null) {
            throw new EpreuveNonCommenceeException(this);
        }
        return this.classement;
    }

    /**
     * Méthode permettant de lancer l'épreuve
     * @throws EpreuveDejaJoueeException si l'épreuve a déjà été jouée
     */
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

    /**
     * Méthode permettant de mettre à jour le classement des participants
     */
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

    /**
     * Méthode permettant d'obtenir le vainqueur de l'épreuve
     * @return le vainqueur de l'épreuve
     * @throws EpreuveNonCommenceeException si l'épreuve n'a pas encore été jouée
     */
    public Participant getVainqueur() throws EpreuveNonCommenceeException {
        if (this.classement == null) {
            throw new EpreuveNonCommenceeException(this);
        }
        return Collections.max(this.classement.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    /**
     * Méthode permettant d'obtenir le pays du vainqueur de l'épreuve
     * @return le pays du vainqueur de l'épreuve
     * @throws EpreuveNonCommenceeException si l'épreuve n'a pas encore été jouée
     */
    public Pays getPaysVainqueur() throws EpreuveNonCommenceeException {
        return this.getVainqueur().getPays();
    }

    /**
     * Méthode permettant d'ajouter un participant à l'épreuve
     * @param participant le participant à ajouter
     * @throws Exception si le participant ne peut pas participer à l'épreuve
     */
    public abstract void participer(Participant participant) throws Exception;

    /**
     * Méthode permettant de calculer le score d'un participant
     * @param participant le participant pour lequel on veut calculer le score
     * @return le score du participant
     */
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

    /**
     * Méthode permettant d'obtenir une représentation textuelle de l'épreuve
     * @return une représentation textuelle de l'épreuve
     */
    public String toString(){
        return this.getSport() + " " + this.getSexe();
    }

    /**
     * Méthode permettant d'obtenir un rapport de l'épreuve
     * @return un rapport de l'épreuve
     * @throws EpreuveNonCommenceeException si l'épreuve n'a pas encore été jouée
     */
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


