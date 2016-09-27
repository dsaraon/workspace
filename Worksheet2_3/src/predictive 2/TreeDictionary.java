package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 * 	This is a tree data structure to insert words from a dictionary file.
 * 	The field variable 'sig' contains a certain numeric signature corresponding to certain words   
 *	The field variable 'wordSet' contains all words that correspond to the signature 'sig'
 *	The field variable 'array' contains an array of 8 TreeDictionary objects
 *	The field variable 'empty' specifies if the object is empty
 */
public class TreeDictionary implements Dictionary	{

	private String sig;
	private Set<String> wordSet = new HashSet<String>();
	private TreeDictionary[] array = new TreeDictionary[8];
	private boolean empty;
	
	public TreeDictionary(){
		this.empty = true;
		this.sig = "";
	}
	
	/**
	 * Constructor reads dictionary file into the tree data structure word by word
	 * the tree is constructed according to the file
	 * root node has null 'signature' an 'wordSet'
	 * 
	 * @param tD source path of the file containing the dictionary 
	 */
	public TreeDictionary(String tD){
		this.empty = false;
		this.sig = "";
		this.wordSet = Collections.emptySet();
		
		try {
			File dict = new File(tD);
			Scanner s = new  Scanner(dict);
			while(s.hasNextLine()){
				String word = s.nextLine().toLowerCase();
				
				if(isValidWord(word)){
					String sig = this.wordToSignature(word);				
					int index =  sig.charAt(0) - '0' - 2;
					int limit = sig.length() -1;
					if(PredictivePrototype.isValidWord(word)){
						insert(this, index, sig, word, limit, 0);
					}
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Helper method for constructing tree 
	 * @param t the tree being constructed 
	 * @param index indicates which array index to go to based on the digit in the signature
	 * @param sig the signature 
	 * @param word the word to be inserted
	 * @param limit the limit of the substring which increases till the length of the signature
	 * allows to create nodes based on signature prefix 
	 * @param i allows to calculate 'index' based on individual characters in 'sig' 
	 */
	public  static void insert(TreeDictionary t, int index, String sig, String word, int limit, int i){
		
		String sub = sig.substring(0, sig.length() -limit);
		//if array element isn't null
		if(  (t.array[index] !=null )){ 
			//check for signature match
			if(sig.equals(t.array[index].getSig())){
				t.array[index].getWordSet().add(word);
			}
			//else - move further down the tree
			else{
				i++;
				limit--;
				int newIndex = sig.charAt(i)- '0' -2 ;
				insert(t.array[index], newIndex, sig,  word, limit, i);
			}
		}
		// if array doesn't exist - construct tree
		else{
			t.array[index] = new TreeDictionary();
			t.array[index].setEmpty(false);
			t.array[index].setSig(sub);
			limit--;
			i++;
			
			//not final
			if(limit >= 0){
				int newIndex = sig.charAt(i)- '0' -2 ;
				insert(t.array[index], newIndex, sig,  word, limit, i);
			}else{ //final node 
				 t.array[index].getWordSet().add(word);
			}
		}	
	}	
	
	public String getSig() {
		return this.sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public Set<String> getWordSet() {
		return this.wordSet;
	}

	public boolean isEmpty() {
		return this.empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	
	/**
	 * This method accepts a string of numbers - 'signature'
	 * Returns a set of words that have the same numeric signature as 'signature'
	 * Also returns all other whose numeric signatures may have 'signature' as their prefix 
	 * 
	 * @param signature signature numeric signature to find possible matching words
	 * @return set of words matching the numeric 'signature' or words having it in their signature prefix
	 */
	@Override
	public Set<String> signatureToWords(String signature) {
		int i = 0;
		int index = signature.charAt(i)- '0' -2;
		
		Set<String> wordSet2 = new HashSet<String>();
		
		if(this == null || this.isEmpty()){
			return wordSet2;
		}
		wordSet2 = sig2WordHelper(this, signature, index, i, wordSet2);
		
		return wordSet2;
	}
	
	/**
	 * Helper method for signatureToWords
	 * 
	 * @param t the tree to traverse
	 * @param signature the signature to match with the words
	 * @param index indicates which array index to go to based on the digit in the signature
	 * @param i allows to calculate 'index' based on individual characters in 'sig' 
	 * @param wordSet2 the possible set of matching words
	 * @return set of words matching the numeric 'signature' or words having it in their signature prefix 
	 */
	public static Set<String> sig2WordHelper(TreeDictionary t, String signature, int index, int i, Set<String> wordSet2){
		//if sig matches
		if(t.array[index].getSig().equals(signature)){		
			
			//copy words and the rest of the subtree with words(prefixes)
			wordSet2 = extractWords(t.array[index], wordSet2, signature.length());
			
			return wordSet2;
		}
		//move further down the tree
		else{
			i++;
			int newIndex = signature.charAt(i)- '0' -2 ;
			return sig2WordHelper(t.array[index], signature, newIndex, i, wordSet2 );
		}
	}
	
	
	/**
	 * helper for sig2WordHelper and signatureToWords
	 * this is called once the signature matches 
	 * All the words in the 8 subtrees are added to the set 
	 * 
	 * @param t the tree from which all the words(or prefixes) contained in the subtrees are to be returned 
	 * @param wordSet2 the set of matching words
	 * @return set of words matching the numeric 'signature' or words having it in their signature prefix 
	 */
	public static Set<String> extractWords(TreeDictionary t, Set<String> wordSet2, int length){
		if(t == null){
			return wordSet2;
		}else{
			wordSet2.addAll(t.getWordSet());
			//RETURNING WORDS FROM REST OF THE TREE
			for(int j = 0; j <= 7; j++){
				wordSet2 = extractWords(t.array[j], wordSet2, length);
			}
			//TRIMMING TO JUST THE PREFIX 
			Set<String> wordSet3 = new HashSet<String>();
			for(String s : wordSet2){
				wordSet3.add(s.substring(0, length));
			}
			return wordSet3;
		}
	}

	/**
	 * Takes a word and returns it's numeric signature based on traditional mobile keypads
	 * Replaces non-alphabetic characters with a space - " "
	 * StringBuffer is used since it is more efficient to append characters to it.
	 *
	 * @param word the word to be converted
	 * @return the numeric signature of 'word'
	 */
	public String wordToSignature(String word){
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
	
	/**
	 * Helper method
	 * Checks if a word has non-alphabetical characters 
	 * 
	 * @param word the words to be checked
	 * @return false if 'word' has non-alphabetical characters, true otherwise
	 */
	public static boolean isValidWord(String word){
		StringBuffer w = new StringBuffer(word);
		
		for(int i = 0; i < word.length(); i++){
			char  letter = Character.toLowerCase( w.charAt(i) );
			
			if(letter < 97 || letter > 122) 
				return false ;		
		}
		return true;
	}
}
