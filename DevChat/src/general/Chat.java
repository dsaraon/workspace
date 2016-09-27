package general;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int ID;
	private ArrayList<String> usernames; //would it have been better to have used a set?!
	private final Boolean team;
	private String teamname;
	private boolean delivered; //not totally sure what to do with this yet
	
	public Chat(int ID, ArrayList<String> usernames) {
		this.ID = ID;
		this.usernames = usernames;
		this.team = false;
	}
	
	public Chat(int ID, ArrayList<String> usernames, String teamname) {
		this.ID = ID;
		this.usernames = usernames;
		this.team = true;
		this.teamname = teamname;
	}

	public int getID() {
		return ID;
	}
	

	public synchronized ArrayList<String> getUsernames() {
		return usernames;
	}

	//no setters for id and usernames- should be done only via the constructor and add/remove Username methods
	public Boolean isTeam() {
		return team;
	}
	
	public String getTeamname() {
		return teamname;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public synchronized void addUsername(String username) {
		if(!usernames.contains(username)) { //stops a name being added twice
			usernames.add(username);
		}
	}

	public synchronized void removeUsername(String username) {
		usernames.remove(username);
	}

	public void setID(int int1) {
		// TODO Auto-generated method stub
		
	}

}