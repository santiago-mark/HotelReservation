 /**
 * List of all of the reservations
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

import java.util.ArrayList;
import java.io.Serializable; 

public class ReservationList implements Serializable
{
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	
	/**
	 * Adds reservation to list
	 * @param reservation reservation to be added
	 */
	public void addReservation(Reservation reservation)
	{
		reservations.add(reservation);
	}
	
	/**
	 * Gets reservation list
	 * @return list of reservations
	 */
	public ArrayList<Reservation> getReservations()
	{
		return reservations;
	}
	
	/**
	 * Sets the reservation list
	 * @param newReservations the new reservations list
	 */
	public void setList(ArrayList<Reservation> newReservations)
	{
		reservations = newReservations;
	}
}
