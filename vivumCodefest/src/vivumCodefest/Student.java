package vivumCodefest;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Student {
	
	public String nm, sc;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void studScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student window = new Student();
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
	public Student() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);
		
		ResultSet rs;
		try{
		Statement stmt = main.con.createStatement();
		String sql = "Select * from std where uname LIKE '"+main.unm+"';";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			nm = rs.getString("fname");
			sc = rs.getString("score");
		}
		}catch (Exception e) {System.out.print(e);}
		
		JLabel lblNewLabel = new JLabel("Welcome, " + nm);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(82, 67, 699, 141);
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblYourScore = new JLabel("Your Score:" + sc);
		lblYourScore.setForeground(Color.WHITE);
		lblYourScore.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblYourScore.setBounds(82, 214, 532, 148);
		frame.getContentPane().add(lblYourScore);
		
		/*JButton btnNxt = new JButton("Next Question");
		btnNxt.setEnabled(false);
		btnNxt.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNxt.setBackground(Color.DARK_GRAY);
		btnNxt.setForeground(Color.WHITE);
		btnNxt.setBounds(304, 339, 361, 111);
		frame.getContentPane().add(btnNxt);
		*/
		
		JButton btnCont = new JButton("Continue");
		btnCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit edit1 = new edit();
				edit1.editscr();
			}
		});
		btnCont.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnCont.setForeground(Color.WHITE);
		btnCont.setBackground(Color.DARK_GRAY);
		btnCont.setBounds(304, 485, 361, 111);
		frame.getContentPane().add(btnCont);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setBounds(304, 636, 361, 111);
		frame.getContentPane().add(btnExit);
		frame.setBounds(100, 100, 1014, 972);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
