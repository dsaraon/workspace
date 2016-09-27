package client;
/**
 * @author Jessica White
 * @author Divyjyot Saraon
 */

// This shouldn't need to be touched now. Should be doing all it needs to do.
import general.Message;

import java.io.ObjectInputStream;
import java.util.concurrent.LinkedBlockingQueue;


public class IncomingReader implements Runnable {
	private ObjectInputStream inputStream;
	private LinkedBlockingQueue<Message> incomingMessages;
	
	/**
	 * 
	 * @param in - to server
	 * @param incomingMessages
	 */
	public IncomingReader(ObjectInputStream in, LinkedBlockingQueue<Message> incomingMessages) {
		inputStream = in;
		this.incomingMessages = incomingMessages;
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		try{
			Message message;
			while((message = (Message)inputStream.readObject()) != null){
				incomingMessages.add(message);
				System.out.println("got a message!: " + message.toString());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
