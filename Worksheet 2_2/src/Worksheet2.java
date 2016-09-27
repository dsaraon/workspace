/** @author Divyjyot Saraon
 * 
 * This class contains the solution for Worksheet2
 */

public class Worksheet2 
{
	/**
	 * This method checks whether a given tree is a height-balanced AVL tree.
	 * An AVL tree is one in which the height of all it's nodes' subtrees differ by no more than 1.    
	 * 
	 * @param a The tree to be checked
	 * @return True if it is height-balanced, false otherwise
	 */
    public static boolean isHeightBalanced(Tree a) {
	    if(a.getEmpty()){
			return true;
		}else if( Math.abs( a.getLeft().getHeight() - a.getRight().getHeight() ) > 1){
			return false;	
		}else{
			return isHeightBalanced(a.getLeft()) && isHeightBalanced(a.getRight());
		}
    }
    
    /**
     * This method checks whether a given tree is a binary search tree. 
     * For a binary search tree, all values to the left of a node are smaller than that of the node,
     * and all values to the right are greater.
     * This method uses a helper method called checkSearch.
     * 
     * @param a The tree to be checked.
     * @return True if it is a binary search tree, false otherwise
     */
    public static boolean isSearchTree(Tree a) {
    	if(a.getEmpty()){
    		return true;
    	}else if (a.getLeft().getEmpty() && a.getRight().getEmpty() ){
    		return true;
		}else{
    		return checkSearch(a.getLeft(), Integer.MIN_VALUE, a.getValue()) && 
    			   checkSearch(a.getRight(),  a.getValue(), Integer.MAX_VALUE);   		
    	}
    }
    
    /**
     * This is a helper method for isSearchTree.
     * This method checks all nodes that have at least one child, 
     * to satisfy conditions of a binary search tree.
     * 
     * @param a The tree to be checked
     * @param lower the lower limit for the value that a node may hold to satisfy the conditions 
     * @param upper the upper limit for the value that a node may hold to satisfy the conditions
     * @return True if it is a binary search tree, false otherwise
     */
    private static boolean checkSearch(Tree a, int lower, int upper){
    	if(a.getEmpty()){
    		return true;
    	}else if(a.getValue() > upper || a.getValue() < lower){
    		return false;
    	}else if (a.getLeft().getEmpty() && a.getRight().getEmpty() ){
    		return true;
    	}else if( a.getLeft().getEmpty() ){
    		return checkSearch(a.getRight(), a.getValue(), upper);
    	}else if( a.getRight().getEmpty() ){
    		return checkSearch(a.getLeft(), lower, a.getValue());
    	}else if(a.getLeft().getValue() > a.getValue() || 
    			 a.getRight().getValue() < a.getValue() ){
    		return false;
    	}else{
    		return checkSearch(a.getLeft(), Integer.MIN_VALUE, a.getValue()) && 
    			   checkSearch(a.getRight(), a.getValue(), Integer.MAX_VALUE);
    	}
    }

    /**
     * This method inserts an integer into a height-balanced tree and returns a height-balanced tree
     * containing that integer. 
     * The parameter passed should only be a height-balanced tree. 
     * Does not work with duplicates.  
     * This method uses two helper methods - insert and balance.
     * Also uses the method isHeightBalanced. 
     * insert - simply inserts the integer into the tree as per binary search tree conditions
     * balance - balances the new tree with value x if it is not balanced
     * 
     * @param x The integer to be inserted into the tree a
     * @param a The height-balanced tree that the integer x is inserted into 
     * @return A new height-balanced tree containing the integer x
     */
    public static Tree insertHB(int x, Tree a) {
    	Tree b =  insert(x,a) ;
    	if( isHeightBalanced(b) ){
    		return b;
    	}else{
    		return balance(b);
    	}
    }
    
    /**
     * This is a helper method for insertHB. 
     * Simply inserts the integer into the tree as per binary search tree conditions. 
     * 
     * @param x The integer to be inserted into the tree a
     * @param a The height-balanced tree that the integer x is inserted into 
     * @return A new tree containing the integer x
     */
    public static Tree insert(int x, Tree a){
    	if(a.getEmpty()){
    		return new Tree(x);
    	}else if(a.getValue() > x){
    		return new Tree(a.getValue(), insert(x, a.getLeft()), a.getRight());
    	}else {
    		return new Tree(a.getValue(), a.getLeft(), insert(x, a.getRight()) );
    	}
    }
    
    /**
     * This is a helper method for insertHB.
     * Balances the tree with rotations, for the four different cases. 
     * 
     * @param a The tree that needs to be balanced
     * @return A new height-balanced tree
     */
    public static Tree balance(Tree a){
    	if(a.getEmpty()){
    		return new Tree();
    	}else if(Math.abs( a.getLeft().getHeight() - a.getRight().getHeight() ) <= 1){
    		return new Tree(a.getValue(), balance(a.getLeft()), balance(a.getRight()) );
    	}else if(a.getLeft().getHeight() > a.getRight().getHeight()){
    	 //left heavy	
    	
    		//Left subtree has greater height - RR rotation, single rotation 
    		if(a.getLeft().getLeft().getHeight() > a.getLeft().getRight().getHeight()){
    			return new Tree(a.getLeft().getValue(), 
    						new Tree(a.getLeft().getLeft().getValue(),
    								a.getLeft().getLeft().getLeft(), a.getLeft().getLeft().getRight()), 
							new Tree(a.getValue(), 
									a.getLeft().getRight(), a.getRight()));
    		}
    		
    		//Right subtree has greater height - RL rotation, double rotation 
    		else{
    			//single right rotation on subtree
    			Tree b = new Tree(a.getValue(),
    							new Tree(a.getLeft().getRight().getValue(),
    									new Tree(a.getLeft().getValue(),
    											a.getLeft().getLeft(), a.getLeft().getRight().getLeft()),
    									a.getLeft().getRight().getRight()),
								a.getRight());
    			
    			return balance(b);
    			
    			/*
    			 *** Solution for RL rotation without recursion***
    			 * 
    			return new Tree(a.getLeft().getRight().getValue(),
    						new Tree(a.getLeft().getValue(),
    								a.getLeft().getLeft(), a.getLeft().getRight().getLeft()),
							new Tree(a.getValue(),
									a.getLeft().getRight().getRight(), a.getRight()));
				*/
    		}

    	}else{	
    	 //right heavy	

    		//Right subtree has greater height - LL rotation, single rotation
    		if(a.getRight().getRight().getHeight() > a.getRight().getLeft().getHeight()){
    			return new Tree(a.getRight().getValue(),
    						new Tree(a.getValue(),
    								a.getLeft(), a.getRight().getLeft()),
							new Tree(a.getRight().getRight().getValue(),
									a.getRight().getRight().getLeft(), a.getRight().getRight().getRight()));
    		}
    		
    		//Left subtree has greater height - LR rotation, double rotation
    		else{
    			//single left rotation on subtree
    			Tree b = new Tree(a.getValue(), a.getLeft(), 
    							new Tree(a.getRight().getLeft().getValue(), a.getRight().getLeft().getLeft(),
    									new Tree(a.getRight().getValue(),
    											a.getRight().getLeft().getRight(), a.getRight().getRight())));
    			 
    			return balance(b);
    			
    			/*
    			 *** Solution for LR rotation without recursion ***
    			return new Tree(a.getRight().getLeft().getValue(),
							new Tree(a.getValue(),
									a.getLeft(), a.getRight().getLeft().getLeft()),
							new Tree(a.getRight().getValue(),
									a.getRight().getLeft().getRight(), a.getRight().getRight()));
				*/
    		}
    	}
    }    
   
    /**
     * This method deletes a given integer from a tree and returns a new height-balanced 
     * without that integer.
     * The method uses two helper methods - delete and balance.
     * delete - Simply deletes the integer x from the tree
     * balance - balances the new tree without value x if it is not balanced
     * Also uses the method isHeightBalanced. 
     * 
     * @param a The tree from which the integer is to be deleted
     * @param x The integer to be deleted 
     * @return new height-balanced tree without integer x
     */
    public static Tree deleteHB(Tree a, int x) {
    	Tree b =  delete(a,x) ;
    	if( isHeightBalanced(b) ){
    		return b;
    	}else{
    		return balance(b);
    	}
    }
    
    /**
     * This is a helper method for deleteHB. 
     * Simply deletes the integer x from the tree.
     * Uses helper method - max
     * 
     * @param a The tree from which the integer is to be deleted 
     * @param x The integer to be deleted 
     * @return new tree without the integer x
     */
    public static Tree delete(Tree a, int x){
		if(a.getEmpty()){
			return new Tree();
		}else if(x != a.getValue()){
			return new Tree(a.getValue(), delete(a.getLeft(), x), delete(a.getRight(), x));
		}else if(a.getLeft().getEmpty() && a.getRight().getEmpty()){
			return new Tree();
		}else if(a.getLeft().getEmpty() && a.getRight().getEmpty() != true){
			return new Tree(a.getRight().getValue(), a.getRight().getLeft(), a.getRight().getRight());
		}else if(a.getRight().getEmpty() && a.getLeft().getEmpty() != true){
			return new Tree(a.getLeft().getValue(), a.getLeft().getLeft(), a.getLeft().getRight());
		}else{
			int y = max(a.getLeft());
			return  new Tree( y, delete( a.getLeft() , y ), a.getRight() );
		}
	}
    
    /**
	 * This method finds the largest integer value in a tree 
	 * 
	 * @param a Tree whose largest integer value is to be found
	 * @return the largest integer value
	 * @throws IllegalStateException when an empty tree is passed as an argument
	 */
    public static int max(Tree a)throws IllegalStateException{
		if(a.getEmpty()){
			throw new IllegalStateException();
		}else if(a.getRight().getEmpty()){
			return a.getValue();
		}else 
			return max(a.getRight());
	}
       

}
