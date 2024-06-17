package src.basededonnee;

import java.sql.*;

/**
 * ConnexionBD
 */
public class ConnexionBD {

    

    private Connection mysql;
	private boolean connecte=false;



	public ConnexionBD() throws ClassNotFoundException{
		this.mysql=null;
		this.connecte=false;
		Class.forName("org.mariadb.jdbc.Driver");
        // try {
		// 	System.out.println("Tentative de connexion à la base de donnée");
        //     this.connecter();
		// 	System.out.println("Connexion reussi !");
        // } catch (Exception e) {
        //     System.out.println("Erreur de connexion à la base de donnée");
        // } 
	}

	public void connecter() throws SQLException {
		this.mysql=null;
		this.connecte=false;
		this.mysql = DriverManager.getConnection("jdbc:mysql://servinfo-maria:3306/DBnagarajah", "nagarajah", "nagarajah");
		this.connecte=true;
	}

	public void close() throws SQLException {
		this.mysql.close();
		this.connecte=false;
	}

    public boolean isConnecte() { 
        return this.connecte;
    }

    public Blob createBlob()throws SQLException{
		return this.mysql.createBlob();
	}

	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
}