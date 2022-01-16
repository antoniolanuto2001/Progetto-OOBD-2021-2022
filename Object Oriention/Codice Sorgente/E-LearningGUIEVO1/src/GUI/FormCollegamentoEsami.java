package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import Controller.Controller;

public class FormCollegamentoEsami extends JPanel
{
	private JFrame frame;
	private Controller controller;
	public JLabel NomeTest;
	public JLabel UtenteTest;
	public String nomeTestPerCollegamentoString;
	public String utentePerCollegamentoString ;
	public int indexStudente;
	public String CodFiscaleDocenteDaCodice;
	public FormCollegamentoEsami(Controller c,JFrame framecorrente) 
	{
		
		// TODO Auto-generated constructor stub
		controller=c;
		frame=framecorrente;
		Color SfondoApple= Color.decode("#90caf9");
		Color GrigioApple = Color.decode("#eeeeee");
		Color RossoAntonio= Color.decode("#f44336");
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SfondoApple);
		setBounds(27, 48, 227, 313);
		setLayout(null);
		
		JLabel IconaEsameLabel = new JLabel("");
		IconaEsameLabel.setBounds(39, 32, 146, 107);
		int n = (int) (Math.random() * 3);
		//Generazione Icona Randomica
		ImageIcon imgiconaIcon1 = new ImageIcon(this.getClass().getResource("/images/EsameIconaEVO11.png"));
		ImageIcon imgiconaIcon2 = new ImageIcon(this.getClass().getResource("/images/EsameIconaEVO21.png"));
		ImageIcon imgiconaIcon3 = new ImageIcon(this.getClass().getResource("/images/EsameIconaEVO3.png"));
		if(n==0)
		{
			IconaEsameLabel.setBounds(65, 32, 146, 107);
			IconaEsameLabel.setIcon(imgiconaIcon1);
			add(IconaEsameLabel);
		}
		else if (n==1) 
		{
			IconaEsameLabel.setBounds(55, 32, 146, 107);
			IconaEsameLabel.setIcon(imgiconaIcon2);
			add(IconaEsameLabel);
		}
		else 
		{
			IconaEsameLabel.setBounds(39, 32, 146, 107);
			IconaEsameLabel.setIcon(imgiconaIcon3);
			add(IconaEsameLabel);
		}
		
		
		 NomeTest = new JLabel("<html><div style='text-align: center;'>Prima prova intercorso <br/>Basi Di dati</div></html>");
		NomeTest.setHorizontalAlignment(JLabel.CENTER);
		NomeTest.setVerticalAlignment(JLabel.CENTER);

		NomeTest.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		NomeTest.setBounds(49, 131, 136, 61);
		add(NomeTest);
		
		UtenteTest = new JLabel("<html><div style='text-align: center;'>Prof<br/>Antonio Lanuto</div></html>");
		UtenteTest.setHorizontalAlignment(JLabel.CENTER);
		UtenteTest.setVerticalAlignment(JLabel.CENTER);
		UtenteTest.setBounds(39, 177, 146, 30);
		UtenteTest.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		UtenteTest.setForeground(Color.BLACK);
		add(UtenteTest);
		
		JButton InvioVersoTest = new JButton("Sostieni Test");
		
		InvioVersoTest.setForeground(Color.BLACK);
		InvioVersoTest.setBackground(RossoAntonio);
		InvioVersoTest.setBorder(new LineBorder(new Color(0, 0, 0)));
		InvioVersoTest.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		InvioVersoTest.setBounds(49, 237, 136, 48);
		add(InvioVersoTest);
		InvioVersoTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frameSvolgiQuiz=new SvolgiQuiz(controller,frame,nomeTestPerCollegamentoString,utentePerCollegamentoString,indexStudente,CodFiscaleDocenteDaCodice);
				frame.setVisible(false);
				frameSvolgiQuiz.setVisible(true);
			}
		});
	}
	public void CollegamentoValori(String nomeTest,String CodFiscaleAutoreTest,int indexStudenteDacodice,String CodFiscaleDocente)
	{
		nomeTestPerCollegamentoString=nomeTest;
		utentePerCollegamentoString=CodFiscaleAutoreTest;
		indexStudente=indexStudenteDacodice;
		CodFiscaleDocenteDaCodice=CodFiscaleDocente;
	}
}
