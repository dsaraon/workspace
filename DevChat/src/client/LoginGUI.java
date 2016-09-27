package client;
import general.ChatMessage;
import general.Message;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class LoginGUI extends JFrame implements Observer, ActionListener {
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	static final private String LOGIN = "login";
	static final private String CREATE_ACCOUNT= "createAccount";
	
	private Client client;
	JTextField userName = new JTextField();
	JPasswordField passWord = new JPasswordField();
	JButton signInButton = new JButton("Sign In");
	JButton createAccountButton = new JButton("Create Account");
	
	/**
	 * Constructor for GUI
	 * @param client - Backend driving the output
	 */
	
	public LoginGUI(Client client) {
		super("DevChat"); 
		this.client = client; 
		client.addObserver(this); 
		setSize(250, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setResizable(false);
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		Font font = new Font("Calibri", Font.BOLD, 18);
		
		//Logo
		JPanel row1 = new JPanel();
		row1.add(Help.createPicture("devchatlogo.png")); 
		main.add(row1);
		
		//Row 2 - Username
		JPanel row2 = new JPanel();
		JLabel userNameLabel = new JLabel("User Name:", JLabel.RIGHT);
		row2.add(userNameLabel);
		row2.add(userName);
		userNameLabel.setFont(font);
		userName.setFont(font);
		userName.setPreferredSize(new Dimension(200,26));
		main.add(row2);
		
		//Row 3 - Password
		JPanel row3 = new JPanel();
		JLabel passWordLabel = new JLabel("Password:", JLabel.RIGHT);
		row3.add(passWordLabel);
		row3.add(passWord);
		passWordLabel.setFont(font);
		passWord.setFont(font);
		passWord.setPreferredSize(new Dimension(200,26));
		main.add(row3);
		
		// Row 4 - Sign In & Create Account Buttons
		JPanel row4 = new JPanel();
		signInButton.setActionCommand(LOGIN);
		signInButton.setToolTipText("Sign In");
		signInButton.addActionListener(this);
		signInButton.setFont(font);
		row4.add(signInButton);

		createAccountButton.setActionCommand(CREATE_ACCOUNT);
		createAccountButton.setToolTipText("Create New Account");
		createAccountButton.addActionListener(this);
		createAccountButton.setFont(font);
		row4.add(createAccountButton);
		
		main.add(row4);
		
		add(main);
		main.getRootPane().setDefaultButton(signInButton);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		switch(cmd){
		case LOGIN:
			client.login(userName.getText(), passWord.getText());
			break;
		case CREATE_ACCOUNT:
			setVisible(false);
			CreateAccountGUI create = new CreateAccountGUI(client, this);
			break;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Client) {
			GUIMessage message = (GUIMessage)arg;
			switch(message.getStatus()){
			case LOGGED_IN:
				setVisible(false);
				reset();
				break;
			case LOGIN_FAIL:
				userName.setText("");
				passWord.setText("");
				JOptionPane.showMessageDialog(this,
					    "You shall not pass!",
					    "Invalid error",
					    JOptionPane.ERROR_MESSAGE);
				setVisible(true);
				break;
			case DISCONNECTED:
				signInButton.setEnabled(false);
				createAccountButton.setEnabled(false);
				JOptionPane.showMessageDialog(this,
					    "Cannot connect to server");
				break;
			case CONNECTED:
				signInButton.setEnabled(true);
				createAccountButton.setEnabled(true);
				JOptionPane.showMessageDialog(this,
					    "Successfully reconnected to server");
				break;
			}
        }
	}
	protected void reset(){
		userName.setText("");
		passWord.setText("");
	}
}
