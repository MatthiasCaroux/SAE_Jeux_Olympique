import java.util.Comparator;

/**
 * Classe permettant de comparer les pays
 */
public class PaysComparator implements Comparator<Pays> {

    /**
     * Méthode permettant de comparer deux pays
     * @param p1 le premier pays
     * @param p2 le deuxième pays
     * @return -1 si le premier pays est plus fort, 1 si le deuxième pays est plus fort, 0 si les deux pays sont égaux
     */
    @Override
    public int compare(Pays p1, Pays p2) {
        if (p1.getScoreTotal() > p2.getScoreTotal()) {
            return -1;
        } else if (p1.getScoreTotal() < p2.getScoreTotal()) {
            return +1;
        } else {
            return 0;
        }
    }

}
