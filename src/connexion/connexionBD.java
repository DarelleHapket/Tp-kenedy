package connexion;

import java.sql.DriverManager;

public class connexionBD {
	
	public static java.sql.Connection Connect() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("Driver ok");
			String url = "jdbc:mysql://localhost:8080/kenedy";
        	String user = "root";
			String password ="";
			java.sql.Connection conn = DriverManager.getConnection(url,user,password);
			System.out.println("Connexion bien etablie");
			return conn;
			}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
//	 public static void main(String[] args) sera appeler dans le main principal pour ne pas avoir deux main {
//		java.sql.Connection cn = Connect();ji
//	
	


