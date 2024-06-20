package src.basededonnee;

/**
 * Classe permettant d'exécuter des requêtes sur la base de données.
 */
public class Requete {
    
    private ConnexionBD connexionBD;

    /**
     * Constructeur de la classe Requete.
     * @throws BaseDeDonneeInaccessibleException si la base de données est inaccessible.
     * @throws ClassNotFoundException si la classe de connexion à la base de données n'est pas trouvée.
     */
    public Requete() throws BaseDeDonneeInaccessibleException, ClassNotFoundException {
        this.connexionBD = new ConnexionBD();
        try {
            this.connexionBD.connecter();
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    /**
     * Retourne le nom de la première colonne d'une table donnée.
     * @param nomTable le nom de la table.
     * @return le nom de la première colonne de la table.
     */
    public String getColumName(String nomTable) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from " + nomTable);
            ResultSet resultat = requete.executeQuery();
            ResultSetMetaData metaData = resultat.getMetaData();
            return metaData.getColumnName(1);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Retourne l'identifiant maximal d'une table donnée.
     * @param nomTable le nom de la table.
     * @return l'identifiant maximal de la table.
     * @throws SQLException si une erreur SQL survient.
     * @throws PasDeIdDansUtilisateurException si la table est "UTILISATEUR".
     */
    public int idMaxTable(String nomTable) throws SQLException, PasDeIdDansUtilisateurException {
        if (nomTable.equals("UTILISATEUR")) {
            throw new PasDeIdDansUtilisateurException();
        }
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select max(" + this.getColumName(nomTable) + ") as max from " + nomTable);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getInt("max");
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Vérifie les informations de connexion d'un utilisateur.
     * @param identifiant l'identifiant de l'utilisateur.
     * @param motDePasse le mot de passe de l'utilisateur.
     * @return true si les informations de connexion sont correctes, false sinon.
     * @throws BaseDeDonneeInaccessibleException si la base de données est inaccessible.
     */
    public boolean connexion(String identifiant, String motDePasse) throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from UTILISATEUR where identifiant = ? and mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, motDePasse);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    /**
     * Vérifie la présence d'un utilisateur avec les informations fournies.
     * @param identifiant l'identifiant de l'utilisateur.
     * @param mail le mail de l'utilisateur.
     * @param motDePasse le mot de passe de l'utilisateur.
     * @return true si l'utilisateur est présent, false sinon.
     */
    public boolean dansUtilisateur(String identifiant, String mail, String motDePasse) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from UTILISATEUR where identifiant = ? and email = ? and mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, mail);
            requete.setString(3, motDePasse);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Connexion défaillante");
            return false;
        }
    }

    /**
     * Vérifie la présence d'un utilisateur avec l'identifiant et le mot de passe fournis.
     * @param identifiant l'identifiant de l'utilisateur.
     * @param motDePasse le mot de passe de l'utilisateur.
     * @return true si l'utilisateur est présent, false sinon.
     */
    public boolean dansUtilisateur(String identifiant, String motDePasse) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from UTILISATEUR where identifiant = ? and mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, motDePasse);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Connexion défaillante");
            return false;
        }
    }

    /**
     * Retourne une liste des identifiants des utilisateurs.
     * @return une liste des identifiants des utilisateurs.
     * @throws BaseDeDonneeInaccessibleException si la base de données est inaccessible.
     */
    public List<String> getNomUtilisateur() throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select identifiant from UTILISATEUR");
            ResultSet resultat = requete.executeQuery();
            List<String> noms = new ArrayList<>();
            while (resultat.next()) {
                noms.add(resultat.getString("identifiant"));
            }
            return noms;
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    /**
     * Retourne le rôle d'un utilisateur.
     * @param identifiant l'identifiant de l'utilisateur.
     * @param motDePasse le mot de passe de l'utilisateur.
     * @return le rôle de l'utilisateur.
     * @throws BaseDeDonneeInaccessibleException si la base de données est inaccessible.
     */
    public char getRoleUtilisateur(String identifiant, String motDePasse) throws BaseDeDonneeInaccessibleException {
        try {
            if (! this.dansUtilisateur(identifiant, motDePasse)) {
                throw new UtilisateurInexistantException(identifiant);
            }
            PreparedStatement requete = this.connexionBD.prepareStatement("Select rôle from UTILISATEUR where identifiant = ? and mdp = ?");
            requete.setString(1, identifiant);
            requete.setString(2, motDePasse);
            ResultSet resultat = requete.executeQuery();
            resultat.next();
            return resultat.getString("rôle").charAt(0);
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    /**
     * Change le rôle d'un utilisateur.
     * @param identifiant l'identifiant de l'utilisateur.
     * @param role le nouveau rôle de l'utilisateur.
     * @throws BaseDeDonneeInaccessibleException si la base de données est inaccessible.
     */
    public void changerRoleUtilisateur(String identifiant, char role) throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Update UTILISATEUR set rôle = ? where identifiant = ?");
            requete.setString(1, role+"");
            requete.setString(2, identifiant);
            requete.executeUpdate();
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    /**
     * Inscrit un nouvel utilisateur dans la base de données.
     * @param identifiant l'identifiant de l'utilisateur.
     * @param mail le mail de l'utilisateur.
     * @param motDePasse le mot de passe de l'utilisateur.
     * @throws BaseDeDonneeInaccessibleException si la base de données est inaccessible.
     */
    public void inscription(String identifiant, String mail, String motDePasse) throws BaseDeDonneeInaccessibleException {
        try {
            if (! this.dansUtilisateur(identifiant, mail)) {
                PreparedStatement requete = this.connexionBD.prepareStatement("Insert into UTILISATEUR values ( ?, ?, ?, 'C')");
                requete.setString(1, identifiant);
                requete.setString(2, mail);
                requete.setString(3, motDePasse);
                requete.executeUpdate();
            } else {
                System.out.println("Utilisateur déjà existant");
                throw new UtilisateurDejaExistantException();
            }
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    /**
     * Supprime un utilisateur de la base de données.
     * @param identifiant l'identifiant de l'utilisateur à supprimer.
     * @throws UtilisateurInexistantException si l'utilisateur n'existe pas.
     */
    public void supprimerUtilisateur(String identifiant) throws UtilisateurInexistantException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("delete from UTILISATEUR where identifiant = ?");
            requete.setString(1, identifiant);
            requete.executeUpdate();
        } catch (Exception e) {
            throw new UtilisateurInexistantException();
        }
    }

    /**
     * Enregistre un voyage dans la base de données.
     * @param identifiant l'identifiant de l'utilisateur.
     * @param destination la destination du voyage.
     * @param note la note du voyage.
     * @param date la date du voyage.
     * @throws BaseDeDonneeInaccessibleException si la base de données est inaccessible.
     */
    public void enregistreVoyage(String identifiant, String destination, int note, String date) throws BaseDeDonneeInaccessibleException {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Insert into VOYAGE values ( ?, ?, ?, ?, ?)");
            requete.setInt(1, this.idMaxTable("VOYAGE") + 1);
            requete.setString(2, identifiant);
            requete.setString(3, destination);
            requete.setInt(4, note);
            requete.setString(5, date);
            requete.executeUpdate();
        } catch (Exception e) {
            throw new BaseDeDonneeInaccessibleException();
        }
    }

    /**
     * Vérifie la présence d'un utilisateur avec l'identifiant et le mail fournis.
     * @param identifiant l'identifiant de l'utilisateur.
     * @param mail le mail de l'utilisateur.
     * @return true si l'utilisateur est présent, false sinon.
     */
    public boolean dansUtilisateur(String identifiant, String mail) {
        try {
            PreparedStatement requete = this.connexionBD.prepareStatement("Select * from UTILISATEUR where identifiant = ? and email = ?");
            requete.setString(1, identifiant);
            requete.setString(2, mail);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
