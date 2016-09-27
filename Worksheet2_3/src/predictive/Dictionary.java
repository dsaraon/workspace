package predictive;

import java.util.Set;

public interface Dictionary {
	
	/**
	 * This method accepts a string of numbers - 'signature'
	 * Returns a set of words that have the same numeric signature as 'signature'
	 * 
	 * @param signature signature numeric signature to find possible matching words
	 * @return set of words matching the numeric 'signature'
	 */
	public Set<String> signatureToWords(String signature);
	
	
	/**
	 * Takes a word and returns it's numeric signature based on traditional mobile keypads
	 * Replaces non-alphabetic characters with a space - " "
	 * StringBuffer is used since it is more efficient to append characters to it.
	 *
	 * @param word the word to be converted
	 * @return the numeric signature of 'word'
	 */
	public String wordToSignature(String word);
	
}
