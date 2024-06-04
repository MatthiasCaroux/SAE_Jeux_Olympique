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
            System.out.println("1");
            hand.participer(equipe2);
            System.out.println("2");
            Athlete test = new Athlete("Doe", "John", Epreuve.Sexe.M, USA, 80, 80, 80);
            hand.participer(test);
            System.out.println("3");
            // System.out.println(hand.getEquipes());
        } catch (PasAssezDeJoueursException e) {
            System.err.println(e.getMessage());
        } catch (TropDeJoueursException e) {
            System.err.println(e.getMessage());
        } catch (PasUneEquipeException e) {
            System.err.println(e.getMessage());
        } catch (EquipeDejaParticipanteException e) {
            System.err.println(e.getMessage());
        }

        Pays paysVainqueur = hand.getPaysVainqueur();
        System.out.println("Le pays vainqueur est : " + paysVainqueur);
        Participant p = hand.getVainqueur();
        System.out.println("Le vainqueur est : " + p);
    }
}
