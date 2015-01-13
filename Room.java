/**
 * The basic class for a room
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable
{
	private double price;  
	private String type;
	private String name;

	private ArrayList<Reservation> roomReservations = new ArrayList<Reservation>();
	
	/**
	 * Creates a new room
	 * @param roomName the room name
	 * @param roomPrice the price of the room
	 * @param roomType the room type
	 */
	Room(String roomName, double roomPrice, String roomType)
	{
		type = roomType;
		price = roomPrice;
		name = roomName;
	}

	/**
	 * Gets all the reservations associated with this room
	 * @return the list of reservations in this room
	 */
	public ArrayList<Reservation> getReservationsForRoom()
	{
		return roomReservations;
	}
	
	/**
	 * Gets the price of the room
	 * @return price
	 */
	public double getPrice() 
	{
		return price;
	}

	/**
	 * Changes the price of the room
	 * @param price the new price
	 */
	public void setPrice(double price) 
	{
		this.price = price;
	}

	/**
	 * Gets the type of the room
	 * @return type of room
	 */
	public String getType() 
	{
		return type;
	}

	/**
	 * Changes type of room
	 * @param type new type of room
	 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/**
	 * Gets the name of the room
	 * @return name of the room
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Changes the name
	 * @param name the new name
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	public String toString()
	{
		return name + " ; " + Double.toString(price) + " ; " + type;
	}
	
	/**
	 * Sets the list of reservations made for this room
	 * @param newReservations the new list of reservations
	 */
	public void setList(ArrayList<Reservation> newReservations)
	{
		roomReservations = newReservations;
	}
}
