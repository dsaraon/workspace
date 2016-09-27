package Ex3;


import static org.junit.Assert.*;

import org.junit.Test;

public class Ex3PublicTests {

	/**
	 * Don Quixote
	 */
	@Test
	public void ex3PublicTest1() {

		long[] result = Ex3.frequencyAnalysis("http://www.cs.bham.ac.uk/internal/courses/java/msc/handouts/exercises/DonQuixote.txt");
		long[] expected = {148970,25519,41224,81586,219488,41740,34245,125330,121889,1654,12516,66773,45899,124802,146903,26297,4213,100115,114741,169435,50147,18704,40609,4562,33237,1106,395475,68099,40006};
	
		assertArrayEquals(expected, result);
	}
	
	/**
	 * Every character
	 */
	@Test
	public void ex3PublicTest2() {

		long[] result = Ex3.frequencyAnalysis("http://www.cs.bham.ac.uk/~jxg856/Simple.txt");
		long[] expected = {1,1,1,1,3,1,1,2,1,1,1,1,1,1,4,1,1,2,1,2,2,1,1,1,1,1,8,1,1};
	
		assertArrayEquals(expected, result);
		
	}

}
