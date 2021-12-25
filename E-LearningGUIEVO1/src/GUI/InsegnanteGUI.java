package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
		frame=this;
		controller=c;
		emailricostruenteString=email;
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
		
		JLabel lblNewLabel = new JLabel("GUI Insegnante");
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
		
		JPanel PanelPrincipale = new JPanel();
		PanelPrincipale.setBackground(Color.WHITE);
		PanelPrincipale.setBounds(0, 122, 1064, 447);
		contentPane.add(PanelPrincipale);
		PanelPrincipale.setLayout(null);
		
		JLabel imgNuovoQuiz = new JLabel("");
		imgNuovoQuiz.setBounds(259, 61, 110, 100);
		PanelPrincipale.add(imgNuovoQuiz);
		
		
		JButton NuovoQuiz = new JButton("Crea Nuovo Quiz");
		NuovoQuiz.setBounds(25, 127, 278, 138);
		ImageIcon imgNuovoquiz= new ImageIcon(this.getClass().getResource("/iconaNuovoQuiz2.png"));
		NuovoQuiz.setIcon(imgNuovoquiz);
		PanelPrincipale.add(NuovoQuiz);
		NuovoQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frameCreaQuiz=new CreaQuizGui(controller,frame,codiceString);
			
				frame.setVisible(false);
				frameCreaQuiz.setVisible(true);
			}
		});
		NuovoQuiz.setVerticalAlignment(SwingConstants.BOTTOM);
		NuovoQuiz.setMinimumSize(new Dimension(95, 23));
		NuovoQuiz.setMaximumSize(new Dimension(103, 23));
		NuovoQuiz.setForeground(Color.BLACK);
		NuovoQuiz.setFont(new Font("Source Code Pro", Font.BOLD, 14));

	}
}
