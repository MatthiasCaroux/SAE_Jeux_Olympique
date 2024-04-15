public class AthletismeHaie extends Athletisme{

    public AthletismeHaie() {
        super("Athlétisme Haie", false);
    }
    
    @Override
    public Pays getPaysVainqueur(){
        return null; // a modif
    }

    @Override
    public void classement(){};
}
