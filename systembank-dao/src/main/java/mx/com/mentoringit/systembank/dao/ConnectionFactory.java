package mx.com.mentoringit.systembank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	//host, user, pass
	private String host;
	
	private String user;
	
	private String password;
	
	public static ConnectionFactory instancia;
	
	private Connection conexion;
	
	private ConnectionFactory(){
		host = "jdbc:mysql://localhost:3306/systembank";
		user = "root";
		password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public static ConnectionFactory getInstancia() {
		if(instancia == null) {
			instancia = new ConnectionFactory();
		}
		return instancia;
	}
	
	public Connection getConnection(){
		
		try {
			conexion = DriverManager.getConnection(host, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conexion;
	}
	
	public void closeConnection() {
		if(conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
