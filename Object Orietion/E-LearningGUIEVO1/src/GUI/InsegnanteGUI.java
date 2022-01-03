package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.osgi.internal.debug.Debug;

import Controller.Controller;
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
import javax.swing.border.EtchedBorder;

public class InsegnanteGUI extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private Controller controller;
	private String emailricostruenteString;
	public JLabel[] labels=new JLabel[100];
	public JTextField MultiplaRisposta[] = new JTextField[100];
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public InsegnanteGUI(Controller c,JFrame frameChiamante,String email) 
	{
		int index=19;
		frame=this;
		emailricostruenteString=email;
		controller=c;
		//TODO Prima della release rimuove questo if e lasciare solo else
		if(emailricostruenteString.contentEquals("c"))
		{
			index=0;
			controller.aggiungiUtente("Admin", "Super", 29, 9, 2001,"LNTNT01P29F839R");
		}
		else 
		{
			index=controller.UtenteDatabaseCreation(email, "docente");	
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel PanelInformazioni = new JPanel();
		PanelInformazioni.setBackground(new Color(102, 102, 255));
		PanelInformazioni.setBounds(0, 0, 1064, 124);
		contentPane.add(PanelInformazioni);
		PanelInformazioni.setLayout(null);
		
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
		utenteArrayList = controller.getUtente(index);
		NomeLabel.setText((String) utenteArrayList.get(0));
		CognomeLabel.setText((String) utenteArrayList.get(1));
		String codiceString=(String) utenteArrayList.get(2);
		utenteArrayList=null;
		PanelInformazioni.repaint();
		PanelInformazioni.revalidate();
		JLabel AccountLabel2 = new JLabel("");
		AccountLabel2.setBounds(10, 26, 50, 57);
		PanelInformazioni.add(AccountLabel2);
		ImageIcon imgAccopuIcon3 = new ImageIcon(this.getClass().getResource("/iconaAccount6.png"));
		AccountLabel2.setIcon(imgAccopuIcon3);
		
		JPanel PanelViewInformation = new JPanel();
		PanelViewInformation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(65, 105, 225), new Color(123, 104, 238)));
		PanelViewInformation.setForeground(new Color(173, 216, 230));
		PanelViewInformation.setBackground(new Color(135, 206, 250));
		PanelViewInformation.setBounds(355, 27, 275, 71);
		PanelInformazioni.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'> Insegnante View </div></html>");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(77, 11, 122, 45);
		PanelViewInformation.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		
		JPanel PanelPrincipale = new JPanel();
		PanelPrincipale.setBackground(Color.WHITE);
		PanelPrincipale.setBounds(0, 122, 1064, 651);
		contentPane.add(PanelPrincipale);
		PanelPrincipale.setLayout(null);
		ImageIcon imgNuovoquiz= new ImageIcon(this.getClass().getResource("/iconaNuovoQuiz2.png"));
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Tasto Crea per creare il quiz");
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 102, 255), new Color(102, 102, 255)));
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(61, 127, 232, 280);
		PanelPrincipale.add(panel);
		panel.setLayout(null);
		
		
		JButton NuovoQuiz = new JButton("Crea");
		NuovoQuiz.setBackground(new Color(255, 102, 102));
		NuovoQuiz.setBounds(40, 206, 148, 43);
		panel.add(NuovoQuiz);
		//NuovoQuiz.setIcon(imgNuovoquiz);
		NuovoQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frameCreaQuiz=new CreaQuizGui(controller,frame,codiceString);
				frame.setVisible(false);
				frameCreaQuiz.setVisible(true);
			}
		});
		NuovoQuiz.setMinimumSize(new Dimension(95, 23));
		NuovoQuiz.setMaximumSize(new Dimension(103, 23));
		NuovoQuiz.setForeground(Color.BLACK);
		NuovoQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		JLabel InfoCreaQuizLabel = new JLabel("<html><div style='text-align: center;'>Qui puoi creare un nuovo <br>quiz</div></html>");
		InfoCreaQuizLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		InfoCreaQuizLabel.setBounds(42, 138, 146, 57);
		panel.add(InfoCreaQuizLabel);
		
		JLabel IconPiuLabel = new JLabel("");
		IconPiuLabel.setBounds(63, 39, 100, 100);
		IconPiuLabel.setIcon(imgNuovoquiz);
		panel.add(IconPiuLabel);

	}
}
