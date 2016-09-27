import java.io.*;
import java.net.*;
 
public class Client {
	
	public  void start(){
		 try {
			 System.out.println("start");
			Socket kkSocket = new Socket("Macneto", 4444);
			PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
			out.write("GET /handbook.pdf HTTP/1.0");
		} catch (IOException e) {
			System.out.println("Exception");
		}
	 }
}