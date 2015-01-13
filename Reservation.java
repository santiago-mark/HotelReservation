/**
 * Creates a reservation
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.Serializable; 

public class Reservation implements Serializable
{
	private double cost;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private Room reservedRoom;
	private User user;
	
	/**
	 * Constructs a reservation
	 * @param newUser the user
	 * @param room the room being reserved
	 * @param start the start date of the reservation
	 * @param end the end date of the reservation
	 */
	public Reservation(User newUser, Room room, java.util.Date start, java.util.Date end)
	{
		user = newUser;
		reservedRoom = room;
		
		startDate = start;
		endDate = end;
	}
	
	/**
	 * Sets the user
	 * @param newUser the user
	 */
	public void setUser(User newUser)
	{
		user = newUser;
	}
	
	/**
	 * Sets the room
	 * @param room the room
	 */
	public void setRoom(Room room)
	{
		reservedRoom = room;
	}
	
	/**
	 * Sets the start date
	 * @param start the start
	 */
	public void setStart(java.util.Date start)
	{
		startDate = start;
	}
	
	/**
	 * Sets the end date
	 * @param end the end
	 */
	public void setEnd(java.util.Date end)
	{
		endDate = end;
	}
	
	/**
	 * Sets the cost of the reservation
	 * @param newCost the total for this reservation
	 */
	public void setCost(Double newCost)
	{
		cost = newCost;
	}
	
	/**
	 * Gets the room of the reservation
	 * @return room being reserved
	 */
	public Room getRoom()
	{
		return reservedRoom;
	}
	
	/**
	 * Gets the user of the reservation
	 * @return the user that reserved
	 */
	public User getUser()
	{
		return user;
	}
	
	/**
	 * Gets the beginning date
	 * @return the starting date of the reservation
	 */
	public java.util.Date getStart()
	{
		return startDate;
	}
	
	/**
	 * Gets the ending date
	 * @return the ending date of the reservation
	 */
	public java.util.Date getEnd()
	{
		return endDate;
	}
	
	/**
	 * Gets the total cost of the reservation
	 * @return the total due
	 */
	public double getCost()
	{
		java.util.Date start = this.getStart();
		java.util.Date end = this.getEnd();
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		
		int counter = 0;
		//Counts days between current reservation's starting and ending dates
		while (calendar.getTime().before(end))
	    {
			counter++;
			calendar.add(Calendar.DATE, 1);
	    }
		
		//Each day the room is reserved is an additional amount to add to cost
		cost = counter * reservedRoom.getPrice();
		DecimalFormat f = new DecimalFormat("#.00");
		String costRounded = f.format(cost);
		cost = Double.valueOf(costRounded);
		return cost;
	}
	
	public String toString()
	{
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); //FORMAT DATES FOR EVERYTHING
		String start = format.format(startDate);
		String end = format.format(endDate);
		return user.toString() + " ; " + reservedRoom.toString() + " ; " + start + " ; " + end;
	}
	
	/**
	 * Gets the range of the reservation
	 * @return list of dates for a reservation
	 */
	public ArrayList<java.util.Date> getReservationRange()
	{
		ArrayList<java.util.Date> reservationRange = new ArrayList<java.util.Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);

		//Makes a date range beginning from start to end
		while (calendar.getTime().before(endDate))
		{
			java.util.Date result = calendar.getTime();
		    reservationRange.add(result);
		    calendar.add(Calendar.DATE, 1);
		}
		
		return reservationRange;
	}
}
