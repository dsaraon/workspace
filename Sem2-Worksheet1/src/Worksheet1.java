/** @author Divyjyot Saraon
 * 
 * This class contains the solution for Worksheet1
 */

public class Worksheet1 {
	
	/**
	 * This method checks if all the elements of a list are positive(>=0) 
	 * @param a The list to be checked
	 * @return boolean - true if all the elements are positive, false otherwise 
	 */
	public static boolean allPositive(List a) {
		if(a.getEmpty()){
			return true;
		}else if(a.getHead() >= 0){
			return allPositive(a.getTail());
		}else{
			return false;
		}
	}
	
	/**
	 * This method checks whether a list is sorted in ascending order. Allows for duplicates
	 * @param a the list to be checked
	 * @return boolean - true if sorted in ascending order, false otherwise
	 */
	public static boolean sorted(List a) {
		if(a.getEmpty()){
			return true;
		}else if(a.getTail().getEmpty()){
			return true;
		}else if(a.getHead() <= a.getTail().getHead()){
			return sorted(a.getTail());
		}else{
			return false;
		}
	}

	/**
	 * Merges two sorted lists into a new sorted list. Retains duplicates
	 * The arguments passed should be sorted lists only 
	 * @param a First list to me merged
	 * @param b Second list to be merged
	 * @return new List consisting of all the elements of a and b
	 */
	public static List merge(List a, List b) {
		if(a.getEmpty() && b.getEmpty()){
			return List.empty();
		}else if(a.getEmpty()){
			return List.cons(b.getHead(), merge(a,b.getTail()));
		}else if(b.getEmpty()){
			return List.cons(a.getHead(), merge(a.getTail(),b));
		}else if(a.getHead() <= b.getHead()){
			return List.cons(a.getHead(), merge(a.getTail(),b));
		}else{
			return List.cons(b.getHead(), merge(a, b.getTail()));
		}
	}
	
	/**
	 * This method removes the duplicates from a list
	 * @param a The list from which the duplicates are removed
	 * @return new list which does not contain the duplicates 
	 */
	static List removeDuplicates(List a) {
		if(a.getEmpty()){
			return List.empty();
		}else if(a.getTail().getEmpty()){
			return List.cons(a.getHead(),a.getTail());
		}else if(a.getHead() == a.getTail().getHead()){
			return removeDuplicates(a.getTail());
		}else{
			return List.cons(a.getHead(), removeDuplicates(a.getTail()));
		}
	}

	/**
	 * This method constructs a new tree that is the mirror image of the tree passed as the argument
	 * @param t The tree to be mirrored 
	 * @return new mirror tree
	 */
	public static Tree mirror(Tree t) {
		if(t.getEmpty()){
			return new Tree();
		}else{
			return new Tree(t.getValue(), mirror(t.getRight()),mirror(t.getLeft()));
		}
	}

	/**
	 * This method returns the integer values of a tree, as a string, in descending order
	 * @param a The tree whose values are to be returned as a string, in descending order
	 * @return String - containing the integer values in descending order 
	 */
	public static String showDescending(Tree a) {
		if(a.getEmpty()){
			return "";
		}else if(a.getRight().getEmpty() && a.getLeft().getEmpty()){
			return a.getValue() + "";
		}else if(a.getRight().getEmpty()){
			return a.getValue() + " " + showDescending(a.getLeft());
		}else if(a.getLeft().getEmpty()){
			return showDescending(a.getRight()) + " " + a.getValue();
		}else{
			return showDescending(a.getRight()) + " " + a.getValue() + " " + showDescending(a.getLeft());
		}
	}

	/**
	 * This method finds the largest integer value in a tree 
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

	/**
	 * This method returns a new tree after deleting(or without) a given node with the corresponding integer 
	 * value passed as an argument. The original tree is not changed.  
	 * 
	 * A node is replaced by it's immediate child if it has only one child. 
	 * A node with two children is replaced with the largest value in the left subtree.
	 * This value is removed from the tree, i.e, it undergoes the same deletion process. 
	 * 
	 * @param a The tree containing the integer value 
	 * @param x The value to be deleted
	 * @return new tree without the corresponding value x 
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

}
