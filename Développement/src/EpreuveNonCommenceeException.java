public class EpreuveNonCommenceeException extends Exception {

    public EpreuveNonCommenceeException(Epreuve epreuve) {  
        super("L'épreuve "+ epreuve + " n'a pas encore commencé");
    }
}
