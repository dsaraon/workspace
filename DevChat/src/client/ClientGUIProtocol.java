package client;

import general.Account;
import general.Chat;

import java.util.ArrayList;

/**
 * The following methods can be called by the client GUI to send requests to the server.
 * All methods are void as the server response is not immediate.
 * The documentation indicates how the notify observers method will be called.
 * Where an error response is not specified, the following GUIMessage is sent:
 * 		status: ERROR, String {error message}
 *
 */
public interface ClientGUIProtocol extends Runnable {
	
	/**
	 * logs a user into the system
	 * GUIMessage:
	 * 	-status: LOGGED_IN, object: String {username}
	 * 	-status: LOGIN_FAIL, object: String {error message}
	 * @param username
	 * @param password
	 */
	public void login(String username, String password);

	/**
	 * creates an account and logs the user in
	 * 	-status: ACCOUNT_CREATED, object: String {username}
	 * 	-status: ACCOUNT_CREATED_FAIL, object: String {error message}
	 * @param account
	 */
	public void createAccount(Account account);

	/**
	 * requests the account belonging to username
	 * 	-status: VIEW_ACCOUNT, object: Account {account requested}
	 * @param username
	 */
	public void viewAccount(String username);

	/**
	 * requests a list of all users online
	 *  -status: USERS_ONLINE, object: ArrayList&ltString&gt {usernames online}
	 */
	public void usersOnline();

	/**
	 * requests a list of all user registered with the system
	 *  -status: ALL_USERS, object: ArrayList&ltString&gt {all usernames}
	 */
	public void allUsers();

	/**
	 * requests a new private chat with username specified
	 *  -status: START_PRIVATE_CHAT, object: ChatWindow {chat window to run}
	 * @param username
	 */
	public void startPrivateChat(String username);

	/**
	 * requests a new team chat with the teamname and usernames specified
	 *  -status: MAKE_TEAM, object: ChatWindow {chat window to run}
	 * @param teamname
	 * @param usernames
	 */
	public void makeTeam(String teamname, ArrayList<String> usernames);

	/**
	 * requests to resume an already begun private chat
	 *  -status: START_PRIVATE_CHAT, object: ChatWindow {chat window to run}
	 * @param chat
	 */
	public void resumePrivateChat(Chat chat);

	/**
	 * requests to resume an already begun team chat
	 *  -status: MAKE_TEAM, object: ChatWindow {chat window to run}
	 * @param chat
	 */
	void resumeTeamChat(Chat chat);
	
	/**
	 * requests a list of private chats the username is part of
	 *  -status: ALL_PRIVATE_CHATS, object: ArrayList&ltChat&gt {all private chats}
	 * @param username
	 */
	void getPrivateChats(String username);
	
	/**
	 * requests a list of team chats the username is part of
	 *  -status: ALL_TEAM_CHATS, object: ArrayList&ltChat&gt {all team chats}
	 * @param username
	 */
	void getTeamChats(String username);

	/**
	 * requests to be logged out of the system
	 *  -status: LOGOUT,  object: null
	 */
	public void logout();
	
	/**
	 * Checks continually for incoming server messages and processes them.
	 * Reestablishes lost connections to the server.
	 */
	@Override
	public void run();
	

}
