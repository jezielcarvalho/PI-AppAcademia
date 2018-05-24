package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import model.Aluno;
import model.AlunoTableModel;
import dao.AlunoDAO;
import dao.AlunoDAOImpl;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Collection;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class ViewAluno extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtCPF;
	private JTextField txtDataNascimento;
	private JTable table;
	
	private AlunoDAO adao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAluno frame = new ViewAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void atualizaTableContatos() {
		Collection<Aluno> alunos = adao.obtemTodosAlunos();
		table.setModel(new AlunoTableModel(alunos));		
	}
	
	/**
	 * Create the frame.
	 */
	public ViewAluno() {
		setTitle("AppAcademia: Cadastro de Alunos");
		adao = new AlunoDAOImpl();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				atualizaTableContatos();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Novo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (adao.obtemTodosAlunos().size() != 0) {
					Object [] conts = adao.obtemTodosAlunos().toArray();
					Aluno ultimo = (Aluno) conts[conts.length-1];
					int id = ultimo.getId() + 1;
					txtID.setText(String.valueOf(id));
				}
				else
					txtID.setText("1");
				//txtID.setText(String.valueOf(cdao.proximoID()));
			}
		});
		button.setBounds(39, 40, 91, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Salvar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtID.getText());
				String nome = txtNome.getText();
				String telefone = txtTelefone.getText();
				String cpf = txtCPF.getText();
				String datanascimento = txtDataNascimento.getText();
				adao.gravaAluno(new Aluno(id,nome,telefone,cpf,datanascimento));
				atualizaTableContatos();
				txtID.setText("");
				txtNome.setText("");
				txtTelefone.setText("");
				txtCPF.setText("");
				txtDataNascimento.setText("");			}
		});
		button_1.setBounds(140, 40, 91, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Editar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button_2.setBounds(241, 40, 91, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Remover");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um contato na lista para remover");
				}	
				else {
					int id = (Integer) table.getModel().getValueAt(table.getSelectedRow(),0);
					adao.apagaAluno(adao.obtemAlunoPorID(id));
					atualizaTableContatos();
				}
			}
		});
		button_3.setBounds(342, 40, 91, 23);
		contentPane.add(button_3);
		
		JLabel label = new JLabel("Telefone");
		label.setBounds(340, 93, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Nome");
		label_1.setBounds(98, 93, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("ID");
		label_2.setBounds(41, 93, 46, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("CPF");
		label_3.setBounds(456, 93, 46, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Dada de Nascimento");
		label_4.setBounds(552, 93, 101, 14);
		contentPane.add(label_4);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(41, 108, 47, 20);
		contentPane.add(txtID);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(98, 108, 232, 20);
		contentPane.add(txtNome);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(340, 108, 105, 20);
		contentPane.add(txtTelefone);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(456, 108, 86, 20);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
		txtDataNascimento = new JTextField();
		txtDataNascimento.setBounds(552, 108, 112, 20);
		contentPane.add(txtDataNascimento);
		txtDataNascimento.setColumns(10);
		
		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBounds(41, 139, 623, 181);
		contentPane.add(table);
		
		
	}
}
