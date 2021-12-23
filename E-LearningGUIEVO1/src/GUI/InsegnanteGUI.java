package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class InsegnanteGUI extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private Controller controller;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public InsegnanteGUI(Controller c,JFrame frameChiamante) 
	{
		frame=this;
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1064, 87);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GUI Insegnante");
		lblNewLabel.setBounds(10, 11, 149, 54);
		panel.add(lblNewLabel);
		
		JButton IndietroButton = new JButton("Indietro");
		IndietroButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		IndietroButton.setVerticalAlignment(SwingConstants.TOP);
		IndietroButton.setBounds(864, 11, 149, 57);
		panel.add(IndietroButton);
		ImageIcon imgAccopuIcon = new ImageIcon(this.getClass().getResource("/backIcona.png"));
		IndietroButton.setIcon(imgAccopuIcon);
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
