package predictive;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

/**
 * All tests were done using the dictionary from file source "/usr/share/dict/words" in MacBook Pro
 * The dictionary may differ between systems 
 *
 */
public class Worksheet2_3Test {

	@Test
	public void wordsToSignatureTest1() {
		String result = PredictivePrototype.wordToSignature("home");
		String expected = "4663";
		
		assertTrue(expected.equals(result));
	}
	
	@Test
	public void wordsToSignatureTest2() {
		String result = PredictivePrototype.wordToSignature("Mumbai2Delhi");
		String expected = "686224 33544";
		
		assertTrue(expected.equals(result));
	}
	
	@Test
	public void wordsToSignatureTest3() {
		boolean x = PredictivePrototype.isValidWord("Mumbai2Delhi");
		
		assertFalse(x);
	}
	
	@Test
	/**
	 * Prototype - signatureToWordsTest
	 */
	public void signatureToWordsTest1(){
		String signature = "4663";
		Set<String> expected = new HashSet<>();
		expected.add("hood");
		expected.add("ione");
		expected.add("good");
		expected.add("gond");
		expected.add("hone");
		expected.add("hoof");
		expected.add("gone");
		expected.add("goof");
		expected.add("home");
		
		assertEquals(expected, PredictivePrototype.signatureToWords(signature));
	}
	
	@Test
	/**
	 *Prototype - signatureToWords
	 */
	public void signatureToWordsTest2(){
		String signature = "233";
		Set<String> expected = new HashSet<>();
		expected.add("bee");
		expected.add("add");
		expected.add("bed");
		expected.add("cee");
		expected.add("ade");
		
		assertEquals(expected, PredictivePrototype.signatureToWords(signature));
	}
	
	@Test
	/**
 	 *ListDictionary -signatureToWords
	 */
	public void signatureToWordsTest3(){
			ListDictionary lD = new ListDictionary();

			String signature = "46788";
			Set<String> expected = new HashSet<>();
			expected.add("input");
			
			assertEquals(expected, lD.signatureToWords(signature));
	}
	
	@Test
	/**
	 * ListDictionary -signatureToWords
	 */
	public void signatureToWordsTest4(){
			ListDictionary lD = new ListDictionary();

			String signature = "4663";
			Set<String> expected = new HashSet<>();
			expected.add("hood");
			expected.add("ione");
			expected.add("good");
			expected.add("gond");
			expected.add("hone");
			expected.add("hoof");
			expected.add("gone");
			expected.add("goof");
			expected.add("home");
			
			assertEquals(expected, lD.signatureToWords(signature));
	}
	
	@Test
	/**
	 * MapDictionary
	 */
	public void signatureToWordsTest5(){
		
			MapDictionary mD = new MapDictionary("/usr/share/dict/words");

			String word = "addition";
			String expected = "23348466";
			
			assertEquals(expected, mD.wordToSignature(word));
	}
	
	@Test
	/**
	 * MapDictionary
	 */
	public void signatureToWordsTest6(){

			MapDictionary mD = new MapDictionary("/usr/share/dict/words");

			String word = "this";
			String expected = "8447";
			
			assertEquals(expected, mD.wordToSignature(word));
	}
	

	@Test
	/**
	 * MapDictionary
	 */
	public void signatureToWordsTest7(){
			MapDictionary mD = new MapDictionary("/usr/share/dict/words");

			String signature = "329";
			Set<String> expected = new HashSet<>();
			expected.add("day");
			expected.add("fay");
			expected.add("daw");

			
			assertEquals(expected, mD.signatureToWords(signature));
	}
	
	private TreeDictionary td;
	private Set<String> expected,result;

	@Before
	public void setUp() throws IOException {
	td = new TreeDictionary("/usr/share/dict/words");
	}
	
	//test for signature 4663
	@Test
	public void test() {
		result = td.signatureToWords("4663");

		//the expected set
		expected = new TreeSet<String>();
	
		expected.add("good");
		expected.add("gone");
		expected.add("home");
		expected.add("hone");
		expected.add("hood");
		expected.add("hoof");
		expected.add("ioof");
		expected.add("ione");
		expected.add("inne");
		expected.add("gome");
		expected.add("gond");
		expected.add("hond");
		expected.add("goof");
		expected.add("gnof");
		expected.add("imme");
		expected.add("inme");
		expected.add("inod");
		expected.add("inof");
		expected.add("inoe");
		expected.add("hooe");
		expected.add("gooe");
		expected.add("gonf");
		expected.add("honf");

	
		assertEquals(expected, result);
	}


}
