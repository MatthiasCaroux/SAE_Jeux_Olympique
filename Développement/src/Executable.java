import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Executable
 */

public class Executable {
    
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


        EpreuveCollective hand = new EpreuveCollective(Epreuve.TypeSport.Handball, Epreuve.Sexe.M);

        
        try {
            hand.participer(equipe1);
            hand.participer(equipe2);
            Athlete test = new Athlete("Doe", "John", Epreuve.Sexe.M, USA, 80, 80, 80);
            hand.participer(test);
        } catch (Exception e) {
        } 

        try {
            Pays paysVainqueur = hand.getPaysVainqueur();
            Participant p = hand.getVainqueur();
        } catch (EpreuveNonCommenceeException e) {
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
            Athlete test = new Athlete("Doe", "John", Epreuve.Sexe.M, USA, 80, 80, 80);
            natrelais.participer(test);

        } catch (Exception e) {
            // system.err.println(e.getMessage());
        } 

        try {
            // natrelais.jouerEpreuve();
            // system.out.println(natrelais.rapport());
        } catch (Exception e) {
            // system.out.println(e.getMessage());
        }
        



        EpreuveIndividuelle natation = new EpreuveIndividuelle(Epreuve.TypeSport.NatationBrasse, Epreuve.Sexe.M);
        Athlete a1 = new Athlete("Tom", "Hollands", Epreuve.Sexe.M, USA, 80, 76, 80);
        Athlete a2 = new Athlete("Robert", "Mask", Epreuve.Sexe.M, France, 74, 76, 85);
        Pays allemagne = new Pays("Allemagne");
        Athlete a3 = new Athlete("Hans", "Zimmer", Epreuve.Sexe.M, allemagne, 78, 80, 82);

        try {
            natation.participer(a1);
            natation.participer(a2);
            natation.participer(a3);

        } catch (Exception e) {
            // system.err.println(e.getMessage());
        }



        try {
            // // system.out.println(jo2024.getClassementPays());
            jo2024.ajouteEpreuve(hand);
            jo2024.ajouteEpreuve(natation);
            jo2024.ajouteEpreuve(natrelais);

        } catch (Exception e) {
            // system.err.println(e.getMessage());
        }

        // system.out.println("USA : " + USA.getMedailles() + ", Score : " + USA.getScoreTotal());
        // system.out.println("France : " + France.getMedailles() + ", Score : " + France.getScoreTotal());
        // system.out.println("Allemagne : " + allemagne.getMedailles() + ", Score : " + allemagne.getScoreTotal());
     







        Map<Epreuve, List<Participant>> map = jo2024.getParticipantsParEpreuve("./Développement/donnees.csv");
        System.out.println(map);

        // System.out.println();
        // for (Map.Entry<Epreuve, List<Participant>> dico : map.entrySet()) {
        //     System.out.println("------" + dico.getKey() + "--------");
        //     for (Participant participant : dico.getValue()) {
        //         if (participant instanceof Athlete) {
        //             System.out.println("Athlete: " + participant);
        //         } else {
        //             Equipe equipe = (Equipe) participant;
        //             System.out.println(" - " + equipe.getNomEquipe() + " " + equipe.getLesAthlètes());
        //         }
        //     }
        //     System.out.println();
        // }







        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        while (choix != 5) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                 Menu                   ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1) Simuler les Jeux Olympiques         ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 2) Afficher tous les athlètes des Jeux ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 3) Afficher toute les épreuves         ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 4) Afficher participant par Epreuve    ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 5) Quitter                             ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Choisissez une option: ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    try{
                        // jo2024.lancerUneEpreuve(hand);
                        // System.out.println(hand.rapport());
                        jo2024.lancerToutesLesEpreuves();
                        System.out.println(jo2024.getClassementPays());
                        for (Pays pays : jo2024.getLesPays()) {
                            System.out.println(pays.getNomPays() + " : " + pays.getMedailles() + ", Score : " + pays.getScoreTotal());
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println(jo2024.lesParticipantsAuxJo());
                    break;
                case 3:
                    for (Epreuve ep : jo2024.getEpreuves()) {
                        for (Participant part : ep.getParticipants()) {
                            System.out.print(ep + " ");
                            System.out.print(part);
                            if (part instanceof Equipe) {
                                Equipe equipe = (Equipe) part;
                                System.out.println(" : " + equipe.getLesAthlètes().size());
                            } else {
                                System.out.println();
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println();
                    for (Map.Entry<Epreuve, List<Participant>> dico : map.entrySet()) {
                        System.out.println("--------" + dico.getKey() + "--------");
                        for (Participant participant : dico.getValue()) {
                            if (participant instanceof Athlete) {
                                System.out.println(" - Athlete: " + participant);
                            } else {
                                Equipe equipe = (Equipe) participant;
                                System.out.println(" - " + equipe.getNomEquipe() + " : " + equipe.getLesAthlètes());
                            }
                        }
                        System.out.println();
                    }
                    break;
                case 5:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
}
