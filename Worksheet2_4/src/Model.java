import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import predictive.*;
import predictive.Dictionary;
import predictive.SampleDictionary;

/**
 * This class acts as the Observable object and links the GUI interface to the dictionary object
 *
 */
public class Model extends Observable {

	private StringBuffer signature;
	private StringBuffer message;
	private Set<String> wordSet = new HashSet<String>();
	private Dictionary dict;
	private Integer i;
	private ArrayList<String> array;

	/**
	 * Constructor - initializes field variables, including the dictionary 
	 */
	public Model(){
		
		try {
			dict = new SampleDictionary("/usr/share/dict/words");
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		array = new ArrayList<String>();
		signature = new StringBuffer();
		message = new StringBuffer();
		i = 0;
	}
	
	//Getters and Setters
	public StringBuffer getSignature() {
		return this.signature;
	}
	
	/**
	 * Updates the signature to obtain a new set of predicted words for the user
	 * @param sig The button that is pressed on the interface
	 */
	public void setSignature(String sig) {
		if(sig.equals("#") && signature.length() != 0){
			this.signature.deleteCharAt(this.signature.length() -1);
		}else if(sig.equals("0")){
			this.signature.delete(0, this.signature.length());
			this.i = 0;
		}else{
			this.signature.append(sig);
		}
		
		if(signature.length() == 0){
			this.i = 0;
		}
		
		this.update(this.signature);

		String newSignature = this.signature.toString();
		this.setWordSet(newSignature);
	}

	public StringBuffer getMessage() {
		return this.message;
	}

	/**
	 * Updates the message on displayMain every time '0' is pressed
	 */
	public void setMessage() {
		if(array.size() != 0){
			this.message.append(array.get(i)) ;
			this.message.append(" ");
			this.update(message);
		}
	}
	
	public Set<String> getWordSet() {
		return this.wordSet;
	}

	/**
	 * Updates the set of predicted words that are obtained from the dictionary 
	 * @param sig
	 */
	public void setWordSet(String sig) {
		Set<String> newWordSet = dict.signatureToWords(sig); 
		this.wordSet =  newWordSet;
		if(!(newWordSet.isEmpty())){
			this.setArray(newWordSet);
		}
		this.update(wordSet);
	}
	
	public int getI() {
		return this.i;
	}

	/**
	 * Updates 'i' when '*' is pressed. 
	 * Used to cycle through 'wordSet'.
	 */
	public void incrementI() {
		if(this.i == array.size() -1){
			this.i = -1;
		}
			this.i ++;
			this.update(i);
		
	}

	public ArrayList<String> getArray() {
		return this.array;
	}
	
	/**
	 * This arrayList is updated every time the signature changes
	 * It is used to display one word at a time on the 'displaySec'
	 * Words are cycled through wit the use of 'i'  
	 * @param newWordSet The set of predicted words
	 */
	private void setArray(Set<String> newWordSet) {
		this.array = new ArrayList<String>(newWordSet);
		this.update(array);
	}


	/**
	 * This method is used to notify observers that the model has changed
	 * Works in sync with the update method of the Observer(View object)
	 * @param arg The field variable that has changed 
	 */
	private void update(Object arg) {
		setChanged();
		notifyObservers(arg);
	}
}
