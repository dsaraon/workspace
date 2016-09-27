class Ex4 {
	/**
	 * This method uses Bubble Sort to sort an array of strings by ascending order of word length 
	 * 
	 * @param stringArray the array of string elements 
	 * @return the method returns the array after sorting through Bubble Sort
	 */
	public static String[] bubbleSort(String[] stringArray){
		
		//iterates for each element of array 
		for(int i = 0; i < stringArray.length; i++){
			//starts at incremented limit every time(after the sorted part of the array) 
			for(int j = i + 1; j < stringArray.length; j++){
				
				
				if(stringArray[i].length() > stringArray[j].length()){
					//performs swap operation only if longer string is before the shorter string   
					String temp = stringArray[i];
					stringArray[i] = stringArray[j];
					stringArray[j] = temp; 
				}
			}
		}
		return stringArray; 
	}

	public static void main(String args[]){
		//testing
		String[] stringArray = {"I", "am", "writing", "a", "static", "method", "for", "Bubble", "Sort", "algorithm", ".", "It", "is", "fun"};
		bubbleSort(stringArray);
		for(int i = 0; i < stringArray.length; i++){
			System.out.println(stringArray[i] + " " + stringArray[i].length());
		}
	}
}