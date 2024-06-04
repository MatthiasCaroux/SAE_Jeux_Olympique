public class TropDeJoueursException extends Exception{

    public TropDeJoueursException(Integer nbJoueur) {
        super("Trop de joueurs dans l'équipe, l'équipe doit avoir " + nbJoueur + " joueurs.");
    }
}
