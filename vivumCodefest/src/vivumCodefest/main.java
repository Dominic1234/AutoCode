package vivumCodefest;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class main {

	private JFrame loginframe;
	private JPasswordField passfield;
	private JTextField unamefield;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static Connection con;
	public static String unm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vivum", "root", "codefest");
		} catch(Exception e) {System.out.print(e);}
		login();
	}
	private void login() {
		loginframe = new JFrame();
		loginframe.getContentPane().setBackground(Color.DARK_GRAY);
		loginframe.setBounds(100, 100, 686, 473);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Login");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Magneto", Font.BOLD, 40));
		lblWelcome.setBounds(245, 27, 170, 50);
		loginframe.getContentPane().add(lblWelcome);
		
		JRadioButton rdbtnStd = new JRadioButton("Student");
		rdbtnStd.setSelected(true);
		rdbtnStd.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnStd.setForeground(Color.WHITE);
		rdbtnStd.setBackground(Color.DARK_GRAY);
		buttonGroup.add(rdbtnStd);
		rdbtnStd.setBounds(85, 275, 155, 29);
		loginframe.getContentPane().add(rdbtnStd);
		
		JRadioButton rdbtnTchr = new JRadioButton("Teacher");
		rdbtnTchr.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnTchr.setForeground(Color.WHITE);
		rdbtnTchr.setBackground(Color.DARK_GRAY);
		buttonGroup.add(rdbtnTchr);
		rdbtnTchr.setBounds(443, 275, 155, 29);
		loginframe.getContentPane().add(rdbtnTchr);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				unm = unamefield.getText();
				if(rdbtnStd.isSelected()) {
					try {
						Statement stmt = con.createStatement();
						@SuppressWarnings("deprecation")
						String sql = "Select * from std where uname LIKE '"+unamefield.getText()+"' AND pass LIKE '"+passfield.getText().toString()+"';";
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next()) {
							loginframe.dispose();
							Student stud = new Student();
							stud.studScreen();
						}
						else
							JOptionPane.showMessageDialog(null, "Login or Password Incorrect");
					}catch(Exception e) {System.out.print(e);}
				}
				else if(rdbtnTchr.isSelected()) {
					try {
						Statement stmt = con.createStatement();
						@SuppressWarnings("deprecation")
						String sql = "Select * from tchr where unam LIKE '"+unamefield.getText()+"' AND pass LIKE '"+passfield.getText().toString()+"';";
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next()) {
							loginframe.dispose();
							Teacher tchr = new Teacher();
							tchr.tchrScreen();
						}
						else
							JOptionPane.showMessageDialog(null, "Login or Password Incorrect");
					}catch(Exception e) {System.out.print(e);System.exit(0);}
				}
				else {
					
				}
			}
		});
		btnLogin.setBounds(85, 342, 115, 29);
		loginframe.getContentPane().add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBackground(Color.DARK_GRAY);
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signup reg = new signup();
				reg.RegisterScreen();
			}
		});
		btnSignUp.setBounds(443, 342, 115, 29);
		loginframe.getContentPane().add(btnSignUp);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(85, 142, 88, 20);
		loginframe.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(85, 204, 94, 20);
		loginframe.getContentPane().add(lblPassword);
		
		passfield = new JPasswordField();
		passfield.setBounds(178, 201, 352, 26);
		loginframe.getContentPane().add(passfield);
		
		unamefield = new JTextField();
		unamefield.setBounds(178, 139, 352, 26);
		loginframe.getContentPane().add(unamefield);
		unamefield.setColumns(10);
		
	}
}

