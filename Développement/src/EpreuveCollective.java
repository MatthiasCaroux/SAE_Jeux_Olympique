import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EpreuveCollective extends Epreuve{

    private ArrayList<Equipe> equipes = new ArrayList<>();

    public EpreuveCollective(TypeSport sport, Sexe sexe) {
        super(sport, sexe);
        this.equipes = new ArrayList<>();
    }

    public ArrayList<Equipe> getEquipes() {
        return this.equipes;
    }

    // @Override
    // public Participant getVainqueur() {

    //     double scoreMax = 0;
    //     Equipe equipeGagnante = this.equipes.get(0);
    //     for (Equipe equipe : equipes) {
    //         double scoreEquipe = calculeScore(equipe);
    //         if (scoreEquipe > scoreMax) {
    //             scoreMax = scoreEquipe;
    //             equipeGagnante = equipe;
    //         }
    //     }
    //     return equipeGagnante;
    // }



    @Override
    public Participant getVainqueur() {
        if (this.equipes.isEmpty()) {
            return null;
        }
        Map<Equipe, Double> scores = new HashMap<>();
        Equipe equipeGagnante = this.equipes.get(0);
        for (Equipe equipe : equipes) {
            double scoreEquipe = calculeScore(equipe);
            scores.put(equipe, scoreEquipe);
        }
        System.out.println(scores);
        for (Map.Entry<Equipe, Double> entry : scores.entrySet()) {
            if (entry.getValue() > scores.get(equipeGagnante)) {
                equipeGagnante = entry.getKey();
            }
        }
        return equipeGagnante;
    }


    @Override
    public Pays getPaysVainqueur() {
        if (this.getVainqueur() == null) {
            return null;
        }
        return getVainqueur().getPays();
    }

    @Override
    public void participer(Participant participant) throws TropDeJoueursException, PasAssezDeJoueursException, PasUneEquipeException, EquipeDejaParticipanteException {
        if(participant instanceof Equipe) {
            Equipe equipe = (Equipe) participant;
            if (this.equipes.contains(participant)) {
                throw new EquipeDejaParticipanteException();
            }
            switch (this.getSport()) {
                case Handball:
                    if (equipe.getNbAthlètes() > 7) {
                        throw new TropDeJoueursException(7);
                    } else if (equipe.getNbAthlètes() < 7) {
                        throw new PasAssezDeJoueursException(7);
                    } else {
                        this.equipes.add(equipe);
                    }
                    break;
                case Volley:
                    if (equipe.getNbAthlètes() > 6) {
                        throw new TropDeJoueursException(6);
                    } else if (equipe.getNbAthlètes() < 6) {
                        throw new PasAssezDeJoueursException(6);
                    } else {
                        this.equipes.add(equipe);
                    }
                    break;
                case NatationRelais:
                    if (equipe.getNbAthlètes() > 4) {
                        throw new TropDeJoueursException(4);
                    } else if (equipe.getNbAthlètes() < 4) {
                        throw new PasAssezDeJoueursException(4);
                    } else {
                        this.equipes.add(equipe);
                    }
                    break;
                case AthlétismeRelais:
                    if (equipe.getNbAthlètes() > 4) {
                        throw new TropDeJoueursException(4);
                    } else if (equipe.getNbAthlètes() < 4) {
                        throw new PasAssezDeJoueursException(4);
                    } else {
                        this.equipes.add(equipe);
                    }
                    break;
                default:
                    break;
            }
        } else {
            throw new PasUneEquipeException();
        }
    }

    public boolean estViable(Equipe equipe){
        switch (this.getSport()) {
            case Handball:
                if (equipe.getNbAthlètes() == 7) {
                    return true;
                }
                return false;
            case Volley:
                if (equipe.getNbAthlètes() == 6) {
                    return true;
                }
                return false;
            case NatationRelais:
                if (equipe.getNbAthlètes() == 4) {
                    return true;
                }
                return false;
            case AthlétismeRelais:
                if (equipe.getNbAthlètes() == 4) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}