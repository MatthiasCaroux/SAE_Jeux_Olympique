package src.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import src.modele.*;


/**
 * Classe de test pour Executable2
 */
public class TestJO {

    private Equipe equipe1;
    private Equipe equipe2;

    @Test
    public void testEquipe() {
        Executable2.main(new String[]{});
        this.setUp();

        assertEquals(7, this.equipe1.getNbAthlètes());
        assertEquals(5, equipe2.getNbAthlètes());
    }


    @Test
    public void testEpreuveCollective() {
        Executable2.main(new String[]{});
        this.setUp();

        EpreuveCollective hand = new EpreuveCollective(Epreuve.TypeSport.Handball, Epreuve.Sexe.M);
        try {
            hand.participer(this.equipe1);
            hand.participer(this.equipe2);//ne peux pas participer car ne contient pas le nombre suffisant d'athlètes dans l'équipe
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertEquals(1, hand.getParticipants().size());
    }

    @Test
    public void testAthlete() {
        Executable2.main(new String[]{});
        this.setUp();

        Athlete test = new Athlete("Doe", "John", Epreuve.Sexe.M, this.equipe2.getPays(), 80, 80, 80);
        assertEquals("Doe", test.getNom());
        assertEquals("John", test.getPrenom());
        assertEquals(Epreuve.Sexe.M, test.getSexe());
        assertEquals(this.equipe2.getPays(), test.getPays());
        assertEquals(80, test.getForce());
        assertEquals(80, test.getEndurance());
        assertEquals(80, test.getAgilité());
    }

    @Test
    public void testPays() {
        Executable2.main(new String[]{});
        this.setUp();

        Pays france = new Pays("France");
        Pays USA = new Pays("USA");

        assertEquals("France", france.getNomPays());
        assertEquals("USA", USA.getNomPays());
    }


    @Test
    public void testJeuxOlympique() {
        Executable2.main(new String[]{});
        this.setUp();

        JeuxOlympique jo2024 = new JeuxOlympique(2024, "Paris", "JO 2024");
        assertEquals(2024, jo2024.getAnnee());
        assertEquals("Paris", jo2024.getLieu());
        assertEquals("JO 2024", jo2024.getNomJO());
    }

    @Test
    public void testEpreuveIndividuelle() {
        Executable2.main(new String[]{});
        this.setUp();

        EpreuveIndividuelle natation = new EpreuveIndividuelle(Epreuve.TypeSport.NatationBrasse, Epreuve.Sexe.M);
        try {
            natation.participer(this.equipe1.getLesAthlètes().get(0));
            natation.participer(this.equipe1.getLesAthlètes().get(1));
            natation.participer(this.equipe2.getLesAthlètes().get(0));
            natation.participer(this.equipe2.getLesAthlètes().get(1));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        assertEquals(4, natation.getParticipants().size());
    }


    @Test
    public void simulationJeuxOlympique() {
        JeuxOlympique jo2024 = new JeuxOlympique(2024, "Paris", "JO 2024");
        Pays france = new Pays("France");
        Pays USA = new Pays("USA");

        Athlete Jean = new Athlete("Dupont", "Jean", Epreuve.Sexe.M, france, 75, 80, 85);
        Athlete Lucas = new Athlete("Hautin", "Lucas", Epreuve.Sexe.M, france, 85, 80, 75);
        Athlete Jack = new Athlete("Doe", "Jack", Epreuve.Sexe.M, france, 75, 80, 80);
        Athlete Mark = new Athlete("Patrick", "Mark", Epreuve.Sexe.M, france, 80, 85, 85);
        Athlete Tom = new Athlete("Hanks", "Tom", Epreuve.Sexe.M, france, 90, 70, 80);
        Athlete Jane = new Athlete("Doe", "Jane", Epreuve.Sexe.M, france, 60, 95, 90);
        Athlete Bernard = new Athlete("Doe", "Bernard", Epreuve.Sexe.M, france, 80, 80, 80);


        Equipe equipe1 = new Equipe("Equipe 1", france, Epreuve.Sexe.M);
        equipe1.ajouterMembre(Jean);
        equipe1.ajouterMembre(Lucas);
        equipe1.ajouterMembre(Jack);
        equipe1.ajouterMembre(Mark);
        equipe1.ajouterMembre(Tom);
        equipe1.ajouterMembre(Jane);
        equipe1.ajouterMembre(Bernard);

        Athlete Matthias = new Athlete("Caroux", "Matthias", Epreuve.Sexe.M, USA, 75, 80, 85);
        Athlete Thomas = new Athlete("Doe", "Thomas", Epreuve.Sexe.M, USA, 75, 80, 80);
        Athlete Paul = new Athlete("Patrick", "Paul", Epreuve.Sexe.M, USA, 80, 85, 85);
        Athlete Pierre = new Athlete("Dupont", "Pierre", Epreuve.Sexe.M, USA, 85, 80, 75);
        Athlete Jill = new Athlete("Doe", "Jill", Epreuve.Sexe.M, USA, 70, 85, 85);
        Athlete Jack2 = new Athlete("Doe", "Jack", Epreuve.Sexe.M, USA, 75, 80, 80);
        Athlete John = new Athlete("Doe", "John", Epreuve.Sexe.M, USA, 80, 80, 80);

        Equipe equipe2 = new Equipe("Equipe 2", USA, Epreuve.Sexe.M);
        equipe2.ajouterMembre(Matthias);
        equipe2.ajouterMembre(Thomas);
        equipe2.ajouterMembre(Paul);
        equipe2.ajouterMembre(Pierre);
        equipe2.ajouterMembre(Jill);
        equipe2.ajouterMembre(Jack2);
        equipe2.ajouterMembre(John);


        EpreuveCollective hand = new EpreuveCollective(Epreuve.TypeSport.Handball, Epreuve.Sexe.M);
        try {
            hand.participer(equipe1);
            hand.participer(equipe2);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        EpreuveIndividuelle natation = new EpreuveIndividuelle(Epreuve.TypeSport.NatationBrasse, Epreuve.Sexe.M);
        try {
            natation.participer(Jean);
            natation.participer(Lucas);
            natation.participer(Matthias);
            natation.participer(Thomas);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try{
            jo2024.ajouteEpreuve(hand);
            jo2024.ajouteEpreuve(natation);
            jo2024.lancerToutesLesEpreuves();

        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        assertEquals(2, jo2024.getEpreuves().size());
        assertEquals(2, jo2024.getEpreuves().get(0).getParticipants().size());// permet de verifier que les deux equipes on bien été ajouté
        assertEquals(4, jo2024.getEpreuves().get(1).getParticipants().size());//permet de verifier que les joueurs ont bien été ajouté

        try {
            jo2024.lancerToutesLesEpreuves();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        List<Pays> pays = new ArrayList<>();
        pays.add(france);
        pays.add(USA);
        try {
            assertEquals(pays, jo2024.getClassementPays());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void setUp(){
        Pays france = new Pays("France");
        Pays USA = new Pays("USA");

        this.equipe1 = new Equipe("Equipe 1", france, Epreuve.Sexe.M);
        this.equipe1.ajouterMembre(new Athlete("Dupont", "Jean", Epreuve.Sexe.M, france, 75, 80, 85));
        this.equipe1.ajouterMembre(new Athlete("Hautin", "Lucas", Epreuve.Sexe.M, france, 85, 80, 75));
        this.equipe1.ajouterMembre(new Athlete("Doe", "Jack", Epreuve.Sexe.M, france, 75, 80, 80));
        this.equipe1.ajouterMembre(new Athlete("Patrick", "Mark", Epreuve.Sexe.M, france, 80, 85, 85));
        this.equipe1.ajouterMembre(new Athlete("Hanks", "Tom", Epreuve.Sexe.M, france, 90, 70, 80));
        this.equipe1.ajouterMembre(new Athlete("Doe", "Jane", Epreuve.Sexe.M, france, 60, 95, 90));
        this.equipe1.ajouterMembre(new Athlete("Doe", "John", Epreuve.Sexe.M, france, 80, 80, 80));

        this.equipe2 = new Equipe("Equipe 2", USA, Epreuve.Sexe.M);
        this.equipe2.ajouterMembre(new Athlete("Caroux", "Matthias", Epreuve.Sexe.M, USA, 75, 80, 85));
        this.equipe2.ajouterMembre(new Athlete("Doe", "Thomas", Epreuve.Sexe.M, USA, 75, 80, 80));
        this.equipe2.ajouterMembre(new Athlete("Patrick", "Paul", Epreuve.Sexe.M, USA, 80, 85, 85));
        this.equipe2.ajouterMembre(new Athlete("Dupont", "Pierre", Epreuve.Sexe.M, USA, 85, 80, 75));
        this.equipe2.ajouterMembre(new Athlete("Doe", "Jane", Epreuve.Sexe.M, USA, 60, 95, 90));
        
    }
}