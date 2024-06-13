/**
 * Interface Participant
 */
public interface Participant {

    /**
     * Méthode permettant de récupérer la force du participant
     * @return la force du participant
     */
    public int getForce();

    /**
     * Méthode permettant de récupérer l'endurance du participant
     * @return l'endurance du participant
     */
    public int getEndurance();

    /**
     * Méthode permettant de récupérer l'agilité du participant
     * @return l'agilité du participant
     */
    public int getAgilité();

    /**
     * Méthode permettant de récupérer le pays du participant
     * @return le pays du participant
     */
    public Pays getPays();
}
