import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class UserLoginFrame extends JFrame 
{
	public UserLoginFrame(UserAccounts accnts, HotelSystem hs, final String mode)
	{
		accounts = accnts;
		hotelSystem = hs;
		executionMode = mode;
		setSize(400,200);
		setLayout(new GridLayout(5,1));
		userName = new JTextField();
		userName.setSize(10, 1);
		if(mode.equals("Guest"))
			notify = new JLabel("Please Login or Create a New Account");
		else
			notify = new JLabel("Please Login for Manager Access");
		notify.setSize(20,1);
		notify.setOpaque(false);
		notify.setEnabled(false);
		
		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(mode.equals("Guest"))
					okButtonClickGuestMode();
				else
					okButtonClickManagerMode();
			}
			
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				cancelButtonClick();
			}
			
		});
		
		add(notify);
		add(userName);
		add(okButton);
		if (executionMode.equals("Guest"))
		{
			createUserButton = new JButton("Create Guest Account");
			createUserButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					createUserButtonClick();
				}
				
			});
			add(createUserButton);
		}
		add(cancelButton);
	}
	
	private void okButtonClickGuestMode()
	{
		if(userName.getText().length() < 1)
			return;
		
		for (User u : accounts.getAccounts())
		{
			if (u.getName().equals(userName.getText()))
			{
				user = u;
				this.setVisible(false);
				hotelSystem.handleGuestLogin(user);
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Unable to find user.", "Warning: ", JOptionPane.INFORMATION_MESSAGE);
		userName.setText("");
 	}
	
	private void okButtonClickManagerMode()
	{
		if(userName.getText().length() < 1)
			return;
		
		for (User u : accounts.getAccounts())
		{
			if (u.getName().equals(userName.getText()))
			{
				if(u.getType().equals("Manager"))
				{
					user = u;
					this.setVisible(false);
					hotelSystem.handleManagerLogin(user);
					return;
				}			
				else
				{
					JOptionPane.showMessageDialog(null, "User is not a manager!", "Warning: ", JOptionPane.INFORMATION_MESSAGE);
					userName.setText("");
					return;
				}	
			}
		}
		JOptionPane.showMessageDialog(null, "Unable to find user.", "Warning: ", JOptionPane.INFORMATION_MESSAGE);
		userName.setText("");
 	}
	
	private void cancelButtonClick()
	{
		if(user != null)
			user = null;
		this.setVisible(false);
	}
	
	private void createUserButtonClick()
	{
		if(userName.getText().length() < 1)
			return;
		user = new User("0", userName.getText(), "Guest");
		for(int i=1; i<100; i++)
		{
			if(accounts.possibleIDNum(user))
			{
				user.setID(String.valueOf(i));
				break;
			}
		}
		accounts.addAccount(user);
		this.setVisible(false);
		hotelSystem.handleGuestLogin(user);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserAccounts accounts;
	private HotelSystem hotelSystem;
	private String executionMode;
	private User user;
	private JTextField userName;
	private JLabel notify;
	private JButton okButton;
	private JButton cancelButton;
	private JButton createUserButton;

}
