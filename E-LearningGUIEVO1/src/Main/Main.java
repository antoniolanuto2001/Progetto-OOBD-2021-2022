package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.Panel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class Main {

	private JFrame frame;
	private JTextField EmailText;
	private JTextField PasswordText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/iconaAccount4.png"));
		AccountLabel.setIcon(imgAccopuIcon);
		
		JLabel lblNewLabel_1 = new JLabel("Portale d'accesso alla piattoforma  di Quiz!");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(37, 226, 319, 34);
		panel_3.add(lblNewLabel_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setLayout(null);
		panel_3_1.setBounds(37, 321, 408, 385);
		LoginLeft.add(panel_3_1);
		
		JButton ButtonLogin = new JButton("Login");
		ButtonLogin.setBackground(new Color(102, 0, 255));
		ButtonLogin.setForeground(new Color(255, 255, 255));
		ButtonLogin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		ButtonLogin.setBounds(95, 244, 214, 33);
		panel_3_1.add(ButtonLogin);
		
		JLabel EmailLabel = new JLabel("Email*");
		EmailLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		EmailLabel.setBounds(38, 34, 168, 28);
		panel_3_1.add(EmailLabel);
		
		EmailText = new JTextField();
		EmailText.setToolTipText("");
		EmailText.setBounds(38, 80, 332, 28);
		panel_3_1.add(EmailText);
		EmailText.setColumns(10);
		
		JLabel PasswordLabel = new JLabel("Password*");
		PasswordLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		PasswordLabel.setBounds(38, 132, 86, 28);
		panel_3_1.add(PasswordLabel);
		
		PasswordText = new JTextField();
		PasswordText.setColumns(10);
		PasswordText.setBounds(38, 173, 332, 28);
		panel_3_1.add(PasswordText);
		
		JPanel LoginRight = new JPanel();
		LoginRight.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		LoginRight.setBackground(new Color(102, 0, 255));
		LoginRight.setBounds(482, 0, 582, 761);
		frame.getContentPane().add(LoginRight);
		LoginRight.setLayout(null);
		
		JLabel ProgrammaerLabel = new JLabel("");
		ProgrammaerLabel.setBounds(-63, 172, 660, 453);
		ImageIcon imgAccopuIcon2 = new ImageIcon(this.getClass().getResource("/ImagePrincipal2.png"));
		LoginRight.add(ProgrammaerLabel);
		ProgrammaerLabel.setIcon(imgAccopuIcon2);
		
		
		
		
		
	}
}
