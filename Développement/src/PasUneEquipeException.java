
public class PasUneEquipeException extends Exception{

    public PasUneEquipeException() {
        super("Vous avez essayé d'ajouter un participant qui n'est pas une équipe.");
    }
}
