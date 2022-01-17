package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class FormCorreggiRisposte extends JPanel
{
	public JLabel NumeroQuiz;
	public JLabel DomandaEffettivaLabel;
	public JLabel RispostaEffettivaLabel; 
	public JSpinner PunteggioSpiner;
	public int punteggioMax;
	public int punteggioMinimo;
	public SpinnerModel costruisciSpinnerModel;
	public FormCorreggiRisposte() 
	{
		Color NeroApple = Color.decode("#eeeeee");
		Color RossoAntonioLabel= Color.decode("#d50000");
		setBackground(NeroApple);
		setBounds(256, 11, 406, 384);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel QuizLabel = new JLabel("Quiz :");
		QuizLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		QuizLabel.setBounds(157, 0, 91, 50);
		add(QuizLabel);
		NumeroQuiz = new JLabel("0");
		NumeroQuiz.setFont(new Font("Segoe UI", Font.BOLD, 20));
		NumeroQuiz.setBounds(213, 1, 80, 50);
		NumeroQuiz.setForeground(RossoAntonioLabel);
		add(NumeroQuiz);
		
		JLabel DomandaLabel = new JLabel("Domanda :");
		DomandaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		DomandaLabel.setBounds(10, 67, 80, 34);
		add(DomandaLabel);
		
		DomandaEffettivaLabel = new JLabel("Testo Domanda");
		DomandaEffettivaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DomandaEffettivaLabel.setVerticalAlignment(SwingConstants.TOP);
		DomandaEffettivaLabel.setBounds(10, 98, 386, 60);
		add(DomandaEffettivaLabel);
		
		JLabel RispostaLabel = new JLabel("Risposta : ");
		RispostaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		RispostaLabel.setBounds(10, 169, 80, 34);
		add(RispostaLabel);
		
		RispostaEffettivaLabel = new JLabel("Testo Risposta");
		RispostaEffettivaLabel.setVerticalAlignment(SwingConstants.TOP);
		RispostaEffettivaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RispostaEffettivaLabel.setBounds(10, 196, 386, 74);
		add(RispostaEffettivaLabel);
		
		JLabel PunteggioLabel = new JLabel("Punteggio : ");
		PunteggioLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		PunteggioLabel.setBounds(158, 269, 91, 25);
		add(PunteggioLabel);
		
		
		
	}
}
