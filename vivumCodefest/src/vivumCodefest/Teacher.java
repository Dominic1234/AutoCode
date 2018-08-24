package vivumCodefest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Teacher {
	
	public String nm, sc;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void tchrScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teacher window = new Teacher();
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
	public Teacher() {

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
		String sql = "Select * from tchr where unam LIKE '"+main.unm+"';";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			nm = rs.getString("tnam");
		}
		}catch (Exception e) {System.out.print(e);}
		
		JLabel label = new JLabel("Welcome, " + nm);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 35));
		label.setBounds(53, 42, 699, 141);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 30));
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(287, 650, 361, 111);
		frame.getContentPane().add(button);
		frame.setBounds(100, 100, 980, 886);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
