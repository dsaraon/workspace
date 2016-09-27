package client;

import general.ChatMessage;

/**
 * The following methods can be called by the chat window GUIs to send requests to the server.
 * All methods are void as the server response is not immediate.
 * The documentation indicates how the notify observers method will be called.
 * These updates may also be caused by another using making the same request to the server.
 * In the case of an error, the following GUIMessage is sent:
 * 		status: ERROR, String {error message}
 *
 * An object observing ChatWindow can also be expected to be updated with the following GUIMessage
 * once it has been created:
 * 	-status: HISTORY, object: ArrayList&ltChatMessage&gt {chat history}
 * 
 */
public interface ChatWindowGUIProtocol extends Runnable {
	
	/**
	 * requests that a private message be sent
	 * 	-status: DISPLAY_MESSAGE, object: ChatMessage {message sent}
	 * @param chatMessage
	 */
	public void sendPrivateMessage(ChatMessage chatMessage);

	/**
	 * requests that a team message be sent
	 * 	-status: DISPLAY_TEAM_MESSAGE, object: ChatMessage {message sent}
	 * @param chatMessage
	 */
	public void sendTeamMessage(ChatMessage chatMessage);

	/**
	 * requests that a team member be added
	 *  -status: ADD_MEMBER, object: String {username added}
	 * @param username
	 */
	public void addTeamMember(String username);

	/**
	 * requests that a team member be removed
 	 *  -status: REMOVE_MEMBER, object: String {username added}
	 * @param username
	 */
	public void removeTeamMember(String username);

	/**
	 * checks continuously for incoming server messages and processes them
	 */
	@Override
	public void run();
}
