package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JTextField NomeTest;
	private JTextField NumeroTest;
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
		
		
		
		NomeTest = new JTextField();
		NomeTest.setBounds(383, 121, 309, 22);
		panel_1.add(NomeTest);
		NomeTest.setColumns(10);
		
		NumeroTest = new JTextField();
		NumeroTest.setBounds(383, 78, 309, 20);
		panel_1.add(NumeroTest);
		NumeroTest.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setToolTipText("sonoquituamadre");
		scrollPane.setBounds(60, 154, 962, 471);
		
		JPanel PanelDoveStannoQuiz = new JPanel();
		PanelDoveStannoQuiz.setBackground(new Color(51, 102, 255));
		PanelDoveStannoQuiz.setBounds(60, 154, 962, 471);
		PanelDoveStannoQuiz.setLayout(null);
		
		PanelFormDomande QuizArray[]= new PanelFormDomande[1000];
		NomeTest.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) 
			{
				PanelDoveStannoQuiz.removeAll();
				posizioneY=20;
				String str=NomeTest.getText();
				if(!str.equals(""))
				{	
					int num=Integer.parseInt(str);
					System.out.println("Numero Letto "+num);
					for (int i = 0; i < num; i++) 
					{
						QuizArray[i]=new PanelFormDomande(i+1);
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
			
		
		IndietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
}
}
