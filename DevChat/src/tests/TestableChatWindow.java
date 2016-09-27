package tests;

import general.Chat;
import general.Message;

import java.util.concurrent.LinkedBlockingQueue;

import client.ChatWindow;

public class TestableChatWindow extends ChatWindow {

	public TestableChatWindow(int threadID, String username, Chat chat,
			LinkedBlockingQueue<Message> out) {
		super(threadID, username, chat, out, null);
	}

	public LinkedBlockingQueue<Message> getIn(){
		return in;
	}
	
	public void testReceive() throws InterruptedException{
		receive();
	}
}