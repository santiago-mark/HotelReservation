import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * An object that represents a date, it updates everytime a user click on the calendar GUI
 * @author erick
 *
 */
public class Date {
	private ArrayList<ChangeListener> listeners;
	private GregorianCalendar calendar;
	private int year;
	private int month;
	private int dayOfTheMonth;
	private HotelSystem hs;
	private RoomReservationsDatabase roomReservationDatabase;
	private java.util.Date reservationDate;
	private ArrayList<Reservation> reservationArray;
	private Iterator<Reservation> reservationListIterator;
	
	/**
	 * Constructs a new Date object with the current date
	 */
	public Date(HotelSystem hs) {
		listeners = new ArrayList<ChangeListener>();
		calendar = new GregorianCalendar();
		year = calendar.get(java.util.Calendar.YEAR);
		month = calendar.get(java.util.Calendar.MONTH);
		dayOfTheMonth = calendar.get(java.util.Calendar.DAY_OF_MONTH);
		
		this.hs = hs;
		roomReservationDatabase = hs.getDatabase();
		reservationDate = new java.util.Date(year, month, dayOfTheMonth);
		reservationArray = roomReservationDatabase.reservationsOfDate(reservationDate);
		reservationListIterator = reservationArray.listIterator();
	}
	
	/**
	 * @return an iterator of the ArrayList<Reservations> 
	 */
	public Iterator<Reservation> listOfReservationsIterator() {
		return reservationListIterator;
	}
	
	/**
	 * Method adds an observer of this date object
	 * @param listener
	 */
	public void addListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * 
	 * @return the year of this date object
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * 
	 * @return the month of this date object
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * 
	 * @return the day of this month's object
	 */
	public int getDay() {
		return dayOfTheMonth;
	}

	/**
	 * Updates the year of this date object
	 * @param year the new year
	 */
	public void setNewYear(int year) {
		this.year = year;
		
		reservationDate = new java.util.Date(year, month, dayOfTheMonth);
		reservationArray = roomReservationDatabase.reservationsOfDate(reservationDate);
		reservationListIterator = reservationArray.listIterator();
		
		notifyListeners();
	}

	/**
	 * Updates the month of this date object
	 * @param month
	 */
	public void setNewMonth(int month) {
		this.month = month;
		
		reservationDate = new java.util.Date(year, month, dayOfTheMonth);
		reservationArray = roomReservationDatabase.reservationsOfDate(reservationDate);
		reservationListIterator = reservationArray.listIterator();
		
		notifyListeners();
	}
	
	/**
	 * Updates the day of this date object
	 * @param day the new day
	 */
	public void setNewDay(int day) {
		this.dayOfTheMonth = day;
		
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try
		{
			reservationDate = format.parse(String.valueOf(month+1)+"/"+String.valueOf(day)+"/"+String.valueOf(year));
			
		}
		catch (Exception e)
		{
			
		}
		
		
		//reservationDate = new java.util.Date(year, month, dayOfTheMonth);
		reservationArray = roomReservationDatabase.reservationsOfDate(reservationDate);
		reservationListIterator = reservationArray.listIterator();
		
		notifyListeners();
	}

	/**
	 * Notifys all of the listeners of this object of a change in the date
	 */
	private void notifyListeners() {
		ChangeEvent event = new ChangeEvent(this);
		for(ChangeListener listener: listeners) {
			listener.stateChanged(event);
		}
	}
	
	/**
	 * Returns a String representation of the state of this date object
	 */
	@Override
	public String toString() {
		String text = this.getClass().getName() + " [" + "year=" + year + ", month=" + month +
				", " + " day=" + dayOfTheMonth + "]\n";
		for (int i = 0; i < 4; i++) {
			text+=text;
		}
		return  text;
	}
}
