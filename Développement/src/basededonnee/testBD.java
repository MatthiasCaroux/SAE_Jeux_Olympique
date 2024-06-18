package src.basededonnee;

import org.junit.Test;

import src.modele.jeuxOlympique.Athlete;
import src.modele.jeuxOlympique.Epreuve;
import src.modele.jeuxOlympique.EpreuveCollective;
import src.modele.jeuxOlympique.EpreuveIndividuelle;
import src.modele.jeuxOlympique.Equipe;
import src.modele.jeuxOlympique.Pays;

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
            int idAthlete = requete.getIdAthlete("test", "prenomTest");
            requete.supprimerAthlete(idAthlete);
            assert !requete.dansAthlete("test", "prenomTest", idAthlete);
            idAthlete = requete.getIdAthlete("Ryan", "Crouser");
            requete.supprimerAthlete(idAthlete);
            assert !requete.dansAthlete("Ryan", "Crouser", idAthlete);
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de donnée");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
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
            // requete.ajouterPays("France");
            requete.ajouterPays("Allemagne");
            requete.ajouterPays("Italie");
            requete.ajouterAthlete("test", "prenomTest", 84, 69, 91, 'M', requete.getIdPays("France"));
            System.out.println(requete.idMaxTable("PAYS"));
        } catch (Exception e) {
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
    }
}
