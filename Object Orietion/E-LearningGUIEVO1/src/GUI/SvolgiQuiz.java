package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.osgi.internal.debug.Debug;

import Controller.Controller;
import Model.Test;

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
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;

public class SvolgiQuiz extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private Controller controller;
	public JLabel[] labels=new JLabel[100];
	public Test provaTest;
	public JTextField MultiplaRisposta[] = new JTextField[100];
	public int aggiornaY;
	
	
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
		
		JLabel lblNewLabel = new JLabel("GUI Test Visualizza");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel.setBounds(470, 27, 140, 57);
		PanelInformazioni.add(lblNewLabel);
		
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
		
		JPanel PanelVisualQuiz = new JPanel();
		PanelVisualQuiz.setBounds(274, 114, 478, 457);
		InformazioniPreliminari.add(PanelVisualQuiz);
		PanelVisualQuiz.setLayout(null);
		
		JLabel QuizLabel = new JLabel("Quiz :");
		QuizLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		QuizLabel.setBounds(10, 11, 87, 40);
		PanelVisualQuiz.add(QuizLabel);
		
		JLabel NumeroQuiz = new JLabel("0");
		NumeroQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		NumeroQuiz.setBounds(69, 10, 80, 40);
		PanelVisualQuiz.add(NumeroQuiz);
		
		JLabel DomandaLabel = new JLabel("Domanda");
		DomandaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		DomandaLabel.setBounds(10, 62, 87, 40);
		PanelVisualQuiz.add(DomandaLabel);
		
		JLabel DomandaEffettivaLabel = new JLabel("Quanti Cani ha Antonella?");
		DomandaEffettivaLabel.setBackground(Color.WHITE);
		DomandaEffettivaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		DomandaEffettivaLabel.setVerticalAlignment(SwingConstants.TOP);
		DomandaEffettivaLabel.setBounds(93, 73, 350, 65);
		PanelVisualQuiz.add(DomandaEffettivaLabel);
		
		JLabel RispostaMultiplaLabelPrinciapale = new JLabel("Risposte");
		RispostaMultiplaLabelPrinciapale.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		RispostaMultiplaLabelPrinciapale.setBounds(10, 147, 87, 40);
		//PanelVisualQuiz.add(RispostaMultiplaLabelPrinciapale);

		JRadioButton [] RispostaMultiplaRadioButton = new JRadioButton[20];
		//PanelVisualQuiz.add(RispostaMultiplaRadioButton);
		
		JLabel[] RisposteMultipleLabel = new JLabel[20];
		
		//PanelVisualQuiz.add(RisposteMultipleLabel);
		
	
		JLabel RispostaApertaLabel = new JLabel("Risposta");
		RispostaApertaLabel.setBounds(10, 149, 73, 38);
		//PanelVisualQuiz.add(RispostaApertaLabel);
		JTextArea RispostaApertaTextArea = new JTextArea();
		RispostaApertaTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(106, 90, 205), new Color(147, 112, 219)));
		RispostaApertaTextArea.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		RispostaApertaTextArea.setToolTipText("Scrivi Qui La Risposta");
		RispostaApertaTextArea.setLineWrap(true);
		RispostaApertaTextArea.setBounds(93, 149, 350, 221);
		//PanelVisualQuiz.add(RispostaApertaTextArea);
		for (int i = 0; i < provaTest.QuizPresenti.size(); i++) 
		{
			if (provaTest.QuizPresenti.get(i).getTipologiaTest().contentEquals("A"))
			{
				String s=String.valueOf(i+1);
				NumeroQuiz.setText(s);
				DomandaEffettivaLabel.setText(provaTest.QuizPresenti.get(i).Domande.get(0).Domanda);
				PanelVisualQuiz.add(RispostaApertaLabel);
				PanelVisualQuiz.add(RispostaApertaTextArea);
				PanelVisualQuiz.repaint();
				PanelVisualQuiz.revalidate();
			}
			else 
			{
				String s=String.valueOf(i+1);
				NumeroQuiz.setText(s);
				DomandaEffettivaLabel.setText(provaTest.QuizPresenti.get(i).Domande.get(0).Domanda);
				PanelVisualQuiz.add(RispostaMultiplaLabelPrinciapale);
				for (int j = 1; j < provaTest.QuizPresenti.get(i).Domande.size(); j++) 
				{
					RisposteMultipleLabel[j]=new JLabel();
					RisposteMultipleLabel[j].setFont(new Font("Segoe UI", Font.PLAIN, 17));
					RisposteMultipleLabel[j].setBounds(40, aggiornaY, 43, 58);
					RisposteMultipleLabel[j].setText(provaTest.QuizPresenti.get(i).Domande.get(j).Domanda);
					RispostaMultiplaRadioButton[j]=new JRadioButton();
					RispostaMultiplaRadioButton[j].setFont(new Font("Source Code Pro", Font.PLAIN, 14));
					RispostaMultiplaRadioButton[j].setBounds(69, aggiornaY+3, 374, 58);
					RispostaMultiplaRadioButton[j].setText(provaTest.QuizPresenti.get(i).Domande.get(j).Riposta);
					aggiornaY=aggiornaY+40;
					PanelVisualQuiz.add(RisposteMultipleLabel[j]);
					PanelVisualQuiz.add(RispostaMultiplaRadioButton[j]);
					PanelVisualQuiz.repaint();
					PanelVisualQuiz.revalidate();
				}
			}
		}	
		
		
		
	}
}
