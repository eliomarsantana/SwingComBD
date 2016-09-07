import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JFrame implements ActionListener {
	private JMenuBar mbar = new JMenuBar();
	private JMenu menu1 = new JMenu("Cadastrar"), menu2 = new JMenu("Pesquisar"), menu3 = new JMenu("Sair");
	private JMenuItem menui1 = new JMenuItem("Pessoas"), menui2 = new JMenuItem("Pessoas");

	public void montarTela(){
		mbar.add(menu1);
		mbar.add(menu2);
		mbar.add(menu3);
		menu1.add(menui1);
		menu2.add(menui2);
		menui1.addActionListener(this);
		menui2.addActionListener(this);
		Container c = getContentPane();
		c.add(mbar, BorderLayout.NORTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(600,500);
		setVisible(true);
	}

	public Menu(){
		super("Sistema de cadastro de pessoas");
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == menui1)
			(new Cadastro()).montarTela();
		else if(event.getSource() == menui2)
			(new Pesquisa()).montarTela();
	}
}