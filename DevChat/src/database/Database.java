package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import general.*;

/**
 * Database class for the DevChat group project. This class runs the JDBC
 * connection for the server and contains all methods for communicating
 * with the postgresql database. All SQL commands are written as prepared
 * statements for the ease of interchangeable variables.
 * @author Sam Joseph (stj485)
 *
 */
public class Database implements Queries {

	private final String URL = "jdbc:postgresql://dbteach2/dev_chat"; // change as needed
	private final String USERNAME = "stj485"; // change as needed
	private final String PASSWORD = "wedrutep"; // change as needed
	
	private Connection connection = null;
	
	// PreparedStatements initialised - Those commented out are yet to be implemented
	private PreparedStatement login = null;
	private PreparedStatement usernameFree = null;
	private PreparedStatement getPrivateChat = null;
	private PreparedStatement createPrivateChat = null;
	private PreparedStatement createTeamChat = null;
	private PreparedStatement savePrivateMessage = null;
	private PreparedStatement saveTeamMessage = null;
	private PreparedStatement setDeliveredPrivate = null;
	//private PreparedStatement setDeliveredTeam = null;
	private PreparedStatement getHistoryPrivate = null;
	private PreparedStatement getHistoryTeam = null;
	private PreparedStatement addUser = null;
	private PreparedStatement removeUser = null;
	private PreparedStatement chatMember = null;
	private PreparedStatement teamMembers = null;
	private PreparedStatement getChats = null;
	private PreparedStatement getTeams = null;
	private PreparedStatement allUsers = null;
	private PreparedStatement createAccount = null;
	private PreparedStatement getAccount = null;
	private PreparedStatement getTeamID = null;
	
	/**
	 * Constructor initialises connection and sets text for prepared statements.
	 * 
	 * Need to think about how to re-establish connections if needed
	 */
	public Database() throws SQLException {
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		//connection = DriverManager.getConnection(URL);
		login = connection.prepareStatement("SELECT username FROM users WHERE username = ? AND password = ?");
		usernameFree = connection.prepareStatement("SELECT username FROM users WHERE username = ?");
		getPrivateChat = connection.prepareStatement("SElECT chat_ID from private_chat WHERE user1 = ? AND user2 = ?");
		createPrivateChat = connection.prepareStatement("INSERT INTO private_chat (user1, user2) VALUES (?, ?)");
		savePrivateMessage = connection.prepareStatement("INSERT INTO private_messages "
												+ "(chat_id, message_sent, user_posted, text) VALUES (?, ?, ?, ?)");
		saveTeamMessage = connection.prepareStatement("INSERT INTO team_messages (chat_id, message_sent, user_posted, text) VALUES (?, ?, ?, ?)");	
		setDeliveredPrivate = connection.prepareStatement("UPDATE private_chat SET ? = false WHERE chat_id = ?");
		chatMember = connection.prepareStatement("SELECT user1, user2 FROM private_chat WHERE chat_id = ?");
		getHistoryPrivate = connection.prepareStatement("SELECT * FROM private_messages WHERE chat_id = ?");
		getChats = connection.prepareStatement("SELECT * FROM private_chat WHERE user1 = ? OR user2 = ?");
		getTeams = connection.prepareStatement("SELECT * FROM teams WHERE team_id IN (SELECT team_id FROM team_members WHERE username = ?)");
		teamMembers = connection.prepareStatement("SELECT username FROM team_members WHERE team_id = ?");
		allUsers = connection.prepareStatement("SELECT username FROM users");
		getTeamID = connection.prepareStatement("SELECT team_id FROM teams WHERE team_name = ?");
		createTeamChat = connection.prepareStatement("INSERT INTO teams (team_name) VALUES (?)");
		addUser = connection.prepareStatement("INSERT INTO team_members (team_id, username) VALUES (?, ?)");
		removeUser = connection.prepareStatement("DELETE FROM team_members WHERE team_id = ? AND username = ?");
		createAccount = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		getHistoryTeam = connection.prepareStatement("SELECT * FROM team_messages WHERE chat_id = ?");
		getAccount = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
	}

	
	@Override
	public void login(String username, String password) throws SQLException, Exception {
		login.setString(1, username);
		login.setString(2, password);
		ResultSet resultSet = login.executeQuery();
		if (!resultSet.next())
			throw new Exception("Username or password not vaild");
	}

	
	@Override
	public boolean usernameFree(String username) throws SQLException {
		usernameFree.setString(1, username);
		ResultSet resultSet = usernameFree.executeQuery();
		if (!resultSet.isBeforeFirst() ) { // checks if there result set contains anything
			return true; // resultSet does not contain rows
		} 
		return false;
	}

	
	@Override
	public Chat createPrivateChat(String username1, String username2) throws SQLException {
		getPrivateChat.setString(1, username1); 
		getPrivateChat.setString(2, username2); 
		ResultSet resultSet = getPrivateChat.executeQuery();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		users.add(username2);
		if (resultSet.next()) {
			return new Chat(resultSet.getInt("chat_id"), users); // chat already exists
			}
		createPrivateChat.setString(1, username1);
		createPrivateChat.setString(2, username2);
		createPrivateChat.executeUpdate();
		resultSet = getPrivateChat.executeQuery();
		resultSet.next();
		return new Chat(resultSet.getInt("chat_id"), users);
	}

	
	@Override
	public void savePrivateMessage(ChatMessage chatMessage) throws SQLException {
		savePrivateMessage.setInt(1, chatMessage.getID());
		savePrivateMessage.setTimestamp(2, new java.sql.Timestamp(chatMessage.getTimestamp().getTimeInMillis()));
		savePrivateMessage.setString(3, chatMessage.getUsername());
		savePrivateMessage.setString(4, chatMessage.getMessage());
		savePrivateMessage.executeUpdate();
		// set delivered to true for user who sent the message
		//setDeliveredPrivate(chatMessage.getID(), chatMessage.getUsername()); 
	}

	
	@Override
	public void setDeliveredPrivate(int chatID, String username) throws SQLException {
		/*
		 * Not implemented
		 */
		ArrayList<String> result = chatMember(chatID);
		if (result.get(0).equals(username)) { // user is user1
			setDeliveredPrivate.setString(1, "delivered1");
			setDeliveredPrivate.setInt(2, chatID);
		} else {
			setDeliveredPrivate.setString(1, "delivered2");
			setDeliveredPrivate.setInt(2, chatID);
		}
		setDeliveredPrivate.executeUpdate();
	}

	
	@Override
	public ArrayList<ChatMessage> getHistoryPrivate(int chatID) throws SQLException {
		ArrayList<ChatMessage> chatHistory = new ArrayList<ChatMessage>();
		getHistoryPrivate.setInt(1, chatID);
		ResultSet resultSet = getHistoryPrivate.executeQuery();
		while (resultSet.next()) {
			Calendar timestamp = Calendar.getInstance();
			timestamp.setTimeInMillis(resultSet.getTimestamp("message_sent").getTime());
			// creates new ChatMessage and adds to the chatHistory array
			chatHistory.add(new ChatMessage(resultSet.getInt("chat_id"), 
					resultSet.getString("user_posted"), timestamp, resultSet.getString("text")));
		}
		return chatHistory;
	}

	
	@Override
	public ArrayList<String> chatMember(int chatID) throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		chatMember.setInt(1, chatID);
		ResultSet resultSet = chatMember.executeQuery();
		while (resultSet.next()) {
			result.add(resultSet.getString("user1"));
			result.add(resultSet.getString("user2"));
		}
		return result;
	}

	
	@Override
	public void createAccount(Account account) throws SQLException {
		createAccount.setString(1, account.getUsername());
		createAccount.setString(2, account.getPassword());
		createAccount.setString(3, account.getEmail());
		createAccount.setString(4, account.getFirstname());
		createAccount.setString(5, account.getLastname());
		createAccount.setString(6, account.getLanguages());
		createAccount.setString(7, account.getLocation());
		createAccount.setString(8, account.getCompany());
		createAccount.setString(9, account.getJobTitle());
		createAccount.setString(10, account.getWebsite());
		createAccount.setString(11, account.getPersonalInfo());
		createAccount.executeUpdate();
	}


	@Override
	public Account getAccount(String username) throws SQLException {
		getAccount.setString(1, username);
		ResultSet resultSet = getAccount.executeQuery();
		if (resultSet.next()) {
			return new Account(resultSet.getString("username"), resultSet.getString("password"),
					resultSet.getString("email"), resultSet.getString("firstname"), resultSet.getString("lastname"),
					resultSet.getString("languages"), resultSet.getString("location"), resultSet.getString("company"),
					resultSet.getString("job_title"), resultSet.getString("website"), resultSet.getString("personal_info"));
		} else {
			return null;
		}
	}


	@Override
	public ArrayList<String> allUsers() throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		ResultSet resultSet = allUsers.executeQuery();
		while (resultSet.next()) {
			result.add(resultSet.getString("username"));
		}
		return result;
	}
	

	@Override
	public Chat createTeam(String teamName, ArrayList<String> usernames) throws SQLException, Exception {
		getTeamID.setString(1, teamName);
		ResultSet resultSet = getTeamID.executeQuery();
		if (resultSet.next()) // team name is already taken
			throw new Exception("Team name already taken");
		createTeamChat.setString(1, teamName);
		createTeamChat.executeUpdate();
		// put everything into a Chat object
		resultSet = getTeamID.executeQuery(); // this should now return a value
		resultSet.next();
		ArrayList<String> users = new ArrayList<String>();
		int teamID = resultSet.getInt("team_id");
		for (String user : usernames) {
			addUser(user, teamID);
		}
		Chat chat = new Chat(teamID, users, teamName);
		return chat;
	}


	@Override
	public void saveTeamMessage(ChatMessage chatMessage) throws SQLException {
		saveTeamMessage.setInt(1, chatMessage.getID());
		saveTeamMessage.setTimestamp(2, new java.sql.Timestamp(chatMessage.getTimestamp().getTimeInMillis()));
		saveTeamMessage.setString(3, chatMessage.getUsername());
		saveTeamMessage.setString(4, chatMessage.getMessage());
		saveTeamMessage.executeUpdate();
		// set delivered to true for user who sent the message
		//setDeliveredTeam(chatMessage.getID(), chatMessage.getUsername()); 
	}


	@Override
	public ArrayList<String> teamMembers(int teamID) throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		teamMembers.setInt(1, teamID);
		ResultSet resultSet = teamMembers.executeQuery();
		while (resultSet.next()) {
			result.add(resultSet.getString("username"));
		}
		return result;
	}


	@Override
	public ArrayList<ChatMessage> getHistoryTeam(int teamID) throws SQLException {
		ArrayList<ChatMessage> chatHistory = new ArrayList<ChatMessage>();
		getHistoryTeam.setInt(1, teamID);
		ResultSet resultSet = getHistoryTeam.executeQuery();
		while (resultSet.next()) {
			Calendar timestamp = Calendar.getInstance();
			timestamp.setTimeInMillis(resultSet.getTimestamp("message_sent").getTime());
			// creates new ChatMessage and adds to the chatHistory array
			chatHistory.add(new ChatMessage(resultSet.getInt("chat_id"), 
					resultSet.getString("user_posted"), timestamp, resultSet.getString("text")));
		}
		return chatHistory;
	}


	@Override
	public void addUser(String username, int teamID) throws SQLException {
		addUser.setInt(1, teamID);
		addUser.setString(2, username);
		addUser.executeUpdate();
	}

	
	@Override
	public void removeUser(String username, int teamID) throws SQLException {
		removeUser.setString(2, username);
		removeUser.setInt(1, teamID);
		removeUser.executeUpdate();		
	}

	
	@Override
	public ArrayList<Chat> getChats(String username) throws SQLException {
		ArrayList<Chat> result = new ArrayList<Chat>();
		getChats.setString(1, username);
		getChats.setString(2, username);
		ResultSet resultSet = getChats.executeQuery();
		while (resultSet.next()) {
			int chat_id = resultSet.getInt("chat_id");
			ArrayList<String> users = new ArrayList<String>();
			users.add(resultSet.getString("user1"));
			users.add(resultSet.getString("user2"));
			result.add(new Chat(chat_id, users));
		}
		return result;
	}

	
	@Override
	public ArrayList<Chat> getTeams(String username) throws SQLException {
		ArrayList<Chat> result = new ArrayList<Chat>();
		getTeams.setString(1, username);
		ResultSet resultSet = getTeams.executeQuery();
		while (resultSet.next()) {
			int chatID = resultSet.getInt("team_id");
			ArrayList<String> users = teamMembers(chatID);
			result.add(new Chat(chatID, users, resultSet.getString("team_name")));
		}
		return result;
	}

	
	@Override
	public void setDeliveredTeam(int teamID, String username) throws SQLException {
		/*
		 * Not implemented
		 */
	}
	
}