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
		//TODO Prima della release rimuove questo if e lasciare solo else
		if(emailricostruenteString.contentEquals("c"))
		{
			index=0;
			controller.aggiungiUtente("Admin", "Super", 29, 9, 2001,"LNTNT01P29F839R");
		}
		else 
		{
			index=controller.UtenteDatabaseCreation(email, "docente");	
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel PanelInformazioni = new JPanel();
		PanelInformazioni.setBackground(new Color(102, 102, 255));
		PanelInformazioni.setBounds(0, 0, 1064, 124);
		contentPane.add(PanelInformazioni);
		PanelInformazioni.setLayout(null);
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		IndietroButton.setVerticalAlignment(SwingConstants.TOP);
		IndietroButton.setBounds(860, 27, 149, 57);
		PanelInformazioni.add(IndietroButton);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/images/backIcona.png"));
		IndietroButton.setIcon(imgAccopuIcon);
		
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
		
		JPanel PanelViewInformation = new JPanel();
		PanelViewInformation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(65, 105, 225), new Color(123, 104, 238)));
		PanelViewInformation.setForeground(new Color(173, 216, 230));
		PanelViewInformation.setBackground(new Color(135, 206, 250));
		PanelViewInformation.setBounds(355, 27, 275, 71);
		PanelInformazioni.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'> Insegnante View </div></html>");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(77, 11, 122, 45);
		PanelViewInformation.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		
		JPanel PanelPrincipale = new JPanel();
		PanelPrincipale.setForeground(Color.BLACK);
		PanelPrincipale.setBackground(Color.DARK_GRAY);
		PanelPrincipale.setBounds(0, 122, 1064, 651);
		contentPane.add(PanelPrincipale);
		PanelPrincipale.setLayout(null);
		ImageIcon imgNuovoquiz= new ImageIcon(this.getClass().getResource("/images/iconaNuovoQuiz2.png"));
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Tasto Crea per creare il quiz");
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 102, 255), new Color(102, 102, 255)));
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(56, 74, 210, 236);
		PanelPrincipale.add(panel);
		panel.setLayout(null);
		
		
		JButton NuovoQuiz = new JButton("Crea");
		NuovoQuiz.setBackground(new Color(255, 102, 102));
		NuovoQuiz.setBounds(30, 171, 148, 43);
		panel.add(NuovoQuiz);
		//NuovoQuiz.setIcon(imgNuovoquiz);
		NuovoQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frameCreaQuiz=new CreaQuizGui(controller,frame,codiceFiscaleDocente);
				frame.setVisible(false);
				frameCreaQuiz.setVisible(true);
			}
		});
		NuovoQuiz.setMinimumSize(new Dimension(95, 23));
		NuovoQuiz.setMaximumSize(new Dimension(103, 23));
		NuovoQuiz.setForeground(Color.BLACK);
		NuovoQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		JLabel InfoCreaQuizLabel = new JLabel("<html><div style='text-align: center;'>Qui puoi creare un nuovo <br>   Test</div></html>");
		InfoCreaQuizLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		InfoCreaQuizLabel.setBounds(32, 103, 146, 57);
		panel.add(InfoCreaQuizLabel);
		
		JLabel IconPiuLabel = new JLabel("");
		IconPiuLabel.setBounds(54, 11, 100, 100);
		IconPiuLabel.setIcon(imgNuovoquiz);
		panel.add(IconPiuLabel);
		
		JPanel PanelControlloTest = new JPanel();
		PanelControlloTest.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelControlloTest.setBounds(503, 74, 551, 220);
		PanelControlloTest.setLayout(null);
		
		JLabel PuoiControllreLabel = new JLabel("Correzione Test  :");
		PuoiControllreLabel.setForeground(Color.WHITE);
		PuoiControllreLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		PuoiControllreLabel.setBounds(503, 45, 217, 22);
		PanelPrincipale.add(PuoiControllreLabel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(503, 74, 551, 220);
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
		
		 scrollPane.setViewportView(PanelControlloTest);
		 PanelPrincipale.add(scrollPane);
		 
		 JLabel CreaTestLabel = new JLabel("Crea Test : ");
		 CreaTestLabel.setForeground(Color.WHITE);
		 CreaTestLabel.setBackground(new Color(255, 255, 255));
		 CreaTestLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		 CreaTestLabel.setBounds(56, 45, 165, 22);
		 PanelPrincipale.add(CreaTestLabel);
	}
}
