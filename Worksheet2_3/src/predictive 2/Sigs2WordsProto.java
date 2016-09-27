package predictive;
/**
 * command-line program to execute the method signatureToWords in class PredictivePrototype
 *
 */
public class Sigs2WordsProto {
	public static void main(String[] args){
			
			for (String s: args){
				System.out.println( s + " " + PredictivePrototype.signatureToWords(s));
			}
		}
}
