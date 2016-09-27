package Ex3;
import java.io.*;
import java.net.URL;

public class Ex3 {
	
	/**
	 * reads a text from URL and counts frequency of the occurrence of characters 
	 * occurrence count of characters a-z, ".", single space and no.of lines is stored in an array
	 * increases count of a-z alphabets by using ASCII numbers 
	 * 
	 * @param string the url to read the text from
	 * @return array with the occurrence count of the characters and no. of lines 
	 */
	public static long[] frequencyAnalysis(String string){
		long[] frequency = new long[29]; 
		
		try {
			// Create a URL for the desired page
			URL url = new URL(string);       
			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			char c;
			int i;
			int index;
			//reads one character at a time
			while( (i = in.read()) != -1){
				c = (char)(i);
				c = Character.toLowerCase(c);
				//index calculated to manipulate alphabet counts by ASCII numbers 
				index = ((int)c) - 97;
				//checks for alphabets, ignores other characters
				if (index >= 0 && index <= 25) frequency[index]++; 
				if(c == ' ') frequency[26]++ ; 
				if(c == '.') frequency[27]++ ; 
				if(c == '\n') frequency[28]++ ; 
			}
			in.close();
			}  catch (IOException e) {
			System.out.println("no access to URL: " + string);
			}
		return frequency;
		}
	
	//testing
	public static void main(String[] args) {
//		frequencyAnalysis("http://www.cs.bham.ac.uk/internal/courses/java/msc/handouts/exercises/DonQuixote.txt");
		frequencyAnalysis("http://www.cs.bham.ac.uk/~jxg856/Simple.txt");
	}
}
