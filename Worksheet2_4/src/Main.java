import javax.swing.JFrame;

/**
 * Main controller for the Model and View classes 
 *
 */
public class Main {
	public static void main(String[] args) {
	
		Model model = new Model();
		
		JFrame frame = new View(model);
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		frame.pack();
		frame.setVisible(true);
	}
}
