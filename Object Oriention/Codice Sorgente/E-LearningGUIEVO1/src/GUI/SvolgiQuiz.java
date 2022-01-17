package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.osgi.internal.debug.Debug;
import org.eclipse.swt.internal.ole.win32.IDataObject;

import Controller.Controller;
import Model.Quiz;
import Model.Test;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
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
	public FormVisualQuiz[] quiz= new FormVisualQuiz[100];
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SvolgiQuiz(Controller c,JFrame frameChiamante,String nomeTest,String UtenteTest,int IndexStudente,String codFiscaleDocente) 
	{
		frame=this;
		controller=c;
		PosizioneY=20;
		
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		int index=controller.returnIndexListaTest(nomeTest, UtenteTest);
		
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
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>Svolgi Test</div></html>");
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
		utenteArrayList = controller.getUtente(IndexStudente);
		NomeLabel.setText((String) utenteArrayList.get(0));
		CognomeLabel.setText((String) utenteArrayList.get(1));
		String codiceString=(String) utenteArrayList.get(2);
		
		JLabel AccountLabel2 = new JLabel("");
		AccountLabel2.setBounds(10, 26, 50, 57);
		panel.add(AccountLabel2);
		ImageIcon imgAccopuIcon3 = new ImageIcon(this.getClass().getResource("/images/iconaAccount6.png"));
		AccountLabel2.setIcon(imgAccopuIcon3);
		
		
		JPanel InformazioniPreliminari = new JPanel();
		InformazioniPreliminari.setBackground(NeroApple);
		InformazioniPreliminari.setBounds(0, 123, 1064, 638);
		contentPane.add(InformazioniPreliminari);
		InformazioniPreliminari.setLayout(null);
		
		JLabel NomeTestLabel = new JLabel("Nome Test :");
		NomeTestLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		NomeTestLabel.setBounds(274, 23, 110, 41);
		InformazioniPreliminari.add(NomeTestLabel);
		
		JLabel NomeTestEffettivoLabel = new JLabel("New label");
		NomeTestEffettivoLabel.setBackground(Color.WHITE);
		NomeTestEffettivoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		NomeTestEffettivoLabel.setBounds(394, 23, 374, 41);
		InformazioniPreliminari.add(NomeTestEffettivoLabel);
		
		JLabel profLabel = new JLabel("Prof:");
		profLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		profLabel.setBounds(274, 62, 110, 41);
		InformazioniPreliminari.add(profLabel);
		
		JLabel profTestEffettivoLabel = new JLabel("Prof si");
		profTestEffettivoLabel.setForeground(new Color(204, 0, 51));
		profTestEffettivoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		profTestEffettivoLabel.setBounds(390, 62, 410, 46);
		InformazioniPreliminari.add(profTestEffettivoLabel);
		ArrayList testArrayList = new ArrayList();
		testArrayList = controller.getTestArrayList(index);
		NomeTestEffettivoLabel.setText("<html><div style='text-align: center;'>"+(String) testArrayList.get(0)+"</div></html>");
		profTestEffettivoLabel.setText("<html><div style='text-align: center;'>"+(String) testArrayList.get(1)+"  "+testArrayList.get(2)+"</div></html>");
		JPanel PanelDoveStannoQuiz = new JPanel();
		PanelDoveStannoQuiz.setBackground(AzzuroPaneAntonio);
		PanelDoveStannoQuiz.setBounds(60, 154, 633, 471);
		PanelDoveStannoQuiz.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(207, 190, 633, 471);
		
		
		
		for (int i = 0; i < provaTest.QuizPresenti.size(); i++) 
		{
			if (provaTest.QuizPresenti.get(i).getTipologiaTest().contentEquals("A"))
			{
				String s=String.valueOf(i+1);
				quiz[i]=new FormVisualQuiz();
				quiz[i].NumeroQuiz.setText(s);
				
				String spitTotaleString=provaTest.QuizPresenti.get(i).Domande.get(0).Domanda;
				quiz[i].contengoOriginaleApertaString=spitTotaleString;
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
			    quiz[i].setLocation(70, PosizioneY);
			    PosizioneY=PosizioneY+490;
			    PanelDoveStannoQuiz.setPreferredSize(new Dimension(962, 500*provaTest.QuizPresenti.size()));
			    scrollPane.setPreferredSize(new Dimension(962,500*provaTest.QuizPresenti.size()));
			}
			else 
			{
				String s=String.valueOf(i+1);
				quiz[i]=new FormVisualQuiz();
				quiz[i].NumeroQuiz.setText(s);
				String domanadaSplitTotaleString=provaTest.QuizPresenti.get(i).Domande.get(0).Domanda;
				quiz[i].contengoOriginaleMultiplaString=domanadaSplitTotaleString;
				System.out.println("Domanda"+quiz[i].contengoOriginaleMultiplaString);
				if(domanadaSplitTotaleString.length()>50)
				{
					int half = domanadaSplitTotaleString.length() % 2 == 0 ? domanadaSplitTotaleString.length()/2 : domanadaSplitTotaleString.length()/2 + 1;
					String first = domanadaSplitTotaleString.substring(0, half);
					String second = domanadaSplitTotaleString.substring(half);
					quiz[i].DomandaEffettivaLabel.setText("<html><body>"+first+"<br>"+second+"</body></html>");
				}
				else
				{
					quiz[i].DomandaEffettivaLabel.setText(domanadaSplitTotaleString);
				}
				
				quiz[i].risposteMultipleGruppo= new ButtonGroup();
				quiz[i].add(quiz[i].RispostaMultiplaLabelPrinciapale);
				for (int j = 1; j < provaTest.QuizPresenti.get(i).Domande.size(); j++) 
				{
					quiz[i].RisposteMultipleLabel[j]=new JLabel();
					quiz[i].RisposteMultipleLabel[j].setFont(new Font("Segoe UI", Font.BOLD, 17));
					quiz[i].RisposteMultipleLabel[j].setBounds(40, aggiornaY, 43, 68);
		
					quiz[i].RisposteMultipleLabel[j].setText(provaTest.QuizPresenti.get(i).Domande.get(j).Domanda);
					quiz[i].RispostaMultiplaRadioButton[j]=new JRadioButton();
					quiz[i].RispostaMultiplaRadioButton[j].setFont(new Font("Segoe UI", Font.PLAIN, 13));
					quiz[i].RispostaMultiplaRadioButton[j].setBounds(69, aggiornaY, 374, 80);
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
					aggiornaY=aggiornaY+60;
					quiz[i].add(quiz[i].RisposteMultipleLabel[j]);
					quiz[i].add(quiz[i].RispostaMultiplaRadioButton[j]);
					quiz[i].repaint();
					quiz[i].revalidate();
					PanelDoveStannoQuiz.setPreferredSize(new Dimension(962, 500*provaTest.QuizPresenti.size()));
				    scrollPane.setPreferredSize(new Dimension(962,500*provaTest.QuizPresenti.size()));
				    PanelDoveStannoQuiz.add(quiz[i]);
				    quiz[i].setLocation(70, PosizioneY);
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
	    
	    
	    JButton CompleteButton = new JButton("Consegna");
	    CompleteButton.setBackground(new Color(211, 211, 211));
	    CompleteButton.addActionListener(new ActionListener() 
	    {
	    	
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		boolean ReadyForCorrection=true;
	    		for (int j = 0; j < provaTest.QuizPresenti.size(); j++) 
	    		{
	    			if (provaTest.QuizPresenti.get(j).getTipologiaTest().contentEquals("A"))
	    			{
	    				
	    				String controllo=quiz[j].RispostaApertaTextArea.getText();
	    				
	    				if(controllo.length()<=0)
	    				{
	    					JOptionPane.showMessageDialog(null,"Compleata Prima tutte le risposte Aperte","Errore Risposta",JOptionPane.INFORMATION_MESSAGE);
	    					ReadyForCorrection=false;
	    					break;
	    				}
	    			}
	    			if (provaTest.QuizPresenti.get(j).getTipologiaTest().contentEquals("M"))
	    			{
	    				Boolean selezionato=isSelection(quiz[j].risposteMultipleGruppo);
	    				
	    				if(selezionato==false)
	    				{
	    					JOptionPane.showMessageDialog(null,"Compleata Prima tutte le risposte Multiple","Errore Risposta",JOptionPane.INFORMATION_MESSAGE);
	    					ReadyForCorrection=false;
	    					break;
	    				}
					}
				}
	    		if (ReadyForCorrection==true) 
	    		{
	    			
	    			int indexResult=controller.AggiungiRisultatoTestalDB(nomeTest, codFiscaleDocente, codiceString);
	    			for (int j = 0; j < provaTest.QuizPresenti.size(); j++) 
		    		{
		    			if (provaTest.QuizPresenti.get(j).getTipologiaTest().contentEquals("A"))
		    			{
		    				
		    				controller.AggiungiValutazioneAlDB("Aperta", (String)quiz[j].RispostaApertaTextArea.getText(), "", (String) quiz[j].contengoOriginaleApertaString, nomeTest,codFiscaleDocente,codiceString,indexResult);
		    				
		    			}
		    			else 
		    			{
		    				String letteraInseritaString = null;
		    				for (int z = 1; z < provaTest.QuizPresenti.get(j).Domande.size(); z++) 
		    				{
		    					if (quiz[j].RispostaMultiplaRadioButton[z].isSelected()) 
		    					{
		    						letteraInseritaString=quiz[j].RisposteMultipleLabel[z].getText();
								}
		    				}
		    				controller.AggiungiValutazioneAlDB("Multipla", "", letteraInseritaString, (String) quiz[j].contengoOriginaleMultiplaString, nomeTest,codFiscaleDocente,codiceString,indexResult);
						}
					}
	    			frameChiamante.setVisible(true);
					frame.setVisible(false);
					frame.dispose();
	    		}
	    	}
	    });
	    CompleteButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    CompleteButton.setBounds(433, 138, 141, 41);
	    InformazioniPreliminari.add(CompleteButton);
	}
	public boolean isSelection(ButtonGroup buttonGroup) 
	{
	    return (buttonGroup.getSelection() != null);
	}
}
