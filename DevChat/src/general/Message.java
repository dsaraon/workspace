package general;
import java.io.Serializable;

/**
 * 
 * @author Anna and Divyjyot
 * @version 27/02/2015
 *
 */
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static enum Status {LOGIN, CREATE_ACCOUNT, VIEW_ACCOUNT, USERS_ONLINE, ALL_USERS, START_PRIVATE_CHAT,
		MAKE_TEAM, VIEW_PRIVATE_HISTORY, VIEW_TEAM_HISTORY, SEND_PRIVATE_MESSAGE, SEND_TEAM_MESSAGE, ADD_USER,
		REMOVE_USER, GET_PRIVATE_CHATS, GET_TEAM_CHATS, LOGOUT, JOIN_CHAT, JOIN_TEAM, LEAVE_TEAM, RECEIVE_PRIVATE_MESSAGE,
		RECEIVE_TEAM_MESSAGE, ADD_TEAM_MEMBER, REMOVE_TEAM_MEMBER, SUCCESS, ERROR};

	private Status status;
	private int threadID;
	private int messageID;
	private Object[] objects;
	
	public Message (Status status, int threadID, int messageID, Object... objects) {
		this.status = status;
		this.threadID = threadID;
		this.messageID = messageID;
		this.objects = objects;
	}
	
	
	public Status getStatus() {
		return status;
	}
	
	public int getThreadID() {
		return threadID;
	}
	
	public int getMessageID() {
		return messageID;
	}
	
	public Object[] getObjects() {
		return objects;
	}
	
}