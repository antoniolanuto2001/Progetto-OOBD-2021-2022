package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class StudenteGUI extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private Controller controller;
	public 	int PosizioneX;
	

	/**
	 * Create the frame.
	 */
	public StudenteGUI(Controller c,JFrame frameChiamante) 
	{
		
		frame=this;
		controller=c;
		PosizioneX=31;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 255));
		panel.setBounds(0, 0, 1064, 124);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GUI Studente");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel.setBounds(456, 27, 140, 57);
		panel.add(lblNewLabel);
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		IndietroButton.setVerticalAlignment(SwingConstants.TOP);
		IndietroButton.setBounds(860, 27, 149, 57);
		panel.add(IndietroButton);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/backIcona.png"));
		IndietroButton.setIcon(imgAccopuIcon);
		
		JLabel NomeLabel = new JLabel("Nome");
		NomeLabel.setForeground(Color.WHITE);
		NomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		NomeLabel.setBounds(70, 27, 97, 24);
		panel.add(NomeLabel);
		
		JLabel CognomeLabel = new JLabel("Cognome");
		CognomeLabel.setForeground(Color.WHITE);
		CognomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		CognomeLabel.setBounds(127, 27, 129, 24);
		panel.add(CognomeLabel);
		
		JLabel Studente = new JLabel("Studente");
		Studente.setForeground(Color.WHITE);
		Studente.setBounds(70, 46, 106, 24);
		panel.add(Studente);
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
		
		JLabel AccountLabel2 = new JLabel("");
		AccountLabel2.setBounds(10, 26, 50, 57);
		panel.add(AccountLabel2);
		ImageIcon imgAccopuIcon3 = new ImageIcon(this.getClass().getResource("/iconaAccount6.png"));
		AccountLabel2.setIcon(imgAccopuIcon3);
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.WHITE);
		panelMain.setBounds(0, 123, 1064, 638);
		contentPane.add(panelMain);
		panelMain.setLayout(null);
		
		JPanel PanelQuizDisponibili = new JPanel();
		PanelQuizDisponibili.setBackground(new Color(102, 153, 255));
		PanelQuizDisponibili.setBounds(246, 89, 782, 388);
		PanelQuizDisponibili.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 67, 818, 425);
		FormCollegamentoEsami NumeroTest[]= new FormCollegamentoEsami[100];
		
		
		ArrayList testArrayList = new ArrayList();
		if(controller.SizeTestArrayList()>0)
		{
			
			for (int i = 0; i < controller.SizeTestArrayList(); i++) 
			{
				NumeroTest[i]= new FormCollegamentoEsami(c, frame);
				testArrayList = controller.getTestArrayList(i);
				NumeroTest[i].NomeTest.setText("<html><div style='text-align: center;'>"+(String) testArrayList.get(0)+"</div></html>");
				NumeroTest[i].UtenteTest.setText("<html><div style='text-align: center;'>"+(String) testArrayList.get(1)+"  "+testArrayList.get(2)+"</div></html>");				
				NumeroTest[i].setLocation(PosizioneX, 59);
				PosizioneX=PosizioneX+303;				
				PanelQuizDisponibili.add(NumeroTest[i]);
				int numero=818*controller.SizeTestArrayList()*5;
				PanelQuizDisponibili.setPreferredSize(new Dimension(numero, 400));
			    scrollPane.setPreferredSize(new Dimension(numero, 400));
			    PanelQuizDisponibili.revalidate();
			    PanelQuizDisponibili.repaint();
				scrollPane.revalidate();
				scrollPane.repaint();
			}
			
		}
		else 
		{
			JLabel nessunEsame = new JLabel("Nessun Esame Presente");
		    nessunEsame.setBounds(54, 149, 146, 48);
		    PanelQuizDisponibili.add(nessunEsame);
		}
		//TODO al posto del index 0 ci vuole index giusto per estrarre Test giusto
		
		
		panelMain.add(scrollPane);
		PanelQuizDisponibili.setPreferredSize(new Dimension(818*10, 400));
	    scrollPane.setPreferredSize(new Dimension(818*10, 400));
	    scrollPane.setViewportView(PanelQuizDisponibili);
	    panelMain.add(scrollPane);
	    
	    
		
	}
}
