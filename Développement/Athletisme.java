public abstract class Athletisme extends Sport{

    public Athletisme(String nomSport, boolean estCollectif) {
        super(nomSport, estCollectif);
    }

    @Override
    public Pays getPaysVainqueur(){
        return null;
    }
}
