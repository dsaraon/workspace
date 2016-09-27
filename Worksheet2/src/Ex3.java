class Ex3 {
	/**
	 * This method checks the first n numbers for prime numbers and returns a corresponding array of boolean values.
	 * A new array has elements with value 'true' if they are prime numbers. 
	 * 
	 * @param n the number of whole integers to be considered for evaluation as prime numbers 
	 * @return array for boolean values for each whole integer   
	 */
	public static boolean[] sieve(int n){
		
		
		//creates new boolean array to be returned  
		boolean[] prime = new boolean[n]; 
		
		//initializing new array. 0,1 are set to false as they are not prime numbers. 
		//All others are set to true as they are candidates for evalution 
		prime[0] = false;
		prime[1] = false;
		for(int i = 2; i < prime.length; i++){
			prime[i] = true;
		}

		//checking for prime and performing operation
		for(int i = 0; i < prime.length; i++ ){
			
			//check if bigger than sqrt of n 
			//check if true
			if ( prime[i] == true && i < Math.sqrt(prime.length) ){
				
				//check if prime
				int divisors = 0; 
				for(int j = 1; j < i ; j ++){
					if(i % j == 0 ){
						divisors++;
					}
				}
				
				//If the number is prime, the value of it's multiples of corresponding elements in the new array 'prime ' are changed to false 
				if(divisors == 1){
					for(int k = i; k < prime.length ; k = k + i){ 
						prime[k] = false; 
					}
				//change the prime number's corresponding boolean value back to true after changing multiples  
					prime[i] = true; 
				}	
				//change corresponding value to false if not a prime number 
				else{
					prime[i] = false; 
				}
			}
		}
		return prime;
	}

	public static void main(String args[]){
//		int n = 100;
		
//		sieve(prime);
//		for(int i = 0; i < 100 ; i++){
//			if( prime[i].equals("true") ){
//				System.out.println( i + " " + prime[i]);
//			}
//		}
	}
}