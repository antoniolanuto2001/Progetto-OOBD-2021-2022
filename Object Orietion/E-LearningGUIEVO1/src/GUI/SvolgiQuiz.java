package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.osgi.internal.debug.Debug;

import Controller.Controller;
import Model.Test;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class SvolgiQuiz extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private Controller controller;
	public JLabel[] labels=new JLabel[100];
	public Test provaTest;
	public JTextField MultiplaRisposta[] = new JTextField[100];
	public int aggiornaY;
	public int PosizioneY;
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SvolgiQuiz(Controller c,JFrame frameChiamante,String nomeTest,String UtenteTest) 
	{
		frame=this;
		controller=c;
		PosizioneY=20;
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		int index=controller.returnIndexListaTest(nomeTest, UtenteTest);
		System.out.println("Ho preso il testo con index "+index);
		try {
			provaTest =controller.ReturnCopiaOfTest(index);
		} 
		catch (CloneNotSupportedException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		aggiornaY=191;
		//TODO Fetch Automatico con database 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel PanelInformazioni = new JPanel();
		PanelInformazioni.setBackground(new Color(102, 102, 255));
		PanelInformazioni.setBounds(0, 0, 1064, 124);
		contentPane.add(PanelInformazioni);
		PanelInformazioni.setLayout(null);
		
		JPanel PanelViewInformation = new JPanel();
		PanelViewInformation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(65, 105, 225), new Color(123, 104, 238)));
		PanelViewInformation.setForeground(new Color(173, 216, 230));
		PanelViewInformation.setBackground(new Color(135, 206, 250));
		PanelViewInformation.setBounds(355, 27, 275, 71);
		PanelInformazioni.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'> Visualizza Test </div></html>");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(87, 11, 112, 45);
		PanelViewInformation.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		IndietroButton.setVerticalAlignment(SwingConstants.TOP);
		IndietroButton.setBounds(860, 27, 149, 57);
		PanelInformazioni.add(IndietroButton);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/backIcona.png"));
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
		//TODO al posto del index 0 ci vuole index giusto per estrarre utente giusto
		utenteArrayList = controller.getUtente(0);
		NomeLabel.setText((String) utenteArrayList.get(0));
		CognomeLabel.setText((String) utenteArrayList.get(1));
		String codiceString=(String) utenteArrayList.get(2);
		
		JLabel AccountLabel2 = new JLabel("");
		AccountLabel2.setBounds(10, 26, 50, 57);
		PanelInformazioni.add(AccountLabel2);
		ImageIcon imgAccopuIcon3 = new ImageIcon(this.getClass().getResource("/iconaAccount6.png"));
		AccountLabel2.setIcon(imgAccopuIcon3);
		
		JPanel InformazioniPreliminari = new JPanel();
		InformazioniPreliminari.setBackground(Color.WHITE);
		InformazioniPreliminari.setBounds(0, 123, 1064, 638);
		contentPane.add(InformazioniPreliminari);
		InformazioniPreliminari.setLayout(null);
		
		JLabel NomeTestLabel = new JLabel("Nome Test :");
		NomeTestLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		NomeTestLabel.setBounds(274, 23, 110, 41);
		InformazioniPreliminari.add(NomeTestLabel);
		
		JLabel NomeTestEffettivoLabel = new JLabel("New label");
		NomeTestEffettivoLabel.setBackground(Color.WHITE);
		NomeTestEffettivoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		NomeTestEffettivoLabel.setBounds(394, 23, 303, 41);
		InformazioniPreliminari.add(NomeTestEffettivoLabel);
		
		JLabel profLabel = new JLabel("Prof:");
		profLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		profLabel.setBounds(274, 62, 110, 41);
		InformazioniPreliminari.add(profLabel);
		
		JLabel profTestEffettivoLabel = new JLabel("Prof siiii");
		profTestEffettivoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		profTestEffettivoLabel.setBounds(390, 62, 307, 46);
		InformazioniPreliminari.add(profTestEffettivoLabel);
		ArrayList testArrayList = new ArrayList();
		testArrayList = controller.getTestArrayList(index);
		NomeTestEffettivoLabel.setText("<html><div style='text-align: center;'>"+(String) testArrayList.get(0)+"</div></html>");
		profTestEffettivoLabel.setText("<html><div style='text-align: center;'>"+(String) testArrayList.get(1)+"  "+testArrayList.get(2)+"</div></html>");
		JPanel PanelDoveStannoQuiz = new JPanel();
		PanelDoveStannoQuiz.setBackground(new Color(51, 102, 255));
		PanelDoveStannoQuiz.setBounds(60, 154, 962, 471);
		PanelDoveStannoQuiz.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(60, 190, 962, 471);
		
		
		FormVisualQuiz[] quiz= new FormVisualQuiz[100];
		for (int i = 0; i < provaTest.QuizPresenti.size(); i++) 
		{
			if (provaTest.QuizPresenti.get(i).getTipologiaTest().contentEquals("A"))
			{
				String s=String.valueOf(i+1);
				quiz[i]=new FormVisualQuiz();
				quiz[i].NumeroQuiz.setText(s);
				String spitTotaleString=provaTest.QuizPresenti.get(i).Domande.get(0).Domanda;
				if(spitTotaleString.length()>50)
				{
					int half = spitTotaleString.length() % 2 == 0 ? spitTotaleString.length()/2 : spitTotaleString.length()/2 + 1;
					String first = spitTotaleString.substring(0, half);
					String second = spitTotaleString.substring(half);
					quiz[i].DomandaEffettivaLabel.setText("<html><body>"+first+"<br>"+second+"</body></html>");
				}
				else
				{
					quiz[i].DomandaEffettivaLabel.setText(spitTotaleString);
				}
				
				quiz[i].add(quiz[i].RispostaApertaLabel);
				quiz[i].add(quiz[i].RispostaApertaTextArea);
				quiz[i].repaint();
				quiz[i].revalidate();
				PanelDoveStannoQuiz.add(quiz[i]);
			    PanelDoveStannoQuiz.repaint();
			    PanelDoveStannoQuiz.revalidate();
			    quiz[i].setLocation(233, PosizioneY);
			    PosizioneY=PosizioneY+490;
			    PanelDoveStannoQuiz.setPreferredSize(new Dimension(962, 500*provaTest.QuizPresenti.size()));
			    scrollPane.setPreferredSize(new Dimension(962,500*provaTest.QuizPresenti.size()));
			}
			else 
			{
				String s=String.valueOf(i+1);
				quiz[i]=new FormVisualQuiz();
				quiz[i].NumeroQuiz.setText(s);
				quiz[i].DomandaEffettivaLabel.setText(provaTest.QuizPresenti.get(i).Domande.get(0).Domanda);
				quiz[i].risposteMultipleGruppo= new ButtonGroup();
				quiz[i].add(quiz[i].RispostaMultiplaLabelPrinciapale);
				for (int j = 1; j < provaTest.QuizPresenti.get(i).Domande.size(); j++) 
				{
					quiz[i].RisposteMultipleLabel[j]=new JLabel();
					quiz[i].RisposteMultipleLabel[j].setFont(new Font("Segoe UI", Font.PLAIN, 17));
					quiz[i].RisposteMultipleLabel[j].setBounds(40, aggiornaY, 43, 58);
		
					quiz[i].RisposteMultipleLabel[j].setText(provaTest.QuizPresenti.get(i).Domande.get(j).Domanda);
					quiz[i].RispostaMultiplaRadioButton[j]=new JRadioButton();
					quiz[i].RispostaMultiplaRadioButton[j].setFont(new Font("Source Code Pro", Font.PLAIN, 13));
					quiz[i].RispostaMultiplaRadioButton[j].setBounds(69, aggiornaY+3, 374, 58);
					String spitTotaleString=provaTest.QuizPresenti.get(i).Domande.get(j).Riposta;
					if(spitTotaleString.length()>40)
					{
						int half = spitTotaleString.length() % 2 == 0 ? spitTotaleString.length()/2 : spitTotaleString.length()/2 + 1;
						String first = spitTotaleString.substring(0, half);
						String second = spitTotaleString.substring(half);
						quiz[i].RispostaMultiplaRadioButton[j].setText("<html><body>"+first+"<br>"+second+"</body></html>");
					}
					else 
					{
						quiz[i].RispostaMultiplaRadioButton[j].setText(spitTotaleString);
					}
					quiz[i].risposteMultipleGruppo.add(quiz[i].RispostaMultiplaRadioButton[j]);
					aggiornaY=aggiornaY+55;
					quiz[i].add(quiz[i].RisposteMultipleLabel[j]);
					quiz[i].add(quiz[i].RispostaMultiplaRadioButton[j]);
					quiz[i].repaint();
					quiz[i].revalidate();
					PanelDoveStannoQuiz.setPreferredSize(new Dimension(962, 500*provaTest.QuizPresenti.size()));
				    scrollPane.setPreferredSize(new Dimension(962,500*provaTest.QuizPresenti.size()));
				    PanelDoveStannoQuiz.add(quiz[i]);
				    quiz[i].setLocation(233, PosizioneY);
				    PanelDoveStannoQuiz.repaint();
				    PanelDoveStannoQuiz.revalidate();
				}
				  PosizioneY=PosizioneY+490;
				  aggiornaY=191;
			}
		}
		InformazioniPreliminari.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setViewportView(PanelDoveStannoQuiz);
	    InformazioniPreliminari.add(scrollPane);
		//InformazioniPreliminari.add(quiz);
		
	}
}
