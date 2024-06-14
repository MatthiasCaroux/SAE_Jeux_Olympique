package src.exceptions;
/**
 * Classe PasAssezDeJoueursException
 */
public class PasAssezDeJoueursException extends Exception {

    /**
     * Constructeur de la classe PasAssezDeJoueursException
     * @param nbJoueur le nombre de joueurs minimum requis
     */
    public PasAssezDeJoueursException(Integer nbJoueur) {
        super("Pas assez de joueurs dans l'équipe, l'équipe doit avoir " + nbJoueur + " joueurs.");
    }
}
