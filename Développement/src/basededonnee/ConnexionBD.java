package src.basededonnee;

import java.sql.*;
import java.io.File;

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
		try{
			System.out.println("Connexion à la base de donnée");
			this.mysql=null;
			this.connecte=false;
			File dir = new File("/home/caroux");
			if (!(dir.exists() && dir.isDirectory())){//si le repertoire n'existe pas (en gros si je suis pas sur mon pc)
				this.mysql = DriverManager.getConnection("jdbc:mysql://servinfo-maria:3306/DBnagarajah", "nagarajah", "nagarajah");
				this.connecte=true;
			}
			else{
				System.out.println("Connexion à la base de donnée locale");
				this.mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/SAEJAVA", "matthias", "matthias");
				this.connecte=true;
				System.out.println("mysql : "+this.mysql);
			}
			System.out.println("Connexion reussi !");
		}
		catch(SQLException e){
			System.out.println("Erreur de connexion à la base de donnée");
			System.out.println(e);
		}
		
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