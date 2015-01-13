import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class ViewReservationGUI 
{
	public ViewReservationGUI(final GuestGUI gg, User usr)
	{
		guestGUI = gg;
		database = gg.getHotelSystem().getDatabase();
		user = usr;
		deleteButton = new JButton("Cancel Reservation");
		deleteButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		deleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				cancelReservation();
			}
		});
		
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				gg.loadOptions();
			}
		});
		panel = new JPanel(new BorderLayout());
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		
		panel.setSize(800, 500);
		comboBox = new JComboBox<Reservation>();
		guestReservations = database.reservationsOfGuest(user);
		for(Reservation r : guestReservations)
			comboBox.addItem(r);
		if(comboBox.getItemCount() < 1)
			deleteButton.setEnabled(false);
		panel1.add(comboBox);
		panel1.setBorder(BorderFactory.createTitledBorder("Select a Room"));
		panel2.add(deleteButton);
		panel2.add(exitButton);
		
		panel.add(panel1, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.SOUTH);
	}

	public JPanel getViewReservationGUIPanel()
	{
		return panel;
	}
	
	private void cancelReservation()
	{
		if (comboBox.getItemCount() < 1)
			return;
		database.cancelReservation((Reservation) comboBox.getSelectedItem());
		guestReservations = database.reservationsOfGuest(user);
		comboBox.removeAllItems();
		for(Reservation r : guestReservations)
			comboBox.addItem(r);
		if(comboBox.getItemCount() < 1)
			deleteButton.setEnabled(false);
	}
	
	
	JPanel panel;
	JPanel panel1;
	JPanel panel2;
	GuestGUI guestGUI;
	RoomReservationsDatabase database;
	User user;
	JButton deleteButton;
	JButton exitButton;
	JComboBox<Reservation> comboBox;
	ArrayList<Reservation> guestReservations;
	private final int BUTTON_WIDTH = 300;
	private final int BUTTON_HEIGHT = 100;
}
