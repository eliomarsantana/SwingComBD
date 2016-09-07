import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Pesquisa extends JFrame implements ActionListener{
	private JTable tabela = new JTable();
	private JButton btEdt = new JButton("Editar");
	private JButton btExc = new JButton("Excluir");
	private JScrollPane scrollTabela = new JScrollPane();
	private OperacoesBanco opBanco = new OperacoesBanco();

	public Pesquisa(){
		super("Pesquisa de pessoas");
	}

	private void atualizaTabela(){
		String [] cabecalho = {"ID", "Nome", "Telefone", "Sexo"};
		Vector<Pessoa> pessoas = opBanco.listaPessoas();

		String [][] registros = new String[pessoas.size()][4];
		for(int i=0; i<pessoas.size(); i++){
			registros[i][0] = String.valueOf(pessoas.get(i).getPessoa_id());
			registros[i][1] = pessoas.get(i).getNome();
			registros[i][2] = pessoas.get(i).getTelefone();
			if (pessoas.get(i).getSexo() == 0)
				registros[i][3] = "Feminino";
			else
				registros[i][3] = "Masculino";
		}
		tabela.setModel(new DefaultTableModel(registros, cabecalho));
	}

	public void montarTela(){
		Container c = getContentPane();
		scrollTabela.setViewportView(tabela);
		c.add(scrollTabela);
		c.add(btEdt);
		c.add(btExc);
		c.setLayout(new FlowLayout());
		atualizaTabela();

		btEdt.addActionListener(this);
		btExc.addActionListener(this);
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event) {
		OperacoesBanco op = new OperacoesBanco();
		Pessoa p;
		int pessoa_id = Integer.parseInt((String)tabela.getValueAt(tabela.getSelectedRow(), 0));
		p = op.carregaPessoa(pessoa_id);
		if (event.getSource() == btEdt) {
			Cadastro c = new Cadastro();
			c.montarTela(p);
			atualizaTabela();
			getContentPane().repaint();
		} else if (event.getSource() == btExc) {
			int conf = JOptionPane.showConfirmDialog(null, "Tem certeza da exclusão?", "Mensagem", JOptionPane.YES_NO_OPTION);
			if (conf == JOptionPane.YES_OPTION)
				op.removePessoa(pessoa_id);
			atualizaTabela();
		}
	}
}