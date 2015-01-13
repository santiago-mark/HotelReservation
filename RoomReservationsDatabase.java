import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//TODO DOES EACH RESERVATION COUNT FOR ALL DAYS IN BETWEEN START AND DATE, OR JUST START AND DATE?

/**
 * Database that holds all rooms and reservations
 * @author Mark Santiago
 *
 */
public class RoomReservationsDatabase
{
	private RoomList roomList = new RoomList();
	private ReservationList reservationList = new ReservationList();
	
	/**
	 * Creates the 20 rooms with their type and price
	 */
	public RoomReservationsDatabase()
	{
		Room room1 = new Room("Room 1", 80, "Economic");
		Room room2 = new Room("Room 2", 80, "Economic");
		Room room3 = new Room("Room 3", 80, "Economic");
		Room room4 = new Room("Room 4", 80, "Economic");
		Room room5 = new Room("Room 5", 80, "Economic");
		Room room6 = new Room("Room 6", 80, "Economic");
		Room room7 = new Room("Room 7", 80, "Economic");
		Room room8 = new Room("Room 8", 80, "Economic");
		Room room9 = new Room("Room 9", 80, "Economic");
		Room room10 = new Room("Room 10", 80, "Economic");
		Room room11 = new Room("Room 11", 200, "Luxury");
		Room room12 = new Room("Room 12", 200, "Luxury");
		Room room13 = new Room("Room 13", 200, "Luxury");
		Room room14 = new Room("Room 14", 200, "Luxury");
		Room room15 = new Room("Room 15", 200, "Luxury");
		Room room16 = new Room("Room 16", 200, "Luxury");
		Room room17 = new Room("Room 17", 200, "Luxury");
		Room room18 = new Room("Room 18", 200, "Luxury");
		Room room19 = new Room("Room 19", 200, "Luxury");
		Room room20 = new Room("Room 20", 200, "Luxury");
		
		roomList.addRoom(room1);
		roomList.addRoom(room2);
		roomList.addRoom(room3);
		roomList.addRoom(room4);
		roomList.addRoom(room5);
		roomList.addRoom(room6);
		roomList.addRoom(room7);
		roomList.addRoom(room8);
		roomList.addRoom(room9);
		roomList.addRoom(room10);
		roomList.addRoom(room11);
		roomList.addRoom(room12);
		roomList.addRoom(room13);
		roomList.addRoom(room14);
		roomList.addRoom(room15);
		roomList.addRoom(room16);
		roomList.addRoom(room17);
		roomList.addRoom(room18);
		roomList.addRoom(room19);
		roomList.addRoom(room20);	
	}
	
	/**
	 * Gets all luxury type rooms
	 * @return luxury room type rooms
	 */
	public ArrayList<Room> getLuxuryRooms()
	{
		ArrayList<Room> luxuryRooms  = new ArrayList<Room>();
		
		for (int i = 0; i < roomList.getRooms().size(); i++)
		{
			Room current = roomList.getRooms().get(i);
			if (current.getType().equalsIgnoreCase("Luxury"))
			{
				luxuryRooms.add(current);
			}
		}
		
		return luxuryRooms;
	}
	
	/**
	 * Gets all economic type rooms
	 * @return economic room type rooms
	 */
	public ArrayList<Room> getEconomicRooms()
	{
		ArrayList<Room> economicRooms  = new ArrayList<Room>();
		
		for (int i = 0; i < roomList.getRooms().size(); i++)
		{
			Room current = roomList.getRooms().get(i);
			if (current.getType().equalsIgnoreCase("Economic"))
			{
				economicRooms.add(current);
			}
		}
		
		return economicRooms;
	}
	
	/**
	 * Gets list of all the reservations
	 * @return list of reservations
	 */
	public ReservationList getReservationList()
	{
		return reservationList;
	}
	
	/**
	 * Gets list of all the rooms
	 * @return list of rooms
	 */
	public RoomList getRoomList()
	{
		return roomList;
	}
	
	/**
	 * Checks if reservation is valid
	 * @param start the beginning date
	 * @param end the ending date
	 * @return true or false depending if reservation is possible
	 */
	public static boolean validReservation(java.util.Date start, java.util.Date end)
	{
		boolean valid = true;
		
		ArrayList<java.util.Date> desiredDateRange = new ArrayList<java.util.Date>();
		
		java.util.Date today = new java.util.Date();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(start);

	    //Makes a date range beginning from start to end
	    while (calendar.getTime().before(end))
	    {
	        java.util.Date result = calendar.getTime();
	        desiredDateRange.add(result);
	        calendar.add(Calendar.DATE, 1); 
	    }
	    
	    if (desiredDateRange.size() > 60) //can't rent a room for more than 60 consecutive days
	    {
	    	valid = false;
	    }
	    else if (start.before(today) || end.before(today)) //can't have start and/or end date before today's date
	    {
	    	valid = false;
	    }
	    return valid;
	}
	
	/**
	 * Gets all available rooms according to options chosen by user
	 * @param rooms the list of rooms
	 * @param start the desired starting date
	 * @param end the desired ending date
	 * @return list of all available rooms
	 * @throws Exception 
	 */
	public ArrayList<Room> getAvailableRooms(ArrayList<Room> rooms, Date start, Date end) throws Exception
	{
		java.util.Date today = new java.util.Date();
		ArrayList<Room> availableRooms = new ArrayList<Room>();
		
		ArrayList<java.util.Date> desiredDateRange = new ArrayList<java.util.Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(start);

	    //Makes a date range beginning from start to end
	    while (calendar.getTime().before(end))
	    {
	        java.util.Date result = calendar.getTime();
	        desiredDateRange.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    
	    if (desiredDateRange.size() > 60) //can't rent a room for more than 60 consecutive days
	    {
	    	throw new Exception("You can not stay more than 60 days.");
	    }
	    else if (start.before(today) || end.before(today)) //can't have start and/or end date before today's date
	    {
	    	throw new Exception("One or more desired dates are in the past.");
	    }
	    else if ((!(start.before(end))))
	    {
	    	throw new Exception("End date can not be before start date.");
	    }
	    else
	    {
	    	//Access each room individually
			//for (int i = 0; i < roomList.getRooms().size(); i++)
	    	for (int i = 0; i < rooms.size(); i++)
			{
				//Room currentRoom = roomList.getRooms().get(i);
				Room currentRoom = rooms.get(i);
				
				boolean reservationPossible = true;
				//Access each reservation of current room individually
				for (int j = 0; j < currentRoom.getReservationsForRoom().size(); j++)
				{
					Reservation currentReservation = currentRoom.getReservationsForRoom().get(j);
					java.util.Date currentStart = currentReservation.getStart();
					java.util.Date currentEnd = currentReservation.getEnd();
					
					ArrayList<java.util.Date> reservationDateRange = new ArrayList<java.util.Date>();
					calendar.setTime(currentStart);
					
					//Makes a date range of current reservation's starting and ending dates
					while (calendar.getTime().before(currentEnd))
				    {
				        java.util.Date result = calendar.getTime();
				        reservationDateRange.add(result);
				        calendar.add(Calendar.DATE, 1);
				    }
					
					//Goes through each day of the reservation's date range
					for (int k = 0; k < reservationDateRange.size(); k++)
					{
						java.util.Date testDate = reservationDateRange.get(k);
						
						//Checks if desired date is within range of reservation
						if (!(testDate.before(start) || testDate.after(end)))
						{
							//Stops checking dates of current reservation
							reservationPossible = false;
							break;		
						}			
					}
					
					//Stops checking if reservation is possible because one or more desired dates already overlaps with a reservation
					if (reservationPossible == false)
					{
						break;
					}
				}	
					
				//Adds room to list if no date overlapping problems.
				if (reservationPossible == true)
				{
					//availableRooms.add(roomList.getRooms().get(i));
					availableRooms.add(rooms.get(i));
				}
				
				//After checking if reservation is possible for current room, goes to the next room.
			}	
			
	    }
	    
	    return availableRooms;
	}
	
	/**
	 * Gets all the reservations on a specific date
	 * @param date the date to check reservations
	 * @return all reservations on a chosen date
	 */
	public ArrayList<Reservation> reservationsOfDate(java.util.Date date)
	{
		java.util.Date compareDate = date;
		ArrayList<Reservation> reservationsOfDate = new ArrayList<Reservation>();
		
		for (int i = 0; i < reservationList.getReservations().size(); i++)
		{
			java.util.Date reservationStart = reservationList.getReservations().get(i).getStart();
			java.util.Date reservationEnd = reservationList.getReservations().get(i).getEnd();
			
			//Checks if date is between reservation's start and end date range, inclusive
			if (!(compareDate.before(reservationStart) || compareDate.after(reservationEnd)))
			{
				reservationsOfDate.add(reservationList.getReservations().get(i));
			}
		}
		
		return reservationsOfDate;
	}
	
	/**
	 * Creates a new reservation
	 * @param user user reserving
	 * @param room room to be reserved
	 * @param start beginning date to be reserved
	 * @param end ending date to be reserved
	 */
	public Reservation makeReservation(User user, Room room, java.util.Date start, java.util.Date end)
	{
		Reservation newReservation = new Reservation(user, room, start, end);
		reservationList.addReservation(newReservation);
		user.getReservations().add(newReservation);
		room.getReservationsForRoom().add(newReservation);
		return newReservation;
	}
	
	/**
	 * Cancels a reservation
	 * @param reservation reservation to be canceled
	 */
	public void cancelReservation(Reservation reservation)
	{
		User user = reservation.getUser();
		Room room = reservation.getRoom();
		reservationList.getReservations().remove(reservation);
		user.getReservations().remove(reservation);
		room.getReservationsForRoom().remove(reservation);
	}
	
	/**
	 * Removes all expired reservations from database
	 */
	public void removeExpiredReservations()
	{
		java.util.Date today = new java.util.Date();
		
		for (int i = reservationList.getReservations().size() - 1; i >= 0; i--)
		{
			Reservation reservationToCheck = reservationList.getReservations().get(i);
			
			if (reservationToCheck.getStart().before(today) || reservationToCheck.getEnd().before(today))
			{
				User user = reservationToCheck.getUser();
				Room room = reservationToCheck.getRoom();
				
				reservationList.getReservations().remove(reservationToCheck);
				room.getReservationsForRoom().remove(reservationToCheck);
				user.getReservations().remove(reservationToCheck);
			}
		}
	}
	
	/**
	 * Gets all reservations of a user
	 * @param user user to check
	 * @return reservations made by user
	 */
	public ArrayList<Reservation> reservationsOfGuest(User user)
	{
		return user.getReservations();
	}
	
	public void setReservationList(ReservationList newList)
	{
		reservationList = newList;
	}
}
