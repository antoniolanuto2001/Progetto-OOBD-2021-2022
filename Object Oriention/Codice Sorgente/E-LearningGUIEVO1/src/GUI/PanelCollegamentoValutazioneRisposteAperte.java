package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Controller.Controller;

public class PanelCollegamentoValutazioneRisposteAperte extends JPanel
{
	public JLabel CorreggiTestLabel;
	public PanelCollegamentoValutazioneRisposteAperte(JFrame framepadre,JFrame framenonno,Controller controller,String CodiceFiscaleStudente,int indexRisultato) 
	{
		// TODO Auto-generated constructor stub
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				JFrame frameValutazioneRisposteAperte=new ValutazioneRisposteAperteGUI(controller,framenonno,CodiceFiscaleStudente,indexRisultato);
				framepadre.setVisible(false);
				frameValutazioneRisposteAperte.setVisible(true);
			}
		});
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(128, 128, 128));
		setBounds(10, 11, 173, 198);
		setLayout(null);
		
		ImageIcon IconaCheckTest = new ImageIcon(this.getClass().getResource("/images/iconaCheck.png"));
		JLabel IcocinaControlloTestLabel = new JLabel("Icona");
		IcocinaControlloTestLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		IcocinaControlloTestLabel.setBounds(33, 11, 99, 92);
		IcocinaControlloTestLabel.setIcon(IconaCheckTest);
		add(IcocinaControlloTestLabel);
		
		CorreggiTestLabel = new JLabel("Correggi Test : 1");
		CorreggiTestLabel.setForeground(Color.WHITE);
		CorreggiTestLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		CorreggiTestLabel.setBounds(15, 114, 136, 28);
		add(CorreggiTestLabel);
		
		JLabel CliccaquiLabel = new JLabel("Clicca Qui Per Controllare !\r\n");
		CliccaquiLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		CliccaquiLabel.setForeground(Color.BLACK);
		CliccaquiLabel.setBounds(10, 146, 153, 28);
		add(CliccaquiLabel);
	}
}
