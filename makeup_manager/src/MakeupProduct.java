import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MakeupProduct implements Comparable<MakeupProduct> { 

	private String name;
	private double price;
	private double weight;
	private int rating;
	
	private String type;
	
	private String image=null;
	
	private ImageIcon picture;

	private BufferedImage img = null;
	private MakeupProductDisplay display;
	private Image dimg;
	
	private EditPage page;
	private String brand;
	
	private int id;
	
	public MakeupProduct(int id, String name, double price, String type, double weight, int rating, String brand, String image){
		this.id =id;
		this.name = name;
		this.price=price;
		this.type=type;
		this.weight=weight;
		this.rating=rating;
		this.brand=brand;
		this.image=image;
		
		
	}

	
	public int getID() {
		return this.id;
	}
	public void setEditPage(String mode) throws IOException{
		this.page =new EditPage(this,mode);
	}
	
	public EditPage getEditPage(){
		return this.page;
	}
	
	public void setDisplay() {
		this.display= new MakeupProductDisplay(this);
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public void setPrice (double price) {
		this.price = price;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getName () {
		return this.name;
	}
	
	public double getPrice () {
		return this.price;
	}
	
	public double getWeight () {
		return this.weight;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getImage() {
		if (this.image==null) {
			return null;
		}
		return this.image;
	}
	
	public void setBrand(String brand) {
		this.brand=brand;
	}
	
	public String getBrand() {
		return this.brand;
	}
	public MakeupProductDisplay getDisplay() {
		return this.display;
	}
	public String toString() {
		return this.name;
	}
	

//reads in the image and stores it
public void readImage() throws IOException {

	if (this.getImage().equals("null")||this.getImage().equals("nullnull")) {
		return;
	}
	this.img = ImageIO.read(new File(this.getImage()));
}

//sets the image to fit the container it is in
public void setImage(int widthOfContainer, int heightOfContainer, JLabel label) {

	if (this.img==null) {
		return;
	}

	
	
		int width = this.img.getWidth();
		int height = this.img.getHeight();
		
		double scale = width/(double)height;
		
		if (width>widthOfContainer) {
			width = widthOfContainer;
			height = (int)(widthOfContainer/scale);
		}
		
		
		if(height>heightOfContainer) {
			height = heightOfContainer;
			width = (int)(heightOfContainer*scale);
		}
		
		dimg = this.img.getScaledInstance(width, height,Image.SCALE_SMOOTH);
		picture = new ImageIcon(dimg);
		label.setIcon(picture);
	}

	public int compareTo (MakeupProduct product2){
		return this.name.compareTo(product2.name);
	}

}
