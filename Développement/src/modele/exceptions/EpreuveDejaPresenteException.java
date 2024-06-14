package src.modele.exceptions;

import src.modele.jeuxOlympique.*;


/**
 * Classe EpreuveDejaPresenteException
 */
public class EpreuveDejaPresenteException extends Exception {

    /**
     * Constructeur de la classe EpreuveDejaPresenteException
     * @param epreuve l'épreuve déjà présente
     */
    public EpreuveDejaPresenteException(Epreuve epreuve) {
        super("L'épreuve " + epreuve + " est déjà présente dans les jeux olympiques");
    }
}
