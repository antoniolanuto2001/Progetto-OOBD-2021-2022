package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Controller.Controller;
import Model.Utente;

public class ControllaRisultatiQuiz extends JFrame 
{

	private JFrame frame;
	private JPanel contentPane;
	private Controller controller;
	private String CodFiscaleStudente;
	public  Utente utenteRisultato;
	public int posizioneY;
	

	/**
	 * Create the frame.
	 */
	public ControllaRisultatiQuiz(Controller c,JFrame frameChiamante,String codFiscaleStudeString,int indexRisultato) 
	{
		int index;
		frame=this;
		controller=c;
		CodFiscaleStudente=codFiscaleStudeString;
		posizioneY=20;
		try {
			utenteRisultato=controller.ReturnCopiaofUtente(codFiscaleStudeString);
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//TODO Prima della release rimuove questo if e lasciare solo else
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 255));
		panel.setBounds(0, 0, 1064, 124);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel PanelViewInformation = new JPanel();
		PanelViewInformation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(65, 105, 225), new Color(123, 104, 238)));
		PanelViewInformation.setForeground(new Color(173, 216, 230));
		PanelViewInformation.setBackground(new Color(135, 206, 250));
		PanelViewInformation.setBounds(355, 27, 275, 71);
		panel.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>Check Test View</div></html>");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(71, 11, 141, 45);
		PanelViewInformation.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		IndietroButton.setVerticalAlignment(SwingConstants.TOP);
		IndietroButton.setBounds(860, 27, 149, 57);
		panel.add(IndietroButton);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/images/backIcona.png"));
		IndietroButton.setIcon(imgAccopuIcon);
		
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
		NomeLabel.setText(utenteRisultato.getNome());
		CognomeLabel.setText(utenteRisultato.getCognome());
		String CodiceFiscaleStudente=utenteRisultato.getCodiceFiscale();
		
		JLabel AccountLabel2 = new JLabel("");
		AccountLabel2.setBounds(10, 26, 50, 57);
		panel.add(AccountLabel2);
		ImageIcon imgAccopuIcon3 = new ImageIcon(this.getClass().getResource("/images/iconaAccount6.png"));
		AccountLabel2.setIcon(imgAccopuIcon3);
		
		JPanel PanelMain = new JPanel();
		PanelMain.setBackground(Color.WHITE);
		PanelMain.setBounds(0, 125, 1064, 636);
		contentPane.add(PanelMain);
		PanelMain.setLayout(null);
		
		JLabel NomeTestLabel = new JLabel("Nome Test : ");
		NomeTestLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		NomeTestLabel.setBounds(307, 42, 112, 32);
		PanelMain.add(NomeTestLabel);
		
		
		String nomeTestLabelString=controller.ReturnNomeTestFromidResultTest(utenteRisultato.ListaRisultati.get(indexRisultato).idRisultatoTest);
		
		JLabel testLabelEffettivo = new JLabel("");
		testLabelEffettivo.setVerticalAlignment(SwingConstants.TOP);
		if(nomeTestLabelString.length()>30)
		{
				int half = nomeTestLabelString.length() % 2 == 0 ? nomeTestLabelString.length()/2 : nomeTestLabelString.length()/2 + 1;
				String first = nomeTestLabelString.substring(0, half);
				String second = nomeTestLabelString.substring(half);
				testLabelEffettivo.setText("<html><body>"+first+"<br>"+second+"</body></html>");
		}
		else 
		{
			testLabelEffettivo.setText(nomeTestLabelString);
		}
		testLabelEffettivo.setFont(new Font("Segoe UI", Font.BOLD, 17));
		testLabelEffettivo.setBounds(413, 45, 292, 48);
		PanelMain.add(testLabelEffettivo);
		
		JLabel PunteggioLabel = new JLabel("Punteggio:");
		PunteggioLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		PunteggioLabel.setBounds(307, 85, 93, 32);
		PanelMain.add(PunteggioLabel);
		
		JLabel Punteggio = new JLabel(""+utenteRisultato.ListaRisultati.get(indexRisultato).punteggioOttenuto);
		Punteggio.setForeground(new Color(0, 153, 51));
		Punteggio.setFont(new Font("Segoe UI", Font.BOLD, 17));
		Punteggio.setBounds(412, 85, 93, 34);
		PanelMain.add(Punteggio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(60, 190, 962, 471);
		JPanel PanelDoveStannoQuiz = new JPanel();
		PanelDoveStannoQuiz.setBackground(new Color(51, 102, 255));
		PanelDoveStannoQuiz.setBounds(60, 154, 962, 471);
		PanelDoveStannoQuiz.setLayout(null);
		FormVisualRisultato PanelRisposte[]= new FormVisualRisultato[100];
		
		for (int i = 0; i < utenteRisultato.ListaRisultati.get(indexRisultato).DomandePresenti.size(); i++) 
		{
			PanelRisposte[i]=new FormVisualRisultato();
			PanelRisposte[i].NumeroQuizLabel.setText("Quiz Numero : "+ 0);
			PanelRisposte[i].DomandaEffettivaLabel.setText(utenteRisultato.ListaRisultati.get(indexRisultato).DomandePresenti.get(i).Domanda);
			PanelRisposte[i].RispostaEffettivaLabel.setText(utenteRisultato.ListaRisultati.get(indexRisultato).DomandePresenti.get(i).Riposta);
			PanelRisposte[i].setLocation(273, posizioneY);
			posizioneY=posizioneY+263;
			PanelDoveStannoQuiz.add(PanelRisposte[i]);
			PanelDoveStannoQuiz.setPreferredSize(new Dimension(962, 400*utenteRisultato.ListaRisultati.get(indexRisultato).DomandePresenti.size()));
			scrollPane.setPreferredSize(new Dimension(962, 400*utenteRisultato.ListaRisultati.get(indexRisultato).DomandePresenti.size()));
			PanelDoveStannoQuiz.revalidate();
			PanelDoveStannoQuiz.repaint();
			scrollPane.revalidate();
			scrollPane.repaint();
		}
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setViewportView(PanelDoveStannoQuiz);	
	    PanelMain.add(scrollPane);
		
	}
	
}
