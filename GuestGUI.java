import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.DimensionUIResource;


public class GuestGUI 
{
	public GuestGUI(HotelSystem hs, User usr)
	{
		hotelSystem = hs;
		user = usr;
		p1 = new JPanel(new GridLayout(3,1));
		panel = new JPanel();
		makeButton = new JButton("Make a Reservation");
		makeButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		makeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				makeButtonClick();
			}
		});
		viewButton = new JButton("View/Cancel a Reservation");
		viewButton.setPreferredSize(new DimensionUIResource(BUTTON_WIDTH, BUTTON_HEIGHT));
		viewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				viewButtonClick();
			}
		});
		quitButton = new JButton("Logout");
		quitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		quitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				quitButtonClick();
			}
		});
		
		a = new JPanel(new FlowLayout());
		b = new JPanel(new FlowLayout());
		c = new JPanel(new FlowLayout());
		
		a.add(makeButton);
		b.add(viewButton);
		c.add(quitButton);
		
		p1.add(a);
		p1.add(b);
		p1.add(c);
		
		panel.setBorder(BorderFactory.createTitledBorder("Guest Options"));
		
		panel.add(p1, BorderLayout.CENTER);
		loadOptions();
	}
	
	public JPanel getGuestGUIPanel() 
	{
		return panel;
	}
	
	public void loadOptions()
	{
		panel.removeAll();
		panel.add(p1);
		panel.revalidate();
		panel.repaint();
	}

	public HotelSystem getHotelSystem()
	{
		return hotelSystem;
	}
	
	public void showReceiptPane(ArrayList<Reservation> res)
	{
		panel.removeAll();
		panel.setLayout(new BorderLayout());
		ReceiptFrame rf = new ReceiptFrame(res, this, user);
		panel.add(rf.getReceiptFramePanel(), BorderLayout.CENTER);
		panel.revalidate();
	}
	
	private void makeButtonClick()
	{
		panel.removeAll();
		panel.setLayout(new BorderLayout());
		MakeReservationModel model = new MakeReservationModel(hotelSystem);
		MakeReservationGUI view = new MakeReservationGUI(this, model, user);
		model.attach(view);
		panel.add(view.getMakeReservationGUIPanel(), BorderLayout.CENTER);
		panel.revalidate();
	}
	
	private void viewButtonClick()
	{
		panel.removeAll();
		panel.setLayout(new BorderLayout());
		ViewReservationGUI v = new ViewReservationGUI(this, user);
		panel.add(v.getViewReservationGUIPanel(), BorderLayout.CENTER);
		panel.revalidate();
	}
	
	private void quitButtonClick()
	{
		hotelSystem.quitGui();
	}
	
	private HotelSystem hotelSystem;
	private User user;
	private JPanel panel;
	private JPanel p1;
	private JPanel a;
	private JPanel b;
	private JPanel c;
	private JButton makeButton;
	private JButton viewButton;
	private JButton quitButton;
	private final int BUTTON_WIDTH = 500;
	private final int BUTTON_HEIGHT = 130;
	
}
