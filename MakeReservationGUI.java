import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MakeReservationGUI implements ChangeListener
{
	public MakeReservationGUI(final GuestGUI gg, MakeReservationModel m, User usr)
	{
		guestGUI = gg;
		model = m;
		user = usr;
		finalPanel = new JPanel(new GridLayout(5,1)); 
		finalPanel.setSize(800,500);
		checkInLabel = new JLabel( "   Check In Date: (mm/dd/yyyy)");
		checkInLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));
		checkOutLabel = new JLabel("Check Out Date: (mm/dd/yyyy)");
		checkOutLabel.setFont(new Font(null, Font.CENTER_BASELINE, 15));
		roomTypeLabel = new JLabel("Room Type: ");
		checkInDateTextField = new JTextField(10);
		checkInDateTextField.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		checkOutDateTextField = new JTextField(10);
		checkOutDateTextField.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						// TODO Auto-generated method stub
						
					}
				});
		economyButton = new JButton("Economy ($80)");
		economyButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		economyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				getEconomicRooms();
			}
		});
		luxuryButton = new JButton("Luxury ($200)");
		luxuryButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		luxuryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				getLuxuryRooms();
			}
		});
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				guestGUI.loadOptions();
			}
		});
		
		confirmButton = new JButton("Confirm");
		confirmButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		confirmButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				makeReservation();	
			}
		});
		confirmButton.setEnabled(false);
		
		roomList.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// Index Selected will hold the index of the room selected
				// room 1 = index 0, you can use for whatever thing you need to use it
				// for
				//int indexSelected = roomList.getSelectedIndex();
				//roomList.revalidate();
				//roomList.repaint();
				//guestGUI.redraw();
				
			}
		});
		
		
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p3 = new JPanel(new FlowLayout());
		p4 = new JPanel(new FlowLayout());
		p5 = new JPanel(new GridLayout(2, 1));
		p6 = new JPanel(new GridLayout(2, 1));
		p7 = new JPanel(new FlowLayout());
		p8 = new JPanel(new FlowLayout());
		p9 = new JPanel(new FlowLayout());
		p5.add(checkInLabel);
		p5.add(checkOutLabel);
		p6.add(checkInDateTextField);
		p6.add(checkOutDateTextField);
		p8.add(p5);
		p8.add(p6);
		p7.add(roomList); //Add JComboBox
		p1.add(p8);
		p1.add(p7);
		p1.setBorder(BorderFactory.createTitledBorder("Room Reservation Date and Number"));
		
		p2.add(economyButton);
		p2.add(luxuryButton);
		p2.setBorder(BorderFactory.createTitledBorder("Room Type"));
		
		p3.add(confirmButton);
		p3.add(exitButton);
		p3.setBorder(BorderFactory.createTitledBorder("Confirm/Cancel Selection"));
		
		// When the transaction is complete set text in this JLabel "Transaction Complete"
		confirmationLabel = new JLabel("                                      ");
		//confirmationLabel.set
		// Print receipt button
		printReceiptButton = new JButton("Finish Transactions and Print Receipt");
		printReceiptButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		printReceiptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gg.showReceiptPane(model.getTransactionReservations());
			}
		});
		printReceiptButton.setEnabled(false);
		p4.add(printReceiptButton);

		p9.add(confirmationLabel);
		
		finalPanel.add(p1);
		finalPanel.add(p2);
		finalPanel.add(p3);
		finalPanel.add(p9);
		finalPanel.add(p4);
	
	}
	
	public void stateChanged(ChangeEvent e) 
	{
		roomList.removeAllItems();
		ArrayList<Room> rooms = model.getRooms();
		if(rooms.size() > 0)
		for(Room r : rooms)
			roomList.addItem(r);
	}
	
	public JPanel getMakeReservationGUIPanel()
	{
		return finalPanel;
	}

	private void makeReservation()
	{
		if(checkInDateTextField.getText().length() < 8)
		{
			checkInDateTextField.setText("");
			return;
		}
		
		if(checkOutDateTextField.getText().length()<8)
		{
			checkOutDateTextField.setText("");
			return;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try
		{
			java.util.Date start = format.parse(checkInDateTextField.getText());
			java.util.Date end = format.parse(checkOutDateTextField.getText());
			Room r = (Room) roomList.getSelectedItem();
			model.makeReservation(r, user, start, end);
			showMessage("Transaction Complete!");
			checkInDateTextField.setText("");
			checkOutDateTextField.setText("");
			confirmButton.setEnabled(false);
			printReceiptButton.setEnabled(true);
			roomList.removeAllItems();
		}
		catch (Exception e)
		{
			showError(e.getMessage());
			checkInDateTextField.setText("");
			checkOutDateTextField.setText("");
		}
	}
	
	private void getEconomicRooms()
	{
		if(checkInDateTextField.getText().length() < 8)
		{
			checkInDateTextField.setText("");
			return;
		}
		
		if(checkOutDateTextField.getText().length()<8)
		{
			checkOutDateTextField.setText("");
			return;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try
		{
			java.util.Date start = format.parse(checkInDateTextField.getText());
			java.util.Date end = format.parse(checkOutDateTextField.getText());
			model.findAvailableRooms("Economic", start, end);
			resetMessage();
			if(roomList.getItemCount() > 0)
				confirmButton.setEnabled(true);
		} 
		catch (ParseException e) 
		{
			showError(e.getMessage());
			checkInDateTextField.setText("");
			checkOutDateTextField.setText("");
		}
		catch (Exception e)
		{
			showError(e.getMessage());
			checkInDateTextField.setText("");
			checkOutDateTextField.setText("");
		}
	}
	
	private void getLuxuryRooms()
	{
		if(checkInDateTextField.getText().length()<8)
		{
			checkInDateTextField.setText("");
			return;
		}
		
		if(checkOutDateTextField.getText().length()<8)
		{
			checkOutDateTextField.setText("");
			return;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try
		{
			java.util.Date start = format.parse(checkInDateTextField.getText());
			java.util.Date end = format.parse(checkOutDateTextField.getText());
			model.findAvailableRooms("Luxury", start, end);
			resetMessage();
			if(roomList.getItemCount() > 0)
				confirmButton.setEnabled(true);
		} 
		catch (ParseException e) 
		{
			showError(e.getMessage());
			checkInDateTextField.setText("");
			checkOutDateTextField.setText("");
		}
		catch (Exception e)
		{
			showError(e.getMessage());
			checkInDateTextField.setText("");
			checkOutDateTextField.setText("");
		}
	}
	
	private void showMessage(String message)
	{
		confirmationLabel.setForeground(Color.RED);
		confirmationLabel.setFont(new Font(null, Font.HANGING_BASELINE, 40));
		confirmationLabel.setText(message);
	}
	
	private void showError(String message)
	{
		confirmationLabel.setForeground(Color.RED);
		confirmationLabel.setFont(new Font(null, Font.HANGING_BASELINE, 18));
		confirmationLabel.setText(message);
	}
	
	private void resetMessage()
	{
		confirmationLabel.setText("");
	}
	
	private GuestGUI guestGUI;
	private MakeReservationModel model;
	private JPanel finalPanel;
	private User user;
	private JLabel checkInLabel;
	private JLabel checkOutLabel;
	private JLabel roomTypeLabel;
	private JTextField checkInDateTextField;
	private JTextField checkOutDateTextField;
	private JButton economyButton;
	private JButton luxuryButton;
	private JButton confirmButton;
	private JButton exitButton;
	private JButton printReceiptButton;
	private JTextArea availableRooms;
	private JComboBox<Room> roomList = new JComboBox<Room>();
	private JLabel confirmationLabel;
	
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private JPanel p6;
	private JPanel p7;
	private JPanel p8;
	private JPanel p9;
	
	private final int BUTTON_WIDTH = 350;
	private final int BUTTON_HEIGHT = 60;

}
