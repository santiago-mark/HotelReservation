/**
 * Basic class for a user
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
	private String userID;
	private String userName;
	private String userType;
	private ArrayList<Reservation> userReservations = new ArrayList<Reservation>();
	
	/**
	 * Constructor for a user
	 * @param idNum the ID number
	 * @param name name of the user
	 * @param type user type
	 */
	public User(String idNum, String name, String type)
	{
		userID = idNum;
		userName = name;
		userType = type;
	}
	
	/**
	 * Sets the
	 * @param idNum
	 */
	public void setID(String idNum)
	{
		userID = idNum;
	}
	
	/**
	 * Sets the name of the account's user
	 * @param name
	 */
	public void setName(String name)
	{
		userName = name;
	}
	
	/**
	 * Sets the type of the user
	 * @param type
	 */
	public void setType(String type)
	{
		userType = type;
	}
	
	/**
	 * Gets the ID number of the user
	 * @return
	 */
	public String getID()
	{
		return userID;
	}
	
	/**
	 * Gets the user's name
	 * @return name of user
	 */
	public String getName()
	{
		return userName;
	}
	
	/**
	 * Gets type of user
	 * @return type of user
	 */
	public String getType()
	{
		return userType;
	}
	
	/**
	 * Adds reservation to user's list
	 * @param reservation reservation to be added
	 */
	public void addReservation(Reservation reservation)
	{
		userReservations.add(reservation);
	}
	
	/**
	 * Gets all reservations made by this user
	 * @return user's reservations
	 */
	public ArrayList<Reservation> getReservations()
	{
		return userReservations;
	}	
	
	public String toString()
	{
		return userID + " ; " + userName + " ; " + userType ;
	}
	
	/**
	 * Sets the list of reservations made by this user
	 * @param newReservations reservations by this user
	 */
	public void setList(ArrayList<Reservation> newReservations)
	{
		userReservations = newReservations;
	}
	
	/**
	 * Checks if desired ID number is possible
	 * @param desiredID the desired ID
	 * @return true or false depending if meets requirements
	 */
	public boolean validIDNum(String desiredID)
	{
		if (desiredID.matches("[0-9]+") && desiredID.length() == 8) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
}
