import static org.junit.Assert.*;

import org.junit.Test;

public class Exercise5Test {

	/**
	 * Example Case - Correct
	 */
	@Test
	public void ex5Test1() {
		int[][] input = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 4, 5, 6, 7, 8, 9, 1, 2, 3 }, { 7, 8, 9, 1, 2, 3, 4, 5, 6 },
				{ 2, 3, 4, 5, 6, 7, 8, 9, 1 }, { 5, 6, 7, 8, 9, 1, 2, 3, 4 },
				{ 8, 9, 1, 2, 3, 4, 5, 6, 7 }, { 3, 4, 5, 6, 7, 8, 9, 1, 2 },
				{ 6, 7, 8, 9, 1, 2, 3, 4, 5 }, { 9, 1, 2, 3, 4, 5, 6, 7, 8 } };
		boolean[][] expected = {
				{ true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true } };
		System.out.println("Ex5 Public Test 1");
		boolean[][] result = Exercise5.checkSudoku(input);
		if (expected.length != result.length) {
			fail("Array lengths do not match");
		}
		for (int i = 0; i < result.length; i++) {
			if (expected[i].length != result[i].length) {
				fail("Array lengths do not match");
			}
		}
		for (int i = 0; i < expected.length; i++) {

			for (int j = 0; j < expected[i].length; j++) {
				if (expected[i][j]) {
					assertTrue(result[i][j]);
				} else {
					assertFalse(result[i][j]);

				}
			}
		}

	}

	/**
	 * Example case - error in bottom left square (8 changed to 9)
	 */
	@Test
	public void ex5Test2() {
		int[][] input = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 4, 5, 6, 7, 8, 9, 1, 2, 3 }, { 7, 8, 9, 1, 2, 3, 4, 5, 6 },
				{ 2, 3, 4, 5, 6, 7, 8, 9, 1 }, { 5, 6, 7, 8, 9, 1, 2, 3, 4 },
				{ 8, 9, 1, 2, 3, 4, 5, 6, 7 }, { 3, 4, 5, 6, 7, 8, 9, 1, 2 },
				{ 6, 7, 9, 9, 1, 2, 3, 4, 5 }, { 9, 1, 2, 3, 4, 5, 6, 7, 8 } };
		boolean[][] expected = {
				{ true, true, true, true, true, true, true, false, true },
				{ true, true, false, true, true, true, true, true, true },
				{ true, true, true, true, true, true, false, true, true } };
		System.out.println("Ex5 Public Test 2");

		boolean[][] result = Exercise5.checkSudoku(input);
		if (expected.length != result.length) {
			fail("Array lengths do not match");
		}
		for (int i = 0; i < result.length; i++) {
			if (expected[i].length != result[i].length) {
				fail("Array lengths do not match");
			}
		}
		for (int i = 0; i < expected.length; i++) {

			for (int j = 0; j < expected[i].length; j++) {
				if (expected[i][j]) {
					assertTrue(result[i][j]);
				} else {
					assertFalse(result[i][j]);

				}
			}
		}

	}

	/**
	 * Errors in multiple positions
	 */
	@Test
	public void ex5Test3() {
		int[][] input = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 4, 5, 6, 7, 8, 9, 1, 2, 3 }, { 7, 8, 9, 1, 2, 3, 4, 5, 6 },
				{ 1, 3, 4, 5, 6, 7, 8, 9, 1 }, { 5, 6, 7, 8, 9, 1, 2, 3, 4 },
				{ 8, 9, 3, 2, 3, 4, 5, 6, 7 }, { 3, 4, 5, 6, 7, 8, 8, 1, 2 },
				{ 6, 7, 8, 9, 1, 2, 3, 4, 5 }, { 9, 1, 2, 3, 2, 5, 6, 7, 8 } };

		boolean[][] expected = {
				{ true, true, true, false, true, false, false, true, false },
				{ false, true, false, true, false, true, false, true, true },
				{ true, true, true, false, true, true, true, false, false } };

		System.out.println("Ex5 Public Test 3");

		boolean[][] result = Exercise5.checkSudoku(input);
		if (expected.length != result.length) {
			fail("Array lengths do not match");
		}
		for (int i = 0; i < result.length; i++) {
			if (expected[i].length != result[i].length) {
				fail("Array lengths do not match");
			}
		}
		for (int i = 0; i < expected.length; i++) {

			for (int j = 0; j < expected[i].length; j++) {
				if (expected[i][j]) {
					assertTrue(result[i][j]);
				} else {
					assertFalse(result[i][j]);

				}
			}
		}

	}

}
