public class JeuxPasCommenceException extends Exception{

    public JeuxPasCommenceException(JeuxOlympique jeuxOlympique) {
        super("Les jeux olympiques de " + jeuxOlympique + " n'ont pas encore commencé.");
    }
}
