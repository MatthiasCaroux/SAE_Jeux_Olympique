package src.modele;

import src.exceptions.*;

/**
 * Executable2
 */
public class Executable2 {

    public static void main(String[] args) {

        JeuxOlympique jo2024 = new JeuxOlympique(2024, "Paris", "JO 2024");

        Pays France = new Pays("France");
        Pays USA = new Pays("USA");
        
        Athlete Jean = new Athlete("Dupont", "Jean", Epreuve.Sexe.M, France, 75, 80, 85);
        Athlete Lucas = new Athlete("Hautin", "Lucas", Epreuve.Sexe.M, France, 85, 80, 75);
        Athlete Jack = new Athlete("Doe", "Jack", Epreuve.Sexe.M, France, 75, 80, 80);
        Athlete Mark = new Athlete("Patrick", "Mark", Epreuve.Sexe.M, France, 80, 85, 85);

        Equipe equipe1 = new Equipe("Equipe 1", France, Epreuve.Sexe.M);
        equipe1.ajouterMembre(Jean);
        equipe1.ajouterMembre(Lucas);
        equipe1.ajouterMembre(Jack);
        equipe1.ajouterMembre(Mark);
        equipe1.ajouterMembre(new Athlete("Hanks", "Tom", Epreuve.Sexe.M, France, 90, 70, 80));
        equipe1.ajouterMembre(new Athlete("Doe", "Jane", Epreuve.Sexe.M, France, 60, 95, 90));
        equipe1.ajouterMembre(new Athlete("Doe", "John", Epreuve.Sexe.M, France, 80, 80, 80));


        Athlete Matthias = new Athlete("Caroux", "Matthias", Epreuve.Sexe.M, USA, 75, 80, 85);
        Athlete Thomas = new Athlete("Doe", "Thomas", Epreuve.Sexe.M, USA, 75, 80, 80);
        Athlete Paul = new Athlete("Patrick", "Paul", Epreuve.Sexe.M, USA, 80, 85, 85);
        Athlete Pierre = new Athlete("Dupont", "Pierre", Epreuve.Sexe.M, USA, 85, 80, 75);

        Equipe equipe2 = new Equipe("Equipe 2", USA, Epreuve.Sexe.M);
        equipe2.ajouterMembre(Matthias);
        equipe2.ajouterMembre(Thomas);
        equipe2.ajouterMembre(Paul);
        equipe2.ajouterMembre(Pierre);
        equipe2.ajouterMembre(new Athlete("Doe", "Jane", Epreuve.Sexe.M, USA, 60, 95, 90));
        equipe2.ajouterMembre(new Athlete("Doe", "John", Epreuve.Sexe.M, USA, 80, 80, 80));
        equipe2.ajouterMembre(new Athlete("Doe", "Jill", Epreuve.Sexe.M, USA, 70, 85, 85));
        System.out.println(equipe2.getLesAthlètes());
        System.out.println("Il y a .... dans l'équipe 2" + equipe2.getNbAthlètes());

        // Sport handball = new Sport(Sport.TypeSport.Handball);
        // System.out.println(handball.getNomSport());

        EpreuveCollective hand = new EpreuveCollective(Epreuve.TypeSport.Handball, Epreuve.Sexe.M);
        System.out.println(hand.getSport());
        System.out.println(hand.getSexe());
        
        try {
            hand.participer(equipe1);
            hand.participer(equipe2);
            // hand.participer(equipe2);
            Athlete test = new Athlete("Doe", "John", Epreuve.Sexe.M, USA, 80, 80, 80);
            hand.participer(test);
            // System.out.println(hand.getEquipes());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } 
        System.out.println(hand.getParticipants());
        // System.out.println("ICICI");

        try {
            // hand.jouerEpreuve(); // Pour tester l'erreur, commenter cette ligne
            System.out.println(hand.getClassement());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 

        try {
            Pays paysVainqueur = hand.getPaysVainqueur();
            System.out.println("Le pays vainqueur est : " + paysVainqueur);
            Participant p = hand.getVainqueur();
            System.out.println("Le vainqueur est : " + p);
        } catch (EpreuveNonCommenceeException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(hand.rapport());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test NationRelais

        Equipe test1 = new Equipe("Equipe 1", France, Epreuve.Sexe.M);
        test1.ajouterMembre(Jean);
        test1.ajouterMembre(Lucas);
        test1.ajouterMembre(Jack);
        test1.ajouterMembre(Mark);

        Equipe test2 = new Equipe("Equipe 2", USA, Epreuve.Sexe.M);
        test2.ajouterMembre(Matthias);
        test2.ajouterMembre(Thomas);
        test2.ajouterMembre(Paul);
        test2.ajouterMembre(new Athlete("Doe", "Jane", Epreuve.Sexe.M, USA, 60, 95, 90));

        EpreuveCollective natrelais = new EpreuveCollective(Epreuve.TypeSport.NatationRelais, Epreuve.Sexe.M);
        try {
            natrelais.participer(test1);
            natrelais.participer(test2);
            // natrelais.participer(test2);
            Athlete test = new Athlete("Doe", "John", Epreuve.Sexe.M, USA, 80, 80, 80);
            natrelais.participer(test);
            // System.out.println(natrelais.getEquipes());
            System.out.println(natrelais.getClassement());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } 

        try {
            // natrelais.jouerEpreuve();
            System.out.println(natrelais.rapport());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

        System.out.println("USA : " + USA.getMedailles() + ", Score : " + USA.getScoreTotal());
        System.out.println("France : " + France.getMedailles() + ", Score : " + France.getScoreTotal());

        System.out.println("Fin de l'executable pour les épreuves collectives\n\n");


        EpreuveIndividuelle natation = new EpreuveIndividuelle(Epreuve.TypeSport.NatationBrasse, Epreuve.Sexe.M);
        Athlete a1 = new Athlete("Tom", "Hollands", Epreuve.Sexe.M, USA, 80, 76, 80);
        Athlete a2 = new Athlete("Robert", "Mask", Epreuve.Sexe.M, France, 74, 76, 85);
        Pays allemagne = new Pays("Allemagne");
        Athlete a3 = new Athlete("Hans", "Zimmer", Epreuve.Sexe.M, allemagne, 78, 80, 82);

        try {
            natation.participer(a1);
            natation.participer(a2);
            natation.participer(a3);
            // natation.participer(a1);
            System.out.println(natation.getParticipants());
            // natation.jouerEpreuve(); // Pour tester l'erreur, commenter cette ligne
            System.out.println(natation.getClassement());
            System.out.println("Le pays vainqueur est : " + natation.getPaysVainqueur());
            System.out.println("Le vainqueur est : " + natation.getVainqueur());
            System.out.println(natation.rapport());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("USA : " + USA.getMedailles() + ", Score : " + USA.getScoreTotal());
        System.out.println("France : " + France.getMedailles() + ", Score : " + France.getScoreTotal());
        System.out.println("Allemagne : " + allemagne.getMedailles() + ", Score : " + allemagne.getScoreTotal());

        System.out.println("Fin de l'executable pour les épreuves individuelles\n\n");

        try {
            // System.out.println(jo2024.getClassementPays());
            jo2024.ajouteEpreuve(hand);
            jo2024.ajouteEpreuve(natation);
            jo2024.ajouteEpreuve(natrelais);
            System.out.println(jo2024.getLesPays());
            // jo2024.ajouteEpreuve(natation);
            jo2024.lancerUneEpreuve(hand);
            System.out.println(hand.rapport());
            // System.out.println(natation.rapport());
            jo2024.lancerToutesLesEpreuves();
            System.out.println(natrelais.rapport());
            System.out.println(natation.rapport());
            System.out.println(jo2024.getEpreuves());
            System.out.println(jo2024.getClassementPays());
            System.out.println("Les épreuves auquels Matthias a participé : " + jo2024.getEpreuveDuParticipant(Matthias));
            System.out.println("Les épreuves auquels la France a participé : " + jo2024.getEpreuvesDuPays(France));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("USA : " + USA.getMedailles() + ", Score : " + USA.getScoreTotal());
        System.out.println("France : " + France.getMedailles() + ", Score : " + France.getScoreTotal());
        System.out.println("Allemagne : " + allemagne.getMedailles() + ", Score : " + allemagne.getScoreTotal());
        
    }
}
