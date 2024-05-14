// import org.junit.Test;

public class TestsJO {
    // @Test
    // public void testAthlete() {
    public static void main(String[] args) {
        Pays f = new Pays("France");
        Athlete a1 = new Athlete("Moi", "Toi", 'M', f, 25, 20, 35);
        Pays b = new Pays("Belgique");
        Athlete a2 = new Athlete("Lui", "Elle", 'F', b, 30, 25, 40);

        AthletismeHaie h = new AthletismeHaie();
        EpreuveIndividuelle e = new EpreuveIndividuelle(h, 'M');
        e.ajouterParticipant(a1);
        e.ajouterParticipant(a2);
        System.out.println(e.getParticipants());
        System.out.println(e.getVainqueur()); 
        System.out.println(e.getPaysVainqueur()); 
        System.out.println(a1.getScore());
        System.out.println(e.getParticipants());
        System.out.println(h.getParticipants());
        e.jouer();
        System.out.println(a1.getScore());
        System.out.println(a2.getScore());
        System.out.println(e.getClassement());
    }
    
};