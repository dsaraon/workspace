package predictive;
/**
 * command-line program to execute the method wordToSignature in class PredictivePrototype
 * @author Divyjyot
 *
 */
public class Words2SigProto {
	
	public static void main(String[] args){
		
		for (String s: args){
			String s2 =  PredictivePrototype.wordToSignature(s);
			System.out.println(s + " : "  + s2);
		}
	}
}
