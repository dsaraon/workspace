package client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewTeamChatGUI extends JFrame implements Observer, ActionListener {
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	static final private String MAKE_TEAM = "makeTeam";
	static final private String CANCEL = "cancel";
	private ArrayList<String> selectedList;
	
	private Client client;

	private Font font;
	private JPanel main;
	
	//Row 1 - Username
	JPanel row1;
	JLabel teamNameLabel;
	JTextField teamName;
	
	//Row 2 - Buttons
	JPanel row2;
	JButton makeTeamButton; 
	JButton cancelButton;
	
	public NewTeamChatGUI(Client client, ArrayList<String> selectedList) { 
		super("Make Team"); 
		//this.client = client; 
		//client.addObserver(this);
		setSize(300, 110); // Set Size
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false); 
		
		font = new Font("Calibri", Font.BOLD, 14);
		main = new JPanel();
		teamName = new JTextField();
		row2 = new JPanel();
		makeTeamButton = new JButton("Make Team"); 
		cancelButton = new JButton("Cancel");
		
		this.selectedList = selectedList;
		// Setup Row 1 - Team Name
		row1.add(teamNameLabel);
		row1.add(teamName);
		teamNameLabel.setFont(font);
		teamName.setFont(font);
		teamName.setPreferredSize(new Dimension(200,26));
		main.add(row1);
		
		// Setup Row 2 - Sign In & Create Account Buttons
		//row2.setLayout(new GridLayout(1,2,20,20));
		makeTeamButton.setActionCommand(MAKE_TEAM);
		makeTeamButton.setToolTipText("Make Team");
		makeTeamButton.addActionListener(this);
		makeTeamButton.setFont(font);
		row2.add(makeTeamButton);
		
		// Create Account Button
		cancelButton.setActionCommand(CANCEL);
		cancelButton.setToolTipText("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setFont(font);
		row2.add(cancelButton);
		
		main.add(row2);
		
		add(main); 
		setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		switch(cmd){
		case MAKE_TEAM:
			client.makeTeam(teamName.getText(), selectedList);
			break;
		case CANCEL:
			dispose();
			break;
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
