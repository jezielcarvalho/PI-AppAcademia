package model;

import java.util.Collection;

import javax.swing.table.AbstractTableModel;

public class AlunoTableModel extends AbstractTableModel {

	private String[] columnNames = {"ID","Nome","Telefone","CPF","DadaNascimento"};
	private Collection<Aluno> alunos;
	
	public AlunoTableModel(Collection<Aluno> conts) {
		alunos = conts;
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return alunos.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Aluno c = (Aluno) alunos.toArray()[arg0];
		switch (arg1) {
		case 0:
			return c.getId();
		case 1:
			return c.getNome();
		case 2:
			return c.getTelefone();
		case 3:
			return c.getCpf();
		case 4:
			return c.getDatanascimento();
		}
		return null;
	}
	
	@Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		switch (columnIndex){
		case 0: 
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		}
		return null;
	}

}
