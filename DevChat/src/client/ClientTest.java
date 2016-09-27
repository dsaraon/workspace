package client;

import static org.junit.Assert.*;

import java.util.ArrayList;

import general.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.TestObserver;
/*
 * These tests check field variables in client
 * A dummy observer class will need to be created to check updates 
 */
public class ClientTest {
	
	private Client client;
	private TestObserver testObserver;
	
	@Before
	public void connect() {
		client = new Client("127.0.0.1", 8888);
		testObserver = new TestObserver(client);
		
	}
	
	@After
	public void disconnect() {
		testObserver.stop();
	}
	
	/**
	 * login- username not null
	 */
	@Test
	public void test01Login() {
		client.login("username","pass3word");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.username.equals("username"));
	}
	
	/**
	 * login- all chats not null
	 */
	@Test
	public void test02Login() {
		client.login("username","pass3word");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.allPrivateChats != null && client.allTeamChats != null);
	}
	
	/**
	 * 
	 */
	@Test
	public void test03CreateAccount() {
		client.createAccount(new Account("username","pass3word", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.username.equals("username"));
	}
	
	/**
	 * 
	 */
	@Test
	public void test04CreateAccount() {
		client.createAccount(new Account("username","pass3word", "email"));client.createAccount(new Account("username","pass3word", "email"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			//do nothing...?
		}
		assertTrue(client.allPrivateChats != null && client.allTeamChats != null);
	}
	
	/**
	 * 
	 */
	@Test
	public void test05StartPrivateChat() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.allPrivateChats.size() == 1);
	}
	
	/**
	 * 
	 */
	@Test
	public void test06StartPrivateChat() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.currentChats.size() == 1);
	}
	
	/**
	 * 
	 */
	@Test
	public void test07StartPrivateChat() {
		client.login("username", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		client.startPrivateChat("username2");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.currentChats.size() == 0);
	}
	
	/**
	 * 
	 */
	@Test
	public void test08MakeTeam() {
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
		assertTrue(client.allTeamChats.size() == 1);
	}
	
	/**
	 * 
	 */
	@Test
	public void test09MakeTeam() {
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
		assertTrue(client.currentChats.size() == 1);
	}
	
	/**
	 * db error
	 */
	@Test
	public void test10MakeTeam() {
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
		assertTrue(client.currentChats.size() == 0);
	}
	
	/**
	 * 
	 */
	@Test
	public void test11ThreadCounter() {
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
		assertTrue(client.currentChats.size() == 2);
	}
	
	/**
	 * 
	 */
	@Test
	public void test12AddTeamMember() {
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
		client.incomingMessages.add(new Message(Message.Status.ADD_TEAM_MEMBER, 0, 0, new Chat(2, team, "teamname"), "username5"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		Chat chat = client.allTeamChats.get(2);
		assertTrue(chat.getUsernames().contains("username5"));
	}
	
	/**
	 * member already in team
	 */
	@Test
	public void test13AddTeamMember() {
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
		client.incomingMessages.add(new Message(Message.Status.ADD_TEAM_MEMBER, 0, 0, new Chat(2, team, "teamname"), "username3"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		Chat chat = client.allTeamChats.get(2);
		assertTrue(chat.getUsernames().size() == 3);
	}
	
	/**
	 * 
	 */
	@Test
	public void test14RemoveTeamMember() {
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
		client.incomingMessages.add(new Message(Message.Status.REMOVE_TEAM_MEMBER, 0, 0, 2, "username4"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		Chat chat = client.allTeamChats.get(2);
		assertFalse(chat.getUsernames().contains("username4"));
	}
	
	/**
	 * member not in team
	 */
	@Test
	public void test15RemoveTeamMember() {
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
		client.incomingMessages.add(new Message(Message.Status.REMOVE_TEAM_MEMBER, 0, 0, 2, "5"));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		Chat chat = client.allTeamChats.get(2);
		assertTrue(chat.getUsernames().size() == 3);
	}
	
	/**
	 * 
	 */
	@Test
	public void test16JoinTeam() {
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
		client.incomingMessages.add(new Message(Message.Status.JOIN_TEAM, 0, 0, chat));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		Chat check = client.allTeamChats.get(2);
		assertTrue(check.getUsernames().size() == 3);
	}
	
	/**
	 * 
	 */
	@Test
	public void test17LeaveTeam() {
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
		client.incomingMessages.add(new Message(Message.Status.LEAVE_TEAM, 0, 0, 2));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.allTeamChats.size() == 0);
	}
	
	/**
	 * 
	 */
	@Test
	public void test18ReceivePrivateMessage() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("username1");
		usernames.add("username2");
		client.allPrivateChats.put(1, new Chat(1, usernames));
		ChatMessage chatMessage = new ChatMessage(1, "username2", "hello");
		client.incomingMessages.add(new Message(Message.Status.RECEIVE_PRIVATE_MESSAGE, 0, 0, chatMessage));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.currentChats.size() == 1);
	}
	
	/**
	 * 
	 */
	@Test
	public void test19ReceiveTeamMessage() {
		client.login("username1", "password1");
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("username1");
		usernames.add("username3");
		usernames.add("username4");
		client.allTeamChats.put(2, new Chat(2, usernames, "teamname"));
		ChatMessage chatMessage = new ChatMessage(2, "username3", "hello");
		client.incomingMessages.add(new Message(Message.Status.RECEIVE_TEAM_MESSAGE, 0, 0, chatMessage));
		try {
			Thread.sleep(500);
		} catch (Exception e) {}
		assertTrue(client.currentChats.size() == 1);
	}
	
	//test resume chats (but implement first)
}

