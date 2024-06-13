/**
 * Classe JeuxPasCommenceException
 */
public class JeuxPasCommenceException extends Exception{

    /**
     * Constructeur de la classe JeuxPasCommenceException
     * @param jeuxOlympique les jeux olympiques qui n'ont pas encore commencé
     */
    public JeuxPasCommenceException(JeuxOlympique jeuxOlympique) {
        super("Les jeux olympiques de " + jeuxOlympique + " n'ont pas encore commencé.");
    }
}
