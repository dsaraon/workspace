package predictive;
/**
 * command-line program to execute the method signatureToWords in class TreeDictionary
 *
 */
public class Sigs2WordsTree {
	
	public static void main(String[] args){
		
		TreeDictionary dict = new TreeDictionary("/usr/share/dict/words");
		
		for (String s: args){
			System.out.println(s + " " + dict.signatureToWords(s));
		}
	}
}
