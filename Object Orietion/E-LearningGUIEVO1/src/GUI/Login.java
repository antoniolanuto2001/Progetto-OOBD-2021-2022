package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JFrame frame;
	private JTextField EmailText;
	private JTextField PasswordText;
	private Controller controller;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public Login(Controller PrincipaleController) {
		controller=PrincipaleController;
		initialize();
		addStyleTextField(EmailText);
		frame.setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public void addStyleTextField(JTextField f)
	{
		Font font=f.getFont();
		font = font.deriveFont(Font.ITALIC);
		f.setFont(font);
		f.setForeground(Color.gray);
		
	}
	public void removeStyleTextField(JTextField f)
	{
		Font font=f.getFont();
		font = font.deriveFont(Font.PLAIN|Font.BOLD);
		f.setFont(font);
		f.setForeground(Color.black);
		
	}
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logoPrincipale.png"));
		frame.setIconImage(logo.getImage());
		
		
		JPanel LoginLeft = new JPanel();
		LoginLeft.setBorder(new EmptyBorder(1, 1, 1, 1));
		LoginLeft.setBackground(Color.WHITE);
		LoginLeft.setBounds(0, 0, 483, 761);
		frame.getContentPane().add(LoginLeft);
		LoginLeft.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(37, 51, 408, 271);
		LoginLeft.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel BenvenutoLogin = new JLabel("Login");
		BenvenutoLogin.setBackground(Color.WHITE);
		BenvenutoLogin.setVerticalAlignment(SwingConstants.TOP);
		BenvenutoLogin.setBounds(37, 192, 319, 41);
		panel_3.add(BenvenutoLogin);
		BenvenutoLogin.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		
		JLabel lblNewLabel = new JLabel("PassaParola E-learning");
		lblNewLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
		lblNewLabel.setBounds(87, 11, 242, 45);
		panel_3.add(lblNewLabel);
		
		JLabel AccountLabel = new JLabel("");
		AccountLabel.setBounds(147, 67, 100, 100);
		panel_3.add(AccountLabel);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/images/iconaAccount4.png"));
		AccountLabel.setIcon(imgAccopuIcon);
		
		JLabel lblNewLabel_1 = new JLabel("Portale d'accesso alla piattoforma  di Quiz!");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(37, 226, 319, 34);
		panel_3.add(lblNewLabel_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setForeground(new Color(0, 0, 0));
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setLayout(null);
		panel_3_1.setBounds(37, 321, 408, 385);
		LoginLeft.add(panel_3_1);
		
		
		
		
		
		JLabel EmailLabel = new JLabel("Email*");
		EmailLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		EmailLabel.setBounds(38, 34, 168, 28);
		panel_3_1.add(EmailLabel);
		
		EmailText = new JTextField();
		EmailText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(EmailText.getText().contentEquals("mail@website.com"));
				{
					EmailText.setText(null);
					EmailText.requestFocus();
					removeStyleTextField(EmailText);
				}
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				if(EmailText.getText().length()==0)
				{
					addStyleTextField(EmailText);
					EmailText.setText("mail@website.com");
				}
			}
		});
		EmailText.setText("mail@website.com");
		EmailText.setToolTipText("");
		EmailText.setBounds(38, 80, 332, 28);
		panel_3_1.add(EmailText);
		EmailText.setColumns(10);
		
		JLabel PasswordLabel = new JLabel("Password*");
		PasswordLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		PasswordLabel.setBounds(38, 132, 86, 28);
		panel_3_1.add(PasswordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(38, 173, 332, 28);
		panel_3_1.add(passwordField);
		
		JPanel LoginRight = new JPanel();
		LoginRight.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		LoginRight.setBackground(new Color(102, 0, 255));
		LoginRight.setBounds(482, 0, 582, 761);
		frame.getContentPane().add(LoginRight);
		LoginRight.setLayout(null);
		
		JLabel ProgrammaerLabel = new JLabel("");
		ProgrammaerLabel.setBounds(-63, 172, 660, 453);
		ImageIcon imgAccopuIcon2 = new ImageIcon(this.getClass().getResource("/images/ImagePrincipal2.png"));
		LoginRight.add(ProgrammaerLabel);
		ProgrammaerLabel.setIcon(imgAccopuIcon2);
		JButton ButtonLogin = new JButton("Login");
		ButtonLogin.setBackground(new Color(102, 0, 255));
		ButtonLogin.setForeground(new Color(255, 255, 255));
		ButtonLogin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		ButtonLogin.setBounds(95, 244, 214, 33);
		panel_3_1.add(ButtonLogin);
		
		JCheckBox MostraCheckBox = new JCheckBox("Mostra Password");
		MostraCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					if (MostraCheckBox.isSelected()) 
					{
						passwordField.setEchoChar((char)0);
					}
					else 
					{
						passwordField.setEchoChar('*');
					}
			}
		});
		MostraCheckBox.setBackground(Color.WHITE);
		MostraCheckBox.setForeground(Color.BLACK);
		MostraCheckBox.setFont(new Font("Segoe UI", Font.BOLD, 11));
		MostraCheckBox.setBounds(38, 214, 125, 23);
		panel_3_1.add(MostraCheckBox);
		
		JLabel RegisterLabel = new JLabel("Non hai un Account? Registrati");
		RegisterLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				JFrame frameCreaRegister=new Register(controller,frame);
				frame.setVisible(false);
				frameCreaRegister.setVisible(true);
			}
		});
		RegisterLabel.setForeground(new Color(0, 102, 255));
		RegisterLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		RegisterLabel.setBounds(212, 216, 168, 17);
		panel_3_1.add(RegisterLabel);
		
	
	
		ButtonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//TODO Metodo al solo scopo di Debug Per implementazione Finale Connessione e metodo esterno DB
				String emailString=EmailText.getText();
				String passwordString=passwordField.getText();
				int result=controller.VerificaUtente(emailString,passwordString);
				System.out.println("result tornato "+result);
				if (result==0||(emailString.contentEquals("c")&&passwordString.contentEquals("c"))) 
				{
					
					//TODO Aggiunta Manuale Da fare Dinamica Con query da Database
					EmailText.setText("");
					passwordField.setText("");
					JFrame frameInsegnante=new InsegnanteGUI(controller,frame,emailString);
					frame.setVisible(false);
					frameInsegnante.setVisible(true);	
				}
				else if (result==1||(emailString.contentEquals("a")&&passwordString.contentEquals("a"))) 
				{
					JFrame frameStudente=new StudenteGUI(controller,frame,emailString);
					EmailText.setText("");
					passwordField.setText("");
					frame.setVisible(false);
					frameStudente.setVisible(true);
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"Email Invalida","Errore Login",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
		
		
	}
}
