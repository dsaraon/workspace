package tests;
/** Tests for all ChatWindow methods relating to team chat.
 *  @author Jessica White */
import static org.junit.Assert.*;
import general.Chat;
import general.ChatMessage;
import general.Message;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/* Test name format => Given-When-Then
 * TODO: ID in Chat could be made alterable but currently hard coded to 1
 * TODO: Can add tests for:
 * 							- removing a username not in chat
 *  						- adding a username already present
 *   						- don't know whether to add ones to test threads
 */
public class ChatWindowTeamChatTests {

		//Testnames => Given-When-Then
	private TestableChatWindow testWindow;
	private String username = "TEST USERNAME";
	private ChatMessage chatMessage; // for methods receiving ChatMessage
	private LinkedBlockingQueue<Message> out;
	private Chat chat;
	private ArrayList<String> usernames;
	private TestObserver testObserver;
	private int threadID;

	@Before
	public void start(){
		out = new LinkedBlockingQueue<Message>();
		usernames = new ArrayList<String>();
		chat = new Chat(1, usernames);
		threadID = 23;
		testWindow = new TestableChatWindow(threadID, username, chat, out);
		testObserver = new TestObserver(testWindow);
	}
	
	@After
	public void stop(){
		testObserver.stop();
	}
	
	@Test
	public void getTeamHistory_success_GUIMessageHistory() throws InterruptedException {
		testWindow.getTeamHistory();	
		Object[] resultObjects = new Object[] { new ArrayList<ChatMessage>() };
		testWindow.getIn().put(new Message(Message.Status.SUCCESS, threadID, 1, resultObjects));
		testWindow.testReceive();
		assertTrue(testObserver.result.contains("history"));
	}

	@Test
	public void getTeamHistory_fail_GUIMessageError() throws InterruptedException {
		testWindow.getTeamHistory();
		Object[] resultObjects = new Object[] { new ArrayList<ChatMessage>() };
		testWindow.getIn().put(new Message(Message.Status.ERROR, threadID, 1, resultObjects));
		testWindow.testReceive();
		assertTrue(testObserver.result.contains("error"));
	}

	//no response to success for send team message
	@Test
	public void sendTeamMessage_success_noGUIMessageError() {
		ChatMessage chatmessage = new ChatMessage(1, username, "hello");
		testWindow.sendTeamMessage(chatmessage);		
		assertFalse(testObserver.result.contains("error"));
	}
	
	@Test
	public void sendTeamMessage_fail_GUIMessageError() throws InterruptedException {
		ChatMessage chatmessage = new ChatMessage(1, username, "hello");
		testWindow.sendTeamMessage(chatmessage);		
		Object[] resultObjects = new Object[] { new ArrayList<ChatMessage>() };
		testWindow.getIn().put(new Message(Message.Status.ERROR, threadID, 1, resultObjects));
		testWindow.testReceive();
		assertTrue(testObserver.result.contains("error"));
	}

	@Test
	public void addTeamMember_success_GUIMessageAddMember() throws InterruptedException {
		testWindow.addTeamMember(username);
		Object[] resultObjects = new Object[] { username, chat };
		testWindow.getIn().put(new Message(Message.Status.SUCCESS, threadID, 1, resultObjects));
		testWindow.testReceive();
		assertTrue(testObserver.result.contains("add member"));
	}

	@Test
	public void addTeamMember_fail_GUIMessageError() throws InterruptedException {
		testWindow.addTeamMember(username);
		Object[] resultObjects = new Object[] { username, chat };
		testWindow.getIn().put(new Message(Message.Status.ERROR, threadID, 1, resultObjects));
		testWindow.testReceive();
		assertTrue(testObserver.result.contains("error"));
	}

	@Test
	public void removeTeamMember_success_GUIMessageRemoveMember() throws InterruptedException {
		testWindow.removeTeamMember(username);
		Object[] resultObjects = new Object[] { username, chat.getID() };
		testWindow.getIn().put(new Message(Message.Status.SUCCESS, threadID, 1, resultObjects));
		testWindow.testReceive();
		assertTrue(testObserver.result.contains("remove member"));
	}

	@Test
	public void removeTeamMember_fail_GUIMessageError() throws InterruptedException {
		testWindow.removeTeamMember(username);
		Object[] resultObjects = new Object[] { username, chat.getID() };
		testWindow.getIn().put(new Message(Message.Status.ERROR, threadID, 1, resultObjects));
		testWindow.testReceive();
		assertTrue(testObserver.result.contains("error"));
	}
}