package src.basededonnee;

import java.beans.Expression;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import src.basededonnee.exception.BaseDeDonneeInaccessibleException;
import src.basededonnee.exception.EpreuveDejaExistantException;
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
            // int idAthlete = requete.getIdAthlete("test", "prenomTest");
            // requete.supprimerAthlete(idAthlete);
            // assert !requete.dansAthlete("test", "prenomTest", idAthlete);
            // idAthlete = requete.getIdAthlete("Ryan", "Crouser");
            // requete.supprimerAthlete(idAthlete);
            // assert !requete.dansAthlete("Ryan", "Crouser", idAthlete);
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, BaseDeDonneeInaccessibleException, EpreuveDejaExistantException, JeuxPasCommenceException {
        // try {
        //     Requete requete = new Requete();
        //     System.out.println(requete.connexion("niksan", "niksan"));
        //     System.out.println("ça marche");
        //     System.out.println(requete.getRoleUtilisateur("admin", "admin"));
        //     requete.inscription("test2", "test@gmail.com", "test");
        //     System.out.println("jeusisdfouh iuhjesus");
        // } catch (Exception e) {
        //     // TODO: handle exception
        //     System.out.println("Heleo");
        //     System.err.println(e.getMessage());
        // }
        try {
            Requete requete = new Requete();
            JeuxOlympique jeux = new JeuxOlympique(2036, "Paris", "France");
            // Epreuve epreuve = new EpreuveIndividuelle(Epreuve.TypeSport.EscrimeÉpée, Epreuve.Sexe.M);
            // requete.ajouteEpreuve(epreuve, jeux);
            // requete.ajouterJO(jeux);
            Pays France = new Pays("CANADA");
            // try {
            //     // requete.ajouterPays("CANADA");
            //     requete.ajouterAthlete(new Athlete("TOM", "POL", Epreuve.Sexe.M, France, 0, 0, 0));
            //     System.out.println("Athlète ajouté");
            // } catch (Exception e) {
            //     System.out.println(e.getMessage());
            //     // Pass, l'athlète existe déjà
            // }
            System.out.println("9874563210000.0");
            Equipe equipe1 = new Equipe("Equipe 1", France, Epreuve.Sexe.M);
            equipe1.ajouterMembre(new Athlete("Hanks", "Tom", Epreuve.Sexe.M, France, 90, 70, 80));
            equipe1.ajouterMembre(new Athlete("Doe", "Jane", Epreuve.Sexe.M, France, 60, 95, 90));
            equipe1.ajouterMembre(new Athlete("Doe", "John", Epreuve.Sexe.M, France, 80, 80, 80));
            equipe1.ajouterMembre(new Athlete("PLOUF", "PLOUF", Epreuve.Sexe.M, France, 60, 95, 90));
            EpreuveCollective epreuveCollective = new EpreuveCollective(Epreuve.TypeSport.NatationRelais, Epreuve.Sexe.M);
            epreuveCollective.participer(equipe1);
            requete.ajouteEpreuve(epreuveCollective, jeux);
            System.out.println("99999999999999999999999999999999999");
            // requete.ajouterEquipe(equipe1);
            // requete.ajouterPays("France");
            // Epreuve epreuve = new EpreuveIndividuelle(Epreuve.TypeSport.EscrimeÉpée, Epreuve.Sexe.M);
            // for (Athlete athlete : equipe1.getLesAthlètes()) {
            //     epreuve.participer(athlete);
            // }
            // requete.ajouteEpreuve(epreuve, jeux);
            System.out.println(requete.getAthletes());
            requete.supprimerUtilisateur("user");
            requete.changerRoleUtilisateur("niksan", 'A');
            requete.ajouterPays("Allemagne");
            requete.ajouterPays("Italie");
            requete.ajouterAthlete("test", "prenomTest", 84, 69, 91, 'M', requete.getIdPays("France"));
            System.out.println(requete.idMaxTable("PAYS"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        // try {
        //     Requete requete = new Requete();
        //     requete.ajouterAthlete("Caroux", "Matthias", 99, 12, 69, 'M', requete.getIdPays("France"));
        //     Pays france = new Pays("France");
        //     requete.ajouterAthlete(new Athlete("Nagarajah", "Niksan", Epreuve.Sexe.M, france, 75, 75, 75));
        //     requete.ajouteEpreuve(new EpreuveIndividuelle(Epreuve.TypeSport.EscrimeÉpée, Epreuve.Sexe.M));
        //     requete.ajouteEpreve(new EpreuveCollective(Epreuve.TypeSport.Handball, Epreuve.Sexe.M));
        //     Equipe equipe = new Equipe("Les Bleus", france, Epreuve.Sexe.M);
        //     equipe.ajouterMembre(new Athlete("Nagarajah", "Niksan", Epreuve.Sexe.M, france, 75, 75, 75));
        //     equipe.ajouterMembre("Caroux", "Matthias", requete.getIdPays("France"));
        //     requete.ajouterEquipe(equipe);
        //     requete.ajouterEquipeDansEpreuve(equipe, requete.getIdEpreuve("Handball", "M"));
        //     requete.ajouterAthleteDansEpreuve(requete.getIdAthlete("Nagarajah", "Niksan"), requete.getIdEpreuve("EscrimeÉpée", "M"));

        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
        JeuxOlympique jeux = new JeuxOlympique(2036, "Paris", "France");
        Requete requete = new Requete();
        Map<Epreuve, List<Participant>> test = jeux.getParticipantsParEpreuve("Développement/donnees.csv");
        for (Epreuve epreuve : test.keySet()) {
            boolean val;
            if (epreuve instanceof EpreuveCollective) {
                val = true;
            } else {
                val = false;
            }
            String text;
            if (test.get(epreuve).get(0) instanceof Equipe) {
                text = "Equipe";
            } else {
                text = "Athlete";
            }
            // System.out.println(epreuve.getSport() + " " + epreuve.getSexe() + " " + val + " " + text);
            // int equipeValide = 0;
            // if (epreuve instanceof EpreuveCollective) {
            //     for (Participant participant : test.get(epreuve)) {
            //         Equipe equipe = (Equipe) participant;
            //         if (equipe.getLesAthlètes().size() == 4) {
            //             equipeValide++;
            //         }
            //     }
            // }
            // System.out.println(equipeValide);
            // Thread.sleep(3000);
            requete.ajouteEpreuve(epreuve, jeux);
            // System.out.println(epreuve.getParticipants());
        }
        System.out.println(jeux.getLesPays());
        System.out.println(jeux.getEpreuves());
        // System.out.println(jeux.lesParticipantsAuxJo());
        System.out.println(requete.getEpreuves(jeux));

        int t1 = requete.getEpreuves(jeux).size();
        int t2 = jeux.getEpreuves().size();
        System.out.println(t1 + " " + t2);
        // String nomEpreuve = "NatationRelais";
        // System.out.println(Epreuve.TypeSport.valueOf(nomEpreuve));
        // System.out.println(requete.getAthletes());
        // System.out.println(jeux.getClassementPays());
        for (Epreuve epreuve : test.keySet()) {
            System.out.println(epreuve.getParticipants().size());
        }
    }
}
