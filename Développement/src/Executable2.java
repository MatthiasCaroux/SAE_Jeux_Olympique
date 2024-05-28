public class Executable2 {
    public static void main(String[] args) {
        Pays France = new Pays("France");
        Pays USA = new Pays("USA");
        
        Athlete Jean = new Athlete("Dupont", "Jean", 'M', France, 75, 80, 85);
        Athlete Lucas = new Athlete("Hautin", "Lucas", 'M', France, 85, 80, 75);
        Athlete Jack = new Athlete("Doe", "Jack", 'M', USA, 75, 80, 80);
        Athlete Mark = new Athlete("Patrick", "Mark", 'M', USA, 80, 85, 85);

        

    }
}
