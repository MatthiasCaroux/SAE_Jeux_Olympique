import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe représentant les jeux olympiques
 */
public class JeuxOlympique {
    /**
     * l'année des jeux olympiques
     */
    private int annee;
    /**
     * le lieu des jeux olympiques
     */
    private String lieu;
    /**
     * le nom des jeux olympiques
     */
    private String nomJO;
    /**
     * la liste des épreuves des jeux olympiques
     */
    private List<Epreuve> epreuves;
    /**
     * la liste des pays participant aux jeux olympiques
     */
    private List<Pays> lesPays;
    /**
     * le classement des pays
     */
    private List<Pays> classementPays;
    // private Map<Pays, Map<String, Integer>> lesMedailles;

    /**
     * Constructeur de la classe JeuxOlympique
     * @param annee l'année des jeux olympiques
     * @param lieu le lieu des jeux olympiques
     * @param nomJO le nom des jeux olympiques
     */
    public JeuxOlympique(int annee, String lieu, String nomJO) {
        this.annee = annee;
        this.lieu = lieu;
        this.nomJO = nomJO;
        this.epreuves = new ArrayList<>();
        this.lesPays = new ArrayList<>();
        this.classementPays = new ArrayList<>();
    }

    /**
     * Méthode permettant de récupérer l'année des jeux olympiques
     * @return l'année des jeux olympiques
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Méthode permettant de modifier l'année des jeux olympiques
     * @param annee l'année des jeux olympiques
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Méthode permettant de récupérer le lieu des jeux olympiques
     * @return le lieu des jeux olympiques
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * Méthode permettant de modifier le lieu des jeux olympiques
     * @param lieu le lieu des jeux olympiques
     */
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    /**
     * Méthode permettant de récupérer le nom des jeux olympiques
     * @return le nom des jeux olympiques
     */
    public String getNomJO() {
        return nomJO;
    }

    /**
     * Méthode permettant de modifier le nom des jeux olympiques
     * @param nomJO le nom des jeux olympiques
     */
    public void setNomJO(String nomJO) {
        this.nomJO = nomJO;
    }

    /**
     * Méthode permettant d'ajouter une épreuve aux jeux olympiques
     * @param epreuve l'épreuve à ajouter
     * @throws EpreuveDejaPresenteException si l'épreuve est déjà présente
     */
    public void ajouteEpreuve(Epreuve epreuve) throws EpreuveDejaPresenteException {
        if (this.epreuves.contains(epreuve)) {
            throw new EpreuveDejaPresenteException(epreuve);
        }
        this.epreuves.add(epreuve);
        for (Participant p : epreuve.getParticipants()) {
            if (!this.lesPays.contains(p.getPays())) {
                this.lesPays.add(p.getPays());
            }
        }
    }

    /**
     * Méthode permettant de récupérer le vainqueur d'une épreuve
     * @param epreuve l'épreuve
     * @return le pays vainqueur de l'épreuve
     * @throws EpreuveNonCommenceeException si l'épreuve n'est pas commencée
     */
    public Pays vainqueurEpreuve(Epreuve epreuve) throws EpreuveNonCommenceeException {
        try {
            return epreuve.getPaysVainqueur();
        } catch (EpreuveNonCommenceeException e) {
            throw new EpreuveNonCommenceeException(epreuve);
        }
    }

    /**
     * Méthode permettant de récupérer le pays vainqueur des jeux olympiques
     * @return le pays vainqueur des jeux olympiques
     */
    public Pays vainqueurJeuxOlympique() {
        Pays vainqueur = null;
        int scoreMax = 0;
        for (Pays pays : this.classementPays) {
            if (pays.getScoreTotal() > scoreMax) {
                scoreMax = pays.getScoreTotal();
                vainqueur = pays;
            }
        }
        return vainqueur;
    }

    /**
     * Méthode permettant de récupérer le classement des pays
     * @return le classement des pays
     * @throws JeuxPasCommenceException si les jeux ne sont pas commencés
     */
    public List<Pays> getClassementPays() throws JeuxPasCommenceException {
        if (this.classementPays.isEmpty()) {
            throw new JeuxPasCommenceException(this);
        }
        System.out.println("Je suis dans getClassementPays");
        Collections.sort(this.classementPays, new PaysComparator());
        System.out.println("Et je suis passé par là");
        return classementPays;
    }

    /**
     * Méthode permettant de récupérer le score d'un pays
     * @param pays le pays
     * @return le score du pays
     */
    public int getScore(Pays pays){
        return pays.getScoreTotal();
    }

    /**
     * Méthode permettant de lancer toutes les épreuves
     */
    public void lancerToutesLesEpreuves(){
        for (Epreuve epreuve : this.epreuves) {
            try {
                epreuve.jouerEpreuve();
                for (Participant participant : epreuve.getParticipants()) {
                    if (!this.classementPays.contains(participant.getPays())) {
                        this.classementPays.add(participant.getPays());
                    }
                }
            } catch (EpreuveDejaJoueeException e) {
                // Ne rien faire, intentionnellement laissé vide
            }
        }
    }

    /**
     * Méthode permettant de lancer une épreuve
     * @param epreuve l'épreuve à lancer
     * @throws EpreuveDejaJoueeException si l'épreuve est déjà jouée
     */
    public void lancerUneEpreuve(Epreuve epreuve) throws EpreuveDejaJoueeException{
        try {
            epreuve.jouerEpreuve();
            for (Participant participant : epreuve.getParticipants()) {
                if (!this.classementPays.contains(participant.getPays())) {
                    this.classementPays.add(participant.getPays());
                }
            }
        } catch (EpreuveDejaJoueeException e) {
            throw new EpreuveDejaJoueeException(epreuve);
        }
    }

    /**
     * Méthode permettant de récupérer les épreuves des jeux olympiques
     * @return les épreuves des jeux olympiques
     */
    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    /**
     * Méthode permettant de récupérer les pays participant aux jeux olympiques
     * @return les pays participant aux jeux olympiques
     */
    public List<Pays> getLesPays() {
        return lesPays;
    }

    /**
     * Méthode permettant de récupérer les épreuves auquels participe un athlete
     * @param athlete l'athlete à qui on veut les épreuves auquels il a participé
     * @return les épreuves auquels participe un athlete
     */
    public List<Epreuve> getEpreuveDuParticipant(Athlete athlete) {
        List<Epreuve> lesEpreuves = new ArrayList<>();
        for (Epreuve epreuve : this.epreuves) {
            if (epreuve instanceof EpreuveIndividuelle) {
                EpreuveIndividuelle epreuveIndividuelle = (EpreuveIndividuelle) epreuve;
                if (epreuveIndividuelle.getParticipants().contains(athlete)) {
                    lesEpreuves.add(epreuve);
                }
            } else {
                EpreuveCollective epreuveCollective = (EpreuveCollective) epreuve;
                for (Participant participant : epreuveCollective.getParticipants()) {
                    Equipe equipe = (Equipe) participant;
                    if (equipe.getLesAthlètes().contains(athlete)) {
                        lesEpreuves.add(epreuve);
                    }
                }
            }
        }
        return lesEpreuves;
    }

    /**
     * Méthode permettant de récupérer les épreuves auquels participe un pays
     * @param pays le pays à qui on veut les épreuves auquels il a participé
     * @return les épreuves auquels participe un pays
     */
    public List<Epreuve> getEpreuvesDuPays(Pays pays) {
        List<Epreuve> lesEpreuves = new ArrayList<>();
        for (Epreuve epreuve : this.epreuves) {
            for (Participant participant : epreuve.getParticipants()) {
                if (participant.getPays().equals(pays)) {
                    lesEpreuves.add(epreuve);
                }
            }
        }
        return lesEpreuves;
    }

    /**
     * Méthode permettant d'obtenir une représentation textuelle des jeux olympiques
     */
    @Override
    public String toString() {
        return "Jeux Olympique de " + this.annee;
    }
}
