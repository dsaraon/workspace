package predictive;

/**
 * command-line program to execute the method signatureToWords in class ListDictionary
 *
 */
public class Sigs2WordsList {
	public static void main(String[] args){
			
			ListDictionary lD = new ListDictionary(); 
			
			for (String s: args){
				System.out.println(s + " " + lD.signatureToWords(s));
			}
			
	}	
}
