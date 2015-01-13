import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MakeReservationModel 
{
	public MakeReservationModel(HotelSystem hs)
	{
		database = hs.getDatabase();
		listeners = new ArrayList<ChangeListener>();
		reservations = new ArrayList<Reservation>();
	}
	
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void addRooms(ArrayList<Room> pRoom)
	{
		rooms = pRoom;
		for (ChangeListener l : listeners)
	      {
	         l.stateChanged(new ChangeEvent(this));
	      }
	}
	
	public ArrayList<Room> getRooms()
	{
		return rooms;
	}
	
	public void findAvailableRooms(String type, java.util.Date start, java.util.Date end) throws Exception
	{
		ArrayList<Room> rms;
		if (type.equals("Economic"))
			rms = database.getEconomicRooms();
		else 
			rms = database.getLuxuryRooms();
		try
		{
			addRooms(rooms = database.getAvailableRooms(rms, start, end));
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public void makeReservation(Room room, User user, java.util.Date start, java.util.Date end) throws Exception
	{
		try
		{
			Reservation reservation = database.makeReservation(user, room, start, end);
			reservations.add(reservation);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public  ArrayList<Reservation> getTransactionReservations()
	{
		return reservations;
	}
	
	private RoomReservationsDatabase database;
	private ArrayList<Room> rooms;
	private ArrayList<Reservation> reservations;
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
}
