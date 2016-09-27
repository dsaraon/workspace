package Ex2;
/* The following example is adapted from [Deitel & Deitel, 2010, Java
 * - How to program, 8th edition, p. 427]
 */

public interface Payable {
    double paymentAmount();
    int dueDate();  // day of the month when the payment is due.
 
    /* The interface provides only skeleton of a class, that is, here,
     * that there is a method which takes no arguments and gives back
     * an int, the amount to be paid. A calling methods needs to know
     * no more. However, in order to run the method we need
     * implementations. 
     */
}
