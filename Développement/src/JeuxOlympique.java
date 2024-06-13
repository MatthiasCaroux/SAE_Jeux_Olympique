import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean equipePleine(Participant equipe, Epreuve.TypeSport sport) {
        Equipe equipeSport = (Equipe) equipe;
        return equipeSport.getLesAthlètes().size() == sport.getNbParticipantNecessaire();
    }

    public Map<Epreuve, List<Participant>> getParticipantsParEpreuve(String cheminVersCSV){
        Map<Epreuve, List<Participant>> participantsParEpreuve = new HashMap<>();
        for (Epreuve epreuve : this.epreuves) {
            participantsParEpreuve.put(epreuve, epreuve.getParticipants());
        }
        System.out.println("Je suis dans getParticipantsParEpreuve");
        System.out.println(participantsParEpreuve);
        //vider la liste des epreuves car elle va etre reconstruite a la fin de la methode
        this.epreuves.clear();
        // a ce moment la on a tous les participants par epreuve representé par une map comme cela : {Epreuve1 : [Participant1, Participant2, Participant3], Epreuve2 : [Participant4, Participant5, Participant6]}
        try {
            BufferedReader br = new BufferedReader(new FileReader(cheminVersCSV));
            String line = "";
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String nom = values[0];
                String prenom = values[1];
                Epreuve.Sexe sexe = Epreuve.Sexe.valueOf(values[2]);
                Pays pays = new Pays(values[3]);
                String nomSPort = values[4];
                int force = Integer.parseInt(values[5]);
                int endurance = Integer.parseInt(values[6]);
                int agilite = Integer.parseInt(values[7]);
                Athlete athlete = new Athlete(nom, prenom, sexe, pays, force, endurance, agilite);
                boolean joueurAjoute = false;
                boolean sportExiste = false;
                if (nomSPort.equals("Natation relais libre")){
                    System.out.println("Le nom du sport est Natation relais libre");
                    for (Epreuve e : participantsParEpreuve.keySet()) {
                        if (e.getSport() == Epreuve.TypeSport.NatationRelais) {
                            sportExiste = true;
                            for (Participant equipe : participantsParEpreuve.get(e)){
                                Equipe equipeNatation = (Equipe) equipe;
                                if (!(equipePleine(equipeNatation, Epreuve.TypeSport.AthlétismeRelais))){
                                    if (equipeNatation.getPays().getNomPays().equals(athlete.getPays().getNomPays())){
                                        if (equipeNatation.getSexeEquipe().equals(athlete.getSexe())){                                    
                                            equipeNatation.ajouterMembre(athlete);
                                    System.out.println("J'ai ajouté un membre à l'équipe de natation");
                                    joueurAjoute = true;
                                    break;
                                        }
                                    }
                                }
                            }
                            if(!joueurAjoute){
                                Equipe nouvelleEquipeNatation = new Equipe(nom, pays, sexe);
                                nouvelleEquipeNatation.ajouterMembre(athlete);
                                participantsParEpreuve.get(e).add(nouvelleEquipeNatation);
                                joueurAjoute = true;
                            }
                        }
                    }
                    if (!sportExiste) {
                        Equipe nouvelleEquipeNatation = new Equipe(nom, pays, sexe);
                        nouvelleEquipeNatation.ajouterMembre(athlete);
                        EpreuveCollective nouvelleEpreuve = new EpreuveCollective(Epreuve.TypeSport.NatationRelais, sexe);
                        participantsParEpreuve.put(nouvelleEpreuve, new ArrayList<>());
                        participantsParEpreuve.get(nouvelleEpreuve).add(nouvelleEquipeNatation);                        
                    }
                }
                else if (nomSPort.equals("Volley-Ball")){
                    System.out.println("Le nom du sport est Volley-Ball");
                    for (Epreuve e : participantsParEpreuve.keySet()) {
                        if (e.getSport() == Epreuve.TypeSport.Volley) {
                            sportExiste = true;
                            for (Participant equipe : participantsParEpreuve.get(e)){
                                Equipe equipeVolley = (Equipe) equipe;
                                if (!(equipePleine(equipeVolley, Epreuve.TypeSport.AthlétismeRelais))){
                                    if (equipeVolley.getPays().getNomPays().equals(athlete.getPays().getNomPays())){
                                        if (equipeVolley.getSexeEquipe().equals(athlete.getSexe())){                                    
                                            equipeVolley.ajouterMembre(athlete);
                                            System.out.println("J'ai ajouté un membre à l'équipe de volley");
                                            joueurAjoute = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!joueurAjoute){
                                Equipe nouvelleEquipeVolley = new Equipe(nom, pays, sexe);
                                nouvelleEquipeVolley.ajouterMembre(athlete);
                                participantsParEpreuve.get(e).add(nouvelleEquipeVolley);
                                joueurAjoute = true;
                            }
                        }
                    }
                    if (!sportExiste) {
                        Equipe nouvelleEquipeVolley = new Equipe(nom, pays, sexe);
                        nouvelleEquipeVolley.ajouterMembre(athlete);
                        EpreuveCollective nouvelleEpreuve = new EpreuveCollective(Epreuve.TypeSport.Volley, sexe);
                        participantsParEpreuve.put(nouvelleEpreuve, new ArrayList<>());
                        participantsParEpreuve.get(nouvelleEpreuve).add(nouvelleEquipeVolley);                        
                    }
                }
                else if (nomSPort.equals("Handball")){
                    System.out.println("Le nom du sport est Handball");
                    for (Epreuve e : participantsParEpreuve.keySet()) {
                        if (e.getSport() == Epreuve.TypeSport.Handball) {
                            sportExiste = true;
                            for (Participant equipe : participantsParEpreuve.get(e)){
                                Equipe equipeHandball = (Equipe) equipe;
                                if (!(equipePleine(equipeHandball, Epreuve.TypeSport.AthlétismeRelais))){
                                    if (equipeHandball.getPays().getNomPays().equals(athlete.getPays().getNomPays())){
                                        if (equipeHandball.getSexeEquipe().equals(athlete.getSexe())){                                    
                                            equipeHandball.ajouterMembre(athlete);
                                            System.out.println("J'ai ajouté un membre à l'équipe de handball");
                                            joueurAjoute = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!joueurAjoute){
                                Equipe nouvelleEquipeHandball = new Equipe(nom, pays, sexe);
                                nouvelleEquipeHandball.ajouterMembre(athlete);
                                participantsParEpreuve.get(e).add(nouvelleEquipeHandball);
                                joueurAjoute = true;
                            }
                        }
                    }
                    if (!sportExiste) {
                        Equipe nouvelleEquipeHandball = new Equipe(nom, pays, sexe);
                        nouvelleEquipeHandball.ajouterMembre(athlete);
                        EpreuveCollective nouvelleEpreuve = new EpreuveCollective(Epreuve.TypeSport.Handball, sexe);
                        participantsParEpreuve.put(nouvelleEpreuve, new ArrayList<>());
                        participantsParEpreuve.get(nouvelleEpreuve).add(nouvelleEquipeHandball);                        
                    }
                }
                else if (nomSPort.equals("Athlétisme relais 400m")){
                    System.out.println("Le nom du sport est Athlétisme relais 400m");
                    for (Epreuve e : participantsParEpreuve.keySet()) {
                        if (e.getSport() == Epreuve.TypeSport.AthlétismeRelais) {
                            sportExiste = true;
                            for (Participant equipe : participantsParEpreuve.get(e)){
                                Equipe equipeAthletisme = (Equipe) equipe;
                                if (!(equipePleine(equipeAthletisme, Epreuve.TypeSport.AthlétismeRelais))){
                                    if (equipeAthletisme.getPays().getNomPays().equals(athlete.getPays().getNomPays())){
                                        if (equipeAthletisme.getSexeEquipe().equals(athlete.getSexe())){
                                            System.out.println("Tout les tests sont passés");
                                            equipeAthletisme.ajouterMembre(athlete);
                                            System.out.println("J'ai ajouté un membre à l'équipe d'athlétisme");
                                            joueurAjoute = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!joueurAjoute){
                                System.out.println("le joueur na pas été ajouté on va donc créer une nouvelle équipe");
                                Equipe nouvelleEquipeAthletisme = new Equipe(nom, pays, sexe);
                                nouvelleEquipeAthletisme.ajouterMembre(athlete);
                                participantsParEpreuve.get(e).add(nouvelleEquipeAthletisme);
                                joueurAjoute = true;
                            }
                        }
                    }
                    if (!sportExiste) {
                        Equipe nouvelleEquipeAthletisme = new Equipe(nom, pays, sexe);
                        nouvelleEquipeAthletisme.ajouterMembre(athlete);
                        EpreuveCollective nouvelleEpreuve = new EpreuveCollective(Epreuve.TypeSport.AthlétismeRelais, sexe);
                        participantsParEpreuve.put(nouvelleEpreuve, new ArrayList<>());
                        participantsParEpreuve.get(nouvelleEpreuve).add(nouvelleEquipeAthletisme);                        
                    }
                }
                else if (nomSPort.equals("Escrime fleuret")){
                    for (Epreuve e : participantsParEpreuve.keySet()) {
                        if (e.getSport() == Epreuve.TypeSport.Escrimefleuret) {
                            sportExiste = true;
                            participantsParEpreuve.get(e).add(athlete);
                        }
                    }
                    if (!sportExiste) {
                        EpreuveIndividuelle nouvelleEpreuve = new EpreuveIndividuelle(Epreuve.TypeSport.Escrimefleuret, sexe);
                        participantsParEpreuve.put(nouvelleEpreuve, new ArrayList<>());
                        participantsParEpreuve.get(nouvelleEpreuve).add(athlete);
                    }
                    
                }
                else if (nomSPort.equals("Escrime épée")){

                    for (Epreuve e : participantsParEpreuve.keySet()) {
                        if (e.getSport() == Epreuve.TypeSport.EscrimeÉpée) {
                            System.out.println(e.getSport());
                            sportExiste = true;
                            participantsParEpreuve.get(e).add(athlete);
                        }
                    }
                    
                    if (!sportExiste) {
                        EpreuveIndividuelle nouvelleEpreuve = new EpreuveIndividuelle(Epreuve.TypeSport.EscrimeÉpée, sexe);
                        participantsParEpreuve.put(nouvelleEpreuve, new ArrayList<>());
                        participantsParEpreuve.get(nouvelleEpreuve).add(athlete);
                    }
                }
                else if (nomSPort.equals("Athétisme 110 haies")){
                    for (Epreuve e : participantsParEpreuve.keySet()) {
                        if (e.getSport() == Epreuve.TypeSport.AthlétismeHaie) {
                            sportExiste = true;
                            participantsParEpreuve.get(e).add(athlete);
                        }
                    }
                    if (!sportExiste) {
                        EpreuveIndividuelle nouvelleEpreuve = new EpreuveIndividuelle(Epreuve.TypeSport.AthlétismeHaie, sexe);
                        participantsParEpreuve.put(nouvelleEpreuve, new ArrayList<>());
                        participantsParEpreuve.get(nouvelleEpreuve).add(athlete);
                    }
                }
                else if (nomSPort.equals("Natation 100 brasse")){
                    for (Epreuve e : participantsParEpreuve.keySet()) {
                        if (e.getSport() == Epreuve.TypeSport.NatationBrasse) {
                            sportExiste = true;
                            participantsParEpreuve.get(e).add(athlete);
                        }
                    }
                    if (!sportExiste) {
                        EpreuveIndividuelle nouvelleEpreuve = new EpreuveIndividuelle(Epreuve.TypeSport.NatationBrasse, sexe);
                        participantsParEpreuve.put(nouvelleEpreuve, new ArrayList<>());
                        participantsParEpreuve.get(nouvelleEpreuve).add(athlete);
                    }                   

                }
            }
            br.close();
            System.out.println("J'ai fini de lire le fichier");
            System.out.println(participantsParEpreuve);
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture du fichier");
        }
        return participantsParEpreuve;
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
