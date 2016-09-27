package client;

import general.Chat;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InboxPanel extends JPanel implements Observer, MouseListener, FocusListener{
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	private Client client;
	
	//Components
	private JList<String> privateList;
	private DefaultListModel<String> privateListModel;
	private JList<String> teamList;
	private DefaultListModel<String> teamListModel;
	
	//ArrayLists storing previous chats
	private ArrayList<String> privateInputList;
	private ArrayList<String> teamInputList;
	private ArrayList<Chat> privateChats;
	private ArrayList<Chat> teamChats;
	private ArrayList<Chat> allChats;
	private Chat teamChat;
	private Chat privateChat;
	private int teamID;
	
	int chosenList; // 1 = PrivateList, 2 = TeamList
	
	private GridBagConstraints c;
	
	public InboxPanel(Client client) {
		
		
		setPreferredSize(new Dimension(400, 515)); 
		//setResizable(false);
		Font font = new Font("Calibri", Font.BOLD, 14); 
		Font font2 = new Font("Calibri", Font.BOLD, 18);
		
		this.client = client; 
		client.addObserver(this);
		
		teamChats = new ArrayList<Chat>();
		privateChats = new ArrayList<Chat>();
		
		//Components
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		//Labels
		JLabel inbox  = new JLabel("<html><u>Recent Private Chats</u></html>", JLabel.LEFT);
		inbox.setFont(font2);
		JLabel inbox2  = new JLabel("<html><u>Recent Team Chats</u></html>", JLabel.LEFT);
		inbox2.setFont(font2);

		// Setup List
		//cList = new ArrayList<String>();
		privateInputList = new ArrayList<String>();
		teamInputList = new ArrayList<String>();
		//allChats = new ArrayList<Chat>();	
		
		privateListModel = new DefaultListModel<String>();
		
		privateList = new JList<String>(privateListModel);
		privateList.setFont(font);
		privateList.addFocusListener(this);
		privateList.setLayoutOrientation(JList.VERTICAL);
		privateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//contactsList.setVisibleRowCount(-1); // No effect
		//inboxList.addListSelectionListener(this);
		JScrollPane listScroller = new JScrollPane(privateList);
		listScroller.setPreferredSize(new Dimension(400, 200));
		
		teamListModel = new DefaultListModel<String>();
		
		teamList = new JList<String>(teamListModel);
		teamList.setFont(font);
		teamList.addFocusListener(this);
		teamList.setLayoutOrientation(JList.VERTICAL);
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//contactsList.setVisibleRowCount(-1); // No effect
		//inboxList.addListSelectionListener(this);
		JScrollPane listScroller2 = new JScrollPane(teamList);
		listScroller2.setPreferredSize(new Dimension(400, 200));
		
		//Add Components
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		add(inbox, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10,0,0,0);
		c.anchor = GridBagConstraints.PAGE_START;
		add(listScroller, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		add(inbox2, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10,0,0,0);
		c.anchor = GridBagConstraints.PAGE_START;
		add(listScroller2, c);
		
		addMouseListener(this);
		privateList.addMouseListener(this);
		teamList.addMouseListener(this);
		
		setVisible(true); 
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Client) {
			GUIMessage message = (GUIMessage)arg;
			switch(message.getStatus()){
				case ALL_PRIVATE_CHATS:
//					allChats.addAll((ArrayList<Chat>) message.getObject());
					privateChats = (ArrayList<Chat>) message.getObject();
					for(Chat c: privateChats){
						ArrayList<String> names = new ArrayList<String>(c.getUsernames());
						for(String n: names){
							if(!client.username.equals(n)){
								privateInputList.add("<html><u><font color=green>" + n + "</u></font></html>");
							}
						}
					}
					for(String s:privateInputList){ 
						if(!privateListModel.contains(s))
							privateListModel.addElement(s);
					}
					break;
				
				case ALL_TEAM_CHATS:
				teamChats = (ArrayList<Chat>) message.getObject();
				for(Chat c: teamChats){
					String teamName = c.getTeamname();
					StringBuilder usernames = new StringBuilder();
					
					for(String s: c.getUsernames()){
						if(!client.username.equals(s)){
							usernames.append(s + ", ");
						}
					}
					if(c.getUsernames().size() > 1){
						String finalUsernames = usernames.toString();
						finalUsernames = finalUsernames.substring(0, finalUsernames.length()-2); 
						teamInputList.add("<html><u><font color=blue>" + teamName + ": " + finalUsernames + "</u><br></font></html>");
					}else{
						teamInputList.add("<html><u><font color=blue>" + teamName + ": </u><br></font></html>");
					}
				}
				for(String s:teamInputList){
					if(!teamListModel.contains(s))
						teamListModel.addElement(s);
				}
				break;
				case JOIN_TEAM:
					teamChat = (Chat) message.getObject();
					teamChats.add(teamChat);
					String teamName = teamChat.getTeamname();
					StringBuilder usernames = new StringBuilder();
					
					for(String s: teamChat.getUsernames()){
						if(!client.username.equals(s)){
							usernames.append(s + ", ");
						}
					}
					if(teamChat.getUsernames().size() > 1){
						String finalUsernames = usernames.toString();
						finalUsernames = finalUsernames.substring(0, finalUsernames.length()-2); 
						teamInputList.add("<html><u><font color=blue>" + teamName + ": " + finalUsernames + "</u><br></font></html>");
					}else{
						teamInputList.add("<html><u><font color=blue>" + teamName + ": </u><br></font></html>");
					}
					for(String s:teamInputList){
						if(!teamListModel.contains(s))
							teamListModel.addElement(s);
					}
					break;
				case JOIN_CHAT:
					privateChat = (Chat) message.getObject();
					privateChats.add(privateChat);
					ArrayList<String> names = new ArrayList<String>(privateChat.getUsernames());
					for(String n: names){
						if(!client.username.equals(n)){
							privateInputList.add("<html><u><font color=green>" + n + "</u></font></html>");
						}
					}
					for(String s:privateInputList){ 
						if(!privateListModel.contains(s))
							privateListModel.addElement(s);
					}
					break;
				case EXIT_TEAM:
					System.out.println("Remove Member");
					teamChat = (Chat) message.getObject();
					for(int i = 0;i < teamChats.size();i++){
						if(teamChats.get(i).equals(teamChat)){
							teamChats.remove(i);
							teamInputList.remove(i);
							teamListModel.remove(i);
							break;
						}
					}
					break;
				}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		// Double Click Event
		try{
		JList list = (JList)e.getSource();
        if (e.getClickCount() == 2 && !e.isConsumed()) {
        	e.consume();
        	int index = list.locationToIndex(e.getPoint());
        	if(chosenList == 1){
        		Chat chat = privateChats.get(index);
        		client.resumePrivateChat(chat);
        	}else{
        		Chat chat = teamChats.get(index);
        		client.resumeTeamChat(chat);
        	}
 
        }
		} catch(ArrayIndexOutOfBoundsException ex){
			//System.out.println("Mouse Click");
			// Stop it from throwing exceptions with single mouse clicks
		} catch(ClassCastException ex){
			//System.out.println("Mouse Click");
			// Stop it from throwing exceptions with single mouse clicks
		}
	}
	
//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//	    if (e.getValueIsAdjusting() == false) {
//
//	        if (privateList.getSelectedIndex() == -1 && !(teamList.getSelectedIndex() == -1)) {
//	        	chosenList = 2; // Chosen List = Team Chats
//	        	System.out.println(chosenList);
//	        } else if(!(privateList.getSelectedIndex() == -1) && (teamList.getSelectedIndex() == -1)) {
//	        	chosenList = 1; // Chosen List = Private Chats
//	        	System.out.println(chosenList);
//	        }
//	    }
//	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Used to test panel by creating a new frame and inserting it
	 */
	@Override
	public void focusGained(FocusEvent e) {
		Object object = e.getSource();
		if(object.equals(privateList)){
			chosenList = 1; // Chosen List = Private Chats
		}else if(object.equals(teamList)){
			chosenList = 2; // Chosen List = Team Chats
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}



}