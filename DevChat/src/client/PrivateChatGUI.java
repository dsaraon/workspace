package client;

import general.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;

public class PrivateChatGUI extends JFrame implements Observer, ActionListener {
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	static final private String SEND_MESSAGE = "sendMessage";

	private ChatWindow chatWindow;
	private Thread thread;
	private String username;
	
	JTextArea mainText;
	JTextArea sendText;
	JButton sendMessage;
	
	/**
	 * Constructor for Chat GUI
	 * @param client - Backend driving the output
	 */
	
	public PrivateChatGUI(ChatWindow chatWindow, String username) { 
		super(username);
		String otherUsername = "";
		ArrayList<String> usernames = chatWindow.getChat().getUsernames();
		for(String s: usernames){
			if(!s.equals(username)){
				otherUsername = s;
			}
		}
		setTitle(otherUsername);
		
		this.chatWindow = chatWindow;
		this.thread = new Thread(chatWindow);
		thread.start();
		this.username = username;
		chatWindow.addObserver(this);
		
		this.addWindowListener(new Close());
		
		setSize(630, 540); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		
		//GridLayout layout = new GridLayout(3, 1, 20, 20); // Create GridLayout 4x1
		JPanel main = new JPanel();
		BoxLayout box = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(box);
		Font font = new Font("Calibri", Font.BOLD, 16);
		
		// Setup Row 1 - Logo
		JPanel row1 = new JPanel();
		row1.setLayout(new BorderLayout()); 
		row1.setBorder(BorderFactory.createEmptyBorder(5,5,0,0)); 
		//row1.add(new ToolbarPanelChatGUI());
		row1.add(Help.createPicture("devchatlogosmall.png"), BorderLayout.WEST);
		main.add(row1); // Add row to Main Frame
		
		// Setup Row 2 - Message History
		JPanel row2 = new JPanel();
		mainText = new JTextArea(15, 35);
		mainText.setEditable(false);
		row2.add(mainText);
		mainText.setLineWrap(true);
		mainText.setWrapStyleWord(true);
		mainText.setFont(font);
		DefaultCaret caret = (DefaultCaret)mainText.getCaret(); 
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		//ScrollPanel without no slider
		JScrollPane scroll = new JScrollPane(mainText,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		row2.add(scroll); 
		main.add(row2);
		
		// Setup Row 3 - Send Message
		JPanel row3 = new JPanel();
		row3.setLayout(new FlowLayout());
		row3.setBorder(BorderFactory.createEmptyBorder(0,1,0,0));
		sendText = new JTextArea(6, 30);
		row3.add(sendText);
		sendText.setLineWrap(true);
		sendText.setWrapStyleWord(true);
		sendText.setFont(font);
		// Add Scroll Pane with no sliders
		JScrollPane scroll2 = new JScrollPane(sendText,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		row3.add(scroll2);
		sendMessage = new JButton("Send");
		row3.add(sendMessage);
		main.add(row3);
				
		// Add Action Listeners and Commands
		sendMessage.setActionCommand(SEND_MESSAGE);
		sendMessage.addActionListener(this); 
		
		add(main); 
		
		setVisible(true); 
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
				chatWindow.sendPrivateMessage(chatMessage);
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
				default:
					break;
				}
		}
	}
	
	
	class Close implements WindowListener {
		

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		thread.interrupt();
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	}
}
