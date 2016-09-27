package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import general.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.ChatWindow;
import client.Client;

/**
 * These tests use a spy version of client and a dummy observer to check
 * that when client methods are called, both field variables and the
 * observer are updated as expected.
 * The server must be running with ClientTestDatabase to use these tests
 */
public class ClientTest {
	
	private TestClient client;
	private TestObserver testObserver;
	
	/**
	 * initialises client and testObserver
	 */
	@Before
	public void connect() {
		client = new TestClient("127.0.0.1", 8888);
		testObserver = new TestObserver(client);
	}
	
	/**
	 * stops testObserver, which releases resources in client
	 */
	@After
	public void disconnect() {
		testObserver.stop();
	}
	
	/**
	 * Login with valid username and password
	 * Expected behaviour: username is initialised
	 */
	@Test
	public void test01Login() {
		client.login("username","pass3word");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.getUsername().equals("username"));
	}
	
	/**
	 * Login with valid username and password
	 * Expected behaviour: allPrivateChats and allTeamChats are initialised
	 */
	@Test
	public void test02Login() {
		client.login("username","pass3word");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.getAllPrivateChats() != null && client.getAllTeamChats() != null);
	}
	
	/**
	 * Login with valid username and password
	 * Expected behaviour: observer updated with login and chat history messages
	 */
	@Test
	public void test03Login() {
		client.login("username","pass3word");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("logged in", "all private chats", "all team chats"));
	}
	
	/**
	 * Login with valid username and password
	 * Expected behaviour: observer updated with correct object types (String + 2x ArrayList)
	 */
	@Test
	public void test04Login() {
		client.login("username","pass3word");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(0).equals("username") &&
				testObserver.objects.get(1) instanceof ArrayList &&
				testObserver.objects.get(2) instanceof ArrayList);
	}
	
	/**
	 * Login with invalid password
	 * Expected behaviour: observer updated with error message
	 */
	@Test
	public void test05Login() {
		client.login("username","password");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("error"));
	}
	
	/**
	 * Login with invalid password
	 * Expected behaviour: observer with correct object type (String)
	 */
	@Test
	public void test06Login() {
		client.login("username","password");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(0) instanceof String);
	}
	
	/**
	 * Login with non-existent credentials
	 * Expected behaviour: observer updated with error message 
	 */
	@Test
	public void test07Login() {
		client.login("fail","pass3word");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("error"));
	}
	
	/**
	 * Login with non-existent credentials
	 * Expected behaviour: observer with correct object type (String)
	 */
	@Test
	public void test08Login() {
		client.login("fail","pass3word");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(0) instanceof String);
	}
	
	/**
	 * Create account with valid details
	 * Expected behaviour: username initialised
	 */
	@Test
	public void test09CreateAccount() {
		client.createAccount(new Account("username","pass3word", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.getUsername().equals("username"));
	}
	
	/**
	 * Create account with valid details
	 * Expected behaviour: allPrivateChats and all TeamChats initialised
	 */
	@Test
	public void test10CreateAccount() {
		client.createAccount(new Account("username","pass3word", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.getAllPrivateChats() != null && client.getAllTeamChats() != null);
	}
	
	
	/**
	 * Create account with valid details
	 * Expected behaviour: observer updated with account created and chat history
	 */
	@Test
	public void test11CreateAccount() {
		client.createAccount(new Account("username","pass3word", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("account created", "all private chats", "all team chats"));
	}
	
	/**
	 * Create account with valid details
	 * Expected behaviour: observer updated with correct object types (String + 2x ArrayList)
	 */
	@Test
	public void test12CreateAccount() {
		client.createAccount(new Account("username","pass3word", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(0).equals("username") &&
				testObserver.objects.get(1) instanceof ArrayList &&
				testObserver.objects.get(2) instanceof ArrayList);
	}
	
	/**
	 * Create account with invalid password
	 * Expected behaviour: observer updated with error message
	 */
	@Test
	public void test13CreateAccount() {
		client.createAccount(new Account("username","password", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("error"));
	}
	
	/**
	 * Create account with invalid password
	 * Expected behaviour: observer updated with String object
	 */
	@Test
	public void test14CreateAccount() {
		client.createAccount(new Account("username","password", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(0) instanceof String);
	}

	/**
	 * Create account but database error
	 * Expected behaviour: observer updated with error message
	 */
	@Test
	public void test15CreateAccount() {
		client.createAccount(new Account("fail","passw3ord", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("error"));
	}
	
	/**
	 * Create account but database error
	 * Expected behaviour: observer updated with String object
	 */
	@Test
	public void test16CreateAccount() {
		client.createAccount(new Account("fail","pass3word", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(0) instanceof String);
	}
	
	
	/**
	 * Start private chat successfully
	 * Expected behaviour: chat added to allPrivateChats
	 */
	@Test
	public void test17StartPrivateChat() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertEquals(1, client.getAllPrivateChats().size());
	}
	
	/**
	 * Start private chat successfully
	 * Expected behaviour: chat added to current chats
	 */
	@Test
	public void test18StartPrivateChat() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertEquals(1, client.getCurrentChats().size());
	}
	
	/**
	 * Start private chat but database error
	 * Expected behaviour: chat not added to current chats
	 */
	@Test
	public void test19StartPrivateChat() {
		client.login("username", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertEquals(0, client.getCurrentChats().size());
	}
	
	/**
	 * Start private chat successfully
	 * Expected behaviour: observer notified of new chat
	 */
	@Test
	public void test20StartPrivateChat() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("logged in", "all private chats", "all team chats", "start private chat"));
	}
	
	/**
	 * Start private chat successfully
	 * Expected behaviour: ChatWindow passed to observer
	 */
	@Test
	public void test21StartPrivateChat() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(3) instanceof ChatWindow);
	}
	
	
	/**
	 * Start private chat but database error
	 * Expected behaviour: observer updated with error message
	 */
	@Test
	public void test22StartPrivateChat() {
		client.login("username", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("logged in", "all private chats", "all team chats", "error"));
	}
	
	/**
	 * Start private chat but database error
	 * Expected behaviour: observer updated with String object
	 */
	@Test
	public void test23StartPrivateChat() {
		client.login("username", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(3) instanceof String);
	}
	
	
	/**
	 * Make team successfully
	 * Expected behaviour: chat added to allTeamChats
	 */
	@Test
	public void test24MakeTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertEquals(1, client.getAllTeamChats().size());
	}
	
	/**
	 * Make team successfully
	 * Expected behaviour: chat added to current chats
	 */
	@Test
	public void test25MakeTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertEquals(1, client.getCurrentChats().size());
	}
	
	/**
	 * Make team successfully
	 * Expected behaviour: observer updated with make team message
	 */
	@Test
	public void test26MakeTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("logged in", "all private chats", "all team chats", "make team"));
	}
	
	/**
	 * Make team successfully
	 * Expected behaviour: observer updated with chat window
	 */
	@Test
	public void test27MakeTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(3) instanceof ChatWindow);
	}
	
	/**
	 * Make team but database error
	 * Expected behaviour: chat not added to current chats
	 */
	@Test
	public void test28MakeTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("FAIL", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertEquals(0, client.getCurrentChats().size());
	}
	
	
	/**
	 * Make team but database error
	 * Expected behaviour: observer updated with error message
	 */
	@Test
	public void test29MakeTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("FAIL", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("logged in", "all private chats", "all team chats", "error"));
	}
	
	/**
	 * Make team but database error
	 * Expected behaviour: observer updated with String object
	 */
	@Test
	public void test30MakeTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("FAIL", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(3) instanceof String);
	}
	
	/**
	 * Increment thread counter by starting private and team chats
	 * Expected behaviour: thread counter incremented by 2 to 4
	 */
	@Test
	public void test31ThreadCounter() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team);
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertEquals(4, client.getThreadCounter());
	}
	
	/**
	 * Add team member successfully
	 * Expected behaviour: username added to list in chat
	 */
	@Test
	public void test32AddTeamMember() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team); //given teamID 2
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(
				new Message(Message.Status.ADD_TEAM_MEMBER, 0, 0, 
						new Chat(2, team, "teamname"), "username5"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		Chat chat = client.getAllTeamChats().get(2);
		assertTrue(chat.getUsernames().contains("username5"));
	}
	
	/**
	 * Add team member but already in the team
	 * Expected behaviour: size of username list in chat does not change
	 */
	@Test
	public void test33AddTeamMember() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team); //given teamID 2
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(new Message
				(Message.Status.ADD_TEAM_MEMBER, 0, 0, new Chat(2, team, "teamname"), "username3"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		Chat chat = client.getAllTeamChats().get(2);
		assertEquals(3, chat.getUsernames().size());
	}
	
	
	/**
	 * Remove team member successfully
	 * Expected behaviour: username no longer in list of usernames in chat
	 */
	@Test
	public void test34RemoveTeamMember() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team); //given teamID 2
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(new Message(Message.Status.REMOVE_TEAM_MEMBER, 0, 0, 2, "username4"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		Chat chat = client.getAllTeamChats().get(2);
		assertFalse(chat.getUsernames().contains("username4"));
	}
	
	/**
	 * Remove team member who isn't in team
	 * Expected behaviour: size of username list is still the same
	 */
	@Test
	public void test35RemoveTeamMember() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team); //given teamID 2
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(new Message(Message.Status.REMOVE_TEAM_MEMBER, 0, 0, 2, "5"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		Chat chat = client.getAllTeamChats().get(2);
		assertTrue(chat.getUsernames().size() == 3);
	}
	
	/**
	 * Join team successfully
	 * Expected behaviour: team added to allTeamChats
	 */
	@Test
	public void test36JoinTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		Chat chat = new Chat(2, team, "teamname");
		team.add("username1");
		team.add("username3");
		team.add("username4");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(new Message(Message.Status.JOIN_TEAM, 0, 0, chat));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertEquals(1, client.getAllTeamChats().size());
	}
	
	/**
	 * Join team successfully
	 * Expected behaviour: observer updated with make team message
	 */
	@Test
	public void test37JoinTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		Chat chat = new Chat(2, team, "teamname");
		team.add("username1");
		team.add("username3");
		team.add("username4");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(new Message(Message.Status.JOIN_TEAM, 0, 0, chat));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("logged in", "all private chats", "all team chats", "make team"));
	}
	
	/**
	 * Join chat successfully
	 * Expected behaviour: observer updated with chat window object
	 */
	@Test
	public void test38JoinTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		Chat chat = new Chat(2, team, "teamname");
		team.add("username1");
		team.add("username3");
		team.add("username4");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(new Message(Message.Status.JOIN_TEAM, 0, 0, chat));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(3) instanceof ChatWindow);
	}
	
	/**
	 * Leave team successfully
	 * Expected behaviour: chat removed from all team chats
	 */
	@Test
	public void test39LeaveTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(new Message(Message.Status.LEAVE_TEAM, 0, 0, 2));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertEquals(0, client.getAllTeamChats().size());
	}
	
	/**
	 * Leave team successfully
	 * Expected behaviour: observer updated with leave team message
	 */
	@Test
	public void test40LeaveTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(new Message(Message.Status.LEAVE_TEAM, 0, 0, 2));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("logged in", "all private chats", "all team chats", "make team", "leave team"));
	} 
	
	/**
	 * Leave team successfully
	 * Expected behaviour: observer updated with integer
	 */
	@Test
	public void test41LeaveTeam() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> team = new ArrayList<String>();
		team.add("username3");
		team.add("username4");
		client.makeTeam("teamname", team);
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.getIncomingMessages().add(new Message(Message.Status.LEAVE_TEAM, 0, 0, 2));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(4) instanceof Integer);
	} 
	
	/**
	 * Receive private message for chat without window open
	 * Expected behaviour: new chat window opened and added to current chats
	 */
	@Test
	public void test42ReceivePrivateMessage() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("username1");
		usernames.add("username2");
		client.getAllPrivateChats().put(1, new Chat(1, usernames));
		ChatMessage chatMessage = new ChatMessage(1, "username2", "hello");
		client.getIncomingMessages().add(new Message(Message.Status.RECEIVE_PRIVATE_MESSAGE, 0, 0, chatMessage));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.getCurrentChats().size() == 1);
	}
	
	/**
	 * Receive private message for chat without window open
	 * Expected behaviour: observer updated with start private chat message
	 */
	@Test
	public void test43ReceivePrivateMessage() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("username1");
		usernames.add("username2");
		client.getAllPrivateChats().put(1, new Chat(1, usernames));
		ChatMessage chatMessage = new ChatMessage(1, "username2", "hello");
		client.getIncomingMessages().add(new Message(Message.Status.RECEIVE_PRIVATE_MESSAGE, 0, 0, chatMessage));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("logged in", "all private chats", "all team chats", "start private chat"));
	}
	
	/**
	 * Receive private message for chat without window open
	 * Expected behaviour: observer updated with chat window object
	 */
	@Test
	public void test44ReceivePrivateMessage() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("username1");
		usernames.add("username2");
		client.getAllPrivateChats().put(1, new Chat(1, usernames));
		ChatMessage chatMessage = new ChatMessage(1, "username2", "hello");
		client.getIncomingMessages().add(new Message(Message.Status.RECEIVE_PRIVATE_MESSAGE, 0, 0, chatMessage));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(3) instanceof ChatWindow);
	}
	

	
	/**
	 * Receive team message for chat without window open
	 * Expected behaviour: chat window created and added to current chats
	 */
	@Test
	public void test45ReceiveTeamMessage() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("username1");
		usernames.add("username3");
		usernames.add("username4");
		client.getAllTeamChats().put(2, new Chat(2, usernames, "teamname"));
		ChatMessage chatMessage = new ChatMessage(2, "username3", "hello");
		client.getIncomingMessages().add(new Message(Message.Status.RECEIVE_TEAM_MESSAGE, 0, 0, chatMessage));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.getCurrentChats().size() == 1);
	}
	
	/**
	 * Receive team message for chat without window open
	 * Expected behaviour: observer updated with make team chat message
	 */
	@Test
	public void test46ReceiveTeamMessage() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("username1");
		usernames.add("username3");
		usernames.add("username4");
		client.getAllTeamChats().put(2, new Chat(2, usernames, "teamname"));
		ChatMessage chatMessage = new ChatMessage(2, "username3", "hello");
		client.getIncomingMessages().add(new Message(Message.Status.RECEIVE_TEAM_MESSAGE, 0, 0, chatMessage));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.contains("logged in", "all private chats", "all team chats", "make team"));
	}
	
	/**
	 * Receive team message for chat without window open
	 * Expected behaviour: observer updated with chat window object
	 */
	@Test
	public void test47ReceiveTeamMessage() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("username1");
		usernames.add("username3");
		usernames.add("username4");
		client.getAllTeamChats().put(2, new Chat(2, usernames, "teamname"));
		ChatMessage chatMessage = new ChatMessage(2, "username3", "hello");
		client.getIncomingMessages().add(new Message(Message.Status.RECEIVE_TEAM_MESSAGE, 0, 0, chatMessage));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(testObserver.objects.get(3) instanceof ChatWindow);
	}
	
	

}

