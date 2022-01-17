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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

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
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		try {
			utenteRisultato=controller.ReturnCopiaofUtente(codFiscaleStudeString);
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//TODO Prima della release rimuove questo if e lasciare solo else
		Color CelesteSchermata = Color.decode("#90caf9");
		Color GrigioApple = Color.decode("#fbfbfd");
		Color NeroApple = Color.decode("#eeeeee");
		Color AzzuroPaneAntonio = Color.decode("#42a5f5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
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
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>Controlla Test</div></html>");
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
		testLabelEffettivo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		testLabelEffettivo.setBounds(405, 45, 300, 48);
		PanelMain.add(testLabelEffettivo);
		
		JLabel PunteggioLabel = new JLabel("Punteggio :");
		PunteggioLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		PunteggioLabel.setBounds(310, 85, 93, 32);
		PanelMain.add(PunteggioLabel);
		
		JLabel Punteggio = new JLabel(""+utenteRisultato.ListaRisultati.get(indexRisultato).punteggioOttenuto);
		Punteggio.setForeground(new Color(0, 153, 51));
		Punteggio.setFont(new Font("Segoe UI", Font.BOLD, 20));
		Punteggio.setBounds(405, 83, 93, 34);
		PanelMain.add(Punteggio);
		
		JPanel PanelDoveStannoQuiz = new JPanel();
		PanelDoveStannoQuiz.setBackground(AzzuroPaneAntonio);
		PanelDoveStannoQuiz.setBounds(60, 154, 633, 471);
		PanelDoveStannoQuiz.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(207, 190, 633, 471);
		FormVisualRisultato PanelRisposte[]= new FormVisualRisultato[100];
		
		for (int i = 0; i < utenteRisultato.ListaRisultati.get(indexRisultato).DomandePresenti.size(); i++) 
		{
			String s=String.valueOf(i+1);
			PanelRisposte[i]=new FormVisualRisultato();
			PanelRisposte[i].NumeroQuiz.setText(s);
			PanelRisposte[i].DomandaEffettivaLabel.setText(utenteRisultato.ListaRisultati.get(indexRisultato).DomandePresenti.get(i).Domanda);
			PanelRisposte[i].RispostaEffettivaLabel.setText(utenteRisultato.ListaRisultati.get(indexRisultato).DomandePresenti.get(i).Riposta);
			PanelRisposte[i].setLocation(110, posizioneY);
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
