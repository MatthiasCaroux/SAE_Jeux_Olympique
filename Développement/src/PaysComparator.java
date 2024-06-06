import java.util.Comparator;

public class PaysComparator implements Comparator<Pays> {

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
