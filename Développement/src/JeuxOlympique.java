import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JeuxOlympique {
    private int annee;
    private String lieu;
    private String nomJO;
    private List<Epreuve> epreuves;
    private List<Pays> lesPays;
    private List<Pays> classementPays;
    // private Map<Pays, Map<String, Integer>> lesMedailles;

    public JeuxOlympique(int annee, String lieu, String nomJO) {
        this.annee = annee;
        this.lieu = lieu;
        this.nomJO = nomJO;
        this.epreuves = new ArrayList<>();
        this.lesPays = new ArrayList<>();
        this.classementPays = new ArrayList<>();
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNomJO() {
        return nomJO;
    }

    public void setNomJO(String nomJO) {
        this.nomJO = nomJO;
    }

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

    public Pays vainqueurEpreuve(Epreuve epreuve) throws EpreuveNonCommenceeException {
        try {
            return epreuve.getPaysVainqueur();
        } catch (EpreuveNonCommenceeException e) {
            throw new EpreuveNonCommenceeException(epreuve);
        }
    }

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

    public List<Pays> getClassementPays() throws JeuxPasCommenceException {
        if (this.classementPays.isEmpty()) {
            throw new JeuxPasCommenceException(this);
        }
        System.out.println("Je suis dans getClassementPays");
        Collections.sort(this.classementPays, new PaysComparator());
        System.out.println("Et je suis passé par là");
        return classementPays;
    }
    public int getScore(Pays pays){
        return pays.getScoreTotal();
    }

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

    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    public List<Pays> getLesPays() {
        return lesPays;
    }

    @Override
    public String toString() {
        return "Jeux Olympique de " + this.annee;
    }
}
