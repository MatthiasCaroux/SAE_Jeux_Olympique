package src.exceptions;
/**
 * Classe TropDeJoueursException
 */
public class TropDeJoueursException extends Exception{

    /**
     * Constructeur de la classe TropDeJoueursException
     * @param nbJoueur le nombre de joueurs maximum requis
     */
    public TropDeJoueursException(Integer nbJoueur) {
        super("Trop de joueurs dans l'équipe, l'équipe doit avoir " + nbJoueur + " joueurs.");
    }
}
