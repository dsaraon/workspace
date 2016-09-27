class Ex2 {
	/**
	 * This method counts the number of times a string occurs in an array of Strings
	 * 
	 * @param stringCompare the string whose occurrences are to be counted 
	 * @param stringArray the array containing String elements 
	 * @return the number of times a string occurs in a String array 
	 */
	public static int countOccurences(String stringCompare, String[] stringArray){
		
		//counter to track occurences
		int count = 0; 
		
		//Iteration through each element of array. Compares string to each element.
		for(int i = 0; i < stringArray.length ; i++){
			if(stringCompare.equals(stringArray[i])){
				count++;
			}
		}
		return count; 
	}

	public static void main(String args[]){
		//testing
		String[] stringArray = {"the", "cat", "sat", "on", "the", "mat"};
		String x = "dog";
		String y = "cat";
		String z = "the";

		System.out.println(countOccurences(x,stringArray));
		System.out.println(countOccurences(y,stringArray));
		System.out.println(countOccurences(z,stringArray));
	}
}