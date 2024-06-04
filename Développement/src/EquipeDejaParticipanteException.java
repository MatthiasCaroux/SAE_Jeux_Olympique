public class EquipeDejaParticipanteException extends Exception{

    public EquipeDejaParticipanteException(){
        super("L'équipe a déjà été ajouter à cette épreuve");
    }
}
