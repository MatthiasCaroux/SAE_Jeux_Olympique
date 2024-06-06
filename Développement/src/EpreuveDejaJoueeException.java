public class EpreuveDejaJoueeException extends Exception {

    public EpreuveDejaJoueeException(Epreuve epreuve) {
        super("L'épreuve " + epreuve + " a déjà été jouée");
    }
}
