/**
 * Creates a list of room
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

import java.util.ArrayList;

public class RoomList 
{
	private ArrayList<Room> rooms = new ArrayList<Room>();
	
	/**
	 * Adds room to list
	 * @param room room to be added
	 */
	public void addRoom(Room room)
	{
		rooms.add(room);
	}
	
	/**
	 * Gets list of rooms
	 * @return list of rooms
	 */
	public ArrayList<Room> getRooms()
	{
		return rooms;
	}
}
