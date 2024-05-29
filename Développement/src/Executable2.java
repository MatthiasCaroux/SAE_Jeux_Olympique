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
        equipe1.ajouterMembre(new Athlete("Hanks", "Tom", 'M', France, 90, 70, 80));
        equipe1.ajouterMembre(new Athlete("Doe", "Jane", 'M', France, 60, 95, 90));
        equipe1.ajouterMembre(new Athlete("Doe", "John", 'M', France, 80, 80, 80));



        Athlete Matthias = new Athlete("Caroux", "Matthias", 'M', USA, 75, 80, 85);
        Athlete Thomas = new Athlete("Doe", "Thomas", 'M', USA, 75, 80, 80);
        Athlete Paul = new Athlete("Patrick", "Paul", 'M', USA, 80, 85, 85);
        Athlete Pierre = new Athlete("Dupont", "Pierre", 'M', USA, 85, 80, 75);


        Equipe equipe2 = new Equipe("Equipe 2", USA);
        equipe2.ajouterMembre(Matthias);
        equipe2.ajouterMembre(Thomas);
        equipe2.ajouterMembre(Paul);
        equipe2.ajouterMembre(Pierre);
        equipe2.ajouterMembre(new Athlete("Doe", "Jane", 'M', USA, 60, 95, 90));
        equipe2.ajouterMembre(new Athlete("Doe", "John", 'M', USA, 80, 80, 80));
        equipe2.ajouterMembre(new Athlete("Doe", "Jill", 'M', USA, 70, 85, 85));
        System.out.println(equipe2.getLesAthlètes());




        // Sport handball = new Sport(Sport.TypeSport.Handball);
        // System.out.println(handball.getNomSport());

        EpreuveCollective football = new EpreuveCollective(Epreuve.TypeSport.Handball, Epreuve.Sexe.M);
        System.out.println(football.getSport());
        System.out.println(football.getSexe());
        
        try {
            football.participer(equipe1);
            football.participer(equipe2);
            System.out.println(football.getEquipes());
        } catch (PasViableException e) {
            System.err.println("L'équipe n'est pas viable");
        }
        football.getEquipes();

        Pays paysVainqueur = football.getPaysVainqueur();
        System.out.println("Le pays vainqueur est : " + paysVainqueur);
        Participant p = football.getVainqueur();
        System.out.println("Le vainqueur est : " + p);
    }
}
