package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelFormDomande extends JPanel
{
	private int NumeroRispostePrecedenti;
	private int aggiorna;
	private int NumeroRisposte;
	public  int numeroquiz;
	public JSpinner QuanteMultipleSpinner;
	public JTextField DomandaField;
	public JTextField RispostaField;
	final public JLabel[] labels=new JLabel[100];
	final public JTextField MultiplaRisposta[] = new JTextField[100];
	public JSpinner punteggioPositivoJSpinner;
	public JCheckBox RispostaMCheckBox;
	public JSpinner punteggioNegativoJSpinner;
	public String modalitaDomanda;
	public int IndexResult;
	public PanelFormDomande(int numero) 
	{
		aggiorna=10;
		numeroquiz=numero;
		modalitaDomanda="A";
		setBounds(273, 184, 434, 441);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel NumeroQuizLabel = new JLabel("Quiz Numero : "+ (numeroquiz+1));
		NumeroQuizLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		NumeroQuizLabel.setBounds(10, 11, 180, 20);
		add(NumeroQuizLabel);
		SpinnerModel model1=new SpinnerNumberModel(1,1,10000,1);
	    SpinnerModel model2=new SpinnerNumberModel(0,-10000,10000,1);
		JLabel PositivoLabel = new JLabel("Puteggio Positivo :");
		PositivoLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		PositivoLabel.setBounds(140, 13, 180, 20);
		add(PositivoLabel);
		punteggioPositivoJSpinner= new JSpinner(model1);
	    punteggioPositivoJSpinner.setBounds(240, 13, 39, 20);
		add(punteggioPositivoJSpinner);
		JLabel NegativoLabel = new JLabel("Puteggio negativo:");
		NegativoLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		NegativoLabel.setBounds(285, 13, 180, 20);
		add(NegativoLabel);
		punteggioNegativoJSpinner= new JSpinner(model2);
		punteggioNegativoJSpinner.setBounds(385, 13, 39, 20);
		add(punteggioNegativoJSpinner);
	    
		
		DomandaField = new JTextField();
		DomandaField.setBounds(71, 48, 335, 31);
		add(DomandaField);
		DomandaField.setColumns(10);
		
		JLabel DomandaLabel = new JLabel("Domanda");
		DomandaLabel.setFont(new Font("Source Code Pro", Font.BOLD, 11));
		DomandaLabel.setBounds(10, 53, 66, 20);
		add(DomandaLabel);
		
		 RispostaMCheckBox = new JCheckBox("Risposta Multipla");
		
		RispostaMCheckBox.setBounds(10, 97, 125, 23);
		add(RispostaMCheckBox);
		
		JLabel RispostaLabel = new JLabel("Risposta");
		RispostaLabel.setFont(new Font("Source Code Pro", Font.BOLD, 11));
		RispostaLabel.setBounds(10, 143, 66, 31);
		add(RispostaLabel);
		
		RispostaField = new JTextField();
		RispostaField.setBounds(71, 143, 335, 31);
		add(RispostaField);
		RispostaField.setColumns(10);
		
		SpinnerModel model1Model=new SpinnerNumberModel(1, 1, 100, 1);
		QuanteMultipleSpinner = new JSpinner(model1Model);
		
		QuanteMultipleSpinner.setEnabled(false);
		QuanteMultipleSpinner.setBounds(135, 97, 36, 23);
		add(QuanteMultipleSpinner);
		
		JPanel DomandePanel = new JPanel();
		DomandePanel.setBounds(0, 197, 434, 233);
		add(DomandePanel);
		DomandePanel.setLayout(null);
		
		
		RispostaMCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			
			
				if (RispostaMCheckBox.isSelected()) 
				{
					modalitaDomanda="M";
					QuanteMultipleSpinner.setEnabled(true);
					NumeroRisposte=(Integer)QuanteMultipleSpinner.getValue();
					char lettera='A';  
					String letteraString=String.valueOf(lettera);  
					aggiorna=10;
			        for (int i=0;i<NumeroRisposte;i++)
			        {
			            labels[i]=new JLabel(letteraString);
			            lettera++;
			            letteraString=String.valueOf(lettera);
			            labels[i].setFont(new Font("Source Code Pro", Font.BOLD, 11));
			            labels[i].setBounds(33, aggiorna, 48, 31);
			            MultiplaRisposta[i] = new JTextField();
			            MultiplaRisposta[i].addActionListener(new ActionListener() {
			                @Override
			                public void actionPerformed(ActionEvent evt) {
			                    System.out.println(MultiplaRisposta[IndexResult].getText());
			                }
			            });
			            MultiplaRisposta[i].setColumns(1);
			    		MultiplaRisposta[i].setBounds(71, aggiorna, 335, 31);
			            aggiorna=aggiorna+40;
			        }
			        for (int i=0;i<NumeroRisposte;i++)
			        {
			            DomandePanel.add(labels[i]);
			            DomandePanel.add(MultiplaRisposta[i]);
			            
			            
			        }
			        DomandePanel.repaint();
			        DomandePanel.revalidate();
					System.out.println("Debug Line-Sono Nella CheckBox"+NumeroRisposte);
					
				}
				else 
				{
					modalitaDomanda="A";
					QuanteMultipleSpinner.setEnabled(false);
					System.out.print(NumeroRisposte);
					DomandePanel.removeAll();
					labels[0]=new JLabel("");
					MultiplaRisposta[0]=new JTextField();
					MultiplaRisposta[0].setBounds(0, 0, 0, 0);
					DomandePanel.add(labels[0]);
			        DomandePanel.add(MultiplaRisposta[0]);
			        DomandePanel.repaint();
			        DomandePanel.revalidate();
			        DomandePanel.removeAll();
				}
			}
		});
			
		QuanteMultipleSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{	
				if(RispostaMCheckBox.isSelected())
				{
					NumeroRispostePrecedenti=NumeroRisposte;
					System.out.println("Precendenti "+NumeroRispostePrecedenti);
					NumeroRisposte=(Integer)QuanteMultipleSpinner.getValue();
					System.out.println("Risposte "+NumeroRisposte);
					if(NumeroRisposte>=1)
					{
						if(NumeroRispostePrecedenti<NumeroRisposte)
						{
							
							NumeroRispostePrecedenti=NumeroRisposte;
							char lettera='A';  
							String letteraString=String.valueOf(lettera);  
							int aggiorna=10;
							DomandePanel.removeAll();
					        for (int i=0;i<NumeroRisposte;i++)
					        {
					            labels[i]=new JLabel(letteraString);
					            lettera++;
					            letteraString=String.valueOf(lettera);
					            labels[i].setFont(new Font("Source Code Pro", Font.BOLD, 11));
					            labels[i].setBounds(33, aggiorna, 48, 31);
					            MultiplaRisposta[i] = new JTextField();
					            IndexResult=i;
					            MultiplaRisposta[i].addActionListener(new ActionListener() {
					                @Override
					                public void actionPerformed(ActionEvent evt) {
					                    System.out.println(MultiplaRisposta[IndexResult].getText());
					                }
					            });
					            MultiplaRisposta[i].setColumns(1);
					    		MultiplaRisposta[i].setBounds(71, aggiorna, 335, 31);
					            aggiorna=aggiorna+40;
					        }
					        for (int i=0;i<NumeroRisposte;i++)
					        {
					            DomandePanel.add(labels[i]);
					            DomandePanel.add(MultiplaRisposta[i]);
					            MultiplaRisposta[i].setEnabled(false);
					            MultiplaRisposta[i].setEnabled(true);
					        }
					        DomandePanel.repaint();
					        DomandePanel.revalidate();
						}
						//Per implementare questo else ci sono volute 2h e 30mim
						else 
						{		
							System.out.println("Sono Nell Altra Parte");
							System.out.println("Risposte Vale"+NumeroRisposte);
							DomandePanel.removeAll();
							char lettera='A';  
							String letteraString=String.valueOf(lettera);  
							int aggiorna=10;
					        for (int i=0;i<NumeroRisposte;i++)
					        {
					            labels[i]=new JLabel(letteraString);
					            lettera++;
					            letteraString=String.valueOf(lettera);
					            labels[i].setFont(new Font("Source Code Pro", Font.BOLD, 11));
					            labels[i].setBounds(33, aggiorna, 48, 31);
					            MultiplaRisposta[i] = new JTextField();
					            MultiplaRisposta[i].addActionListener(new ActionListener() {
					                @Override
					                public void actionPerformed(ActionEvent evt) {
					                    System.out.println(MultiplaRisposta[IndexResult].getText());
					                }
					            });
					            MultiplaRisposta[i].setColumns(1);
					    		MultiplaRisposta[i].setBounds(71, aggiorna, 335, 31);
					            aggiorna=aggiorna+40;
					        }
					        for (int i=0;i<NumeroRisposte;i++)
					        {
					        	
					            DomandePanel.add(labels[i]);
					            DomandePanel.add(MultiplaRisposta[i]);
					            MultiplaRisposta[i].setEnabled(false);
					            MultiplaRisposta[i].setEnabled(true);
					            
					        }
					        
							DomandePanel.repaint();
							DomandePanel.revalidate();
						}
					}
					
					
				}
			}
		});
	}
}
