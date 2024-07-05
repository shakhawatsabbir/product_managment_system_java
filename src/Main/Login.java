package Main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField emailFild;
	private JTextField passwordFild;
	private static String email,currentPass,password;
	public static int AdminLoginStatus =0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 943, 694);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setBounds(0, 0, 927, 655);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel errorMassage = new JLabel("");
		errorMassage.setForeground(new Color(255, 0, 0));
		errorMassage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		errorMassage.setBounds(344, 196, 256, 14);
		panel.add(errorMassage);
		
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Raleway SemiBold", Font.PLAIN, 42));
		lblNewLabel.setBounds(10, 91, 907, 68);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email :");
		lblNewLabel_1.setFont(new Font("Raleway SemiBold", Font.PLAIN, 23));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(342, 235, 97, 28);
		panel.add(lblNewLabel_1);
		
		emailFild = new JTextField();
		emailFild.setBounds(344, 274, 273, 34);
		panel.add(emailFild);
		emailFild.setColumns(10);
		
		passwordFild = new JTextField();
		passwordFild.setColumns(10);
		passwordFild.setBounds(344, 373, 273, 34);
		panel.add(passwordFild);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Raleway SemiBold", Font.PLAIN, 23));
		lblNewLabel_1_1.setBounds(342, 330, 230, 28);
		panel.add(lblNewLabel_1_1);
		
		JButton login = new JButton("login");
		login.setFont(new Font("Raleway SemiBold", Font.BOLD, 18));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email = emailFild.getText();
				password = Password.passwordHash(passwordFild.getText()) ;
				try {
					AdminLoginStatus = LoginCheck(); 
					if(AdminLoginStatus == 1) {
						Admin.main(null);
						setVisible(false);
					}
					else if(AdminLoginStatus == 0)  errorMassage.setText("Email not pound");
					else errorMassage.setText("Password dos't match");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		login.setBounds(504, 439, 113, 28);
		panel.add(login);
	}
	
	public static int LoginCheck() throws Exception {
		Connection con =DBconnection.connection();
		PreparedStatement pr = con.prepareStatement("Select *from Admins where email=?");
						pr.setString(1, email);
		ResultSet rs = pr.executeQuery();
		int count =0;
		while(rs.next())
		{
			currentPass = rs.getString("password");
			count++;
		}
		
		if(count > 0) {
			if(currentPass.equals(password)) AdminLoginStatus = 1;
			else AdminLoginStatus = 2;
		}
		else AdminLoginStatus = 0;
		
		return AdminLoginStatus ;
	}

}
