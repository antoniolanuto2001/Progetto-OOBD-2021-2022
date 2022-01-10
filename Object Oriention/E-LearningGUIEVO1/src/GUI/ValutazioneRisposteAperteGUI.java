package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

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
		
		try {
			utenteRisultato=controller.ReturnCopiaofUtente(codfiscaleDocente);
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//TODO Prima della release rimuove questo if e lasciare solo else
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
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
		PanelViewInformation.setBounds(355, 27, 275, 71);
		panel.add(PanelViewInformation);
		PanelViewInformation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>Valutazione Risposte Aperte View</div></html>");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(23, 11, 228, 45);
		PanelViewInformation.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		IndietroButton.setVerticalAlignment(SwingConstants.TOP);
		IndietroButton.setBounds(860, 27, 149, 57);
		panel.add(IndietroButton);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/images/backIcona.png"));
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
		
		JLabel Studente = new JLabel("Insegnante");
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
		NomeLabel.setText(utenteRisultato.getNome());
		CognomeLabel.setText(utenteRisultato.getCognome());
		String CodFiscaleDocente=utenteRisultato.getCodiceFiscale();
		
		JLabel AccountLabel2 = new JLabel("");
		AccountLabel2.setBounds(10, 26, 50, 57);
		panel.add(AccountLabel2);
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
		
		JLabel AlunnoEffettivoLabel = new JLabel("New label");
		AlunnoEffettivoLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AlunnoEffettivoLabel.setText(utenteRisultato.ListaValutazioni.get(indexRisultato).matricolaString);
		AlunnoEffettivoLabel.setBounds(471, 73, 231, 34);
		PanelMain.add(AlunnoEffettivoLabel);
		
		JButton ButtonSubmit = new JButton("Esegui Correzione");
		
		ButtonSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ButtonSubmit.setBounds(446, 109, 144, 34);
		PanelMain.add(ButtonSubmit);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(60, 190, 962, 471);
		JPanel PanelDoveStannoQuiz = new JPanel();
		PanelDoveStannoQuiz.setBackground(new Color(51, 102, 255));
		PanelDoveStannoQuiz.setBounds(60, 154, 962, 471);
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
			Form[i].setLocation(256,posizioneY);
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
