package Ex3;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import javax.imageio.ImageIO;

public class PropertyList {
	
	
	public static void main(String args[]) throws IOException{
			
		ForSale p1 = new HouseForSale(5, "WOW, swimming pool", ImageIO.read(new File("House.jpg")), 150 );
		ToLet p2 = new HouseToLet(5, "cool place", ImageIO.read(new File("Apartment.jpg")), 50 );
		ForSale p3 = new ApartmentForSale(5, "WOW, swimming pool", ImageIO.read(new File("House.jpg")), 100 );
		ToLet p4 = new ApartmentToLet(5, "cool place", ImageIO.read(new File("Apartment.jpg")), 30 );
		ForSale p5 = new HouseForSale(5, "WOW, swimming pool", ImageIO.read(new File("House.jpg")), 200 );
		ForSale p6 = new ApartmentForSale(5, "WOW, swimming pool", ImageIO.read(new File("House.jpg")), 140 );
		ToLet p7 = new ApartmentToLet(5, "cool place", ImageIO.read(new File("Apartment.jpg")), 80 );
		ToLet p8 = new ApartmentToLet(5, "cool place", ImageIO.read(new File("Apartment.jpg")), 65 );

		ArrayList<ForSale> propertiesForSale = new ArrayList<ForSale>();
		ArrayList<ToLet> propertiesToLet = new ArrayList<ToLet>();
		
		propertiesForSale.add(p1);
		propertiesToLet.add(p2);
		propertiesForSale.add(p3);
		propertiesToLet.add(p4);
		propertiesForSale.add(p5);
		propertiesForSale.add(p6);
		propertiesToLet.add(p7);
		propertiesToLet.add(p8);
		
		//html output
		bubbleSortSale(propertiesForSale);
			
		File page1 = new File("ForSale.html");
		BufferedWriter pg1 = new BufferedWriter(new FileWriter(page1));
		pg1.write("<html>");
		
		pg1.write("<h1>FANCY AGENCY NAME</h1>");
		pg1.write("<h2>Properties For Sale</h2>");
		pg1.write("<ul>");
		for(ForSale p: propertiesForSale){
			pg1.write("<li>");
			pg1.write(p.toString() + "<br> <br>");
			pg1.write("</li>");
		}
		pg1.write("</ul>");
		pg1.write("</html>");
		pg1.close();
		
		//ToLet html
		
		bubbleSortToLet(propertiesToLet);
		
		File page2 = new File("ToLet.html");
		BufferedWriter pg2 = new BufferedWriter(new FileWriter(page2));
		pg2.write("<html>");
		
		pg2.write("<h1>FANCY AGENCY NAME</h1>");
		pg2.write("<h2>Properties To Let</h2>");
		pg2.write("<ul>");
		for(ToLet p: propertiesToLet){
			pg2.write("<li>");
			pg2.write(p.toString() + "<br> <br>");
			pg2.write("</li>");
		}
		pg2.write("</ul>");
		pg2.write("</html>");
		
		pg2.close();
	}
	
	
	//sorting methods
	public static void bubbleSortSale(ArrayList<ForSale> list){
		
		ForSale temp ;
		for(int i = 0; i < list.size(); i++){
			//starts at incremented limit every time(after the sorted part of the array) 
			for(int j = i + 1; j < list.size(); j++){
				if(list.get(i).getPrice() > list.get(j).getPrice()){				
					temp = list.get(i);
					list.set(i,list.get(j));
					list.set(j,temp); 
				}
			}
		}
	}
	
	public static void bubbleSortToLet(ArrayList<ToLet> list){
		
		ToLet temp ;
		for(int i = 0; i < list.size(); i++){
			//starts at incremented limit every time(after the sorted part of the array) 
			for(int j = i + 1; j < list.size(); j++){
				if(list.get(i).getRent() > list.get(j).getRent()){ 				
					temp = list.get(i);
					list.set(i,list.get(j));
					list.set(j,temp); 
				}
			}
		}
	}
	
}
