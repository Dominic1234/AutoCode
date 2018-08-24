package vivumCodefest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class signup {

	private JFrame signframe;
	private JTextField usrField;
	private JTextField passField;
	private JTextField confField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField gradeField;
	private JTextField secField;
	private JTextField fnmField;

	/**
	 * Launch the application.
	 */
	public void RegisterScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup window = new signup();
					window.signframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		signframe = new JFrame();
		signframe.getContentPane().setBackground(Color.DARK_GRAY);
		signframe.setBounds(100, 100, 701, 766);
		signframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signframe.getContentPane().setLayout(null);
		
		JLabel lblRegistration = new JLabel("Registration");
		lblRegistration.setForeground(Color.WHITE);
		lblRegistration.setFont(new Font("Magneto", Font.BOLD, 40));
		lblRegistration.setBounds(172, 39, 279, 50);
		signframe.getContentPane().add(lblRegistration);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 25));
		lblUsername.setBounds(36, 235, 138, 50);
		signframe.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 25));
		lblPassword.setBounds(36, 327, 138, 50);
		signframe.getContentPane().add(lblPassword);
		
		usrField = new JTextField();
		usrField.setBounds(172, 250, 317, 26);
		signframe.getContentPane().add(usrField);
		usrField.setColumns(10);
		
		passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(172, 342, 317, 26);
		signframe.getContentPane().add(passField);
		
		JLabel lblReconfirm = new JLabel("Re-confirm:");
		lblReconfirm.setForeground(Color.WHITE);
		lblReconfirm.setFont(new Font("Arial", Font.BOLD, 25));
		lblReconfirm.setBounds(27, 375, 147, 50);
		signframe.getContentPane().add(lblReconfirm);
		
		confField = new JTextField();
		confField.setColumns(10);
		confField.setBounds(172, 390, 317, 26);
		signframe.getContentPane().add(confField);
		
		JButton btnRgtr = new JButton("Register");
		
		JRadioButton rdbtnStd = new JRadioButton("Student");
		buttonGroup.add(rdbtnStd);
		rdbtnStd.setFont(new Font("Tahoma", Font.BOLD, 20));
		rdbtnStd.setForeground(Color.WHITE);
		rdbtnStd.setBackground(Color.DARK_GRAY);
		rdbtnStd.setBounds(61, 149, 155, 29);
		signframe.getContentPane().add(rdbtnStd);
		
		JRadioButton rdbtnTchr = new JRadioButton("Teacher");
		buttonGroup.add(rdbtnTchr);
		rdbtnTchr.setFont(new Font("Tahoma", Font.BOLD, 20));
		rdbtnTchr.setForeground(Color.WHITE);
		rdbtnTchr.setBackground(Color.DARK_GRAY);
		rdbtnTchr.setBounds(414, 149, 155, 29);
		signframe.getContentPane().add(rdbtnTchr);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setForeground(Color.WHITE);
		lblGrade.setFont(new Font("Arial", Font.BOLD, 25));
		lblGrade.setBounds(27, 419, 147, 50);
		signframe.getContentPane().add(lblGrade);
		
		gradeField = new JTextField();
		gradeField.setColumns(10);
		gradeField.setBounds(172, 434, 317, 26);
		signframe.getContentPane().add(gradeField);
		
		JLabel lblSec = new JLabel("Section:");
		lblSec.setForeground(Color.WHITE);
		lblSec.setFont(new Font("Arial", Font.BOLD, 25));
		lblSec.setBounds(27, 463, 147, 50);
		signframe.getContentPane().add(lblSec);
		
		secField = new JTextField();
		secField.setColumns(10);
		secField.setBounds(172, 478, 317, 26);
		signframe.getContentPane().add(secField);
		
		btnRgtr.setForeground(new Color(60, 179, 113));
		btnRgtr.setBackground(Color.DARK_GRAY);
		btnRgtr.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnRgtr.setBounds(232, 625, 196, 69);
		signframe.getContentPane().add(btnRgtr);
		
		JLabel lblFname = new JLabel("Fullname:");
		lblFname.setForeground(Color.WHITE);
		lblFname.setFont(new Font("Arial", Font.BOLD, 25));
		lblFname.setBounds(36, 281, 138, 50);
		signframe.getContentPane().add(lblFname);
		
		fnmField = new JTextField();
		fnmField.setColumns(10);
		fnmField.setBounds(172, 296, 317, 26);
		signframe.getContentPane().add(fnmField);
		
		btnRgtr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnStd.isSelected()) {
					try {
						Statement stmt = main.con.createStatement();
						String sql = "INSERT INTO Std VALUES (null, '"+usrField.getText()+"', '"+passField.getText()+"', '"+fnmField.getText()+"', '"+gradeField.getText()+"', '"+secField.getText()+"',0,null,1);";
						stmt.execute(sql);
					}catch(Exception e) {System.out.print(e);}
				}
				else if(rdbtnTchr.isSelected()) {
					try {
						Statement stmt = main.con.createStatement();
						String sql = "INSERT INTO Tchr VALUES (null, '"+usrField.getText()+"', '"+fnmField.getText()+"', '"+gradeField.getText()+"', '"+secField.getText()+"', '"+passField.getText()+"');";
						stmt.execute(sql);
					}catch(Exception e) {System.out.print(e);}
				}
				JOptionPane.showMessageDialog(null, "Success!");
				signframe.dispose();
			}
		});
	}
}
