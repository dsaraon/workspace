package Ex3;

import java.awt.image.BufferedImage;

public class ToLet extends Property{

	private double rent;
	
	public ToLet(int bedrooms, String description, BufferedImage img, double rent) {
		super(bedrooms, description, img);
		this.rent = rent;
	}
	

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n Rent is " + getRent() + " $ per month.";
	}

}
