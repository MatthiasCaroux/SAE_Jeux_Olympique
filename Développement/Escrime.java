public abstract class Escrime extends Sport{
    public Escrime(String nomSport, boolean estCollectif) {
        super(nomSport, estCollectif);
    }

    public int meilleurScore(){
        return 0;
    }

    @Override
    public Pays getPaysVainqueur(){
        return null;
    }

    @Override
    public void classement(){};
}
