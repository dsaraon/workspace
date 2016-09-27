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

public class InviteRemoveGUI extends JFrame implements Observer, ActionListener, ListSelectionListener {
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	static final private String ADD_MEMBER = "addMember";
	static final private String REMOVE_MEMBER = "removeMember";
	static final private String DONE = "done";
	private ChatWindow chatWindow;
	private Client client;
	
	// Declare Components
	private JButton removeButton;
	private JButton addButton;
	private JButton doneButton;
	private JList<String> currentMembersList;
	private DefaultListModel<String> listModel;
	private JComboBox<String> addList;
	
	// Declare ArrayLists storing members and contacts
	protected ArrayList<String> membersList;
	protected ArrayList<String> allUsers;
	
	public InviteRemoveGUI(ChatWindow chatWindow, Client client, ArrayList<String> allUsers) {
		//Create and set up the window.
		super("Add/Remove Team Members"); // Create new Frame with title
		setSize(500, 500); // Set Size
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close Operation
		JPanel main = new JPanel(); // Create and initialise JPanel
		Font font = new Font("Calibri", Font.BOLD, 14); // Default Font
		this.chatWindow = chatWindow;
		this.client = client;
		chatWindow.addObserver(this);
		client.addObserver(this);
		
		// Setup Components
		main.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Labels
		JLabel currentMembersLabel = new JLabel("Current Members:", JLabel.RIGHT);
		currentMembersLabel.setFont(font);
		JLabel addMemberLabel = new JLabel("Add Member:", JLabel.RIGHT);
		addMemberLabel.setFont(font);
		// Buttons
		removeButton = new JButton("Remove Member");
		removeButton.setActionCommand(REMOVE_MEMBER);
		removeButton.setToolTipText("Remove Member");
		removeButton.addActionListener(this);
		removeButton.setEnabled(false);
		removeButton.setFont(font);
		addButton = new JButton("Add");
		addButton.setActionCommand(ADD_MEMBER);
		addButton.setToolTipText("Add Member");
		addButton.addActionListener(this);
		addButton.setFont(font);
		doneButton = new JButton("Done");
		doneButton.setActionCommand(DONE);
		doneButton.setToolTipText("Done");
		doneButton.addActionListener(this);
		doneButton.setFont(font);
		
		// Setup List - GET THE USERNAMES FROM CHAT OBJECT
		membersList = chatWindow.getChat().getUsernames();
		
		listModel = new DefaultListModel<String>();
			for(String s:membersList){
				listModel.addElement(s);
			}
		currentMembersList = new JList<String>(listModel);
		currentMembersList.setFont(font);
		currentMembersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentMembersList.setLayoutOrientation(JList.VERTICAL);
		currentMembersList.addListSelectionListener(this);
		JScrollPane listScroller = new JScrollPane(currentMembersList);
		listScroller.setPreferredSize(new Dimension(150, 150));
		
		// Setup Combo Box - GET ALL USERNAMES AND ADD
		this.allUsers = new ArrayList<String>(allUsers);
		this.allUsers.removeAll(membersList);
		addList = new JComboBox(this.allUsers.toArray());
		this.allUsers.removeAll(membersList); // avoids current members from being displayed
		addList.setEditable(true);
		addList.addActionListener(this);
		addList.setFont(font);

		// Setup Individual Grid Cells
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		main.add(currentMembersLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,10,0,0); // 20 Pixels in column
		main.add(listScroller, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0,10,0,0);
		main.add(removeButton, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(10,0,0,0);
		main.add(addMemberLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10,10,0,0);
		main.add(addList, c);
		
		c.gridx = 2;
		c.gridy = 2;
		main.add(addButton, c);
		
		c.gridx = 1;
		c.gridy = 3;
		main.add(doneButton, c);
		
		add(main);
		setVisible(true); // Make Frame Visible
	}
	
	/**
	 * Once a selection is made the below method is fired
	 */
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {
	        if (currentMembersList.getSelectedIndex() == -1) {
	        //No selection, disable remove button.
	            removeButton.setEnabled(false);
	        } else {
	        //Selection, enable the remove button.
	        	removeButton.setEnabled(true);
	        }
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		switch(cmd){
			case ADD_MEMBER:
					String name = (String) addList.getSelectedItem();
					int index = addList.getSelectedIndex();
					// Add code for add member
					chatWindow.addTeamMember(name);
					//REASSEMBLE GUI
					addList.removeItemAt(index); // since it has to be removed from here before adding elsewhere
					membersList.add(name);
					listModel.addElement(name);
				break;
			case REMOVE_MEMBER:
					int index2 = currentMembersList.getSelectedIndex();
					String name2 = (String) currentMembersList.getSelectedValue();//add to drop dpwn menu
						if(listModel.getSize() == 0){ // No members left
						}else{
							if (index2 == listModel.getSize()) {
					            index2--;//removed item in last position
					        }
							currentMembersList.setSelectedIndex(index2);
							currentMembersList.ensureIndexIsVisible(index2);
						}
					chatWindow.removeTeamMember(name2);
					listModel.remove(index2);
					addList.addItem(name2);
				break;
			case DONE:
					setVisible(false);
				    dispose();
			    break;
			default:
				break;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
	
		if(o instanceof ChatWindow){
			GUIMessage message = (GUIMessage)arg;
			switch(message.getStatus()){
				case ADD_MEMBER:
					break;
				case REMOVE_MEMBER:
					break;
				default:
					break;
			}
		}
	
	}
}
