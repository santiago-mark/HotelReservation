import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Class will create the manager GUI display.
 * @author erick
 *
 */
public class ManagerGUI {
	/**
	 * Creates a new ManagerGUI
	 */
	public ManagerGUI(HotelSystem hs) {
		hotelSystem = hs;
		
		// Get the year and month of the current date
		saveButton = new JButton("Save");
		
		saveButton.addActionListener(new
				ActionListener() {
					public void actionPerformed(ActionEvent e) {
						java.io.File file = new java.io.File("reservations.txt");
						java.io.PrintWriter output;
						try {
							output = new java.io.PrintWriter(file);
							output.print(textArea.getText());
							output.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
		
		quitButton = new JButton("Quit");
		
		quitButton.addActionListener(new
				ActionListener() {
					
					//@Override
					public void actionPerformed(ActionEvent e) {
						hotelSystem.quitGui();
					}
				});
		
		calendar = new GregorianCalendar();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int currentYear = cal.get(java.util.Calendar.YEAR);
		int currentMonth = cal.get(java.util.Calendar.MONTH);
		
		date = new Date(hotelSystem);
		
		//calendar = new Calendar();
		// Create a new JComboBox to hold the months
		monthList = new JComboBox<String>(monthStrings);
		
		// Sets the default combo box index to the current month.
		monthList.setSelectedIndex(currentMonth);
		
		
		// Add action listener to the month list
		monthList.addActionListener(new
				ActionListener() {
					public void actionPerformed(ActionEvent e) {
						date.setNewMonth(monthList.getSelectedIndex());
					}
				});
		
		
		
		// Create a spinner model, with a step of 1 and with the default current year
		SpinnerModel yearModel = new SpinnerNumberModel(currentYear, //initial value
                										currentYear - 1000, //min
                										currentYear + 1000, //max
                										1);                //step
		
		// Assign the model to the spinner
		spinner = new JSpinner(yearModel);
		
		
		//Make the year be formatted without a thousands separator.
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "#"));
		
        
		// Add a change listener to the spinner
		spinner.addChangeListener(new
				ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						date.setNewYear((Integer)spinner.getValue());
					}
				});
		
		ChangeListener listener = new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				String tittle = "Reservations on " + monthStrings[date.getMonth()] +
						" " + date.getDay() + ", " + date.getYear() +".\n\n";
				textArea.setText(tittle);
				Iterator<Reservation> iterator = date.listOfReservationsIterator();
				String text = "";
				while(iterator.hasNext()) {
					text += iterator.next().toString() + "\n";
				}
				textArea.setText(textArea.getText() + text);
			}
		};
		
		date.addListener(listener);
		
		
		buttons = new JButton[42];
		for (int i = 0; i < 42; i++) {
			buttons[i] = new JButton("");
			Dimension preferredSize = new Dimension(50, 50);
			buttons[i].setPreferredSize(preferredSize);
			
			buttons[i].setOpaque(true);
			buttons[i].setFont(new Font("", Font.PLAIN, 12));
			buttons[i].setForeground(Color.BLACK);
		}
		
		int startingIndex = 0;
		for (int i = 0, day = 1; i < 42; i++) {
			calendar.set(calendar.get(java.util.Calendar.YEAR), 
						 calendar.get(java.util.Calendar.MONTH), 1);
			
			if ( (i+1) < calendar.get(java.util.Calendar.DAY_OF_WEEK) ) continue;
			if ( day > calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH) ) break;
			if ( day == 1 ) startingIndex = i;
			buttons[date.getDay() + startingIndex - 1].setOpaque(true);
			buttons[date.getDay() + startingIndex - 1].setFont(new Font("", Font.BOLD, 14));
			buttons[date.getDay() + startingIndex - 1].setForeground(new Color(0, 181, 238));
			
			buttons[i].setText(day + "");
			day++;
		}
		
		for (int i = 0; i < 42; i++) {
			if (buttons[i].getText().equals("")) continue;
			String calendarDayString = buttons[i].getText();
			final int calendarDayInt = Integer.parseInt(calendarDayString);
			buttons[i].addActionListener(new
					ActionListener() {
						//@Override
						public void actionPerformed(ActionEvent e) {
							date.setNewDay(calendarDayInt);
						}
					});
		}
		
		labels = new JLabel[7];
		for (int i = 0; i < 7; i++) {
			labels[i] = new JLabel();
			Dimension preferredSize = new Dimension(40, 20);
			labels[i].setPreferredSize(preferredSize);
		}
		
		calendarPanel = new JPanel();
		daysOfTheWeekPanel = new JPanel();
		daysOfTheMonthPanel = new JPanel();
		
		
		daysOfTheWeekPanel.setLayout(new GridLayout(1,7));
		daysOfTheMonthPanel.setLayout(new GridLayout(6, 7));
		calendarPanel.setLayout(new BorderLayout());
		
		// Days of the week 
		
		for (int i = 0; i < 7; i++) {
			labels[i].setText(daysOfTheWeek[i]);
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			labels[i].setOpaque(true);
			labels[i].setBackground(new Color(0, 181, 238));
			labels[i].setForeground(Color.WHITE);
			daysOfTheWeekPanel.add(labels[i]);
		}
		
		for (int i = 0; i < 42; i++) {
			daysOfTheMonthPanel.add(buttons[i]);
		}
		
		calendarPanel.add(daysOfTheWeekPanel, BorderLayout.NORTH);
		calendarPanel.add(daysOfTheMonthPanel, BorderLayout.SOUTH);
		
		ChangeListener calendarListener = new ChangeListener() {
			
			//@Override
			public void stateChanged(ChangeEvent e) {
				calendar.set(date.getYear(), date.getMonth(), 1);
				
				for (int i = 0; i < 42; i++) {
					buttons[i].setText("");
					buttons[i].setOpaque(true);
					buttons[i].setFont(new Font("", Font.PLAIN, 12));
					buttons[i].setForeground(Color.BLACK);
				}
				
				int startingIndex = 0;
				for (int i = 0, day = 1; i < 42; i++) {
					calendar.set(calendar.get(java.util.Calendar.YEAR), 
								 calendar.get(java.util.Calendar.MONTH), 1);
					
					if ( (i+1) < calendar.get(java.util.Calendar.DAY_OF_WEEK) ) continue;
					if ( day > calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH) ) break;
					if ( day == 1 ) startingIndex = i;
					buttons[date.getDay() + startingIndex - 1].setOpaque(true);
					buttons[date.getDay() + startingIndex - 1].setFont(new Font("", Font.PLAIN, 14));
					buttons[date.getDay() + startingIndex - 1].setForeground(new Color(0, 181, 238));
					buttons[i].setText(day++ + "");
				}
				
				for (int i = 0; i < 42; i++) {
					if (buttons[i].getActionListeners().length  != 0)
						buttons[i].removeActionListener(buttons[i].getActionListeners()[0]);
					if (buttons[i].getText().equals("")) continue;
					String calendarDayString = buttons[i].getText();
					final int calendarDayInt = Integer.parseInt(calendarDayString);
					buttons[i].addActionListener(new
							ActionListener() {
								//@Override
								public void actionPerformed(ActionEvent e) {
									date.setNewDay(calendarDayInt);
								}
							});
					
				}
			}
		};
		
		date.addListener(calendarListener);
		
		buttonsPanel = new JPanel(new FlowLayout());
		buttonsPanel.add(saveButton);
		buttonsPanel.add(quitButton);
		
		rightPanel = new JPanel(new BorderLayout());
		textArea = new JTextArea(23, 25);
		JScrollPane scroller = new JScrollPane(textArea);
		rightPanel.add(scroller, BorderLayout.CENTER);
		rightPanel.setBorder(BorderFactory.createTitledBorder("Reservations"));
		
		
		leftPanel = new  JPanel(new BorderLayout());
		monthAndYearPanel = new JPanel(new FlowLayout());
		monthAndYearPanel.add(monthList);
		monthAndYearPanel.add(spinner);
		leftPanel.add(monthAndYearPanel, BorderLayout.NORTH);
		leftPanel.add(calendarPanel, BorderLayout.CENTER);
		leftPanel.add(buttonsPanel, BorderLayout.SOUTH);
		leftPanel.setBorder(BorderFactory.createTitledBorder("Pick a Date"));
		
		managerGUIPanel = new JPanel(new FlowLayout());
		managerGUIPanel.add(leftPanel/*, BorderLayout.CENTER*/);
		managerGUIPanel.add(rightPanel/*, BorderLayout.EAST*/);
		
		finalPanel = new JPanel();
		//finalPanel.setSize(1000, 180);
		finalPanel.setBorder(BorderFactory.createTitledBorder("Hotel Reservations by Date"));
		finalPanel.add(managerGUIPanel, BorderLayout.CENTER);
	}
	
	
	/**
	 * 
	 * @return a JPanel containing the calendar GUI
	 */
	public JPanel getManagerGUIPanel() {
		return finalPanel;
	}
	final JTextField t = new JTextField();// <-- example only ERASE!
	private GregorianCalendar calendar;
	
	
	// the days of the week
	private final String [] daysOfTheWeek = {"Sun",
											 "Mon",
											 "Tue",
											 "Wed",
											 "Thu",
											 "Fri",
											 "Sat"};
	
	// To hold the months of the year
	private final String [] monthStrings = {"January",
			 								"February",
			 								"March",
			 								"April",
			 								"May",
			 								"June",
			 								"July",
			 								"August",
			 								"September",
			 								"October",
			 								"November",
											"December" };
	
	// This combo box will hold the months of the year
	private final JComboBox<String> monthList;
	private final JSpinner spinner;
	private final Date date;
	private final JLabel[] labels;
	private final JPanel calendarPanel;
	private final JPanel daysOfTheWeekPanel;
	private final JPanel daysOfTheMonthPanel;
	private JPanel buttonsPanel;
	private JButton saveButton;
	private JButton quitButton;
	private JButton[] buttons;
	private JTextArea textArea;
	private JPanel monthAndYearPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JPanel managerGUIPanel;
	private JPanel finalPanel;
	
	private HotelSystem hotelSystem;
}
