package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;

public class StudenteGUI extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private String emailricostruenteString;
	private Controller controller;
	public 	int PosizioneX;
	public 	int PosizioneY;
	

	/**
	 * Create the frame.
	 */
	public StudenteGUI(Controller c,JFrame frameChiamante,String email) 
	{
		
		frame=this;
		controller=c;
		PosizioneX=31;
		PosizioneY=0;
		int index;
		emailricostruenteString=email;
		if(emailricostruenteString.contentEquals("a"))
		{
			index=0;
			controller.aggiungiUtente("Admin", "Super", 29, 9, 2001,"LNTNTN01P29F839R");
		}
		else 
		{
			index=controller.UtenteDatabaseCreation(email, "Studente");	
		}
		//Caricamento Tutti Gli Esami Presenti nel DB
		if(controller.PrimoAccessoController==0)
		{
			System.out.println("Sono qui di nuovo");
			controller.PrimoAccessoFlagOff();
			controller.CretionTestFromDB();
		}
		Color CelesteSchermata = Color.decode("#90caf9");
		Color GrigioApple = Color.decode("#fbfbfd");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(CelesteSchermata);
		panel.setBounds(0, 0, 1064, 124);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel PanelViewInformation = new JPanel();
		PanelViewInformation.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		PanelViewInformation.setForeground(new Color(173, 216, 230));
		PanelViewInformation.setBackground(CelesteSchermata);
		PanelViewInformation.setBounds(378, 27, 275, 71);
		panel.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>Homepage </div></html>");
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
		panel.add(IndietroButton);
		
		
		JLabel NomeLabel = new JLabel("Nome");
		NomeLabel.setForeground(Color.WHITE);
		NomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		NomeLabel.setBounds(70, 27, 97, 24);
		panel.add(NomeLabel);
		
		JLabel CognomeLabel = new JLabel("Cognome");
		CognomeLabel.setForeground(Color.WHITE);
		CognomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		CognomeLabel.setBounds(127, 27, 129, 24);
		panel.add(CognomeLabel);
		
		JLabel Studente = new JLabel("Studente");
		Studente.setFont(new Font("Segoe UI", Font.BOLD, 11));
		Studente.setForeground(Color.WHITE);
		Studente.setBounds(70, 46, 106, 24);
		panel.add(Studente);
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
		String CodiceFiscaleStudente=(String) utenteArrayList.get(2);
		
		JLabel AccountLabel2 = new JLabel("");
		AccountLabel2.setBounds(10, 26, 50, 57);
		panel.add(AccountLabel2);
		ImageIcon imgAccopuIcon3 = new ImageIcon(this.getClass().getResource("/images/iconaAccount6.png"));
		AccountLabel2.setIcon(imgAccopuIcon3);
		
		Color BiancoApple2 = Color.decode("#f3f4f6");
		JPanel panelMain = new JPanel();
		Color NeroApple = Color.decode("#eeeeee");
		Color BiancoApple = Color.decode("#fbfbfd");
		panelMain.setBackground(BiancoApple2);
		panelMain.setBounds(0, 123, 1064, 638);
		contentPane.add(panelMain);
		panelMain.setLayout(null);
		
		JPanel PanelQuizDisponibili = new JPanel();
		Color AzzurroApple= Color.decode("#7bafe4");
		PanelQuizDisponibili.setBackground(NeroApple);
		PanelQuizDisponibili.setBounds(246, 89, 782, 388);
		PanelQuizDisponibili.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(271, 67, 770, 425);
		FormCollegamentoEsami NumeroTest[]= new FormCollegamentoEsami[100];
		
		
		ArrayList testArrayList = new ArrayList();
		if(controller.SizeTestArrayList()>0)
		{
			
			for (int i = 0; i < controller.SizeTestArrayList(); i++) 
			{
				int ciao=controller.SizeTestArrayList();
				NumeroTest[i]= new FormCollegamentoEsami(c, frame);
				testArrayList = controller.getTestArrayList(i);
				NumeroTest[i].NomeTest.setText("<html><div style='text-align: center;'>"+(String) testArrayList.get(0)+"</div></html>");
				NumeroTest[i].UtenteTest.setText("<html><div style='text-align: center;'>"+(String) testArrayList.get(1)+"  "+testArrayList.get(2)+"</div></html>");				
				NumeroTest[i].CollegamentoValori((String) testArrayList.get(0),(String) testArrayList.get(1),index,(String) testArrayList.get(3));
				NumeroTest[i].setLocation(PosizioneX, 59);
				PosizioneX=PosizioneX+303;				
				PanelQuizDisponibili.add(NumeroTest[i]);
				int numero=320*controller.SizeTestArrayList();
				PanelQuizDisponibili.setPreferredSize(new Dimension(numero, 400));
			    scrollPane.setPreferredSize(new Dimension(numero, 400));
			    PanelQuizDisponibili.revalidate();
			    PanelQuizDisponibili.repaint();
				scrollPane.revalidate();
				scrollPane.repaint();
			}
			
		}
		else 
		{
			JLabel nessunEsame = new JLabel("Nessun Esame Presente");
		    nessunEsame.setBounds(54, 149, 146, 48);
		    PanelQuizDisponibili.add(nessunEsame);
		}
		panelMain.add(scrollPane);
	    scrollPane.setViewportView(PanelQuizDisponibili);
	    panelMain.add(scrollPane);
	 
	    JScrollPane scrollPaneRisultati = new JScrollPane();
	    scrollPaneRisultati.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPaneRisultati.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPaneRisultati.setBounds(10, 67, 251, 425);
	    JPanel PanelQuizCompletati = new JPanel();
	    PanelQuizCompletati.setBackground(NeroApple);
	    PanelQuizCompletati.setBounds(10, 67, 251, 425);
	    PanelQuizCompletati.setLayout(null);
	    PanelRisultatiCollegamento CollegamentiRisultati[] = new PanelRisultatiCollegamento[100];
	    System.out.println("Codice Fiscale Studente pari : "+CodiceFiscaleStudente);
	    int num =controller.ReturnNumberOfResult(CodiceFiscaleStudente);
	    if(num>0)
	    {
	    	controller.AggiungiResultDaDB(CodiceFiscaleStudente);
	    }
	    for (int i = 0; i < num; i++) 
	    {
	    	CollegamentiRisultati[i]= new PanelRisultatiCollegamento(frame, c, CodiceFiscaleStudente,i);
	    	CollegamentiRisultati[i].setLocation(0,PosizioneY);
	    	PosizioneY=PosizioneY+97;
	    	PanelQuizCompletati.setPreferredSize(new Dimension(425, 500*num));
		    scrollPaneRisultati.setPreferredSize(new Dimension(425, 500*num));
	    	PanelQuizCompletati.add(CollegamentiRisultati[i]);	
		}
	    JLabel RisultatiCheckLabel = new JLabel("I tuoi risultati :");
	    RisultatiCheckLabel.setForeground(Color.BLACK);
	    RisultatiCheckLabel.setFont(new Font("Segoe UI", Font.BOLD, 19));
	    RisultatiCheckLabel.setBounds(11, 36, 224, 26);
	    panelMain.add(RisultatiCheckLabel);
	    JLabel TestDispobiliLabel = new JLabel("Test Disponibili : ");
	    TestDispobiliLabel.setForeground(Color.BLACK);
	    TestDispobiliLabel.setFont(new Font("Segoe UI", Font.BOLD, 19));
	    TestDispobiliLabel.setBounds(271, 36, 224, 23);
	    panelMain.add(TestDispobiliLabel);
	    PanelQuizCompletati.setPreferredSize(new Dimension(425, 1400));
	    scrollPaneRisultati.setPreferredSize(new Dimension(425, 1400));
	    scrollPaneRisultati.setViewportView(PanelQuizCompletati);
	    panelMain.add(scrollPaneRisultati);
	    
		
	}
}
