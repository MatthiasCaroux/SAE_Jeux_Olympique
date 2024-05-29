import java.lang.ProcessBuilder.Redirect.Type;



public class Executable2 {

    public static void main(String[] args) {

        JeuxOlympique jo2024 = new JeuxOlympique(2024, "Paris", "JO 2024");



        Pays France = new Pays("France");
        Pays USA = new Pays("USA");
        
        Athlete Jean = new Athlete("Dupont", "Jean", 'M', France, 75, 80, 85);
        Athlete Lucas = new Athlete("Hautin", "Lucas", 'M', France, 85, 80, 75);
        Athlete Jack = new Athlete("Doe", "Jack", 'M', France, 75, 80, 80);
        Athlete Mark = new Athlete("Patrick", "Mark", 'M', France, 80, 85, 85);

        Equipe equipe1 = new Equipe("Equipe 1", France);
        equipe1.ajouterMembre(Jean);
        equipe1.ajouterMembre(Lucas);
        equipe1.ajouterMembre(Jack);
        equipe1.ajouterMembre(Mark);



        Athlete Matthias = new Athlete("Caroux", "Matthias", 'M', USA, 75, 80, 85);
        Athlete Thomas = new Athlete("Doe", "Thomas", 'M', USA, 75, 80, 80);
        Athlete Paul = new Athlete("Patrick", "Paul", 'M', USA, 80, 85, 85);
        Athlete Pierre = new Athlete("Dupont", "Pierre", 'M', USA, 85, 80, 75);


        Equipe equipe2 = new Equipe("Equipe 2", USA);
        equipe2.ajouterMembre(Matthias);
        equipe2.ajouterMembre(Thomas);
        equipe2.ajouterMembre(Paul);
        equipe2.ajouterMembre(Pierre);


        

        Sport handball = new Sport(Sport.TypeSport.Handball);

        EpreuveCollective football = new EpreuveCollective(handball, 'M');
        football.participer(equipe1);
        football.participer(equipe2);
        football.getEquipes();

        Pays paysVainqueur = football.getPaysVainqueur();
        System.out.println("Le pays vainqueur est : " + paysVainqueur.toString());
        Participant p = football.getVainqueur();
        System.out.println("Le vainqueur est : " + p.toString());



        

    }
}
