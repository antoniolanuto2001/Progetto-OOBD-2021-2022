package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.osgi.internal.debug.Debug;

import Controller.Controller;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsegnanteGUI extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private Controller controller;
	private String emailricostruenteString;
	public JLabel[] labels=new JLabel[100];
	public JTextField MultiplaRisposta[] = new JTextField[100];
	public int PosizioneX;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public InsegnanteGUI(Controller c,JFrame frameChiamante,String email) 
	{
		int index=19;
		frame=this;
		emailricostruenteString=email;
		controller=c;
		PosizioneX=5;
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		if(emailricostruenteString.contentEquals("c"))
		{
			index=0;
			controller.aggiungiUtente("Admin", "Super", 29, 9, 2001,"LNTNT01P29F839R");
		}
		else 
		{
			index=controller.UtenteDatabaseCreation(email, "docente");	
		}
		Color CelesteSchermata = Color.decode("#90caf9");
		Color GrigioApple = Color.decode("#fbfbfd");
		Color NeroApple = Color.decode("#eeeeee");
		Color AzzuroPaneAntonio = Color.decode("#42a5f5");
		Color SfondoApple= Color.decode("#90caf9");
		Color RossoAntonio= Color.decode("#f44336");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel PanelInformazioni = new JPanel();
		PanelInformazioni.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelInformazioni.setBackground(CelesteSchermata);
		PanelInformazioni.setBounds(0, 0, 1064, 124);
		contentPane.add(PanelInformazioni);
		PanelInformazioni.setLayout(null);
		
		JPanel PanelViewInformation = new JPanel();
		PanelViewInformation.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		PanelViewInformation.setForeground(new Color(173, 216, 230));
		PanelViewInformation.setBackground(CelesteSchermata);
		PanelViewInformation.setBounds(378, 27, 275, 71);
		PanelInformazioni.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>Homepage</div></html>");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(92, 11, 108, 45);
		PanelViewInformation.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		
		JButton IndietroButton = new JButton();
		
		IndietroButton.setBorderPainted(false);
		IndietroButton.setBorder(null);
		IndietroButton.setMargin(new Insets(0, 0, 0, 0));
		IndietroButton.setContentAreaFilled(false);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/images/iconaBack2.png"));
		IndietroButton.setIcon(imgAccopuIcon);
		IndietroButton.setBounds(945, 27, 76, 71);
		PanelInformazioni.add(IndietroButton);
		
		
		JLabel NomeLabel = new JLabel("Nome");
		NomeLabel.setForeground(Color.WHITE);
		NomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		NomeLabel.setBounds(70, 27, 97, 24);
		PanelInformazioni.add(NomeLabel);
		
		JLabel CognomeLabel = new JLabel("Cognome");
		CognomeLabel.setForeground(Color.WHITE);
		CognomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		CognomeLabel.setBounds(127, 27, 129, 24);
		PanelInformazioni.add(CognomeLabel);
		
		
		JLabel Insegnante = new JLabel("Insegnante");
		Insegnante.setForeground(Color.WHITE);
		Insegnante.setBounds(70, 46, 106, 24);
		PanelInformazioni.add(Insegnante);
		IndietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		ArrayList utenteArrayList = new ArrayList();
		utenteArrayList = controller.getUtente(index);
		NomeLabel.setText((String) utenteArrayList.get(0));
		CognomeLabel.setText((String) utenteArrayList.get(1));
		String codiceFiscaleDocente=(String) utenteArrayList.get(2);
		utenteArrayList=null;
		PanelInformazioni.repaint();
		PanelInformazioni.revalidate();
		JLabel AccountLabel2 = new JLabel("");
		AccountLabel2.setBounds(10, 26, 50, 57);
		PanelInformazioni.add(AccountLabel2);
		ImageIcon imgAccopuIcon3 = new ImageIcon(this.getClass().getResource("/images/iconaAccount6.png"));
		AccountLabel2.setIcon(imgAccopuIcon3);
		
		
		
		JPanel PanelPrincipale = new JPanel();
		PanelPrincipale.setForeground(Color.BLACK);
		PanelPrincipale.setBackground(NeroApple);
		PanelPrincipale.setBounds(0, 122, 1064, 651);
		contentPane.add(PanelPrincipale);
		PanelPrincipale.setLayout(null);
		ImageIcon imgNuovoquiz= new ImageIcon(this.getClass().getResource("/images/plusicon.png"));
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Tasto Crea per creare il quiz");
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(SfondoApple);
		panel.setBounds(56, 74, 179, 160);
		PanelPrincipale.add(panel);
		panel.setLayout(null);
		
		JLabel InfoCreaQuizLabel = new JLabel("<html><div style='text-align: center;'>Qui puoi creare un nuovo <br>   Test</div></html>");
		InfoCreaQuizLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		InfoCreaQuizLabel.setBounds(21, 103, 146, 57);
		panel.add(InfoCreaQuizLabel);
		
		JLabel IconPiuLabel = new JLabel("");
		IconPiuLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameCreaQuiz=new CreaQuizGui(controller,frame,codiceFiscaleDocente);
				frame.setVisible(false);
				frameCreaQuiz.setVisible(true);
				
			}
		});
		IconPiuLabel.setBounds(41, 11, 100, 100);
		IconPiuLabel.setIcon(imgNuovoquiz);
		panel.add(IconPiuLabel);
		
		JPanel PanelControlloTest = new JPanel();
		PanelControlloTest.setBackground(Color.WHITE);
		PanelControlloTest.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelControlloTest.setBounds(503, 74, 551, 220);
		PanelControlloTest.setLayout(null);
		
		JLabel PuoiControllreLabel = new JLabel("Correzione Test  :");
		PuoiControllreLabel.setForeground(Color.BLACK);
		PuoiControllreLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		PuoiControllreLabel.setBounds(503, 45, 217, 22);
		PanelPrincipale.add(PuoiControllreLabel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(503, 74, 551, 230);
		PanelCollegamentoValutazioneRisposteAperte Prova[] = new PanelCollegamentoValutazioneRisposteAperte[100];
		int numero=controller.CountNumeroValutazioniDacorreggere(codiceFiscaleDocente);
		if(numero>0)
		{
		controller.AggiungiValutazioniAperteDaDB(codiceFiscaleDocente);
			for (int i = 0; i < numero; i++) 
			{
				Prova[i]= new PanelCollegamentoValutazioneRisposteAperte(frame,frameChiamante,c, codiceFiscaleDocente, i);
				Prova[i].CorreggiTestLabel.setText("Correggi Test : "+(i+1));
				Prova[i].setLocation(PosizioneX, 10);
				PosizioneX=PosizioneX+178;
				PanelControlloTest.add(Prova[i]);
				PanelControlloTest.setPreferredSize(new Dimension(5000, 220));
			    scrollPane.setPreferredSize(new Dimension(5000, 220));
			}
		}
		else 
		{
			 JLabel CorrezioneLabel = new JLabel("<html><body>Nessun Correzione <br> Da Effettuare !</body></html>");
			 CorrezioneLabel.setVerticalAlignment(SwingConstants.TOP);
			 CorrezioneLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
			 CorrezioneLabel.setBounds(10, 27, 174, 107);
			 PanelControlloTest.add(CorrezioneLabel);
		}
		
		 scrollPane.setViewportView(PanelControlloTest);
		 PanelPrincipale.add(scrollPane);
		 
		 JLabel CreaTestLabel = new JLabel("Crea Test : ");
		 CreaTestLabel.setForeground(Color.BLACK);
		 CreaTestLabel.setBackground(new Color(255, 255, 255));
		 CreaTestLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		 CreaTestLabel.setBounds(56, 45, 165, 22);
		 PanelPrincipale.add(CreaTestLabel);
	}
}
