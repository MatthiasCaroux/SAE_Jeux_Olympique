/**
 * Classe EquipeDejaParticipanteException
 */
public class EquipeDejaParticipanteException extends Exception{

    /**
     * Constructeur de la classe EquipeDejaParticipanteException
     */
    public EquipeDejaParticipanteException(){
        super("L'équipe a déjà été ajouter à cette épreuve");
    }
}
