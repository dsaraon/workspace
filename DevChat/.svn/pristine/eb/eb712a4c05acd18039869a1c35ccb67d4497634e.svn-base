package server;
import general.Message;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;


public class Connection {
	
	private Socket socket;
	private Server server;
	private int sessionID;
	private LinkedBlockingQueue<Message> queue;
	private Protocol protocol;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Thread listener;
	private Thread writer;
	private boolean stop;
	
	public Connection(Server server, Socket socket, int sessionID) throws IOException {
		this.socket = socket;
		this.server = server;
		this.sessionID = sessionID;
		this.queue = new LinkedBlockingQueue<Message>();
		this.protocol = new Protocol(sessionID, server);

		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.out.flush();
		this.in = new ObjectInputStream(socket.getInputStream());
	
		this.listener = new Thread(new Listener());
		this.writer = new Thread(new Writer());
		this.listener.start();
		this.writer.start();
	}
	
	
	protected void sendMessage(Message message) {//is the queue already synchronised?
		queue.add(message);
	}
	
	
	protected void disconnect(){
		//current risk that messages will considered delivered when actually they never make it
		stop();
		try {
			in.close();
			out.close();
			socket.close();
			server.removeConnection(sessionID);
		} catch (IOException e) {
			server.removeConnection(sessionID);
		}

		
	}
	

	protected void stop() {
		stop = true;
	}
	
	class Listener implements Runnable {
		
		@Override
		public void run() {
			while(!stop) {
				try {
					Message message = (Message)in.readObject();
					if (message.getStatus() != Message.Status.SUCCESS && message.getStatus() != Message.Status.ERROR) {
						Message response = protocol.processMessage(message);
						sendMessage(response);
					}
				} catch (ClassNotFoundException| IOException e) {
					disconnect();
				}
	
			}
		}
		
	}
	
	class Writer implements Runnable {

		@Override
		public void run() {
			while(!stop) {
				try {
					out.writeObject(queue.take());
					out.flush();
				} catch (IOException | InterruptedException e) {
					disconnect();
				}
			}
			
		}	

	}

}
