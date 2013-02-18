import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Driver
{
	static JFrame main;
	
	public static void main(String[] args)
	{
		main = createStartFrame();
		main.getContentPane().add(createLoginMenu());
	}
	
	private static Component createLoginMenu() {
		JButton blogin = new JButton("Login");
		JPanel panel = new JPanel();
		final JTextField txuser = new JTextField(15);
		final JPasswordField pass = new JPasswordField(15);
		
		txuser.setBounds(70,30,150,20);
		pass.setBounds(70,65,150,20);
		blogin.setBounds(110,100,80,20);
		
		// Create Login Action
		blogin.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						String user = txuser.getText();
						String passwd = new String(pass.getPassword());
						/**
						 * TODO
						 * Remove Before Release
						 */
						if(user.equals("test") && passwd.equals("123"))
						{
							main.getContentPane().removeAll();
							actionLogin();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Bad Login");
							txuser.setText("");
							pass.setText("");
						}
					}
				});
		
		panel.add(txuser);
		panel.add(pass);
		panel.add(blogin);
		return panel;
	}

	private static JFrame createStartFrame()
	{
		JFrame temp = new JFrame("Conception");		
		temp.setSize(800, 600);
		temp.setResizable(true);
		temp.setVisible(true);
		temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return temp;
	}
	
	public static void actionLogin()
	{
		JLabel welcome = new JLabel("Welcome to a New Frame");
		JPanel panel = new JPanel();

		main.setSize(300,200);
		main.setLocation(500,280);
		main.setResizable(true);
		panel.setLayout (null); 

		welcome.setBounds(70,50,150,60);

		panel.add(welcome);

		main.getContentPane().add(panel);
	}
}

