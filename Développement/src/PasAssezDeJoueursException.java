public class PasAssezDeJoueursException extends Exception {

    public PasAssezDeJoueursException(Integer nbJoueur) {
        super("Pas assez de joueurs dans l'équipe, l'équipe doit avoir " + nbJoueur + " joueurs.");
    }
}
