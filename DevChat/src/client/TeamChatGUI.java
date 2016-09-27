package client;

import general.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;


public class TeamChatGUI extends JFrame implements Observer, ActionListener {
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	static final private String SEND_MESSAGE = "sendMessage";
	
	private Client client;
	private ChatWindow chatWindow;
	private Thread thread;
	private String username;
	private ArrayList<String> teamMembers;
	private JTextArea membersList;
	private JTextArea mainText;
	private JTextArea sendText;
	private JButton sendMessage;
	private ArrayList<String> allUsers;
	
	/**
	 * Constructor for Chat GUI
	 * @param client - Backend driving the output
	 */
	
	public TeamChatGUI(ChatWindow chatWindow, String username, Client client, ArrayList<String> allUsers) { // Used for testing GUI on its own
		super(chatWindow.getChat().getTeamname()); // Create new Frame with title
		this.chatWindow = chatWindow;
		chatWindow.addObserver(this);
		this.client = client;
		client.addObserver(this);
		this.addWindowListener(new Close());
		thread = new Thread(chatWindow);
		thread.start();
		this.username = username;
		this.allUsers = new ArrayList<String>(allUsers);
		System.out.println("TCG " + allUsers);
		this.teamMembers = new ArrayList<String>();
	
		setSize(630, 610); // Set Size
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close Operation
		JPanel main = new JPanel();
		BoxLayout box = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(box); // Set Layout
		Font font = new Font("Calibri", Font.BOLD, 16);
		
		// Setup Row 1 - Logo
		JPanel row1 = new JPanel();
		row1.setLayout(new BorderLayout()); // Allows logo to be placed far left
		row1.setBorder(BorderFactory.createEmptyBorder(5,5,0,0)); // Adds 5 pixels to left
		row1.add(new ToolbarPanelChatGUI(chatWindow, username, client, allUsers,this));
		main.add(row1); // Add row to Main Frame

		// Setup Row 2 - List of team members
		JPanel row2 = new JPanel();
		JLabel membersLabel = new JLabel("<html>Team<br>Members:</html>");
		row2.add(membersLabel);
		membersList = new JTextArea(2, 30);
		membersList.setEditable(false);
		
		row2.add(membersList);
		membersList.setLineWrap(true);
		membersList.setWrapStyleWord(true);
		membersList.setFont(font);
		JScrollPane scroll = new JScrollPane(membersList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		row2.add(scroll); // Add components to panel
		main.add(row2); // Add Panel to Frame
		
		
		// Setup Row 3 - Message History
		JPanel row3 = new JPanel();
		mainText = new JTextArea(15, 35);
		mainText.setEditable(false);
		row3.add(mainText);
		mainText.setLineWrap(true);
		mainText.setWrapStyleWord(true);
		mainText.setFont(font);
		DefaultCaret caret = (DefaultCaret)mainText.getCaret(); // Auto Scroll
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); // Auto Scroll
		// Add Scroll Pane with no sliders
		JScrollPane scroll2 = new JScrollPane(mainText,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		row3.add(scroll2); // Add components to panel
		main.add(row3); // Add Panel to Frame
				
		// Setup Row 4 - Send Message
		JPanel row4 = new JPanel();
		sendText = new JTextArea(6, 30);
		row4.setBorder(BorderFactory.createEmptyBorder(0,1,0,0));
		row4.add(sendText); // Add Send Textbox
		sendText.setLineWrap(true);
		sendText.setWrapStyleWord(true);
		sendText.setFont(font);
		// Add Scroll Pane with no sliders
		JScrollPane scroll3 = new JScrollPane(sendText,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		row4.add(scroll3); // Add components to panel
		sendMessage = new JButton("Send");
		row4.add(sendMessage); // Add Send Message Button
		main.add(row4); // Add Panel to Frame
				
		// Add Action Listeners and Commands
		sendMessage.setActionCommand(SEND_MESSAGE);
		sendMessage.addActionListener(this); // Send Button Action		
		add(main); // Add main frame
		
		//INITIALISE MEMBERS LIST
				teamMembers = chatWindow.getChat().getUsernames();
				System.out.println(chatWindow.getChat().getUsernames().toString() + " here ");
				String membersDisplay = teamMembers.toString();
				membersList.setText(membersDisplay.substring(1, membersDisplay.length() - 1));
//				for(int i = 0; i < teamMembers.size(); i++){
//					String s = teamMembers.get(i);
//					System.out.println(s + " here");
//					if(i < teamMembers.size()-1){
//						membersList.append(s + ", ");
//					}else{
//						membersList.append(s);
//					}
//				}
				
		setVisible(true); // Make Frame Visible
	}
	
	/**
	 * Execute instance methods in Model dependent upon which button has been clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		switch(cmd){
		case SEND_MESSAGE:
			ChatMessage chatMessage = new ChatMessage(chatWindow.getChat().getID() ,
												username, 
												sendText.getText());
		chatWindow.sendTeamMessage(chatMessage);
		sendText.setText("");
		break;
		}
	}
	
	/**
	 * Method called when the observable object changes (in this case when text output changes)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof ChatWindow){
			GUIMessage message = (GUIMessage)arg;
			switch(message.getStatus()){
				case DISPLAY_MESSAGE:
						String u = ((ChatMessage)message.getObject()).getUsername();
						String m = ((ChatMessage)message.getObject()).getMessage();
						mainText.append(u + " >>> " + m + "\n");
					break;
				case HISTORY:
					@SuppressWarnings("unchecked")
					ArrayList<ChatMessage> chatHistory = (ArrayList<ChatMessage>) message.getObject();
					for(ChatMessage cM : chatHistory){
						String un = cM.getUsername();
						String s = cM.getMessage();
						mainText.append(un + " >>> " + s + "\n");	
					}
					break;
				case ADD_MEMBER : case REMOVE_MEMBER:
						teamMembers = chatWindow.getChat().getUsernames();
						for (String s : chatWindow.getChat().getUsernames()) {
							System.out.println(s);
						}
						String membersDisplay = teamMembers.toString();
						membersList.setText(membersDisplay.substring(1, membersDisplay.length() - 1));
					break;
				case LEAVE_TEAM:
					//dispose();
					break;
				default:
					break;
			}
		}
	}
	
	class Close implements WindowListener {
			@Override
			public void windowOpened(WindowEvent e) {
			}
	
			@Override
			public void windowClosing(WindowEvent e) {
			}
	
			@Override
			public void windowClosed(WindowEvent e) {
				thread.interrupt();
			}
	
			@Override
			public void windowIconified(WindowEvent e) {
			}
	
			@Override
			public void windowDeiconified(WindowEvent e) {	
			}
	
			@Override
			public void windowActivated(WindowEvent e) {
			}
	
			@Override
			public void windowDeactivated(WindowEvent e) {

			}
		}
}