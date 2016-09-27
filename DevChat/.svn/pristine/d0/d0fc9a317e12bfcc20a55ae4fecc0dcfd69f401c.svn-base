package tests;

import general.Account;
import general.Chat;
import general.ChatMessage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import database.Queries;

//write stubs for testing
public class TestDatabase implements Queries {

	@Override
	public void login(String username, String password) throws Exception {
		if (!((username.equals("firstname") || username.equals("firstname1")) && password.equals("password"))) {
			throw new Exception();
		}
	}

	@Override
	public boolean usernameFree(String username) {
		if (username.equals("username") || username.equals("another username")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void createAccount(Account account) throws SQLException {
		if (!account.getUsername().equals("username")) {
			throw new SQLException();
		}
	}

	@Override
	public Account getAccount(String username) throws SQLException {
		if (username.equals("a")) {
			return new Account("a","b","c");
		} else {
			throw new SQLException();
		}
	}

	@Override
	public ArrayList<String> allUsers() {
		return new ArrayList<String>();
	}

	@Override
	public Chat createPrivateChat(String username1, String username2)
			throws SQLException {
		if (username1.equals("firstname") && username2.equals("username")) {
			return new Chat(0, null);
		} else {
			throw new SQLException();
		}
	}

	@Override
	public Chat createTeam(String teamName, ArrayList<String> usernames)
			throws SQLException, Exception {
		if (teamName.equals("teamname")) {
			return new Chat(0, usernames, teamName);
		} else {
			throw new Exception();
		}
	}

	@Override
	public void savePrivateMessage(ChatMessage chatMessage)
			throws SQLException {
		if(!(chatMessage.getID() == 1)) {
			throw new SQLException();
		}
		
	}

	@Override
	public void saveTeamMessage(ChatMessage chatMessage) throws SQLException {
		if(!(chatMessage.getID() == 1)) {
			throw new SQLException();
		}
		
	}

	@Override
	public ArrayList<String> chatMember(int chatID) throws SQLException {
		if (chatID == 1 || chatID == 2) {
			ArrayList<String> a = new ArrayList<String>();
			a.add("firstname");
			return a;
		} else {
			throw new SQLException();
		}
	}

	@Override
	public ArrayList<String> teamMembers(int teamID) throws SQLException {
		if (teamID == 1 || teamID == 2) {
			ArrayList<String> a = new ArrayList<String>();
			a.add("firstname");
			return a;
		} else {
			throw new SQLException();
		}
	}

	@Override
	public void setDeliveredPrivate(int chatID, String username)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDeliveredTeam(int teamID, String username)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ChatMessage> getHistoryPrivate(int chatID)
			throws SQLException {
		if (chatID == 1) {
			return new ArrayList<ChatMessage>();
		} else {
			throw new SQLException();
		}
	}

	@Override
	public ArrayList<ChatMessage> getHistoryTeam(int teamID)
			throws SQLException {
		if (teamID == 1) {
			return new ArrayList<ChatMessage>();
		} else {
			throw new SQLException();
		}
	}

	@Override
	public void addUser(String username, int teamID) throws SQLException {
		if (teamID != 1) {
			throw new SQLException();
		}
		
	}

	@Override
	public void removeUser(String username, int teamID) throws SQLException {
		if (!(teamID == 1)) {
			throw new SQLException();
		}
		
	}

	@Override
	public ArrayList<Chat> getChats(String username) throws SQLException {
		if (username.equals("firstname")) {
			return new ArrayList<Chat>();
		} else {
			throw new SQLException();
		}
	}

	@Override
	public ArrayList<Chat> getTeams(String username) throws SQLException {
		if (username.equals("firstname")) {
			return new ArrayList<Chat>();
		} else {
			throw new SQLException();
		}
	}



}
