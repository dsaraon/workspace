import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleHttpServer {
	
	private int port;
	private String rootDirectory;
	private ExecutorService executor;
	
	public SimpleHttpServer(String rootDirectory, int port) {
		this.rootDirectory = rootDirectory;
		this.port = port;
		this.executor = Executors.newFixedThreadPool(10);
	}

	/**
	 * #1. Create a server socket, listening on <port>
	 * #2. Use a loop to wait and accept new connections
	 * #3. When you accept a new connection, create a <handler> to handle it.
	 * #4. You are not required to consider how to stop the loop
	 */
	public void start() {
		ServerSocket serverSocket;

		// Create serverSocket listening on port
		try {
			serverSocket = new ServerSocket(this.port);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		while(true){
			try {
				Socket remote = serverSocket.accept();
				SimpleHttpHandler handler = new SimpleHttpHandler(this.rootDirectory, remote);
				executor.execute(handler);
			} catch (Exception e) {
				e.printStackTrace();
				executor.shutdown();
			}
		}
	}
	
	/*
	 * Parameters:
	 * <root directory> <port>
	 * 
	 * DO NOT Modify the main method
	 * 
	 */
	public static void main(String[] args){
		SimpleHttpServer server = new SimpleHttpServer(args[0], Integer.parseInt(args[1]));
		server.start();
	}

}
