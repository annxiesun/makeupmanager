import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.BorderFactory;
import java.text.DecimalFormat;

import java.text.NumberFormat;
import javax.swing.text.JTextComponent;

public class EditPage implements ActionListener {

	private NumberFormat formatter = new DecimalFormat("#0.00");
	
	private MakeupProduct product;
	private JPanel imagePanel;
	private JPanel changePanel;
	private JComboBox <String> productTypes;
	private JScrollPane scrollPane;
	private JTextField productName;
	private JTextField productPrice;
	private JTextField productWeight;
	
	private JLabel textLabel;
	private JLabel textLabel2;
	private TextPrompt price;
	private TextPrompt weight;
	private TextPrompt title;
	
	private JLabel lblNewLabel;
	private JLabel labelPrice;
	private JLabel labelWeight;

	private JLabel image;
	private JButton button;
	
	private JButton save;
	private JButton edit;
	private StarRating rating;
	
	private JPanel mainPanel;
	private JLabel typeName;
	private JButton btnDelete;
	
	private JButton backButton;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	

	private JLabel textLabel_1;

	private JTextField brandText;
	private JLabel lblRating;
	
	
	public EditPage(MakeupProduct product, String mode) throws IOException{
		
		this.product = product;
		
		//layout
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(1200, 700));
		mainPanel.setLayout(null);
		
		imagePanel = new JPanel ();
		imagePanel.setBorder(null);
		imagePanel.setBounds(0, 0, 616, 700);
		imagePanel.setBackground (new Color(255, 204, 204));
		imagePanel.setPreferredSize(new Dimension(600, 700));
		
		changePanel= new JPanel();
		
		changePanel.setBounds(604, -14, 609, 719);
		
		changePanel.setBackground (Color.WHITE);
		changePanel.setPreferredSize(new Dimension (600, 1200));
	
		changePanel.setLayout(null);
		imagePanel.setLayout(null);
		
		image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setBounds(73, 44, 505, 604);
		image.setFont(new Font("Proxima Nova", Font.PLAIN, 21));
		imagePanel.add(image);
		
		//upload image
		button = new JButton("");
		button.setIcon(new ImageIcon("/Users/annie/eclipse-workspace/final/upload_image.png"));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(528, 0, 70, 77);
		button.setActionCommand ("EditI");
		button.addActionListener(this);
		imagePanel.add(button);
		
		//Name
		productName= new JTextField(40);
		productName.setBounds(55, 74, 483, 57);
		productName.setBackground(Color.WHITE);
		productName.setFont(new Font("Playfair Display", Font.BOLD, 40));
		productName.setColumns(10);
		changePanel.add(productName);
		
		/*
		JLabel lblProductClass = new JLabel("Product Class");
		lblProductClass.setFont(new Font("Proxima Nova", Font.PLAIN, 28));
		lblProductClass.setBounds(56, 133, 200, 38);
		changePanel.add(lblProductClass);
		*/

		//Type
		productTypes = new JComboBox();
		addEditDropdownMenu(productTypes,"Type", main.types,55, 216, 474, 57);
		
		
		//Price
		labelPrice = new JLabel("$");
		labelPrice.setFont(new Font("Playfair Display", Font.PLAIN, 36));
		labelPrice.setBounds(152, 361, 21, 31);
		changePanel.add(labelPrice);
	
	
		productPrice= new JTextField(6);
		productPrice.setHorizontalAlignment(SwingConstants.LEFT);

		productPrice.setFont(new Font("Playfair Display", Font.PLAIN, 43));
		productPrice.setBounds(180, 353, 139, 50);
		changePanel.add(productPrice);
		productPrice.setColumns(10);
		
		textLabel = new JLabel("Price:");
		textLabel.setFont(new Font("Proxima Nova", Font.BOLD, 16));
		textLabel.setBounds(55, 361, 125, 26);
		changePanel.add(textLabel);
		
		price = new TextPrompt("00.00", productPrice);
		price.changeAlpha(0.4f);
		price.setFont(new Font("Playfair Display", Font.ITALIC, 43));
		
	
		//Weight
		productWeight= new JTextField(6);

		labelWeight = new JLabel("g");
		labelWeight.setFont(new Font("Playfair Display", Font.PLAIN, 36));
		labelWeight.setBounds(318, 437, 33, 57);
		changePanel.add(labelWeight);
		
		productWeight.setFont(new Font("Playfair Display", Font.PLAIN, 43));
		productWeight.setBounds(181, 424, 138, 50);
		changePanel.add(productWeight);
		productWeight.setColumns(10);
		
		textLabel2 = new JLabel("Weight:");
		textLabel2.setFont(new Font("Proxima Nova", Font.BOLD, 16));
		textLabel2.setBounds(55, 437, 125, 26);
		changePanel.add(textLabel2);
		
		weight = new TextPrompt("00.00", productWeight);
		weight.changeAlpha(0.4f);
		weight.setFont(new Font("Playfair Display", Font.ITALIC, 43));
		
		//Edit and Save buttons
		edit = new JButton("");
		
		edit.setIcon(new ImageIcon("/Users/annie/eclipse-workspace/final/edit_page.png"));
		edit.setOpaque(false);
		edit.setContentAreaFilled(false); //to make the content area transparent
		edit.setBorderPainted(false);
		
		edit.setBounds(486, 611, 117, 57);
		edit.setActionCommand ("Edit");
		edit.addActionListener(this);
		changePanel.add(edit);
		
		save = new JButton("");
		
		save.setIcon(new ImageIcon("/Users/annie/eclipse-workspace/final/save.png"));
		save.setOpaque(false);
		save.setContentAreaFilled(false); //to make the content area transparent
		save.setBorderPainted(false);
		
		save.setBounds(486, 611, 117, 57);
		save.setActionCommand ("Save");
		save.addActionListener(this);
		changePanel.add(save);
		
		//placeholder texts
		title = new TextPrompt("Product Name", productName);
		title.changeAlpha(0.4f);
		title.setFont(new Font("Playfair Display", Font.BOLD | Font.ITALIC, 40));
		
		//back button
		
		backButton = new JButton("X");
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false); //to make the content area transparent
		backButton.setBorderPainted(false);
		backButton.setVerticalAlignment(SwingConstants.BOTTOM);
		backButton.setForeground(Color.DARK_GRAY);
		backButton.setFont(new Font("Quicksand", Font.PLAIN, 32));
		backButton.setBounds(518, 6, 85, 72);
		backButton.setActionCommand ("Back");
		backButton.addActionListener(this);
		changePanel.add(backButton);
		
		//star rating
		rating = new StarRating(35);
		rating.setRating(product.getRating());
		rating.setVisual();
		rating.setBounds(57, 179, 557, 183);
		changePanel.add(rating);
		
		lblRating = new JLabel("Rating:");
		lblRating.setFont(new Font("Proxima Nova", Font.BOLD, 16));
		lblRating.setBounds(55, 145, 104, 27);
		changePanel.add(lblRating);
		mainPanel.add(imagePanel);
		
		mainPanel.add(changePanel);
		
		//Brands
		textLabel_1 = new JLabel("Brand:");
		textLabel_1.setFont(new Font("Proxima Nova", Font.BOLD, 16));
		textLabel_1.setBounds(54, 502, 125, 26);
		changePanel.add(textLabel_1);

		brandText = new JTextField(80);
		brandText.setFont(new Font("Playfair Display", Font.PLAIN, 28));
		brandText.setColumns(50);
		brandText.setBounds(55, 529, 488, 41);
		changePanel.add(brandText);
		

		
		//chooses which mode to display
		if (mode=="View") {
			uneditableMode();
		} else {
			
			editableMode();
		}
	}
	
	
	//makes the edit page visible
	public void setPage() {
		
		main.frame.getContentPane().removeAll();
		main.frame.getContentPane().revalidate();
		main.frame.getContentPane().repaint();
		
		main.frame.getContentPane().add(mainPanel);

		main.frame.pack ();
		main.frame.setVisible (true);
		

	}
	
	public void actionPerformed (ActionEvent event) {
		String eventName = event.getActionCommand();
		
		if (eventName.equals ("Save")) {
			save();

		//changes the image
		} else if (eventName.equals("EditI")){
			FileDialog openDialog = new FileDialog (main.frame, "Open a new file", FileDialog.LOAD);
			openDialog.setVisible (true);
	
			String fileName = openDialog.getFile ();
			String pathName = openDialog.getDirectory ()+fileName;
			
			product.setImage(pathName);
			try {
			product.readImage();
			}catch(IOException e) {
				
			}
			product.setImage(500,600,image);
			product.getDisplay().changeImage();
	
		//shows collection page
		} else if (eventName.equals("Back")){
			main.collection.setPage();
			edit.setVisible(true);
			save.setVisible(false);
		
		//goes into edit mode
		} else if (eventName.equals("Edit")){
			editableMode();
		}
	}
	
	//takes all the information the user enters and stores in the object, and then writes to the external file to save the information
	private void save ()  {
		 
		if (productName.getText().trim().isEmpty()) {
			product.setName("Unnamed");
		} else {
		product.setName(productName.getText());
		}
		
		product.setType((String)productTypes.getSelectedItem());

		if (brandText.getText().trim().isEmpty()) {
			product.setBrand("null");
		} else {
		product.setBrand(brandText.getText());
		}

		try {
			
		product.setPrice(Double.parseDouble(productPrice.getText()));
		} catch(NumberFormatException e) {
			product.setPrice(0.00);
		}
		
		try {
			
			product.setWeight(Double.parseDouble(productWeight.getText()));
		} catch(NumberFormatException e) {
			product.setWeight(0.00);
		}
	
		product.setRating(rating.getValue());

		String type = (String)productTypes.getSelectedItem ();
		product.setType(type);
		
		product.setDisplay();
		uneditableMode();
		
		try {
		main.updateFile();
		} catch (IOException e) {
	
		}
		
	
	}
	
	//makes everything uneditable
	private void uneditableMode() {
		
		button.setVisible(false);
		
		product.setImage(500,600, image);
		
		if (product.getName().equals("Unnamed")) {
			//productName.setFont(new Font("Playfair Display", Font.PLAIN, 40));
			
		} else {
		productName.setText(product.getName());
		}
		
		productPrice.setText(formatter.format(product.getPrice()));
		productWeight.setText(formatter.format(product.getWeight()));
		
		if (product.getBrand().equals("null")) {
		brandText.setText("N/A");
		} else {
			brandText.setText(product.getBrand());
		}
		
		productName.setEditable(false);
		productPrice.setEditable(false);
		productWeight.setEditable(false);
		brandText.setEditable(false);
		
		productName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		productPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		productWeight.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		brandText.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		
		productTypes.setSelectedItem(product.getType());
		if (productTypes.getSelectedItem().equals(" ")) {
			typeName.setText("N/A");
		} else {
			typeName.setText(product.getType());
		}
		
		rating.setEditable(false);
		
		productTypes.setVisible(false);
		typeName.setVisible(true);
		
		edit.setVisible(true);
		save.setVisible(false);
		
	}
	
	//makes everything editable
	private void editableMode() {
		button.setVisible(true);
		productName.setEditable(true);
		productPrice.setEditable(true);
		productWeight.setEditable(true);
		brandText.setEditable(true);
		
		productName.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#bfbfbf")));
		productPrice.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#404040")));
		productWeight.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#404040")));
		brandText.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#bfbfbf")));
		
		rating.setEditable(true);
		
		productTypes.setVisible(true);
		typeName.setVisible(false);
		
		edit.setVisible(false);
		save.setVisible(true);
		
	}
	
	
	//adds a drop down menu with label
	private void addEditDropdownMenu(JComboBox <String> box, String label, String[] items, int bound1,int bound2, int bound3, int bound4) {
	
		lblNewLabel = new JLabel("Type:");
		lblNewLabel.setFont(new Font("Proxima Nova", Font.BOLD, 16));
		lblNewLabel.setBounds(55, 252, bound3-370, bound4-30);
		changePanel.add(lblNewLabel);
		
		typeName = new JLabel("New label");
		typeName.setFont(new Font("Playfair Display", Font.PLAIN, 28));
		typeName.setBounds(56, 282, 388, 41);
		changePanel.add(typeName);
		
		box.setModel(new DefaultComboBoxModel(items));
		box.setFont(new Font("Proxima Nova", Font.PLAIN, 20));
		box.setBounds(55, 282, bound3, bound4);
		changePanel.add(box);
	}
	
	
	
}
