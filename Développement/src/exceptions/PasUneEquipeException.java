package src.exceptions;

/**
 * Classe PasUneEquipeException
 */
public class PasUneEquipeException extends Exception{

    /**
     * Constructeur de la classe PasUneEquipeException
     */
    public PasUneEquipeException() {
        super("Vous avez essayé d'ajouter un participant qui n'est pas une équipe.");
    }
}
