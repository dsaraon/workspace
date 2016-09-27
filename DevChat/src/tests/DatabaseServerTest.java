package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import general.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for server/database communication. The database must be cleared 
 * each time the tests are run.
 */
public class DatabaseServerTest {
	

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	@Before
	public void initialise() throws IOException {
		socket = new Socket("127.0.0.1", 8888);
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	@After
	public void finish() throws IOException {
		in.close();
		out.close();
		socket.close();
	}
	
	/**
	 * Adds a user account to the database
	 * Expected response: SUCCESS
	 * Expected information in database: Account created in user table
	 */
	@Test
	public void createAccountSuccess1() {
		try {
			out.writeObject(new Message(Message.Status.CREATE_ACCOUNT, 1, 0, new Account("user1", "password", "email@email.com")));
			Message message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.SUCCESS);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Adds a user account to the database
	 * Expected response: SUCCESS
	 * Expected information in database: Account created in user table
	 */
	@Test
	public void createAccountSuccess2() {
		try {
			out.writeObject(new Message(Message.Status.CREATE_ACCOUNT, 1, 1, 
					new Account("user2", "password", "email@email.com")));
			Message message = (Message)in.readObject();
			System.out.println(message.getStatus());
			assertTrue(message.getStatus() == Message.Status.SUCCESS);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Attempts to add user to database where username is already taken
	 * Expected response: ERROR
	 * Expected information in database: No information stored
	 */
	@Test
	public void createAccountFail() {
		try {
			out.writeObject(new Message(Message.Status.CREATE_ACCOUNT, 1, 1, 
					new Account("user2", "password", "email@email.com")));
			Message message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.ERROR);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Logs in user by querying database
	 * Expected response: SUCCESS
	 * Expected information in database: n/a
	 */
	@Test
	public void loginSuccess() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 2, "user1", "password"));
			Message message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.SUCCESS);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Log in fails as user does not exist
	 * Expected response: ERROR
	 * Expected information in database: n/a
	 */
	@Test
	public void loginFail1() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 3, "user3", "password"));
			Message message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.ERROR);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Log in fails as password in database does not match
	 * Expected response: ERROR
	 * Expected information in database: n/a
	 */
	@Test
	public void loginFail2() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 4, "user1", "password1"));
			Message message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.ERROR);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Obtains account information from the database
	 * Expected response: SUCCESS & Account object
	 * Expected information in database: n/a
	 */
	@Test
	public void viewAccount01() {
		try {
			out.writeObject(new Message(Message.Status.VIEW_ACCOUNT, 1, 5, "user1"));
			Message message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.SUCCESS && 
					message.getObjects()[0] instanceof Account);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Checks account information obtain from the database is correct
	 * Expected response: correct username & email
	 * Expected information in database: n/a
	 */
	@Test
	public void viewAccount02() {
		try {
			out.writeObject(new Message(Message.Status.VIEW_ACCOUNT, 1, 6, "user1"));
			Message message = (Message)in.readObject();
			Account account = (Account)message.getObjects()[0];
			assertTrue(account.getUsername().equals("user1") &&
					account.getEmail().equals("email@email.com"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Obtains list of all users in database
	 * Expected response: ArrayList users contains users and is correct size
	 * Expected information in database: n/a
	 */
	@Test
	public void allUsers() {
		try {
			out.writeObject(new Message(Message.Status.ALL_USERS, 1, 7));
			Message message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			ArrayList<String> users = (ArrayList<String>)message.getObjects()[0];
			assertTrue(users.size() == 2 && users.contains("user1") && users.contains("user2"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Starts private chat between user1 and user2
	 * Expected response: SUCCESS & Chat object
	 * Expected information in database: Chat logged in database with a unique chat ID
	 */
	@Test
	public void startPrivateChat1() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 8, "user1", "password"));
			Message message = (Message)in.readObject();
			System.out.println(message.getStatus());
			out.writeObject(new Message(Message.Status.START_PRIVATE_CHAT, 1, 9, "user2"));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.SUCCESS && 
					message.getObjects()[0] instanceof Chat);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Checks that a private chat between two users is given its assigned chat ID if a chat
	 * between them already exists, rather than generating a new one
	 * Expected response: ChatID equals ID in the database
	 * Expected information in database: No new chat logged in database
	 */
	@Test
	public void startPrivateChat2() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 8, "user1", "password"));
			Message message = (Message)in.readObject();
			System.out.println(message.getStatus());
			out.writeObject(new Message(Message.Status.START_PRIVATE_CHAT, 1, 9, "user2"));
			message = (Message)in.readObject();
			Chat chat = (Chat)message.getObjects()[0];
			assertTrue(chat.getID() == 1);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Gets list of private chats for user1
	 * Expected response: SUCCESS & ArrayList
	 * Expected information in database: n/a
	 */
	@Test
	public void getPrivateChats1() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 10, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_PRIVATE_CHATS, 1, 11));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.SUCCESS && 
					message.getObjects()[0] instanceof ArrayList);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Checks that the returned private chats are correct 
	 * Expected response: ArrayList of Chats only contains the one chat with user2
	 * Expected information in database: n/a
	 */
	@Test
	public void getPrivateChats2() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 12, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_PRIVATE_CHATS, 1, 13));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			ArrayList<Chat> chats = (ArrayList<Chat>)message.getObjects()[0];
			assertTrue(chats.size() == 1 && chats.get(0).getUsernames().contains("user2"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Checks that the returned private chats are correct 
	 * Expected response: RECEIVE_PRIVATE_MESSAGE & message text and senders username are correct
	 * Expected information in database: Message, stored correctly
	 */
	@Test
	public void sendPrivateMessages() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 14, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_PRIVATE_CHATS, 1, 15));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			Chat chat = ((ArrayList<Chat>)message.getObjects()[0]).get(0);
			out.writeObject(new Message(Message.Status.SEND_PRIVATE_MESSAGE, 1, 16, new ChatMessage(chat.getID(), "user1", "hello" )));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.RECEIVE_PRIVATE_MESSAGE &&
					((ChatMessage)message.getObjects()[0]).getMessage().equals("hello") &&
					((ChatMessage)message.getObjects()[0]).getUsername().equals("user1"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Obtains chat history for user1
	 * Expected response: SUCCESS & ArrayList
	 * Expected information in database: n/a
	 */
	@Test
	public void viewPrivateChatHistory1() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 16, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_PRIVATE_CHATS, 1, 17));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			Chat chat = ((ArrayList<Chat>)message.getObjects()[0]).get(0);
			out.writeObject(new Message(Message.Status.VIEW_PRIVATE_HISTORY, 1, 18, chat.getID()));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.SUCCESS &&
					message.getObjects()[0] instanceof ArrayList);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Checks obtained chat history contains the message from user1 reading "hello"
	 * Expected response: ChatMessage object
	 * Expected information in database: n/a
	 */
	@Test
	public void viewPrivateChatHistory2() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 18, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_PRIVATE_CHATS, 1, 19));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			Chat chat = ((ArrayList<Chat>)message.getObjects()[0]).get(0);
			out.writeObject(new Message(Message.Status.VIEW_PRIVATE_HISTORY, 1, 20, chat.getID()));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			ChatMessage chatMessage = ((ArrayList<ChatMessage>)message.getObjects()[0]).get(0);
			assertTrue(chatMessage.getUsername().equals("user1") && chatMessage.getMessage().equals("hello"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	

	/**
	 * Creates a new team with user1 and user2
	 * Expected response: SUCCESS & Chat object
	 * Expected information in database: Team is stored in team and team_member tables
	 */
	@Test
	public void makeTeam() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 21, "user1", "password"));
			Message message = (Message)in.readObject();
			ArrayList<String> names = new ArrayList<String>();
			names.add("user2");
			out.writeObject(new Message(Message.Status.MAKE_TEAM, 1, 22, names, "team"));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.SUCCESS &&
					message.getObjects()[0] instanceof Chat);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Obtains team chat history for user1
	 * Expected response: SUCCESS & ArrayList
	 * Expected information in database: n/a
	 */
	@Test
	public void getTeamChats1() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 23, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_TEAM_CHATS, 1, 24));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.SUCCESS && 
					message.getObjects()[0] instanceof ArrayList);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Checks that the returned team chats are correct 
	 * Expected response: ArrayList of Chats only contains the one chat with user2
	 * Expected information in database: n/a
	 */
	@Test
	public void getTeamChats2() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 25, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_TEAM_CHATS, 1, 26));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			ArrayList<Chat> chats = (ArrayList<Chat>)message.getObjects()[0];
			assertTrue(chats.size() == 1 && chats.get(0).getUsernames().contains("user2") 
					&& chats.get(0).getTeamname().equals("team"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Checks that team messages are sent correctly
	 * Expected response: RECEIVE_TEAM_MESSAGE & message text and sender's username are correct
	 * Expected information in database: Message, stored correctly under the correct team ID
	 */
	@Test
	public void sendTeamMessage() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 27, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_TEAM_CHATS, 1, 28));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			Chat chat = ((ArrayList<Chat>)message.getObjects()[0]).get(0);
			out.writeObject(new Message(Message.Status.SEND_TEAM_MESSAGE, 1, 29, new ChatMessage(chat.getID(), "user1", "hello" )));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.RECEIVE_TEAM_MESSAGE &&
					((ChatMessage)message.getObjects()[0]).getMessage().equals("hello") &&
					((ChatMessage)message.getObjects()[0]).getUsername().equals("user1"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Obtains team chat history for user1
	 * Expected response: SUCCESS & ArrayList
	 * Expected information in database: n/a
	 */
	@Test
	public void viewTeamChatHistory1() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 30, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_TEAM_CHATS, 1, 31));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			Chat chat = ((ArrayList<Chat>)message.getObjects()[0]).get(0);
			out.writeObject(new Message(Message.Status.VIEW_TEAM_HISTORY, 1, 32, chat.getID()));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.SUCCESS &&
					message.getObjects()[0] instanceof ArrayList);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Checks obtained chat history contains the message from user1 reading "hello"
	 * Expected response: ChatMessage object
	 * Expected information in database: n/a
	 */
	@Test
	public void viewTeamChatHistory2() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 33, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_TEAM_CHATS, 1, 34));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			Chat chat = ((ArrayList<Chat>)message.getObjects()[0]).get(0);
			out.writeObject(new Message(Message.Status.VIEW_TEAM_HISTORY, 1, 35, chat.getID()));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			ChatMessage chatMessage = ((ArrayList<ChatMessage>)message.getObjects()[0]).get(0);
			assertTrue(chatMessage.getUsername().equals("user1") && chatMessage.getMessage().equals("hello"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Adds another user account to the database for testing
	 */
	@Test
	public void createAccount() {
		try {
			out.writeObject(new Message(Message.Status.CREATE_ACCOUNT, 1, 36, new Account("user3", "password", "email@email.com")));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * user1 adds user3 to team chat
	 * Expected response: SUCCESS
	 * Expected information in database: Account created in user table
	 */
	@Test
	public void addTeamMember() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 37, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_TEAM_CHATS, 1, 38));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			Chat chat = ((ArrayList<Chat>)message.getObjects()[0]).get(0);
			out.writeObject(new Message(Message.Status.ADD_USER, 1, 39, "user3", chat));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.ADD_TEAM_MEMBER &&
					(int)message.getObjects()[0] == chat.getID() &&
					((String)message.getObjects()[1]).equals("user3"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Checks that team chat history is retrieved correctly and user3 can view all previous messages
	 * Expected response: ChatMessage contains the message from user1 reading "hello"
	 * Expected information in database: n/a
	 */
	@Test
	public void getTeamHistory() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 40, "user3", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_TEAM_CHATS, 1, 41));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			Chat chat = ((ArrayList<Chat>)message.getObjects()[0]).get(0);
			out.writeObject(new Message(Message.Status.VIEW_TEAM_HISTORY, 1, 42, chat.getID()));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			ChatMessage chatMessage = ((ArrayList<ChatMessage>)message.getObjects()[0]).get(0);
			assertTrue(chatMessage.getUsername().equals("user1") && chatMessage.getMessage().equals("hello"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Adds a user account to the database
	 * Expected response: REMOVE_TEAM_MEMBER & returned message contains correct information
	 * Expected information in database: user3 is removed from team_members
	 */
	@Test
	public void removeUser() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 43, "user1", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_TEAM_CHATS, 1, 44));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			Chat chat = ((ArrayList<Chat>)message.getObjects()[0]).get(0);
			out.writeObject(new Message(Message.Status.REMOVE_USER, 1, 45, "user3", chat.getID()));
			message = (Message)in.readObject();
			assertTrue(message.getStatus() == Message.Status.REMOVE_TEAM_MEMBER &&
					(int)message.getObjects()[0] == chat.getID() &&
					((String)message.getObjects()[1]).equals("user3"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * user3 can no longer see the team chat they were previously part of
	 * Expected response: ArrayList of Chat has no elements
	 * Expected information in database: n/a
	 */
	@Test
	public void getTeamsNotInChat() {
		try {
			out.writeObject(new Message(Message.Status.LOGIN, 1, 46, "user3", "password"));
			Message message = (Message)in.readObject();
			out.writeObject(new Message(Message.Status.GET_TEAM_CHATS, 1, 47));
			message = (Message)in.readObject();
			@SuppressWarnings("unchecked")
			ArrayList<Chat> chats = (ArrayList<Chat>)message.getObjects()[0];
			assertTrue(chats.size() == 0);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}	
}
