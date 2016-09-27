package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ListDictionary implements Dictionary{
	
	private ArrayList<WordSig> list = new ArrayList<WordSig>();
	
	public ArrayList<WordSig> getList() {
		return this.list;
	}

	/**
	 * Constructor reads dictionary file into ArrayList word by word, which is then sorted
	 * Only take words without non-alphabetical characters
	 * Copies words and their numeric signature into 'WordSig' object to store in ArrrayList
	 */
	public ListDictionary(){
		try{
			File dict = new File("/usr/share/dict/words");
			
			Scanner s = new  Scanner(dict);
			while(s.hasNextLine()){
				String word = s.nextLine().toLowerCase();
				if(isValidWord(word)){
					WordSig ws = new WordSig(word, this.wordToSignature(word));
					this.list.add(ws);
				}
			}
			s.close();
		}
		catch(FileNotFoundException e){
			System.out.println("not found");
			e.printStackTrace();
		}
		//sort arraylist
		Collections.sort(this.getList());
	}
	
	/**
	 * This method accepts a string of numbers - 'signature'
	 * Returns a set of words that have the same numeric signature as 'signature'
	 * Uses Collections.binary to search the matching word(s) 
	 * 
	 * @param signature signature numeric signature to find possible matching words
	 * @return set of words matching the numeric 'signature'
	 */
	public Set<String> signatureToWords(String signature){
		
		Set<String> wordSet = new HashSet<String>();
		WordSig ws = new WordSig("", signature);
	
		
		int index = Collections.binarySearch(this.getList(), ws);
		if(index < 0 ){
			return wordSet;
		}
		wordSet.add(this.getList().get(index).getWords());
		
		//search for previous and successive indexes
		if(index > 0){
			boolean x = true;
			boolean y = true;
			int indexPrevious = index - 1, indexSuccesive = index + 1;
			
			while(x && indexPrevious > 0){
				if(this.getList().get(indexPrevious).getSignature().equals(signature)){
					wordSet.add(this.getList().get(indexPrevious).getWords());
					indexPrevious-- ;
				}
				else 
					x = false;
			}
			
			while(y && indexSuccesive < this.getList().size()){
				if(this.getList().get(indexSuccesive).getSignature().equals(signature)){
					wordSet.add(this.getList().get(indexSuccesive).getWords());
					indexSuccesive++ ;
				}
				else 
					y = false;
			}
		}
		return wordSet;	
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
