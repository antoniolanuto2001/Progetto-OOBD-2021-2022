package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FormVisualRisultato extends JPanel
{
	public JLabel NumeroQuizLabel;
	public JLabel DomandaLabel;
	public JLabel DomandaEffettivaLabel;
	public JLabel RispostaLabel;
	public JLabel RispostaEffettivaLabel;
	public JLabel NumeroQuiz;
	public FormVisualRisultato()
	{
		Color RossoAntonioLabel= Color.decode("#d50000");
		
		setBounds(259, 11, 387, 257);	
		setLayout(null);
		
		NumeroQuizLabel = new JLabel("Quiz: ");
		NumeroQuizLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		NumeroQuizLabel.setBounds(155, 11, 100, 40);
		NumeroQuiz = new JLabel("0");
		NumeroQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		NumeroQuiz.setForeground(RossoAntonioLabel);
		NumeroQuiz.setBounds(206, 11, 30, 40);
		add(NumeroQuiz);
		add(NumeroQuizLabel);
		DomandaLabel = new JLabel("Domanda : ");
		DomandaLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		DomandaLabel.setBounds(10, 53, 100, 31);
		add(DomandaLabel);
		RispostaLabel = new JLabel("Risposta : ");
		RispostaLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		RispostaLabel.setBounds(10, 143, 83, 31);
		add(RispostaLabel);
		
		DomandaEffettivaLabel = new JLabel("New label");
	
		DomandaEffettivaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		DomandaEffettivaLabel.setVerticalAlignment(SwingConstants.TOP);
		DomandaEffettivaLabel.setBounds(10, 84, 367, 59);
		add(DomandaEffettivaLabel);
		
		RispostaEffettivaLabel = new JLabel("New label");
		
		RispostaEffettivaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		RispostaEffettivaLabel.setVerticalAlignment(SwingConstants.TOP);
		RispostaEffettivaLabel.setBounds(10, 173, 367, 59);
		add(RispostaEffettivaLabel);

	}
}
