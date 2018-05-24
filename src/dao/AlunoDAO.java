package dao;

import java.util.Collection;

import model.Aluno;

public interface AlunoDAO {
	
	public Aluno obtemAlunoPorID(int id);
	
	public void gravaAluno(Aluno c);
	
	public Collection<Aluno> obtemTodosAlunos();
	
	public void apagaAluno(Aluno c);
	
}
