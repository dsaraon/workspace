package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * This class contains static helper methods to help keep code in GUI's clean
 * 
 * @author hseager
 * @version 2015-03-10
 *
 */

public class Help {

	// Create Pictures

	/**
	 * Create a JLabel picture with no alternative text
	 * 
	 * @param filename - filename of picture
	 * @return Picture as JLabel
	 */

	public static JLabel createPicture(String filename) {
		BufferedImage pic = null; // Declare Logo
		String path = ""; // Create "images/"

		try { // Must catch IOException
			pic = ImageIO.read(new File(path + filename));
		} catch (IOException e) {
			System.out.println("No picture found");
		}

		JLabel picLabel = new JLabel(new ImageIcon(pic));

		return picLabel;
	}

	/**
	 * Create a JLabel picture with alternative text
	 * 
	 * @param filename- filename of picture
	 * @param altText - Alternative text if picture cannot be shown
	 * @return Picture as JLabel
	 */

	public static JLabel createPicture(String filename, String altText) {
		BufferedImage pic = null; // Declare Logo
		String path = ""; // Create "images/"

		try { // Must catch IOException
			pic = ImageIO.read(new File(path + filename));
		} catch (IOException e) {
			System.out.println("No picture found");
		}

		JLabel picLabel = new JLabel(new ImageIcon(pic, altText));

		return picLabel;
	}

}