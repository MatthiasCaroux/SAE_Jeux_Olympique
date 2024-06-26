package src.basededonnee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import src.basededonnee.exception.AthleteInexistantException;
import src.basededonnee.exception.BaseDeDonneeInaccessibleException;
import src.basededonnee.exception.EpreuveDejaExistantException;
import src.basededonnee.exception.EpreuveInexistanteException;
import src.basededonnee.exception.PaysInexistantException;
import src.modele.exceptions.EpreuveDejaJoueeException;
import src.modele.exceptions.EpreuveDejaPresenteException;
import src.modele.exceptions.JeuxPasCommenceException;
import src.modele.jeuxOlympique.*;

public class testBD {

    @Test
    public void testConnexion() {
        try {
            Requete requete = new Requete();
            assert requete.connexion("admin", "admin");
            assert requete.connexion("user", "user");
            assert !requete.connexion("admin1", "admin");
            assert !requete.connexion("admin1", "admin1");
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    @Test
    public void testDansUtilisateur() {
        try {
            Requete requete = new Requete();
            assert requete.dansUtilisateur("admin", "admin@admin.com", "admin");
            assert requete.dansUtilisateur("user", "user@user.com", "user");
            assert !requete.dansUtilisateur("toto", "toto", "toto");
            assert !requete.dansUtilisateur("Cho", "co", "lat");
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    @Test
    public void testajouterPays() {
        try {
            Requete requete = new Requete();
            requete.ajouterPays("France");
            assert requete.dansPays("France");
            requete.ajouterPays("Allemagne");
            assert requete.dansPays("Allemagne");
            requete.ajouterPays("Italie");
            assert requete.dansPays("Italie");
        } catch (Exception e) {
            System.out.println("Pays déjà existant");
        }
    }

    @Test
    public void testAjouterAtheleteAndDelete() {
        try {
            Requete requete = new Requete();
            requete.ajouterAthlete("test", "prenomTest", 84, 69, 91, 'M', 1);;
            assert requete.dansAthlete("toto", "prenomTest", 1);
            Athlete athlete = new Athlete("Ryan", "Crouser", Epreuve.Sexe.M, new Pays("France"), 75, 67, 82);
            requete.ajouterAthlete(athlete);
            assert requete.dansAthlete("Ryan", "Crouser", 1);
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, BaseDeDonneeInaccessibleException, EpreuveDejaExistantException, JeuxPasCommenceException, AthleteInexistantException, EpreuveDejaPresenteException, EpreuveDejaJoueeException, PaysInexistantException {
        try {
            Requete requete = new Requete();
            JeuxOlympique jeux = new JeuxOlympique(2036, "Paris", "France");
            Pays France = new Pays("CANADA");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JeuxOlympique jeux = new JeuxOlympique(2024, "Paris", "France");
        Requete requete = new Requete();
        System.out.println(requete.getAthletes() + "&&&&&&&&&&&&&&&&&&&&&&&&&&");
        // Map<Epreuve, List<Participant>> test = jeux.getParticipantsParEpreuve("Développement/donnees.csv");
        Map<Epreuve, List<Participant>> test = jeux.getParticipantsParEpreuve("Développement/donnees.csv");
        System.out.println(test.keySet() + "***********************");
        for (Epreuve epreuve : test.keySet()) {
            System.out.println("Attention !!!!!!!!!!");
            System.out.println(epreuve);
            requete.ajouteEpreuve(epreuve, jeux);
            requete.lancerUneEpreuve(epreuve, jeux);
            System.out.println("je passs");
        }
        System.out.println(jeux.getClassementPays() + "????????????????????????????,");
        for (Equipe equipe : requete.getLesEquipes()) {
            System.out.println(equipe.getLesAthlètes().size() + "!!!!!!!!!!!!!!!!!!!!!!");
        }
        System.out.println(requete.getEpreuves(jeux) + "£££££££££££££££££££££££££££");
        System.out.println(jeux.getEpreuves() + "ùùùùùùùùùùùùùùùùùùùùùùùùùùùù");
        System.out.println(requete.getPays() + "///////////////////");
        System.out.println(jeux.getLesPays() + "///////////////////");
        System.out.println(jeux.getAnnee() + "///////////////////");
        for (Epreuve epreuve : jeux.getEpreuves()) {
            System.out.println(epreuve + "ççççççççççççççççççç");
            requete.lancerUneEpreuve(epreuve, jeux);
        }
        System.out.println(jeux.getEpreuves());
        System.out.println(requete.getEpreuves(jeux));
        // System.out.println(jeux.getClassementPays());
        System.out.println(requete.getPays());

    }
}
