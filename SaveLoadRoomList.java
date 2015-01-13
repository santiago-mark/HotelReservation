/**
 * Saves and load reservation from a file
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoadRoomList
{
	private RoomList roomList;
	
	File data = new File("roomList.ser"); 
	
	/**
	 * Saves all reservations into a file
	 * @param list the list of reservations
	 * @throws FileNotFoundException file to be saved in was not found
	 */
	public void save(RoomList list) throws FileNotFoundException
	{
		roomList = list;
		
		FileOutputStream hotelData = new FileOutputStream(data);
		ObjectOutputStream reservations;
		
		try {
			reservations = new ObjectOutputStream(hotelData);
			reservations.writeObject(roomList); 
			reservations.close();
			hotelData.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loads the reservation list
	 * @return list of prior reservations
	 * @throws Exception can't find file
	 */
	public RoomList load() throws Exception
	{
		FileInputStream hotelData = new FileInputStream(data);
		try	{
			ObjectInputStream input = new ObjectInputStream(hotelData);
			roomList = (RoomList) input.readObject();
			input.close();
			hotelData.close();
			
		} catch (IOException e){
			e.printStackTrace();
		}
		return roomList;
		
	}
}
