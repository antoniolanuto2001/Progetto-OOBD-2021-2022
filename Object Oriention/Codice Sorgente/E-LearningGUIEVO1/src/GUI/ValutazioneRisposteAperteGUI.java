package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import org.eclipse.jface.preference.StringFieldEditor;

import Controller.Controller;
import Model.Utente;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;

public class ValutazioneRisposteAperteGUI extends JFrame {


	private JFrame frame;
	private JPanel contentPane;
	private Controller controller;
	private String CodFiscaleDocente;
	public  Utente utenteRisultato;
	public int posizioneY;
	

	/**
	 * Create the frame.
	 */
	public ValutazioneRisposteAperteGUI(Controller c,JFrame frameChiamante,String codfiscaleDocente,int indexRisultato) 
	{
		int index;
		frame=this;
		controller=c;
		CodFiscaleDocente=codfiscaleDocente;
		posizioneY=11;
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		try {
			utenteRisultato=controller.ReturnCopiaofUtente(codfiscaleDocente);
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Color CelesteSchermata = Color.decode("#90caf9");
		Color GrigioApple = Color.decode("#fbfbfd");
		Color NeroApple = Color.decode("#eeeeee");
		Color AzzuroPaneAntonio = Color.decode("#42a5f5");
		Color SfondoApple= Color.decode("#90caf9");
		Color RossoAntonio= Color.decode("#f44336");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel PanelInformazioni = new JPanel();
		PanelInformazioni.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelInformazioni.setBackground(CelesteSchermata);
		PanelInformazioni.setBounds(0, 0, 1064, 124);
		contentPane.add(PanelInformazioni);
		PanelInformazioni.setLayout(null);
		
		JPanel PanelViewInformation = new JPanel();
		PanelViewInformation.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		PanelViewInformation.setForeground(new Color(173, 216, 230));
		PanelViewInformation.setBackground(CelesteSchermata);
		PanelViewInformation.setBounds(378, 27, 275, 71);
		PanelInformazioni.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>Correggi Risposte</div></html>");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(68, 11, 154, 45);
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
		PanelInformazioni.add(IndietroButton);
		
		
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
		
		NomeLabel.setText(utenteRisultato.getNome());
		CognomeLabel.setText(utenteRisultato.getCognome());
		String CodFiscaleDocente=utenteRisultato.getCodiceFiscale();
		
		JLabel AccountLabel2 = new JLabel("");
		AccountLabel2.setBounds(10, 26, 50, 57);
		PanelInformazioni.add(AccountLabel2);
		ImageIcon imgAccopuIcon3 = new ImageIcon(this.getClass().getResource("/images/iconaAccount6.png"));
		AccountLabel2.setIcon(imgAccopuIcon3);
		
		
		JPanel PanelMain = new JPanel();
		PanelMain.setBackground(Color.WHITE);
		PanelMain.setBounds(0, 125, 1064, 636);
		contentPane.add(PanelMain);
		PanelMain.setLayout(null);
		
		JLabel NomeTestLabel = new JLabel("Nome Test : ");
		NomeTestLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		NomeTestLabel.setBounds(371, 28, 100, 34);
		PanelMain.add(NomeTestLabel);
		
		String nomeTestLabelString=controller.ReturnNomeTestFromidResultTest(utenteRisultato.ListaValutazioni.get(indexRisultato).idRisultatoTest);
		JLabel NomeTestEffettivoLabel = new JLabel(nomeTestLabelString);
		NomeTestEffettivoLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		NomeTestEffettivoLabel.setBounds(471, 28, 231, 34);
		PanelMain.add(NomeTestEffettivoLabel);
		
		JLabel AlunnoLabel = new JLabel("Alunno :");
		AlunnoLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		AlunnoLabel.setBounds(393, 73, 72, 34);
		PanelMain.add(AlunnoLabel);
		ArrayList utenteArrayList = new ArrayList();
		String nomeString = "";
		String cognomeString="";
		String matricolaString=utenteRisultato.ListaValutazioni.get(indexRisultato).matricolaString;
		utenteArrayList=controller.RecuperaStudenteFromMatricola(matricolaString);
		nomeString=(String) utenteArrayList.get(0);
		cognomeString=(String) utenteArrayList.get(1);
		JLabel AlunnoEffettivoLabel = new JLabel("New label");
		AlunnoEffettivoLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AlunnoEffettivoLabel.setText(""+nomeString+" "+cognomeString+" "+matricolaString);
		AlunnoEffettivoLabel.setBounds(471, 73, 231, 34);
		PanelMain.add(AlunnoEffettivoLabel);
		
		JButton ButtonSubmit = new JButton("Esegui Correzione");
		ButtonSubmit.setBackground(new Color(211, 211, 211));
		ButtonSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ButtonSubmit.setBounds(446, 109, 144, 34);
		PanelMain.add(ButtonSubmit);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(207, 190, 633, 471);
		JPanel PanelDoveStannoQuiz = new JPanel();
		PanelDoveStannoQuiz.setBackground(AzzuroPaneAntonio);
		PanelDoveStannoQuiz.setBounds(60, 154, 633, 471);
		PanelDoveStannoQuiz.setLayout(null);
		
		
		FormCorreggiRisposte Form[] = new FormCorreggiRisposte[100];
		System.out.println("il size vale : "+utenteRisultato.ListaValutazioni.get(indexRisultato).Valutazioniaperte.size()+"mentre index risulato vale "+indexRisultato);
		for (int i = 0; i < utenteRisultato.ListaValutazioni.get(indexRisultato).Valutazioniaperte.size(); i++) 
		{
			Form[i]=new FormCorreggiRisposte();
			String LabelString=utenteRisultato.ListaValutazioni.get(indexRisultato).Valutazioniaperte.get(i).rispostaInseritaString;			
			Form[i].RispostaEffettivaLabel.setText("Perche spaccima non funzioni");
			Form[i].repaint();
			Form[i].revalidate();
			if(LabelString.length()>45)
			{
					int half = LabelString.length() % 2 == 0 ? LabelString.length()/2 : LabelString.length()/2 + 1;
					String first = LabelString.substring(0, half);
					String second = LabelString.substring(half);
					Form[i].RispostaEffettivaLabel.setText("<html><body>"+first+"<br>"+second+"</body></html>");
					System.out.println("<html><body>"+first+"<br>"+second+"</body></html>");
			}
			else 
			{
				Form[i].RispostaEffettivaLabel.setText(LabelString);
			}
			LabelString=utenteRisultato.ListaValutazioni.get(indexRisultato).Valutazioniaperte.get(i).domandaInseritaString;
			if(LabelString.length()>30)
			{
					int half = LabelString.length() % 2 == 0 ? LabelString.length()/2 : LabelString.length()/2 + 1;
					String first = LabelString.substring(0, half);
					String second = LabelString.substring(half);
					Form[i].DomandaEffettivaLabel.setText("<html><body>"+first+"<br>"+second+"</body></html>");
			}
			else 
			{
				Form[i].DomandaEffettivaLabel.setText(LabelString);
			}
			Form[i].costruisciSpinnerModel= new SpinnerNumberModel(utenteRisultato.ListaValutazioni.get(indexRisultato).Valutazioniaperte.get(i).MinPunt,utenteRisultato.ListaValutazioni.get(indexRisultato).Valutazioniaperte.get(i).MinPunt,utenteRisultato.ListaValutazioni.get(indexRisultato).Valutazioniaperte.get(i).MaxPunt,1);
			Form[i].PunteggioSpiner= new JSpinner(Form[i].costruisciSpinnerModel);
			Form[i].PunteggioSpiner.setBounds(173, 293, 45, 34);
			Form[i].add(Form[i].PunteggioSpiner);
			Form[i].NumeroQuiz.setText(""+(i+1));
			Form[i].setLocation(110,posizioneY);
			posizioneY=posizioneY+397;
			PanelDoveStannoQuiz.add(Form[i]);
			PanelDoveStannoQuiz.setPreferredSize(new Dimension(962, 400*3));
			scrollPane.setPreferredSize(new Dimension(962, 400*3));
			PanelDoveStannoQuiz.revalidate();
			PanelDoveStannoQuiz.repaint();
			scrollPane.revalidate();
			scrollPane.repaint();
		}
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setViewportView(PanelDoveStannoQuiz);	
	    PanelMain.add(scrollPane);
	    
	    ButtonSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				for (int i = 0; i < utenteRisultato.ListaValutazioni.get(indexRisultato).Valutazioniaperte.size(); i++) 
				{
			    	controller.UpdateValutazioniAperte(utenteRisultato.ListaValutazioni.get(indexRisultato).Valutazioniaperte.get(i).idvalutazioneApertaDB, (Integer)Form[i].PunteggioSpiner.getValue());
				}
				controller.ClearAllListaValutazioni(CodFiscaleDocente);
				frameChiamante.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
}
