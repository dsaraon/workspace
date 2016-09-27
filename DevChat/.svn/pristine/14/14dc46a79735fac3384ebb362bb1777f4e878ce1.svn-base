package tests;

import general.Account;
import general.Chat;
import general.ChatMessage;

import java.sql.SQLException;
import java.util.ArrayList;


import database.Queries;

public class ClientTestDatabase implements Queries {
	
	//maybe use a map to simulate the database

	@Override
	public void login(String username, String password) throws SQLException,
			Exception {
		if (username.equals("fail")) {
			throw new Exception();
		}
		
	}

	@Override
	public boolean usernameFree(String username) throws SQLException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void createAccount(Account account) throws SQLException {
		if (account.getUsername().equals("fail")) {
			throw new SQLException();
		}
		
	}

	@Override
	public Account getAccount(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> allUsers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat createPrivateChat(String username1, String username2)
			throws SQLException {
		if (username1.equals("username1") && username2.equals("username2")) {
			ArrayList<String> usernames = new ArrayList<String>();
			usernames.add(username1);
			usernames.add(username2);
			return new Chat(1, usernames);
		}
		throw new SQLException();
	}

	@Override
	public Chat createTeam(String teamName, ArrayList<String> usernames)
			throws SQLException, Exception {
		if (teamName.equals("teamname")) {
			return new Chat(2, usernames, teamName);
		}
		throw new Exception();
	}

	@Override
	public void savePrivateMessage(ChatMessage chatMessage)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveTeamMessage(ChatMessage chatMessage) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> chatMember(int chatID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> teamMembers(int teamID) throws SQLException{
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ChatMessage> getHistoryTeam(int teamID)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(String username, int teamID) throws SQLException {
		if (username.equals("username6")) {
			throw new SQLException();
		}
		
	}

	@Override
	public void removeUser(String username, int teamID) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Chat> getChats(String username) throws SQLException {
		return new ArrayList<Chat>();
	}

	@Override
	public ArrayList<Chat> getTeams(String username) throws SQLException {
		return new ArrayList<Chat>();
	}

}
