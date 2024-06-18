package src.basededonnee;

import org.junit.Test;

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

    // @Test
    // public void testInscriptionAndDelete() {
    //     try {
    //         Requete requete = new Requete();
    //         requete.ajouterAthlete("toto", ");
    //         assert requete.dansUtilisateur("toto", "toto@gmail.com", "toto");
    //         requete.inscription("titi", "tit@test.com", "titi");
    //         assert requete.dansUtilisateur("titi", "tit@test.com", "titi");
    //         int id = requete.getIdAthlete(null, null)
    //     } catch (Exception e) {
    //         System.out.println("Erreur de connexion à la base de donnée");
    //     }
    // }
}
