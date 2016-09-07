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

public class CadastroPessoa extends JFrame{
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
	private JCheckBox ckEsp = new JCheckBox("Esporte"), ckCin = new JCheckBox("Cinema"), 
			ckBal = new JCheckBox("Balada"), ckEst = new JCheckBox("Estudar");
	public CadastroPessoa(){
		super("Cadastro de pessoas");
	}

	public void montarTela(){
		ButtonGroup grupoBt = new ButtonGroup();
		grupoBt.add(rbMasc);
		grupoBt.add(rbFem);
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
		cbCidade.addItem("Salvador");
		cbCidade.addItem("Santo Amaro");
		cbCidade.addItem("Feira de Santana");
		painelDosRadios.add(rbMasc);
		painelDosRadios.add(rbFem);
		painelDosRadios.setBorder(BorderFactory.createEtchedBorder());
		painelDosChecks.add(ckCin);
		painelDosChecks.add(ckEsp);
		painelDosChecks.add(ckBal);
		painelDosChecks.add(ckEst);
		painelDosChecks.setBorder(BorderFactory.createEtchedBorder());
		painelSul.add(btOk);
		painelSul.add(btCancel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,280);
		setResizable(false);
		setVisible(true);
	}	
}