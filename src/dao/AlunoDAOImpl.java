package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import conexao.ConexaoMySQL;
import model.Aluno;

public class AlunoDAOImpl implements AlunoDAO {
	public Aluno obtemAlunoPorID(int id) {
		Connection c = ConexaoMySQL.getConexao();
		Aluno con = null;		
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Aluno WHERE id="+id+";");
			if (rs.next()) {
				con = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("cpf"), rs.getString("datanascimento"));
			}
		}
		catch(SQLException e) {
			System.out.println("Erro na consulta");
			System.exit(1);			
		}
		return con;
	}
	
	public void gravaAluno(Aluno c) {
		Connection conn = ConexaoMySQL.getConexao();
		try {
			Statement stmt = conn.createStatement();
			if (obtemAlunoPorID(c.getId())==null) {
				// Aluno não existe ==> inserir na tabela
				stmt.executeUpdate( "INSERT INTO Aluno VALUES ("+c.getId()+",'"
																+c.getNome()+"','"
																+c.getTelefone()+"','"
																+c.getCpf()+"','"
																+ c.getDatanascimento()+"');" );
			}
			else {
				// Aluno já existe ==> atualizar na tabela
				stmt.executeUpdate( "UPDATE Aluno SET nome='"+c.getNome()
											+"', telefone='"+c.getTelefone()
											+"'', cpf='"+ c.getCpf()
											+"'', datanascimento='"+ c.getDatanascimento()
											+"' WHERE id="+c.getId()+";" );
			}
		}
		catch(SQLException e) {
			System.out.println("Erro na consulta (grava)");
			System.exit(1);				
		}
	}
	
	public Collection<Aluno> obtemTodosAlunos() {
		Connection c = ConexaoMySQL.getConexao();
		List<Aluno> Alunos = new LinkedList<Aluno>();		
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Aluno ORDER BY id;");
			while (rs.next()) {
				Alunos.add(new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("cpf"), rs.getString("datanascimento")));
			}
		}
		catch(SQLException e) {
			System.out.println("Erro na consulta");
			System.exit(1);			
		}
		return Alunos;		
	}
	
	public void apagaAluno(Aluno c) {
		try {
			Connection conn = ConexaoMySQL.getConexao();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate( "DELETE FROM Aluno WHERE id="+c.getId()+";" );
		}
		catch(SQLException e) {
			System.out.println("Erro na consulta");
			System.exit(1);				
		}
	}

}
