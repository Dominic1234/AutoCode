package vivumCodefest;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class edit {

	private JFrame frame;
	private JTextArea area = new JTextArea(20,120);
	
	public String in, quest, ans;
	public Integer pnt;
	public File SavFile;

	/**
	 * Launch the application.
	 */
	public void editscr() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					edit window = new edit();
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
	public edit() {
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
		String sql = "Select * from quests, std where uname LIKE '"+main.unm+"' and quests.q_no LIKE std.quest;";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			in = rs.getString("q_no");
			quest = rs.getString("question");
			ans = rs.getString("expans");
			pnt = rs.getInt("points");
		}
		}catch (Exception e) {System.out.print(e);}
		
		JLabel lblQuestion = new JLabel("Q." + in +" : " + quest);
		lblQuestion.setVerticalAlignment(SwingConstants.TOP);
		lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuestion.setForeground(Color.WHITE);
		lblQuestion.setBounds(48, 35, 721, 113);
		frame.getContentPane().add(lblQuestion);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(84, 501, 115, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser save = new JFileChooser();
				int returnVal = save.showSaveDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					try { 
						SavFile = new File(save.getSelectedFile()+".cpp");
						FileWriter fw = new FileWriter(save.getSelectedFile()+".cpp");
						fw.write(area.getText().toString());
						fw.close();
						} catch (Exception fe) {};
						
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Color.DARK_GRAY);
		btnSave.setBounds(250, 501, 115, 29);
		frame.getContentPane().add(btnSave);
		
		JButton btnCompile = new JButton("Compile");
		btnCompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {Process process = new ProcessBuilder("cmd.exe", "/C", "gcc",SavFile.toString()).start();
				System.out.println(SavFile.toString());

				JOptionPane.showMessageDialog(null, "Compiling...");
	
				/*if (process.getInputStream().read() == -1) {
				    // that means something was written to stderr, and you can do something like
				    JOptionPane.showMessageDialog(null, process.getInputStream());
				    System.out.println(process.getInputStream());
				    System.exit(-1);
				}
				else {*/
				process.waitFor();
				JOptionPane.showMessageDialog(null, "Success!");
				//}
				BufferedReader stdInput = new BufferedReader(new
				         InputStreamReader(process.getInputStream()));
				        String s = null;
				        while ((s = stdInput.readLine()) != null)
				        {
				            System.out.println(s);
				        }
				} catch (Exception fe) {
					System.out.print(fe);
					return ;}
				         
			}
		});
		btnCompile.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCompile.setForeground(Color.WHITE);
		btnCompile.setBackground(Color.DARK_GRAY);
		btnCompile.setBounds(421, 501, 115, 29);
		frame.getContentPane().add(btnCompile);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Process process = new ProcessBuilder("cmd.exe", "/C", "a.exe >> res.txt").start();
					File ansf = new File ("res.txt");
					BufferedReader br = new BufferedReader(new FileReader(ansf));
					String anst = br.readLine();
					BufferedReader stdInput = new BufferedReader(new
					         InputStreamReader(process.getInputStream()));
					System.out.println(stdInput);
					if(ans.equals(anst)) {
						try{
							Statement stmt = main.con.createStatement();
							String sql = "Update std set score = score + '"+pnt+"' where uname = '"+main.unm+"'";
							stmt.executeUpdate(sql);
							String sql2 = "Update std set quest = quest + 1 where quest + 1 IN(SELECT q_no FROM quests)";
							stmt.executeUpdate(sql2);
							br.close();
						}catch (Exception e) {System.out.print(e);}
						JOptionPane.showMessageDialog(null, "Correct! Points Added");
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Wrong Output");
					}
						
						
				} catch (Exception fe) { System.out.println(fe);}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(Color.DARK_GRAY);
		btnSubmit.setBounds(622, 501, 115, 29);
		frame.getContentPane().add(btnSubmit);
		
		JScrollPane scrollPane = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(48, 79, 731, 371);
		frame.getContentPane().add(scrollPane);
		frame.setBounds(100, 100, 860, 602);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
