package GUI;

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
	public FormVisualRisultato()
	{
		JPanel PanelFormCheckRisultati = new JPanel();
		setBounds(259, 11, 387, 257);
		
		setLayout(null);
		
		NumeroQuizLabel = new JLabel("Quiz Numero : "+ 0);
		NumeroQuizLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		NumeroQuizLabel.setBounds(10, 11, 180, 20);
		PanelFormCheckRisultati.add(NumeroQuizLabel);
		DomandaLabel = new JLabel("Domanda : ");
		DomandaLabel.setFont(new Font("Source Code Pro", Font.BOLD, 13));
		DomandaLabel.setBounds(10, 53, 83, 20);
		add(DomandaLabel);
		RispostaLabel = new JLabel("Risposta : ");
		RispostaLabel.setFont(new Font("Source Code Pro", Font.BOLD, 12));
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
