package conexao;

import java.sql.*;

public class ConexaoMySQL {
	private static Connection con = null;
	private ConexaoMySQL() {}
	
	public static Connection getConexao() {
		
		String url = "jdbc:mysql://localhost:3306/Academia?useTimezone=true&serverTimezone=UTC";
		String user = "root";
		String password = "";
		
		try {
			if ( con == null ) {
				
			    Class.forName("com.mysql.cj.jdbc.Driver");			    
				con = DriverManager.getConnection(url, user, password);
				
			}
		}
		catch(SQLException e) {
			System.out.println("Erro de conexão com o banco");
			System.exit(1);
		}
		catch(ClassNotFoundException e) {
			System.out.println("Erro: driver do banco não encontrado");
			System.exit(1);
		}
		return con;
	}
}
