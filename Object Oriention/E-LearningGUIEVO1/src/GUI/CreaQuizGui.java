package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.JavaBean;
import java.time.Month;
import java.time.MonthDay;
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
import javax.swing.border.EtchedBorder;

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
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images//logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		
		
		
		
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
		JPanel PanelViewInformation = new JPanel();
		PanelViewInformation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(65, 105, 225), new Color(123, 104, 238)));
		PanelViewInformation.setForeground(new Color(173, 216, 230));
		PanelViewInformation.setBackground(new Color(135, 206, 250));
		PanelViewInformation.setBounds(375, 27, 275, 71);
		panel.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'> Crea Test </div></html>");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(99, 11, 86, 45);
		PanelViewInformation.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		IndietroButton.setVerticalAlignment(SwingConstants.TOP);
		IndietroButton.setBounds(860, 27, 149, 57);
		panel.add(IndietroButton);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/images//backIcona.png"));
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
	    scrollPane.setPreferredSize(new Dimension(962, 10000));
	    scrollPane.setViewportView(PanelDoveStannoQuiz);	
	    panel_1.add(scrollPane);
	    String spinnerRiferimentoString= String.valueOf(java.time.Year.now());
	    MonthDay m = MonthDay.now();
	    int YearGraficoSpinner=Integer.parseInt(spinnerRiferimentoString);
	    int DayGraficoSpinner=m.getDayOfMonth();
	    int meseGraficoSpinner=m.getMonthValue();
	    SpinnerModel model1=new SpinnerNumberModel(DayGraficoSpinner,DayGraficoSpinner,31,1);
	    SpinnerModel model3=new SpinnerNumberModel(YearGraficoSpinner,YearGraficoSpinner,YearGraficoSpinner+1000,1);
	    SpinnerModel model2=new SpinnerNumberModel(meseGraficoSpinner,meseGraficoSpinner,12,1);
	    JSpinner Giornospinner = new JSpinner(model1);
	    Giornospinner.setBounds(742, 122, 39, 20);
	    panel_1.add(Giornospinner);
	    
	    JSpinner MeseSpinner = new JSpinner(model2);
	    MeseSpinner.setBounds(799, 122, 30, 20);
	    panel_1.add(MeseSpinner);
	    
	    JSpinner AnnoSpinner = new JSpinner(model3);
	    AnnoSpinner.setBounds(846, 122, 55, 20);
	    panel_1.add(AnnoSpinner);
	    
	    JLabel GiornoLabel = new JLabel("Giorno");
	    GiornoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    GiornoLabel.setBounds(742, 97, 46, 14);
	    panel_1.add(GiornoLabel);
	    
	    JLabel MeseLabel = new JLabel("Mese");
	    MeseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    MeseLabel.setBounds(799, 97, 46, 14);
	    panel_1.add(MeseLabel);
	    
	    JLabel AnnoLabel = new JLabel("Anno");
	    AnnoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	    AnnoLabel.setBounds(855, 97, 46, 14);
	    panel_1.add(AnnoLabel);
	    
	    JButton SubmitButton = new JButton("Invio Quiz");
	    SubmitButton.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		String str=NumeroQuiz.getText();
	    		String str2=NomeTest.getText();
	    		int num=Integer.parseInt(str);
	    	Boolean eseguiBoolean=true;
	    	for (int i = 0; i < num; i++) 
	    	{
	    		String controllo=QuizArray[i].RispostaField.getText();
	    		if(QuizArray[i].RispostaMCheckBox.isSelected()&&controllo.length()>1)
	    		{
	    			eseguiBoolean=false;
	    		}
	    	}
	    	
	    	if (eseguiBoolean==true) 
	    	{
	    		
	    		if(!str.equals("")&&!str2.equals(""))
	    		{
	    			
	    			
	    			controller.aggiungiTest(NomeTest.getText(), codiceString,(int)Giornospinner.getValue(),(int)MeseSpinner.getValue(),(int)AnnoSpinner.getValue());
	    			controller.AggiungiTestalDb();
	    			System.out.println("Inizio Procedura InvioController");
	    			for (int i = 0; i < num; i++) 
					{
	    				controller.addQuizToTest((int) QuizArray[i].punteggioPositivoJSpinner.getValue(),(int) QuizArray[i].punteggioNegativoJSpinner.getValue() , QuizArray[i].modalitaDomanda);
	    				if(QuizArray[i].modalitaDomanda.contentEquals("M"))
	    				{
	    					int NumeroRisposteMesse=(Integer)QuizArray[i].QuanteMultipleSpinner.getValue();
	    					String domandaString=QuizArray[i].DomandaField.getText();
							String testoString=QuizArray[i].RispostaField.getText();
							controller.addDomanda(domandaString,testoString);
							controller.AggiungiQuizAlDB("M");
							for (int j = 0; j < NumeroRisposteMesse; j++) 
	    					{
	    						 domandaString=QuizArray[i].labels[j].getText();
								 testoString=QuizArray[i].MultiplaRisposta[j].getText();
								 controller.addDomanda(domandaString,testoString);
								 controller.AggiungiRipostaAlDB(domandaString,testoString);
	    					}
	    				}
	    				else if(QuizArray[i].modalitaDomanda.contentEquals("A")) 
	    				{
	    					String domandaString=QuizArray[i].DomandaField.getText();
							String testoString=QuizArray[i].RispostaField.getText();
							controller.addDomanda(domandaString,testoString);
							controller.AggiungiQuizAlDB("A");
	    				}
	    				else {
	    					System.out.println("C é un errore Tommaso");
						}
					}
	    			System.out.println("Procedura finita Tutto OK");
	    			JOptionPane.showMessageDialog(null,"Quiz Eseguito","Conferma Quiz",JOptionPane.INFORMATION_MESSAGE);
	    			frameChiamante.setVisible(true);
					frame.setVisible(false);
					frame.dispose();
					
	    		}
			}
	    	else 
	    	{
	    		JOptionPane.showMessageDialog(null,"Inserire una sola Lettera corretta in quiz a riposta multipla","Quiz Errore",JOptionPane.INFORMATION_MESSAGE);
			}
	    		
	    	
	    		
	    	}
	    });
	    SubmitButton.setBounds(454, 154, 126, 23);
	    panel_1.add(SubmitButton);
	    
	    JLabel lblNewLabel_1 = new JLabel("Scandenza Quiz:");
	    lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    lblNewLabel_1.setBounds(742, 78, 103, 18);
	    panel_1.add(lblNewLabel_1);
	    
	    
			
		
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
