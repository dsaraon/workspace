package client;
/**
 * @author Jessica White
 * @author Divyjyot Saraon
 */

// This should do all it needs to do. I don't think it needs any editing from this point.
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import general.ChatMessage;
import general.Message;

public class OutgoingWriter implements Runnable {
	private ObjectOutputStream outputStream;
	private LinkedBlockingQueue<Message> messagesToSend;
	
	/**
	 * @param out - from server
	 * @param messagesToSend
	 */
	public OutgoingWriter(ObjectOutputStream out, LinkedBlockingQueue<Message> messagesToSend) {
		outputStream = out;
		this.messagesToSend = messagesToSend;
	}
	
	/**
	 * 
	 */
	public void run() {
		while(true){
			try{
				Message message = messagesToSend.take();
				outputStream.writeObject(message);
				outputStream.flush();
			} catch(InterruptedException e){
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}
