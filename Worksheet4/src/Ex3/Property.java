package Ex3;

import java.awt.image.BufferedImage;

public abstract class Property {
	
	private int bedrooms; 
	private String description;
	private BufferedImage img;
	
	public Property(int bedrooms, String description, BufferedImage img) {
		this.bedrooms = bedrooms;
		this.description = description;
		this.img = img;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return 	"bedrooms=" + getBedrooms() + 
				"\n\r description=" + getDescription() + 
				"\n display image here";
	}
	
	
}
