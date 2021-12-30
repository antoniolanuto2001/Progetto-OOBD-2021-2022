package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.DimensionUIResource;

import com.ibm.icu.impl.number.parse.ParsedNumber;

import javax.swing.event.ChangeEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class CreaQuizGui extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private Controller controller;
	private JTextField NumeroQuiz;
	private JTextField NomeTest;
	public int posizioneY;
	public String CodiceFiscale;	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Integer NumeroRisposte ;
	public CreaQuizGui(Controller c,JFrame frameChiamante,String codiceString) 
{
		
		frame=this;
		CodiceFiscale=codiceString;
		posizioneY=20;
		controller=c;
		//TODO Da implementare La corrispondeza tra codice fiscale ed autore del test
	
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
		JLabel lblNewLabel = new JLabel("Crea Quiz");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel.setBounds(470, 27, 140, 57);
		panel.add(lblNewLabel);
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		IndietroButton.setVerticalAlignment(SwingConstants.TOP);
		IndietroButton.setBounds(860, 27, 149, 57);
		panel.add(IndietroButton);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/backIcona.png"));
		IndietroButton.setIcon(imgAccopuIcon);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 114, 1064, 636);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel InformazioniLabel = new JLabel("Informazioni Preliminari");
		InformazioniLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		InformazioniLabel.setBounds(396, 11, 258, 56);
		panel_1.add(InformazioniLabel);
		
		JLabel NomeLabel = new JLabel("Nome Test");
		NomeLabel.setFont(new Font("Source Code Pro", Font.BOLD, 11));
		NomeLabel.setBounds(291, 77, 69, 22);
		panel_1.add(NomeLabel);
		
		JLabel NumeroLabel = new JLabel("Numero Di Quiz");
		NumeroLabel.setFont(new Font("Source Code Pro", Font.BOLD, 11));
		NumeroLabel.setBounds(261, 121, 112, 22);
		panel_1.add(NumeroLabel);
		
		
		
		NumeroQuiz = new JTextField();
		NumeroQuiz.setBounds(383, 121, 309, 22);
		panel_1.add(NumeroQuiz);
		NumeroQuiz.setColumns(10);
		
		NomeTest = new JTextField();
		NomeTest.setBounds(383, 78, 309, 20);
		panel_1.add(NomeTest);
		NomeTest.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(60, 190, 962, 471);
		
		JPanel PanelDoveStannoQuiz = new JPanel();
		PanelDoveStannoQuiz.setBackground(new Color(51, 102, 255));
		PanelDoveStannoQuiz.setBounds(60, 154, 962, 471);
		PanelDoveStannoQuiz.setLayout(null);
		
		PanelFormDomande QuizArray[]= new PanelFormDomande[1000];
		NumeroQuiz.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) 
			{
				PanelDoveStannoQuiz.removeAll();
				posizioneY=20;
				String str=NumeroQuiz.getText();
				if(!str.equals(""))
				{	
					int num=Integer.parseInt(str);
					System.out.println("Numero Letto "+num);
					for (int i = 0; i < num; i++) 
					{
						QuizArray[i]=new PanelFormDomande(i);
						QuizArray[i].setLocation(273, posizioneY);
						posizioneY=posizioneY+490;
						PanelDoveStannoQuiz.add(QuizArray[i]);
					}
					PanelDoveStannoQuiz.setPreferredSize(new Dimension(962, 490*num));
				    scrollPane.setPreferredSize(new Dimension(962, 500*num));
					PanelDoveStannoQuiz.revalidate();
					PanelDoveStannoQuiz.repaint();
					scrollPane.revalidate();
					scrollPane.repaint();
				}
				
			    
				
			}
		});
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    PanelDoveStannoQuiz.setPreferredSize(new Dimension(962, 5000));
	    scrollPane.setPreferredSize(new Dimension(962, 5000));
	    scrollPane.setViewportView(PanelDoveStannoQuiz);	
	    panel_1.add(scrollPane);
	    
	    JButton SubmitButton = new JButton("Invio Quiz");
	    SubmitButton.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		String str=NumeroQuiz.getText();
	    		String str2=NomeTest.getText();
	    		if(!str.equals("")&&!str2.equals(""))
	    		{
	    			int num=Integer.parseInt(str);
	    			controller.aggiungiTest(NomeTest.getText(), codiceString);
	    			System.out.println("Inizio Procedura InvioController");
	    			for (int i = 0; i < num; i++) 
					{
	    				controller.addQuizToTest(0, 0, QuizArray[i].modalitaDomanda);
	    				if(QuizArray[i].modalitaDomanda.contentEquals("M"))
	    				{
	    					int NumeroRisposteMesse=(Integer)QuizArray[i].QuanteMultipleSpinner.getValue();
	    					String domandaString=QuizArray[i].DomandaField.getText();
							String testoString=QuizArray[i].RispostaField.getText();
							controller.addDomanda(domandaString,testoString);
	    					for (int j = 0; j < NumeroRisposteMesse; j++) 
	    					{
	    						 domandaString=QuizArray[i].labels[j].getText();
								 testoString=QuizArray[i].MultiplaRisposta[j].getText();
								 controller.addDomanda(domandaString,testoString);
	    					}
	    				}
	    				else if(QuizArray[i].modalitaDomanda.contentEquals("A")) 
	    				{
	    					String domandaString=QuizArray[i].DomandaField.getText();
							String testoString=QuizArray[i].RispostaField.getText();
							controller.addDomanda(domandaString,testoString);
						}
	    				else {
	    					System.out.println("C é un errore Tommaso");
						}
					}
	    			System.out.println("Procedura finita Tutto OK");
	    			controller.Debug();
	    			JOptionPane.showMessageDialog(null,"Quiz Eseguito","Conferma Quiz",JOptionPane.INFORMATION_MESSAGE);
	    			frameChiamante.setVisible(true);
					frame.setVisible(false);
					frame.dispose();
					
	    		}
	    		
	    	}
	    });
	    SubmitButton.setBounds(454, 154, 126, 23);
	    panel_1.add(SubmitButton);
			
		
		IndietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
				controller.Debug();
			}
		});
}
}
