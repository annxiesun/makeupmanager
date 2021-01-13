import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class main {

	public static ArrayList<MakeupProduct> makeupCollection = new ArrayList<MakeupProduct>();
	public static JFrame frame;
	public static BufferedImage img = null;
	public static BufferedImage img2 = null;
	
	public static BufferedImage intro = null;
	public static CollectionPage collection;
	
	public static ArrayList<MakeupProduct> results;
	
	public static String[] types = {" ","Blush","Bronzer","Concealer","Countour","Eyebrow Product","Eyeliner","Eyeshadow",
			"Face Powder","Highlighter","Lip Balm","Lip Gloss","Lip Liner","Lipstick","Liquid Lipstick",
			"Mascara","Primer","Setting Spray"};
	
	//creates JFrame
	public static void newFrame() {
		frame = new JFrame ("Makeup  Manager");
		frame.setPreferredSize(new Dimension(1200, 700));
		frame.setLocation(100, 100);

		frame.setLayout( new FlowLayout()); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//reads in information from the external file and stores the info in an list of makeup products
	public static void readProducts() throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer line;
		String theLine;
		
		while(in.ready()) {
			
			theLine = in.readLine();
			if (theLine.trim().equals("")) {
				break;
			}
			
			line = new StringTokenizer(theLine,"^");
		
			int id = Integer.parseInt(line.nextToken());
			String name = line.nextToken();

			double price = Double.parseDouble(line.nextToken());
			String type = line.nextToken();
			double weight = Double.parseDouble(line.nextToken());
			int rating = Integer.parseInt(line.nextToken());
			String brand = line.nextToken();
			String image = line.nextToken();

			
			MakeupProduct newMakeup = new MakeupProduct(id, name,price,type,weight,rating,brand,image);
		
			newMakeup.readImage();
			newMakeup.setDisplay();
			
			newMakeup.setEditPage("View");
			
			makeupCollection.add(newMakeup);
			
			
		}
		in.close();
		results = makeupCollection;
		
		
	}
	
	//writes out the information of all products into an external file
	public static void updateFile() throws IOException{
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("input.txt")));
		for (MakeupProduct product : main.makeupCollection) {
			outFile.println(product.getID()+"^"+product.getName()+"^"+product.getPrice()+"^"+product.getType()+"^"+product.getWeight()+"^"+product.getRating()+"^"+product.getBrand()+"^"+product.getImage());
		}
		outFile.close();
		
	}
	
	
	public static void main(String[] args) throws IOException{
		img2 = ImageIO.read(new File("full_star.png"));
		img = ImageIO.read(new File("empty_star.png"));

		
		//animated intro
		long start = System.nanoTime();    
		long elapsedTime=(System.nanoTime() - start)/100000000;
		
		newFrame();
	    new Intro();
		
	    readProducts();
	    
		while(elapsedTime<100) {
		 elapsedTime = (System.nanoTime() - start)/100000000;
		}
		
        //program starts
		collection = new CollectionPage();
		collection.setPage();
		

	}

}
