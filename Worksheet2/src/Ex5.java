class Ex5 {
	/**
	 * This method accepts a Sudoku solution as a 2-dimensional array and tests whether it satisfies the conditions for a perfect solution.    
	 * 
	 * @param game 2-dimensional array(9X9) representing a Sudoku solution 
	 * @return returns Boolean 2-dimensional array(3X9). The array represents true or false values for each row, column and sub-square in each row respectively.  
	 */
	public static boolean[][] checkSudoku(int[][] game){ 
		
		//initialize the boolean array to be returned. All elements are considered true initially.  
		boolean result[][] = {	{ true,true,true,true,true,true,true,true,true,} ,
								{ true,true,true,true,true,true,true,true,true,} ,
								{ true,true,true,true,true,true,true,true,true,} };
				
		//Outermost loops iterate through each element to compare them to corresponding row,column and subSquare  
		for(int i = 0; i < 9; i++){ 
			for(int j = 0; j < 9; j++){
				int checkValue = game[i][j]; //value to be checked for duplicates

				//checking every row for duplicates
				//row(i) remains same, column(j) value changes - represented by k 
				for(int k = j+1; k < 9; k++){
					if(checkValue == game[i][k]){
						result[0][i] = false;
						break;
					}
				}

				//checking every column for duplicates 
				//column(j) remains same, row(i) value changes - represented by k
				for(int k = i+1; k < 9; k++){
					if(checkValue == game[k][j]){
						result[1][j] = false;
						break;
					}
				}

				
				//checking every sub-square
				int subSquare = (i/3)*3 + (j/3);
				int subRow = (subSquare/3)*3 + subSquare;
				int subCol = (subSquare%3)*3 + subSquare;
				
				
	//			for(int subRow = (subSquare/3)*3 + i; subRow < 9; subRow++){
	//			for(int subCol = (subSquare/3)*3 + j; subCol < 9; subCol++){
			
					if(subSquare<9){ 
					if( checkValue == game[subRow][subCol] ){
					result[2][subSquare] = false;
					break;
	//				}
	//				}	
					}
				}				

				
			}
		}
		
		//printing for debugging 
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
		return result ; 
	}

	public static void main(String args[]){
		//trial1
		int[][] sudoku = { {1,2,3, 4,5,6, 7,8,9},
                           {4,5,6, 2,2,2, 2,2,2},
                           {7,8,9, 3,3,3, 3,3,3},
                           
                           {4,4,4, 4,4,4, 4,4,4},
                           {5,5,5, 5,5,5, 5,5,5},
                           {6,6,6, 6,6,6, 6,6,6},
                           
                           {7,7,7, 7,7,7, 7,7,7},
                           {8,8,8, 8,8,8, 8,8,8},
                           {9,9,9, 9,9,9, 9,9,1} };
        checkSudoku(sudoku);                   
        

	}
}