package src.modele.jeuxOlympique;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import src.modele.exceptions.*;
import src.modele.comparator.*;
import src.basededonnee.*;

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

    public List<Participant> lesParticipantsAuxJo(){
        List<Participant> lesParticipantsAuxJo = new ArrayList<>();
        for (Epreuve epreuve : this.epreuves) {
            lesParticipantsAuxJo.addAll(epreuve.getParticipants());
        }
        return lesParticipantsAuxJo;
    }
    

    /**
     * Méthode permettant d'ajouter une épreuve aux jeux olympiques
     * @param epreuve l'épreuve à ajouter
     * @throws EpreuveDejaPresenteException si l'épreuve est déjà présente
     */
    public void ajouteEpreuve(Epreuve epreuve) throws EpreuveDejaPresenteException {
        System.out.println(epreuve);
        System.out.println(this.epreuves);
        System.out.println(this.epreuves.contains(epreuve));
        if (! this.epreuves.contains(epreuve)) {
            this.epreuves.add(epreuve);
            System.out.println("Je suis dans ajouteEpreuve");
            for (Participant p : epreuve.getParticipants()) {
                if (!this.lesPays.contains(p.getPays())) {
                    this.lesPays.add(p.getPays());
                }
            }
        }
    }

    public boolean equipePleine(Participant equipe, Epreuve.TypeSport sport) {
        Equipe equipeSport = (Equipe) equipe;
        return equipeSport.getLesAthlètes().size() == sport.getNbParticipantNecessaire();
    }

    /**
     * Permet de récupérer les participants par épreuve et de les ajouter avec le fichier CSV
     * @param cheminVersCSV
     * @return
     */
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
            int indice = 0;
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
                                Equipe nouvelleEquipeNatation = new Equipe(pays.getNomPays() + " " + indice, pays, sexe);
                                indice++;
                                nouvelleEquipeNatation.ajouterMembre(athlete);
                                participantsParEpreuve.get(e).add(nouvelleEquipeNatation);
                                joueurAjoute = true;
                            }
                        }
                    }
                    if (!sportExiste) {
                        Equipe nouvelleEquipeNatation = new Equipe(pays.getNomPays() + " " + indice, pays, sexe);
                        indice++;
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
                                Equipe nouvelleEquipeVolley = new Equipe(pays.getNomPays() + " " + indice, pays, sexe);
                                indice++;
                                nouvelleEquipeVolley.ajouterMembre(athlete);
                                participantsParEpreuve.get(e).add(nouvelleEquipeVolley);
                                joueurAjoute = true;
                            }
                        }
                    }
                    if (!sportExiste) {
                        Equipe nouvelleEquipeVolley = new Equipe(pays.getNomPays() + " " + indice, pays, sexe);
                        indice++;
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
                                Equipe nouvelleEquipeHandball = new Equipe(pays.getNomPays() + " " + indice, pays, sexe);
                                indice++;
                                nouvelleEquipeHandball.ajouterMembre(athlete);
                                participantsParEpreuve.get(e).add(nouvelleEquipeHandball);
                                joueurAjoute = true;
                            }
                        }
                    }
                    if (!sportExiste) {
                        Equipe nouvelleEquipeHandball = new Equipe(pays.getNomPays() + " " + indice, pays, sexe);
                        indice++;
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
                                Equipe nouvelleEquipeAthletisme = new Equipe(pays.getNomPays() + " " + indice, pays, sexe);
                                indice++;
                                nouvelleEquipeAthletisme.ajouterMembre(athlete);
                                participantsParEpreuve.get(e).add(nouvelleEquipeAthletisme);
                                joueurAjoute = true;
                            }
                        }
                    }
                    if (!sportExiste) {
                        Equipe nouvelleEquipeAthletisme = new Equipe(pays.getNomPays() + " " + indice, pays, sexe);
                        indice++;
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
            // on a fini de lire le fichier
            System.out.println(participantsParEpreuve);
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture du fichier");
        }
        System.out.println(this.epreuves);// doit etre vide
        //maintenant on va reconstruire la liste des epreuves et ajouter les nouvelles
        for (Epreuve epreuve : participantsParEpreuve.keySet()) {
            if (epreuve instanceof EpreuveIndividuelle){
                EpreuveIndividuelle epreuveIndividuelle = (EpreuveIndividuelle) epreuve;
                List<Participant> participants = participantsParEpreuve.get(epreuve);
                for (Participant participant : participants) {
                    try {
                        epreuveIndividuelle.participer(participant);
                    } catch (Exception e) {
                        // Ne rien faire, intentionnellement laissé vide
                        System.err.println(e.getMessage());
                    }
                    
                }
                try{
                    this.ajouteEpreuve(epreuveIndividuelle);
                }
                catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
            else if (epreuve instanceof EpreuveCollective){
                List<Participant> participants = participantsParEpreuve.get(epreuve);
                EpreuveCollective epreuveCollective = (EpreuveCollective) epreuve;
                for (Participant participant : participants) {
                    try {
                        epreuveCollective.participer(participant);
                    } catch (Exception e) {
                        // Ne rien faire, intentionnellement laissé vide
                        System.err.println(e.getMessage());
                    }
                }
                try{
                    if (!epreuveCollective.getParticipants().isEmpty()){
                        this.ajouteEpreuve(epreuveCollective);
                    }
                }
                catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
        }
        System.out.println(this.epreuves);
        this.epreuves.clear();
        return participantsParEpreuve;
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
        Collections.sort(this.classementPays, new PaysComparator());
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
