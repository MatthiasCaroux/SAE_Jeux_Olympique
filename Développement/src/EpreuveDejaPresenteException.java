public class EpreuveDejaPresenteException extends Exception {

    public EpreuveDejaPresenteException(Epreuve epreuve) {
        super("L'épreuve " + epreuve + " est déjà présente dans les jeux olympiques");
    }
}
