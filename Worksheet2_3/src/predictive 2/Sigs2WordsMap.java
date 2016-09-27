package predictive;
/**
 * command-line program to execute the method signatureToWords in class MapDictionary
 *
 */
public class Sigs2WordsMap {
	public static void main(String[] args){
			
			Dictionary dict = new MapDictionary("/usr/share/dict/words"); 
			
			
			for (String s: args){
				System.out.println(s + " " + dict.signatureToWords(s));
			}
			
	}	
}
