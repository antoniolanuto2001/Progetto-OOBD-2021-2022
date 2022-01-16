package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.management.loading.PrivateClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Controller.Controller;

public class PanelRisultatiCollegamento extends JPanel
{

	public PanelRisultatiCollegamento(JFrame frame,Controller controller,String CodiceFiscaleStudente,int indexRisultato) 
	{
		// TODO Auto-generated constructor stub
		 
		    addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent e) 
		    	{
		    		JFrame frameControllaRisultato=new ControllaRisultatiQuiz(controller,frame,CodiceFiscaleStudente,indexRisultato);
					frame.setVisible(false);
					frameControllaRisultato.setVisible(true);
		    	}
		    });
		    setBorder(new LineBorder(new Color(0,0,0)));
		    Color AzzurroMateorialDesign = Color.decode("#b3e5fc");
		    setBackground(AzzurroMateorialDesign);
		    setBounds(0, 0, 251, 93);
		    setLayout(null);
		    
		    JLabel IcocinaRisultatoCheckLabel = new JLabel("");
		    ImageIcon logoRisultati = new ImageIcon(this.getClass().getResource("/images/undrawCheckRisultati2.png"));
		    IcocinaRisultatoCheckLabel.setForeground(Color.WHITE);
		    IcocinaRisultatoCheckLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    IcocinaRisultatoCheckLabel.setBounds(10, 11, 59, 71);
		    IcocinaRisultatoCheckLabel.setIcon(logoRisultati);
		    add(IcocinaRisultatoCheckLabel);
		    JLabel NomeTestLabelCheck = new JLabel("Risultato n "+(indexRisultato+1));
		    Color BiancoApple = Color.decode("#fdfdfd");
		    NomeTestLabelCheck.setForeground(Color.BLACK);
		    NomeTestLabelCheck.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		    NomeTestLabelCheck.setBounds(79, 23, 162, 27);
		    add(NomeTestLabelCheck);
		    
		    JLabel DocenteDataLabel = new JLabel("Cliccando Puoi Controllare");
		    Color GrigioScritteApple = Color.decode("#9e9da0");
		    DocenteDataLabel.setForeground(Color.BLACK);
		    DocenteDataLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		    DocenteDataLabel.setBounds(79, 46, 162, 23);
		    add(DocenteDataLabel);
	}
}
