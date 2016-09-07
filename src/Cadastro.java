import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Cadastro extends JFrame implements ActionListener {
	private OperacoesBanco opbanco = new OperacoesBanco();
	private Container container = getContentPane();
	private JPanel painelCentral = new JPanel();
	private JPanel painelCimaInterno = new JPanel();
	private JPanel painelSul = new JPanel();
	private JPanel painelCentroCimaInterno = new JPanel();
	private JPanel painelCentroBaixoInterno = new JPanel();
	private JPanel painelBaixoInterno = new JPanel();
	private JPanel painelDosRadios = new JPanel();
	private JPanel painelDosChecks = new JPanel();
	private JTextField fNome = new JTextField(19), fTel = new JTextField(10);
	private JRadioButton rbMasc = new JRadioButton("Masculino"), rbFem = new JRadioButton("Feminino");
	private JComboBox cbCidade = new JComboBox();
	private JButton btOk, btCancel;
	private Vector <JCheckBox> cbs = new Vector<JCheckBox>();
	private Pessoa pessoa = null;
	public Cadastro(){
		super("Cadastro de pessoas");
	}

	public void montarTela(){
		ButtonGroup grupoBt = new ButtonGroup();
		grupoBt.add(rbMasc);
		grupoBt.add(rbFem);
		Vector<Cidade> cidades;
		Vector<Curticao> curticoes;
		cidades = opbanco.listaCidades();
		curticoes = opbanco.listaCurticoes();
		for (int i = 0;i < curticoes.size(); i++){
			JCheckBox cb = new JCheckBox(curticoes.get(i).getDescricao());
			cb.setMnemonic((int)curticoes.get(i).getCurticao_id());
			cbs.add(cb);
		}

		for (int i = 0;i < cidades.size(); i++)
			cbCidade.addItem(cidades.get(i));
		btOk = new JButton("Ok");
		btCancel = new JButton("Cancelar");
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
		painelDosRadios.setLayout(new BoxLayout(painelDosRadios, BoxLayout.Y_AXIS));
		painelDosChecks.setLayout(new BoxLayout(painelDosChecks, BoxLayout.Y_AXIS));
		container.add(painelCentral, BorderLayout.CENTER);
		container.add(painelSul, BorderLayout.SOUTH);
		painelCentral.add(painelCimaInterno);
		painelCentral.add(painelCentroCimaInterno);
		painelCentral.add(painelCentroBaixoInterno);
		painelCentral.add(painelBaixoInterno);
		JLabel lbNome = new JLabel("Nome");
		painelCimaInterno.setLayout(fl);
		painelCimaInterno.add(lbNome);
		painelCimaInterno.add(fNome);
		painelCentroCimaInterno.setLayout(fl);
		JLabel lbFone = new JLabel("Telefone");
		painelCentroCimaInterno.add(lbFone);
		painelCentroCimaInterno.add(fTel);
		JLabel lbSexo = new JLabel("Sexo");
		JLabel lbCurticao = new JLabel("Curticao");
		painelCentroBaixoInterno.setLayout(fl);
		painelCentroBaixoInterno.add(lbSexo);
		painelCentroBaixoInterno.add(painelDosRadios);
		painelCentroBaixoInterno.add(lbCurticao);
		painelCentroBaixoInterno.add(painelDosChecks);
		painelBaixoInterno.setLayout(fl);
		painelBaixoInterno.add(new Label("Cidade"));
		painelBaixoInterno.add(cbCidade);
		painelDosRadios.add(rbMasc);
		painelDosRadios.add(rbFem);
		painelDosRadios.setBorder(BorderFactory.createEtchedBorder());
		for (int i = 0; i < cbs.size(); i++)
		   painelDosChecks.add(cbs.get(i));
		painelDosChecks.setBorder(BorderFactory.createEtchedBorder());
		painelSul.add(btOk);
		painelSul.add(btCancel);

		btOk.addActionListener(this);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,280);
		setResizable(false);
		setVisible(true);
	}

	public void montarTela(Pessoa pessoa){
		montarTela();
		this.pessoa = pessoa;
		fNome.setText(pessoa.getNome());
		fTel.setText(pessoa.getTelefone());
		if (pessoa.getSexo() == 0)
			rbFem.setSelected(true);
		else
			rbMasc.setSelected(true);
		for (int i=0; i<pessoa.getCurticoes().size(); i++){
			boolean achou = false;
			int j = 0;
			while(!achou && j<cbs.size()) {
				achou = cbs.get(j).getText().equals(pessoa.getCurticoes().get(i).getDescricao());
				j++;
			}
			if (achou)
				cbs.get(j-1).setSelected(true);
		}
		for (int i=0; i < cbCidade.getItemCount(); i++)
			if (pessoa.getCidade().getCidade_id() == ((Cidade)cbCidade.getItemAt(i)).getCidade_id())
				cbCidade.setSelectedIndex(i);
	}

	public void actionPerformed(ActionEvent event) {
		Pessoa pessoa;
		boolean atualiza = false;
		if (event.getSource() == btOk) {
			Vector<Curticao> curticoes = new Vector<Curticao>();
			Curticao curticao;
			if (this.pessoa == null)
			    pessoa = new Pessoa();
			else {
				atualiza = true;
				pessoa = this.pessoa;
			}

			pessoa.setNome(fNome.getText());
			pessoa.setTelefone(fTel.getText());
			if (rbFem.isSelected())
				pessoa.setSexo(0);
			else
				pessoa.setSexo(1);
			pessoa.setCidade((Cidade)cbCidade.getSelectedItem());
			for (int i = 0; i < cbs.size(); i++){
				if (cbs.get(i).isSelected()){
					curticao = new Curticao();
					curticao.setCurticao_id(cbs.get(i).getMnemonic());
					curticao.setDescricao(cbs.get(i).getText());
					curticoes.add(curticao);
					cbs.get(i).setSelected(false);
				}
			}
			pessoa.setCurticoes(curticoes);
			if (!atualiza)
				opbanco.SalvaPessoa(pessoa);
			else
				opbanco.AtualizaPessoa(pessoa);
			fNome.setText("");
			fTel.setText("");
			JOptionPane.showMessageDialog(null,"Cadastro efetuado com sucesso","Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}