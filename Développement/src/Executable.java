<<<<<<< HEAD
import java.util.List;
import java.util.Map;
import java.util.Scanner;

=======
/**
 * Executable
 */
>>>>>>> origin
public class Executable {
    
    public static void main(String[] args) {

        JeuxOlympique jo = new JeuxOlympique(2024, "Paris", "JO 2024");

        Pays France = new Pays("France");
        Pays USA = new Pays("USA");
        
        Athlete Jean = new Athlete("Dupont", "Jean", Epreuve.Sexe.M, France, 75, 80, 85);
        Athlete Lucas = new Athlete("Hautin", "Lucas", Epreuve.Sexe.M, France, 85, 80, 75);
        Athlete Jack = new Athlete("Doe", "Jack", Epreuve.Sexe.M, France, 75, 80, 80);
        Athlete Mark = new Athlete("Patrick", "Mark", Epreuve.Sexe.M, France, 80, 85, 85);
        Athlete test = new Athlete("Doe", "John", Epreuve.Sexe.M, France, 80, 80, 80);
        Athlete test2 = new Athlete("Doe", "Jane", Epreuve.Sexe.M, France, 60, 95, 90);
        Athlete test3 = new Athlete("Doe", "Jeahn", Epreuve.Sexe.M, France, 80, 80, 80);


        Equipe equipe1 = new Equipe("Equipe 1", France, Epreuve.Sexe.M);
        equipe1.ajouterMembre(Jean);
        equipe1.ajouterMembre(Lucas);
        equipe1.ajouterMembre(Jack);
        equipe1.ajouterMembre(Mark);
        equipe1.ajouterMembre(test);
        equipe1.ajouterMembre(test2);
        equipe1.ajouterMembre(test3);


        EpreuveCollective hand = new EpreuveCollective(Epreuve.TypeSport.Handball, Epreuve.Sexe.M);
        try {
            hand.participer(equipe1);
            jo.ajouteEpreuve(hand);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        






        Map<Epreuve, List<Participant>> map = jo.getParticipantsParEpreuve("./Développement/donnees.csv");
        System.out.println(map);
        List<Participant> participants = map.get(hand);
        for (Participant participant : participants) {
            if (participant instanceof Athlete) {
                System.out.println("Athlete: " + participant);
            } else {
                Equipe equipe = (Equipe) participant;
                System.out.println("Equipe: " + equipe.getLesAthlètes());
            }
        }
































        // Scanner scanner = new Scanner(System.in);
        // int choix = 0;

        // while (choix != 4) {
        //     System.out.println("╔════════════════════════════════════════╗");
        //     System.out.println("║                 Menu                   ║");
        //     System.out.println("╠════════════════════════════════════════╣");
        //     System.out.println("║ 1) Simuler les Jeux Olympiques         ║");
        //     System.out.println("╠════════════════════════════════════════╣");
        //     System.out.println("║ 2) Afficher tous les athlètes des Jeux ║");
        //     System.out.println("╠════════════════════════════════════════╣");
        //     System.out.println("║ 3) Afficher toute les épreuves         ║");
        //     System.out.println("╠════════════════════════════════════════╣");
        //     System.out.println("║ 4) Quitter                             ║");
        //     System.out.println("╚════════════════════════════════════════╝");
        //     System.out.print("Choisissez une option: ");
        //     choix = scanner.nextInt();

        //     switch (choix) {
        //         case 1:
        //             System.out.println("lancement des jeux");
        //             break;
        //         case 2:
        //             System.out.println("toto");
        //             break;
        //         case 3:
        //             System.out.println("les compétitions sont :");
        //             break;
        //         case 4:
        //             System.out.println("Au revoir!");
        //             break;
        //         default:
        //             System.out.println("Option invalide. Veuillez réessayer.");
        //     }
        // }
    }
}
