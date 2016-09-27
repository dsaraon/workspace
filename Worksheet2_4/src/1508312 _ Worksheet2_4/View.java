import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class creates the GUI interface and acts as the Observer and ActionListener
 */
public class View extends JFrame implements Observer, ActionListener {
	
	private Model model;
	private JTextField displayMain;
	private JTextField displaySec;
	
	/**
	 * Constructor to create the GUI interface
	 * Uses helper method called frameSetup	
	 * 
	 * @param model the model this class is associated with 
	 */
	public View(Model model) {
		
		super("Predictive Text");
		this.model = model;
		model.addObserver(this);
		frameSetup();
	}
	
	/**
	 * Helper method to create the GUI interface
	 */
	private void frameSetup() {
		
		setSize(200,600);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//main Panel container for the 2 Jtextfields and keypad
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		
		
			displayMain = new JTextField();
			displayMain.setEditable(false);
			displayMain.setPreferredSize( new Dimension( 200, 150 ) );
			
			displaySec = new JTextField();
			displaySec.setEditable(false);
			displayMain.setPreferredSize( new Dimension( 200, 100 ) );
			
			JPanel keypad = new JPanel(new GridLayout(4,3));
			
				JButton button1 = new JButton("  1  ");
				button1.setActionCommand("1");
				button1.addActionListener(this);
				
				JButton button2 = new JButton("abc 2");
				button2.setActionCommand("2");
				button2.addActionListener(this);
				
				JButton button3 = new JButton("def 3");
				button3.setActionCommand("3");
				button3.addActionListener(this);
				
				JButton button4 = new JButton("ghi 4");
				button4.setActionCommand("4");
				button4.addActionListener(this);
				
				JButton button5 = new JButton("jkl 5");
				button5.setActionCommand("5");
				button5.addActionListener(this);
				
				JButton button6 = new JButton("mno 6");
				button6.setActionCommand("6");
				button6.addActionListener(this);
				
				JButton button7 = new JButton("pqrs 7");
				button7.setActionCommand("7");
				button7.addActionListener(this);
				
				JButton button8 = new JButton("tuv 8");
				button8.setActionCommand("8");
				button8.addActionListener(this);
				
				JButton button9 = new JButton("wxyz 9");
				button9.setActionCommand("9");
				button9.addActionListener(this);
				
				JButton button10 = new JButton("  *  ");
				button10.setActionCommand("*");
				button10.addActionListener(this);
				
				JButton button11 = new JButton("  0  ");
				button11.setActionCommand("0");
				button11.addActionListener(this);
				
				JButton button12 = new JButton("  #  ");
				button12.setActionCommand("#");
				button12.addActionListener(this);
				
				keypad.add(button1);
				keypad.add(button2);
				keypad.add(button3);
				keypad.add(button4);
				keypad.add(button5);
				keypad.add(button6);
				keypad.add(button7);
				keypad.add(button8);
				keypad.add(button9);
				keypad.add(button10);
				keypad.add(button11);
				keypad.add(button12);
				
			container.add(displayMain, BorderLayout.NORTH);
			container.add(keypad, BorderLayout.CENTER);
			container.add(displaySec, BorderLayout.SOUTH);
			
			
		getContentPane().add(container);;	
		
		
		//This code places JFrame in the middle of the screen
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
				(int) screen.getHeight() / 2 - getHeight() / 2);
	}
	
	

	public JTextField getDisplayMain() {
		return this.displayMain;
	}

	public JTextField getDisplaySec() {
		return this.displaySec;
	}

	/** 
	 * The update method called when the notifyObservers call is made in the model
	 *
	 * @param o the observable object the update originated from (model)
	 * @param arg the argument passed to the notifyObservers call in the model
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof Model) {
            
			//when the wordSet or i are updated in the model
			if( (arg instanceof Set<?>)  || 
        		(arg instanceof Integer) ){
           
				if(model.getArray().size() != 0){
					String str = model.getArray().get(model.getI()) ;
					this.getDisplaySec().setText(str);
				}
			
			//when the display message is updated('0' is pressed)
            }else if(arg.equals(model.getMessage())){
            	this.getDisplayMain().setText(arg.toString());
            }
        }
	}
	
	/** 
	 * This method is called when an action occurs(user interacts with the interface)
	 * When button with digits '2'-'9' are  pressed, the signature of the model is updated
	 * '0' completes the entry of the word displayed on displaySec(bottom JTextfield) from the wordSet
	 * '*' cycles through the words in the predictive wordSet and displays one on the displaySec
	 * '#' deletes a character from the end of the signature before it is confirmed by pressing '0'
	 *     
	 */
	public void actionPerformed(ActionEvent event) {
		String e = event.getActionCommand();
		if (e.equals("2")) {
			model.setSignature("2");
		}else if (e.equals("3")) {
			model.setSignature("3");
		}else if (e.equals("4")) {
			model.setSignature("4");
		}else if (e.equals("5")) {
			model.setSignature("5");
		}else if (e.equals("6")) {
			model.setSignature("6");
		}else if (e.equals("7")) {
			model.setSignature("7");
		}else if (e.equals("8")) {
			model.setSignature("8");
		}else if (e.equals("9")) {
			model.setSignature("9");
		}else if (e.equals("*")) {
			model.incrementI();
		}else if (e.equals("0")) {
			model.setMessage();
			model.setSignature("0");
		}else if (e.equals("#")) {
			model.setSignature("#");
		}
	}
}
