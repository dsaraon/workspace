package client;

import general.Account;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountGUI extends JFrame implements Observer, ActionListener, FocusListener {
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	static final private String CREATE = "create";
	static final private String CANCEL = "cancel";
	static final private String RESET = "reset";
	
	private Client client;
	private LoginGUI loginGUI;
	
	// Declare Components
	private JButton createButton;
	private JButton cancelButton;
	private JButton resetButton;
	private JTextField username;
	private JPasswordField password;
	private JTextField email;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField languages;
	private JTextField location;
	private JTextField company;
	private JTextField jobTitle;
	private JTextField website;
	private JTextField personalInfo;
	
	public CreateAccountGUI(Client client, LoginGUI loginGUI) {

		
		//Create and set up the window.
		super("Create Account"); 
		setSize(350, 400); // Set Size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setResizable(false);
		JPanel buttons1 = new JPanel();
		JPanel main = new JPanel(); 
		JPanel buttons2 = new JPanel(); 
		Font font = new Font("Calibri", Font.BOLD, 14); 
	
		this.client = client; 
		client.addObserver(this);
		this.loginGUI = loginGUI;
		
		// Setup Components
		this.setLayout(new BorderLayout());
		main.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Labels
		JLabel accountInfo  = new JLabel("<HTML><U>Account Info.</U></HTML>", JLabel.RIGHT);
		JLabel personalInfoLabel  = new JLabel("<HTML><U>Personal Info.</U></HTML>", JLabel.RIGHT);
		JLabel userNameLabel = new JLabel("User Name:", JLabel.RIGHT);
		JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
		JLabel emailLabel = new JLabel("E-mail:", JLabel.RIGHT);
		JLabel firstNameLabel = new JLabel("First Name:", JLabel.RIGHT);
		JLabel lastNameLabel = new JLabel("Last Name:", JLabel.RIGHT);
		JLabel languagesLabel = new JLabel("Languages:", JLabel.RIGHT);
		JLabel locationLabel = new JLabel("Location:", JLabel.RIGHT);
		JLabel companyLabel = new JLabel("Company Name:", JLabel.RIGHT);
		JLabel jobTitleLabel = new JLabel("Job Title:", JLabel.RIGHT);
		JLabel websiteLabel = new JLabel("Website:", JLabel.RIGHT);
		JLabel personalInfo2Label = new JLabel("Personal Info.:", JLabel.RIGHT);
		userNameLabel.setFont(font);
		accountInfo.setFont(font);
		personalInfoLabel.setFont(font);
		passwordLabel.setFont(font);
		emailLabel.setFont(font);
		firstNameLabel.setFont(font);
		lastNameLabel.setFont(font);
		languagesLabel.setFont(font);
		locationLabel.setFont(font);
		companyLabel.setFont(font);
		jobTitleLabel.setFont(font);
		websiteLabel.setFont(font);
		personalInfo2Label.setFont(font);
		
		// Buttons 1
		resetButton = new JButton("Reset");
		resetButton.setActionCommand(RESET);
		resetButton.setToolTipText("Reset Fields");
		resetButton.addActionListener(this);
		resetButton.setFont(font);
		
		// Buttons 2
		createButton = new JButton("Create Account");
		createButton.setActionCommand(CREATE);
		createButton.setToolTipText("Create Account");
		createButton.addActionListener(this);
		createButton.setEnabled(false);
		createButton.setFont(font);
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand(CANCEL);
		cancelButton.setToolTipText("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setFont(font);
		
		// Text Entry
		username = new JTextField(15);
		password = new JPasswordField(15);
		email = new JTextField(15);
		email.addFocusListener(this);
		firstName = new JTextField(15);
		lastName = new JTextField(15);
		languages = new JTextField(15);
		location = new JTextField(15);
		company = new JTextField(15);
		jobTitle = new JTextField(15);
		website = new JTextField(15);
		personalInfo = new JTextField(15);

		// Setup Individual Grid Cells
		//c.insets = new Insets(0,10,0,0); // 20 Pixels in column
		//c.anchor = GridBagConstraints.LINE_END;
		//c.fill = GridBagConstraints.HORIZONTAL;
		//c.anchor = GridBagConstraints.PAGE_START;
		
		// Adding Labels
		c.gridx = 0;
		c.gridy = 0;
		//c.insets = new Insets(10,0,0,0);
		c.anchor = GridBagConstraints.PAGE_START;
		main.add(accountInfo, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(userNameLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(passwordLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(emailLabel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(personalInfoLabel, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(firstNameLabel, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(lastNameLabel, c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(languagesLabel, c);
		
		c.gridx = 0;
		c.gridy = 8;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(locationLabel, c);
		
		c.gridx = 0;
		c.gridy = 9;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(companyLabel, c);
		
		c.gridx = 0;
		c.gridy = 10;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(jobTitleLabel, c);
		
		c.gridx = 0;
		c.gridy = 11;
		c.anchor = GridBagConstraints.LINE_END;
		main.add(websiteLabel, c);
		
		c.gridx = 0;
		c.gridy = 12;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		main.add(personalInfo2Label, c);
		
		// Adding Text Fields
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(username, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(password, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(email, c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(firstName, c);
		
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(lastName, c);
		
		c.gridx = 1;
		c.gridy = 7;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(languages, c);
		
		c.gridx = 1;
		c.gridy = 8;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(location, c);
		
		c.gridx = 1;
		c.gridy = 9;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(company, c);
		
		c.gridx = 1;
		c.gridy = 10;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(jobTitle, c);
		
		c.gridx = 1;
		c.gridy = 11;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		main.add(website, c);
		
		c.gridx = 1;
		c.gridy = 12;
		c.insets = new Insets(0,10,0,0);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		main.add(personalInfo, c);
		
		buttons1.add(resetButton);
		buttons2.add(createButton);
		buttons2.add(cancelButton);
		
		add(buttons1, BorderLayout.NORTH);
		add(main, BorderLayout.CENTER);
		add(buttons2, BorderLayout.SOUTH);
		setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		switch(cmd){
		case CREATE:
			if(password.getText().length() > 8 && password.getText().matches(".*\\d+.*")){
				Account newAccount = new Account(username.getText(), password.getText(), email.getText(),
						firstName.getText(), lastName.getText(), languages.getText(), location.getText(),
						company.getText(), jobTitle.getText(), website.getText(), personalInfo.getText());
				client.createAccount(newAccount);
			}else{
				JOptionPane.showMessageDialog(this,
					    "Password must contain min. 8 characters and 1 number",
					    "Password Error",
					    JOptionPane.ERROR_MESSAGE);
				password.setText("");
			}
			
			break;
		case CANCEL:
			setVisible(false);
		    dispose();
		    loginGUI.setVisible(true);
		    break;
		case RESET:
			reset();
			createButton.setEnabled(false);
			break;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Client) {
			GUIMessage message = (GUIMessage)arg;
			switch(message.getStatus()){
			case ACCOUNT_CREATED:
				setVisible(false);
				dispose();//do we need this? since it won't be used by this user again
				// launch main GUI from View
				break;
			case ACCOUNT_CREATED_FAIL:
				JOptionPane.showMessageDialog(this,//use rootPane instead?
					    "Invalid information. Please check again!",
					    "Invalid info error",
					    JOptionPane.ERROR_MESSAGE);
				setVisible(true); // Originally was closing create account gui
				break;
			}
		}
	}
	
	/**
	 * Method called when user wants to reset the create account form 
	 */
	private void reset(){
		username.setText("");
		password.setText("");
		email.setText("");
		firstName.setText("");
		lastName.setText("");
		languages.setText("");
		location.setText("");
		company.setText("");
		jobTitle.setText("");
		website.setText("");
		personalInfo.setText("");
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object source = e.getSource();
		if(source == email){
			if(!username.equals("") && !password.equals("") && !email.equals("")){
				createButton.setEnabled(true);
			}
		}
	}

}