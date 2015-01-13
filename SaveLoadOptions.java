/**
 * Saves and loads reservation data to and from a file
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SaveLoadOptions 
{
	private RoomReservationsDatabase r;
	private UserAccounts accounts;
	
	File data = new File("hotelReservations.txt");
	
	/**
	 * Saves all reservations to a file
	 * @throws IOException file not found
	 */ 
	public void save() throws IOException //MAY HAVE TO WRITE USERS RESERVATIONS BECAUSE IF USER HAS NO RESERVATIONS, THEY WONT BE WRITTEN TO FILE AND THEREFORE USER WONT EXIST WHEN LOADING
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(data, false)); //clears file then starts writing to it
		
		for (int i = 0; i < r.getReservationList().getReservations().size(); i++)
		{
			Reservation temp = r.getReservationList().getReservations().get(i);
			out.write(temp.toString() + "#");
		}
		
		out.close();
	}
	
	/**
	 * Loads all reservations from a file
	 * @throws IOException file not found
	 * @throws ParseException not possible date format
	 */
	public void load() throws IOException, ParseException  
	{
		BufferedReader reader = new BufferedReader( new FileReader( data ) );
		
		while (reader.ready()) //While there are content left to read	
		{ 
			String line = reader.readLine(); //Read the next line from the file
		    String[] reservations = line.split( "#" ); //Split the string at every # character. Place the results in an array.			
			
		    //Makes a reservation from each line
		    for (int i = 0; i < reservations.length; i++)
		    {
		    	String reservation = reservations[i];
		    	
		    	Scanner in = new Scanner(reservation);
		    	in.useDelimiter(" ;");
		    	
		    	String idNum = null;
		    	String name = null;
		    	String userType = null;
		    	String roomName = null;
		    	String cost = null;
		    	String roomType = null;
		    	String startDate = null;
		    	String endDate = null;
		    	
		    	//Assigns each value to the appropriate parameter
		    	while (in.hasNext())
		    	{
		    		idNum = in.next();
		    		name = in.next();
		    		userType = in.next();
		    		roomName = in.next();
		    		cost = in.next();
		    		roomType = in.next();
		    		startDate = in.next();
		    		endDate = in.next();
		    	}
		    	
		    	Room correctRoom = null;
		    	User correctUser = null;
		    	
		    	//Gets the correct room of the reservation
		    	for (int k = 0; k < r.getRoomList().getRooms().size(); k++)
		    	{
		    		if (roomName.equals(r.getRoomList().getRooms().get(k).getName()))
		    		{
		    			correctRoom = r.getRoomList().getRooms().get(k);
		    			break;
		    		}
		    	}
		    	
		    	//Gets the user of the reservation
		    	correctUser = new User(idNum, name, userType);
		    	boolean alreadyUser = false; //Checks if user should be added to accounts or not
		    	for (int j = 0; j < accounts.getAccounts().size(); j++)
		    	{
		    		if (correctUser.equals(accounts.getAccounts().get(j)))
		    		{
		    			alreadyUser = true;
		    			break;
		    		}
		    	}
		    	//if user is not in accounts already, adds user to accounts
		    	if (alreadyUser == false)
		    	{
		    		accounts.addAccount(correctUser);
		    	}
		    	
		    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		    	java.util.Date start = format.parse(startDate);
		    	java.util.Date end = format.parse(endDate);
		    	
		    	Reservation newReservation = new Reservation(correctUser, correctRoom, start, end);
		    
		    	r.getReservationList().addReservation(newReservation);
		    	correctUser.addReservation(newReservation);
		    	correctRoom.getReservationsForRoom().add(newReservation);
		    	
		    	in.close();
		    }
		    
			reader.close(); 
		}
	}
}
