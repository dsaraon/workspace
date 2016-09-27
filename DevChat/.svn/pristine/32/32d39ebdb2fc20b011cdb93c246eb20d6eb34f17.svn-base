package client;

import general.Account;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfilePanel extends JPanel implements Observer, ActionListener {
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	static final private String VIEW_PROFILE = "viewProfile";
	
	private Client client; 
	private String username;
	
	private JButton profileButton;
	
	public ProfilePanel(String username, Client client) {
		
		setPreferredSize(new Dimension(200, 200));
		//setResizable(false);
		Font font = new Font("Calibri", Font.BOLD, 18); 
		
		this.client = client; 
		client.addObserver(this);
		
		this.username = username;
		System.out.println(username);
		
		// Setup Components
//		this.setLayout(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new BorderLayout());
		JPanel south = new JPanel();
		south.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Labels
		JLabel name  = new JLabel(username, JLabel.LEFT);
		name.setFont(font);

		// Buttons
		profileButton = makeNavigationButton("personicon.png", VIEW_PROFILE,
				"View Profile", "View Profile");
		
//		// Adding Components
//		c.gridx = 0;
//		c.gridy = 0;
//		c.anchor = GridBagConstraints.PAGE_START;
//		c.ipadx = 50;
//		add(profileButton, c);
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		c.anchor = GridBagConstraints.CENTER;
		south.add(name, c);
		
		c.gridx = 0;
		c.gridy = 1;
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
	
		
		add(profileButton, BorderLayout.NORTH);
		add(south, BorderLayout.CENTER);
		//add(company, BorderLayout.SOUTH);
		
		setVisible(true); 
	}
	
	protected JButton makeNavigationButton(String imageName,
			String actionCommand, String toolTipText, String altText) {
		// Look for the image.
		String imgLocation = "" + imageName;
		//String imgLocation = "images/" + imageName;
		URL imageURL = ProfilePanel.class.getResource(imgLocation);

		// Create and initialize the button.
		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(this);

		if (imageURL != null) { // image found
			button.setIcon(new ImageIcon(imageURL, altText));
		} else { // no image found
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
		}

		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		switch(cmd){
		case VIEW_PROFILE:
			client.viewAccount(username);	
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	/*
	 * Used to test panel by creating a new frame and inserting it
	 */
	
}