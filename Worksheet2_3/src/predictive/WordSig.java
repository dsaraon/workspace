package predictive;

public class WordSig implements Comparable<WordSig>{

	private String words;
	private String signature;
	
 
	public WordSig(String words){
		this.words = words;
		this.signature = wordToSignature(words); 
	}
	
	public WordSig(String words, String signature){
		this.words = words; 
		this.signature = signature;
	}
	
	
	public String getWords() {
		return words;
	}


	public void setWords(String words) {
		this.words = words;
	}


	public String getSignature() {
		return signature;
	}


	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * compareTo method compares two WordSig objects based on their 'signatures'
	 */
	@Override
	public int compareTo(WordSig o) {
		return this.signature.compareTo(o.getSignature());
	}
	
	/**
	 * Takes a word and returns it's numeric signature based on traditional mobile keypads
	 * Replaces non-alphabetic characters with a space - " "
	 * StringBuffer is used since it is more efficient to append characters to it.
	 *
	 * @param word the word to be converted
	 * @return the numeric signature of 'word'
	 */
	public static String wordToSignature(String word){
		StringBuffer w = new StringBuffer(word);
		StringBuffer signature = new StringBuffer();
		
		for(int i = 0; i < word.length(); i++){
			char  letter = Character.toLowerCase( w.charAt(i) );
			
			if(letter < 97 || letter > 122){
				signature.append(' ');
			}else if(letter >= 97 && letter <= 99){
				signature.append('2');
			}else if(letter >= 100 && letter <= 102){
				signature.append('3');
			}else if(letter >= 103 && letter <= 105){
				signature.append('4');
			}else if(letter >= 106 && letter <= 108){
				signature.append('5');
			}else if(letter >= 109 && letter <= 111){
				signature.append('6');
			}else if(letter >= 112 && letter <= 115){
				signature.append('7');
			}else if(letter >= 116 && letter <= 118){
				signature.append('8');
			}else{
				signature.append('9');
			}
		}	
		String s = signature.toString();
		return s; 
	}
	

	
}
