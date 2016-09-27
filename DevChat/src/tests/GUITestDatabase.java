package tests;

import general.Account;
import general.Chat;
import general.ChatMessage;

import java.sql.SQLException;
import java.util.ArrayList;

import database.Queries;

public class GUITestDatabase implements Queries {

	@Override
	public void login(String username, String password) throws SQLException,
			Exception {
	}

	@Override
	public boolean usernameFree(String username) throws SQLException {
		if(username.equals("fail")) {return false;}
		return true;
	}

	@Override
	public void createAccount(Account account) throws SQLException {
		
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
			ArrayList<String> testpeople = new ArrayList<String>();
			testpeople.add(username1);
			testpeople.add(username2);
			Chat chat = new Chat(0, testpeople);
			System.out.println(chat.isTeam());
			return new Chat(0, testpeople);
	}

	@Override
	public Chat createTeam(String teamName, ArrayList<String> usernames)
			throws SQLException, Exception {
		if (teamName.equals("teamName")) {
			return new Chat(1, usernames, teamName);
		} else {
			throw new Exception();
		}
	}

	@Override
	public void savePrivateMessage(ChatMessage chatMessage)
			throws SQLException {
		if(!(chatMessage.getID() == 0)) {
			throw new SQLException();
		}		
	}

	@Override
	public void saveTeamMessage(ChatMessage chatMessage) throws SQLException {
		if(!(chatMessage.getID() == 0)) {
			throw new SQLException();
		}		
	}

	@Override
	public ArrayList<String> chatMember(int chatID) throws SQLException{
		if (chatID == 0) {
			ArrayList<String> a = new ArrayList<String>();
			a.add("Anna Jackson");
			a.add("Jess Pattison");
			return a;
		} else {
			throw new SQLException();
		}
	}

	@Override
	public ArrayList<String> teamMembers(int teamID) throws SQLException {
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
		return new ArrayList<ChatMessage>();
	}

	@Override
	public ArrayList<ChatMessage> getHistoryTeam(int teamID)
			throws SQLException {
		// TODO Auto-generated method stub
		return new ArrayList<ChatMessage>();
	}

	@Override
	public void addUser(String username, int teamID) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(String username, int teamID) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Chat> getChats(String username) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Chat> chatArrayList = new ArrayList<Chat>();
		return chatArrayList;
	}

	@Override
	public ArrayList<Chat> getTeams(String username) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Chat> chatArrayList = new ArrayList<Chat>();
		return chatArrayList;
	}
}