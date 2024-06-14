package src.modele.exceptions;

import src.modele.jeuxOlympique.*;

/**
 * Classe EpreuveDejaJoueeException
 */
public class EpreuveDejaJoueeException extends Exception {

    /**
     * Constructeur de la classe EpreuveDejaJoueeException
     * @param epreuve l'épreuve qui a déjà été jouée
     */
    public EpreuveDejaJoueeException(Epreuve epreuve) {
        super("L'épreuve " + epreuve + " a déjà été jouée");
    }
}
