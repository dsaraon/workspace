// Into to CS - Assignment 3

public class Binary {
	 
	/** Integer square root
     * Calculates the integer part of the square root
     *   of n, i.e. integer s such that
     *   s*s <= n and (s+1)*(s+1) > n
     * requires n >= 0
     * @param n number to find the square root of
     * @return integer part of its square root
     */
    private static int iSqrt(int n){
      int l = 0;
      int r = n;
      /* loop invariant
       * 0 <= l <= r <= n
       */
      while(l<r) {
        int m = (l+r)/2; 
        if(m*m < n && ( (m+1)*(m+1) ) <= n){
        	l = m+1;
        }else{
        	r=m;
        }
      }
      return l; 
    }
    
    public static void main(String args[]){
    	int a=225, b=9, c=15;
    	System.out.println("Integer square root of "+ a +" is : " + iSqrt(a));
    	System.out.println("Integer square root of "+ b +" is : " + iSqrt(b));
    	System.out.println("Integer square root of "+ c +" is : " + iSqrt(c));
    }

}


//make private
//public static boolean isPossibleMatch(String word, String signature){
//	StringBuffer w = new StringBuffer(word);
//	StringBuffer sign = new StringBuffer(signature);
//	
//	for(int i = 0; i < word.length(); i++){
//		char  letter = w.charAt(i);
//		char  number = sign.charAt(i);
//		System.out.println(letter + " " + number);
//		
//		//conditions never satisfied - why???? 
//		//always return false from inside the if()
//		if(		(letter >= 'a' && letter <= 'c' && number != '2') || 
//				(letter >= 100 && letter <= 102 && number != '3') ||
//				(letter >= 103 && letter <= 105 && number != '4') ||
//				(letter >= 106 && letter <= 108 && number != '5') ||
//				(letter >= 109 && letter <= 111 && number != '6') ||
//				(letter >= 112 && letter <= 115 && number != '7') || 
//				(letter >= 116 && letter <= 118 && number != '8') ||
//				(letter >= 119 && letter <= 122 && number != '9')  ){
//			return false;
//		}
//	}	
//	return true;
//}
