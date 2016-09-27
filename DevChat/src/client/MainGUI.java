package client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainGUI extends JFrame implements Observer{
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	private Client client; // Declare Model (Backend)
	private InboxPanel inboxPanel;
	private ToolbarPanelMainGUI toolbar;
	private ContactsPanel contactsPanel;
	private ProfilePanel profilePanel;
	
	public MainGUI(Client client) {
//	public MainGUI() {
		
		//Create and set up the window.
		super("DevChat"); // Create new Frame with title
		setSize(670, 650); // Set Size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close Operation
		setResizable(false);
		//JPanel main = new JPanel(); // Create and initialise JPanel
		
		// Integrate GUI with backend
		this.client = client; 
		client.addObserver(this);
		
		// Setup Components
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel leftBox = new JPanel(); // Holds Toolbar & Inbox
		JPanel rightBox = new JPanel(); // Holds Contacts & Profile
		leftBox.setLayout(new BoxLayout(leftBox, BoxLayout.Y_AXIS));
		rightBox.setLayout(new BoxLayout(rightBox, BoxLayout.Y_AXIS));
		
		// Setup Individual Boxes
		toolbar = new ToolbarPanelMainGUI(client);
		leftBox.add(toolbar);
		inboxPanel = new InboxPanel(client);
		leftBox.add(inboxPanel);
		contactsPanel = new ContactsPanel(client);
		rightBox.add(contactsPanel);
		profilePanel = new ProfilePanel(client.getUsername(), client);
		rightBox.add(profilePanel, client);

		// Setup Individual Grid Cells
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		add(leftBox, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		add(rightBox, c);

		setVisible(true); // Make Frame Visible
	}
	
	protected void close() {
		contactsPanel.stop();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Client){
			GUIMessage message = (GUIMessage)arg;
			switch(message.getStatus()){
			case LOGOUT:
				dispose();
				break;	
			}
		}
	}
	
}
