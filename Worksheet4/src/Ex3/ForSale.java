package Ex3;

import java.awt.image.BufferedImage;

public class ForSale extends Property{

	private double price;
	
	public ForSale(int bedrooms, String description, BufferedImage img, double price) {
		super(bedrooms, description, img);
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return super.toString() + "\n ForSale [price=" + price + "]";
	}
	
	

}
