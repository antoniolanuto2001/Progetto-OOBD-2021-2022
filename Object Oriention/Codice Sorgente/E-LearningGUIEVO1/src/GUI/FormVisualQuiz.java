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
import javax.swing.border.LineBorder;

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
		Color NeroApple = Color.decode("#eeeeee");
		Color RossoAntonioLabel= Color.decode("#d50000");
		setBackground(NeroApple);
		setBounds(274, 114, 478, 457);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		
		JLabel QuizLabel = new JLabel("Quiz :");
		QuizLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		QuizLabel.setBounds(190, 11, 95, 40);
		add(QuizLabel);
		
		NumeroQuiz = new JLabel("0");
		NumeroQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		NumeroQuiz.setBounds(239, 11, 30, 40);
		NumeroQuiz.setForeground(RossoAntonioLabel);
		add(NumeroQuiz);
		
		JLabel DomandaLabel = new JLabel("Domanda : ");
		DomandaLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		DomandaLabel.setBounds(10, 62, 100, 40);
		add(DomandaLabel);
		
		DomandaEffettivaLabel = new JLabel("Quanti Cani ha Antonella?");
		DomandaEffettivaLabel.setBackground(Color.WHITE);
		DomandaEffettivaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		DomandaEffettivaLabel.setVerticalAlignment(SwingConstants.TOP);
		DomandaEffettivaLabel.setBounds(100, 73, 350, 65);
		add(DomandaEffettivaLabel);
		
		RispostaMultiplaLabelPrinciapale = new JLabel("Risposte : ");
		RispostaMultiplaLabelPrinciapale.setFont(new Font("Segoe UI", Font.BOLD, 17));
		RispostaMultiplaLabelPrinciapale.setBounds(10, 147, 87, 40);
		
		RispostaApertaLabel = new JLabel("Risposta");
		RispostaApertaLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		RispostaApertaLabel.setBounds(10, 149, 73, 38);
		
		RispostaApertaTextArea = new JTextArea();
		RispostaApertaTextArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		RispostaApertaTextArea.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		RispostaApertaTextArea.setToolTipText("Scrivi Qui La Risposta");
		RispostaApertaTextArea.setLineWrap(true);
		RispostaApertaTextArea.setBounds(93, 149, 350, 221);
	
	}
}
