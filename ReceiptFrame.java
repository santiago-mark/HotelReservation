import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ReceiptFrame
{

	public ReceiptFrame(ArrayList<Reservation> res, GuestGUI gg, User usr)
	{
		transactionReservations = res;
		guestGUI = gg;
		user = usr;
		comp = new Comprehensive();
		simple = new Simple();
		simpleButton = new JButton("Simple Receipt");
		simpleButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		simpleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				displaySimple();
			}
		});
		comprehensive = new JButton("Comprehensive Receipt");
		comprehensive.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		comprehensive.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				displayComplex();
			}
		});
		
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				exit();
			}
		});
		receiptDisplay = new JTextArea(20,60);
		buttonPanel = new JPanel(new FlowLayout());
		textPanel = new JPanel(new FlowLayout());
		finalPanel = new JPanel(new BorderLayout());
		finalPanel.setSize(800, 500);
		buttonPanel.add(simpleButton);
		buttonPanel.add(comprehensive);
		buttonPanel.add(exitButton);
		textPanel.setBorder(BorderFactory.createTitledBorder("Receipt Options"));
		textPanel.add(receiptDisplay);
		textPanel.setBorder(BorderFactory.createTitledBorder("Receipt Details"));
		finalPanel.add(buttonPanel, BorderLayout.CENTER);
		finalPanel.add(textPanel, BorderLayout.SOUTH);
	}

	public JPanel getReceiptFramePanel()
	{
		return finalPanel;
	}
	
	private void displaySimple()
	{
		receiptDisplay.removeAll();
		simple.setReservationsOfTransaction(transactionReservations);
		receiptDisplay.setText(simple.printReceipt(user));
	}
	
	private void displayComplex()
	{
		receiptDisplay.removeAll();
		receiptDisplay.setText(comp.printReceipt(user));
	}
	
	private void exit()
	{
		guestGUI.loadOptions();
	}
	
	private ArrayList<Reservation> transactionReservations;
	private GuestGUI guestGUI;
	private User user;
	private JButton simpleButton;
	private JButton comprehensive;
	private JButton exitButton;
	private JTextArea receiptDisplay;
	private JPanel buttonPanel;
	private JPanel textPanel;
	private JPanel finalPanel;
	private Comprehensive comp;
	private Simple simple;
	
	private final int BUTTON_WIDTH = 200;
	private final int BUTTON_HEIGHT = 60;
}
