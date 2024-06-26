package src.modele.exceptions;

import src.modele.jeuxOlympique.Epreuve;


/**
 * Classe EpreuveNonCommenceeException
 */
public class EpreuveNonCommenceeException extends Exception {

    /**
     * Constructeur de la classe EpreuveNonCommenceeException
     * @param epreuve l'épreuve qui n'a pas encore commencé
     */
    public EpreuveNonCommenceeException(Epreuve epreuve) {  
        super("L'épreuve "+ epreuve + " n'a pas encore commencé");
    }
}
