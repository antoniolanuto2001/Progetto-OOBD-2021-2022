package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.JavaBean;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

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
	public Integer NumeroRisposte;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public CreaQuizGui(Controller c,JFrame frameChiamante,String codiceString) 
{
		
		frame=this;
		CodiceFiscale=codiceString;
		posizioneY=20;
		controller=c;
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		Color CelesteSchermata = Color.decode("#90caf9");
		Color GrigioApple = Color.decode("#fbfbfd");
		Color NeroApple = Color.decode("#eeeeee");
		Color AzzuroPaneAntonio = Color.decode("#42a5f5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(CelesteSchermata);
		panel.setBounds(0, 0, 1064, 124);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel PanelViewInformation = new JPanel();
		PanelViewInformation.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		PanelViewInformation.setForeground(new Color(173, 216, 230));
		PanelViewInformation.setBackground(CelesteSchermata);
		PanelViewInformation.setBounds(378, 27, 275, 71);
		panel.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>Crea Test</div></html>");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(95, 11, 93, 45);
		PanelViewInformation.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		
		JButton IndietroButton = new JButton();
		IndietroButton.setBorderPainted(false);
		IndietroButton.setBorder(null);
		IndietroButton.setMargin(new Insets(0, 0, 0, 0));
		IndietroButton.setContentAreaFilled(false);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/images/iconaBack2.png"));
		IndietroButton.setIcon(imgAccopuIcon);
		IndietroButton.setBounds(945, 27, 76, 71);
		panel.add(IndietroButton);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 114, 1064, 636);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel InformazioniLabel = new JLabel("Informazioni Preliminari");
		InformazioniLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		InformazioniLabel.setBounds(396, 11, 258, 56);
		panel_1.add(InformazioniLabel);
		
		JLabel NomeLabel = new JLabel("Nome Test : ");
		NomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		NomeLabel.setBounds(291, 77, 80, 22);
		panel_1.add(NomeLabel);
		
		JLabel NumeroLabel = new JLabel("Numero Di Quiz : ");
		NumeroLabel.setFont(new Font("Segoe UI ", Font.BOLD, 12));
		NumeroLabel.setBounds(265, 121, 112, 22);
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
		scrollPane.setBounds(207, 190, 633, 471);
		
		JPanel PanelDoveStannoQuiz = new JPanel();
		PanelDoveStannoQuiz.setBackground(AzzuroPaneAntonio);
		PanelDoveStannoQuiz.setBounds(60, 154, 633, 471);
		PanelDoveStannoQuiz.setLayout(null);
		
		PanelFormDomande QuizArray[]= new PanelFormDomande[1000];
		NumeroQuiz.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) 
			{
				PanelDoveStannoQuiz.removeAll();
				String str=NumeroQuiz.getText();
				Boolean Autorizazione;
				Autorizazione=isValid(str);
				posizioneY=20;
				if (Autorizazione) 
				{
					if(!str.equals(""))
					{	
						int num=Integer.parseInt(str);
						if (num>1000) 
						{
							JOptionPane.showMessageDialog(null,"Hey ma 1000 quiz veramente non ti bastano :(","Quanti Quiz Vuoi Creare",JOptionPane.INFORMATION_MESSAGE);
						} 
						else {
							for (int i = 0; i < num; i++) 
							{
								QuizArray[i]=new PanelFormDomande(i);
								QuizArray[i].setLocation(90, posizioneY);
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
						System.out.println("Numero Letto "+num);
						}
					
				
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
	public boolean isValid(String passwordhere) {

		Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
	    Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
	    Pattern lowerCasePatten = Pattern.compile("[a-z ]");
	    boolean flag=true;
	    if (UpperCasePatten.matcher(passwordhere).find()) {
	    	//System.out.println("Password must have atleast one uppercase character !!");
	        flag=false;
	    }
	    if (lowerCasePatten.matcher(passwordhere).find()) {
	    	//System.out.println("Password must have atleast one lowercase character !!");
	        flag=false;
	    }
	    if (specailCharPatten.matcher(passwordhere).find()) {
	    	//System.out.println("Password must have atleast one specail character !!");
	        flag=false;
	    }

	    return flag;

	}
}
