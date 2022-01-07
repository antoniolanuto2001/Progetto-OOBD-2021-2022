package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class FormVisualQuiz extends JPanel
{
	public JRadioButton [] RispostaMultiplaRadioButton = new JRadioButton[20];
	public JLabel[] RisposteMultipleLabel = new JLabel[20];
	public JLabel NumeroQuiz;
	public JLabel DomandaEffettivaLabel;
	public JLabel RispostaMultiplaLabelPrinciapale;
	public JLabel RispostaApertaLabel;
	public JTextArea RispostaApertaTextArea;
	public ButtonGroup risposteMultipleGruppo;
	public String contengoOriginaleApertaString;
	public String contengoOriginaleMultiplaString;

	
	public FormVisualQuiz() 
	{
		// TODO Auto-generated constructor stub

		setBounds(274, 114, 478, 457);
		setLayout(null);
		
		JLabel QuizLabel = new JLabel("Quiz :");
		QuizLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		QuizLabel.setBounds(10, 11, 87, 40);
		add(QuizLabel);
		
		NumeroQuiz = new JLabel("0");
		NumeroQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		NumeroQuiz.setBounds(69, 10, 80, 40);
		add(NumeroQuiz);
		
		JLabel DomandaLabel = new JLabel("Domanda");
		DomandaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		DomandaLabel.setBounds(10, 62, 87, 40);
		add(DomandaLabel);
		
		DomandaEffettivaLabel = new JLabel("Quanti Cani ha Antonella?");
		DomandaEffettivaLabel.setBackground(Color.WHITE);
		DomandaEffettivaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		DomandaEffettivaLabel.setVerticalAlignment(SwingConstants.TOP);
		DomandaEffettivaLabel.setBounds(93, 73, 350, 65);
		add(DomandaEffettivaLabel);
		
		RispostaMultiplaLabelPrinciapale = new JLabel("Risposte");
		RispostaMultiplaLabelPrinciapale.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		RispostaMultiplaLabelPrinciapale.setBounds(10, 147, 87, 40);
		
		RispostaApertaLabel = new JLabel("Risposta");
		RispostaApertaLabel.setBounds(10, 149, 73, 38);
		
		RispostaApertaTextArea = new JTextArea();
		RispostaApertaTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(106, 90, 205), new Color(147, 112, 219)));
		RispostaApertaTextArea.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		RispostaApertaTextArea.setToolTipText("Scrivi Qui La Risposta");
		RispostaApertaTextArea.setLineWrap(true);
		RispostaApertaTextArea.setBounds(93, 149, 350, 221);
	
	}
}
