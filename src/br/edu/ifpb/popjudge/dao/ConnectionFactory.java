package br.edu.ifpb.popjudge.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jmysql://xq7t6tasopo9xxbs.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/jc1q6saag9lc5q1d/POP_JUDGE?useSSL=false";
	private static final String USER = "fy06tbg72m8rava4";
	private static final String PASS = "cbyy8rrf033m81qn";
	public Connection getConnection() throws SQLException{
		
//		try {
//			return DriverManager.getConnection("jdbc:mysql://poocare.ddns.net:3306/POP_JUDGE", "root", "");
//		} catch (SQLException exe){
			try {
				Class.forName(DRIVER);
				return  DriverManager.getConnection(URL, USER, PASS);
			} catch (SQLException | ClassNotFoundException exep){
				System.out.println("Nao foi posivel conectar com o Banco de Dados, tente novamente mais tarde");
				return null;
			}
//		}
	}
	
}
