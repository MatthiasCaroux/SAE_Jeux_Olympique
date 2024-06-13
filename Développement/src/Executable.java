import java.util.List;
import java.util.Map;

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
    }
}
