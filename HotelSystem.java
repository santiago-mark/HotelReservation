import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HotelSystem 
{
	public static void main(String[] args) 
	{
		HotelSystem hs = new HotelSystem();
		hs.run();
	}
		
	private void run()
	{
		database = new RoomReservationsDatabase();
		accounts = new UserAccounts();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadHomeScreen();
		frame.pack();
		frame.setSize(800,500);
		frame.setVisible(true);
		try
		{
			SaveLoadUser userLoader = new SaveLoadUser();
			UserAccounts accts = userLoader.load();
			accounts = accts;
			SaveLoad loader = new SaveLoad();
			ReservationList res = loader.load();
			database.setReservationList(res);
			
			for (int i = 0; i < res.getReservations().size(); i++)
			{
				Room temp = res.getReservations().get(i).getRoom();
				Reservation tempReservation = res.getReservations().get(i);
				temp.getReservationsForRoom().add(tempReservation);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		makeTestData();
	}
		
	public void quitGui()
	{
		frame.remove(panel);
		loadHomeScreen();
		frame.revalidate();
		frame.repaint();
		SaveLoad saver = new SaveLoad();
		SaveLoadUser userSaver = new SaveLoadUser();
		try 
		{
			saver.save(database.getReservationList());
			userSaver.save(accounts);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public ArrayList<Room> getLuxuryRooms()
	{
		return database.getLuxuryRooms();
	}
		
	public ArrayList<Room> getEconomicRooms()
	{
		return database.getEconomicRooms();
	}
		
	public void addUserAccount(User user)
	{
		accounts.addAccount(user);
	}
		
	public ArrayList<User> getUserAccounts()
	{
		return accounts.getAccounts();
	}
		
	private void loadHomeScreen()
	{
		frame.setName("Welcome");
		if (frame.getComponentCount() > 1)
			frame.remove(panel);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
			
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(GRAY_COLOR);
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(GRAY_COLOR);
		panel.setBackground(GRAY_COLOR);
			
		JButton userButton = new JButton(new ImageIcon("src/images/guestOptionButton.png"));
		userButton.setPressedIcon(new ImageIcon("src/images/guestOptionButtonPressed.png"));
		userButton.setOpaque(false);
		userButton.setContentAreaFilled(false);
		userButton.setBorderPainted(false);
		userButton.setFocusPainted(false);
			
		userButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				guestButtonClick();
			}
		});
		JButton managerButton = new JButton(new ImageIcon("src/images/managerOptionButton.png"));
		managerButton.setPressedIcon(new ImageIcon("src/images/managerOptionButtonPressed.png"));
		managerButton.setOpaque(false);
		managerButton.setContentAreaFilled(false);
		managerButton.setBorderPainted(false);
		managerButton.setFocusPainted(false);
	
		managerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				managerButtonClick();	
			}
		});
			
		hotelAnonymusLabel = new JLabel(new ImageIcon("src/images/AnonymusHotelLogo.png"));
			
		panel1.add(hotelAnonymusLabel);
		panel2.add(userButton);
		panel2.add(managerButton);
			
		panel.add(panel1, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.SOUTH);
		frame.add(panel);
	}
		
	public void handleGuestLogin(User user)
	{
		if(user != null)
			loadGuestGui(user);
	}
		
	public void handleManagerLogin(User user)
	{
		if(user != null)
			loadManagerGui();
	}
		
	public RoomReservationsDatabase getDatabase()
	{
		return database;
	}
		
	private void guestButtonClick()
	{
		UserLoginFrame login = new UserLoginFrame(accounts, this, "Guest");
		login.setVisible(true);
	}
		
	private void managerButtonClick()
	{
		UserLoginFrame login = new UserLoginFrame(accounts, this, "Manager");
		login.setVisible(true);
	}

	private void loadManagerGui()
	{
		frame.remove(panel);
		managerGUI = new ManagerGUI(this);
		frame.setName("Manager View");
		panel = (managerGUI.getManagerGUIPanel());
		frame.add(panel);
		frame.setVisible(true); 
	}
		
	private void loadGuestGui(User user)
	{
		frame.remove(panel);
		guestGUI = new GuestGUI(this, user);
		frame.setName("Guest View");
		panel = (guestGUI.getGuestGUIPanel());
		frame.add(panel);
		frame.setVisible(true);	
	}
		
	private void makeTestData()
	{
		//User u = new User("1","Fred","Guest");
		User u2 = new User("1m", "Manager","Manager");
		//accounts.addAccount(u);
		accounts.addAccount(u2);
		java.util.Date today = new java.util.Date();
		Room rm = database.getEconomicRooms().get(0);
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try
		{
			java.util.Date start = format.parse("12/10/2014");
			java.util.Date end = format.parse("12/15/2014");
			//database.makeReservation(u, rm , start, end);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}	
	}

	private JFrame frame;
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel hotelAnonymusLabel;
	private RoomReservationsDatabase database;
	private UserAccounts accounts;
	private GuestGUI guestGUI;
	private ManagerGUI managerGUI;
	private final int BUTTON_WIDTH = 300;
	private final int BUTTON_HEIGHT = 150;
	private final Color GRAY_COLOR = new Color(50, 50, 50);
}
