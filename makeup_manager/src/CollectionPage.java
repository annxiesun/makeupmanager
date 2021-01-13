import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.*;
import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import keeptoo.KButton;

public class CollectionPage implements ActionListener {
	
	private JPanel mainPanel;
	private JPanel collectionPanel;
	private JScrollPane scrollPane;
	
	private String[] sortBy_Modes= {"Recent", "Name", "Price","Price by Weight","Rating"};
	private JTextField searchBar;
	private KButton addButton;
	
	private KButton btnDelete;
	private KButton btnDeleteSelected;
	
	private String sortMode="Added";
	private String searchValue="";
	private String typeFilter=" ";
	
	private KButton searchButton;
	
	private JLabel lblNewLabel;
	
	private JLabel shadow;
	private JLabel filterBy_lbl;
	private JComboBox typefilter_box;
	
	private JComboBox comboBox;
	
	private ArrayList<MakeupProductDisplay> resultDisplays= new ArrayList<MakeupProductDisplay>();
	private JLabel lblSortBy;
	
	
	//creates the collection page panel
	public CollectionPage() {
		sortMode="Added";
		
		//layouts
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(1200, 700));
		
		collectionPanel = new JPanel ();
		collectionPanel.setBackground(Color.WHITE);

		scrollPane=new JScrollPane(collectionPanel);
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(1200, 700));
		scrollPane.setBounds(0, 172, 1200, 522);
		
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		collectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
		
		
		mainPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1211, 175);
		mainPanel.add(panel);
		panel.setLayout(null);
		
		//sort combobox
		comboBox = new JComboBox(sortBy_Modes);
		comboBox.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
		
		comboBox.setBounds(995, 103, 180, 27);
		comboBox.addActionListener(this);
		comboBox.setActionCommand("Sort");
		panel.add(comboBox);
		
		lblSortBy = new JLabel("sort by:");
		lblSortBy.setFont(new Font("Proxima Nova Alt", Font.PLAIN, 15));
		lblSortBy.setBounds(933, 108, 61, 16);
		panel.add(lblSortBy);
		
		//declutter and delete buttons
		btnDelete = new KButton();
		btnDelete.setFont(new Font("Proxima Nova", Font.PLAIN, 18));
		btnDelete.setText("Declutter Mode");
		btnDelete.setBounds(674, 102, 192, 29);
		
		btnDelete.setkEndColor(Color.decode("#ffe7a6"));
		btnDelete.setkStartColor(Color.decode("#fa9696"));
		btnDelete.setkHoverForeGround(Color.decode("#ffffff"));
		btnDelete.setkHoverEndColor(Color.decode("#ffd973"));
		btnDelete.setkHoverStartColor(Color.decode("#ff7373"));
		btnDelete.setkBorderRadius(20);
		
		btnDelete.addActionListener(this);
		btnDelete.setActionCommand("Delete");
		panel.add(btnDelete);
		
		btnDeleteSelected = new KButton();
		btnDeleteSelected.setText("Delete Selected");
		btnDeleteSelected.setBounds(674, 102, 192, 29);
		btnDeleteSelected.setFont(new Font("Proxima Nova", Font.PLAIN, 18));
		
		btnDeleteSelected.setkEndColor(Color.decode("#fa9696"));
		btnDeleteSelected.setkStartColor(Color.decode("#ffc582"));
		btnDeleteSelected.setkHoverForeGround(Color.decode("#ffffff"));
		btnDeleteSelected.setkHoverEndColor(Color.decode("#ff7373"));
		btnDeleteSelected.setkHoverStartColor(Color.decode("#ffd973"));
		btnDeleteSelected.setkBorderRadius(20);
		
		btnDeleteSelected.addActionListener(this);
		btnDeleteSelected.setActionCommand("DeleteSelected");
		btnDeleteSelected.setVisible(false);
		panel.add(btnDeleteSelected);
		
			
		//searchbar
		lblNewLabel = new JLabel("Search");
		lblNewLabel.setFont(new Font("Playfair Display", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(26, 18, 61, 16);
		panel.add(lblNewLabel);
		
			searchBar = new JTextField();
			searchBar.setFont(new Font("Proxima Nova Alt", Font.PLAIN, 23));
			searchBar.setBounds(26, 45, 308, 40);
			searchBar.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#d9cece")));
			
			panel.add(searchBar);
			searchBar.setColumns(10);
			
			searchButton = new KButton();
			searchButton.setFont(new Font("Lemon/Milk", Font.PLAIN, 30));
			searchButton.setText("GO");
			
			searchButton.setOpaque(false);
			searchButton.setContentAreaFilled(false);
			searchButton.setBorderPainted(false);
			
			searchButton.setkEndColor(Color.decode("#ffe7a6"));
			searchButton.setkStartColor(Color.decode("#fa9696"));
			searchButton.setkHoverForeGround(Color.decode("#ffffff"));
			searchButton.setkHoverEndColor(Color.decode("#ffd973"));
			searchButton.setkHoverStartColor(Color.decode("#ff7373"));
			
			searchButton.setkBorderRadius(50);
			searchButton.setBounds(354, 45, 76, 42);
			searchButton.addActionListener(this);
			searchButton.setActionCommand("Search");
			panel.add(searchButton);
			
			//add button
			addButton = new KButton();
			addButton.setText("+");
			addButton.setFont(new Font("Proxima Nova Alt", Font.BOLD, 30));
			addButton.setBounds(26, 102, 42, 29);
			addButton.setkBorderRadius(30);
			addButton.setkEndColor(Color.decode("#ffe7a6"));
			addButton.setkStartColor(Color.decode("#fa9696"));
			addButton.setkHoverForeGround(Color.decode("#ffffff"));
			addButton.setkHoverEndColor(Color.decode("#ffd973"));
			addButton.setkHoverStartColor(Color.decode("#ff7373"));
			
			
			addButton.addActionListener(this);
			addButton.setActionCommand("Add");
			panel.add(addButton);
			
	
			//shadow between the top bar and the scrollview
			shadow = new JLabel("");
			shadow.setBounds(0, 150, 1200, 40);
			panel.add(shadow);
			shadow.setIcon(new ImageIcon("/Users/annie/eclipse-workspace/final/shadow.png"));
			
			//filter combobox
			filterBy_lbl = new JLabel("select type:");
			filterBy_lbl.setFont(new Font("Proxima Nova Alt", Font.PLAIN, 15));
			filterBy_lbl.setBounds(911, 69, 83, 16);
			panel.add(filterBy_lbl);
			
			typefilter_box = new JComboBox(main.types);
			typefilter_box .addActionListener(this);
			typefilter_box.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
			typefilter_box.setBounds(995, 64, 180, 27);
			
			typefilter_box.setActionCommand("Filter");

			panel.add(typefilter_box);
		
		mainPanel.add(scrollPane);
		
		main.frame.getContentPane().add(mainPanel);
		
	}
	
	//makes the collection page visible
	public void setPage() {
		main.frame.getContentPane().removeAll();
		main.frame.getContentPane().revalidate();
		main.frame.getContentPane().repaint();
		
		setProducts(sortMode, typeFilter, searchValue);
		main.frame.getContentPane().add(mainPanel);
		
		main.frame.pack ();
		main.frame.setVisible (true);
	}
	
	//sets products to be visible, taking into account if they match the type filter and search term, displays them in the order selected
	public void setProducts(String sortMode, String type, String value) {
		
		collectionPanel.removeAll();
		collectionPanel.revalidate();
		collectionPanel.repaint();
		resultDisplays.clear();
		
		filterType(type);
		searchByName(value);
		
		ArrayList<MakeupProduct> results = main.results;
		
		if(sortMode.equals("Name")) {
			Collections.sort(results);
		} else if (sortMode.equals("Price")) {
			Collections.sort(results, new sortPrice());
		} else if (sortMode.equals("Rating")) {
			Collections.sort(results, new sortRating());
		} else if (sortMode.equals("Price by Weight")){
			Collections.sort(results, new sortPriceWeight());
		}else {
			Collections.sort(results, new sortRecent());
		}
		
		if (results.isEmpty()) {
			JLabel blank = new JLabel("");
			collectionPanel.add(blank);
		
		}
		
		int total = (int)Math.ceil((results.size()+4)/5)*5;
		

		int numRows=(int)(Math.ceil((results.size()+4)/5));
		
		collectionPanel.setLayout(new GridLayout(0, 5, 0, 0));
		collectionPanel.setPreferredSize(new Dimension(650, 280*numRows));
		
		
		for (int i =0; i<total;i++) {
			if (i<results.size()) {
			MakeupProductDisplay newDisplay = results.get(i).getDisplay();
			resultDisplays.add(newDisplay);
			collectionPanel.add(newDisplay);
			//JPanel newDisplay = new JPanel();
			
			
			} else {
				JLabel blank = new JLabel("");
				collectionPanel.add(blank);
			}
			

		
		}
	}
	
	//makes the results all the products that match the search value 
	public void searchByName(String value) {
		
		if (value.equals("")) {
			return;
		}
		
		Multimap<String, MakeupProduct> search_wordbank = ArrayListMultimap.create();
		
		for (int i =0; i<main.results.size();i++) {
			
			MakeupProduct product = main.results.get(i);
			
			String name = product.getName();
			StringTokenizer name_tokenized = new StringTokenizer(name," ");
			
			while(name_tokenized.hasMoreTokens()) {
				search_wordbank.put(name_tokenized.nextToken().toLowerCase(),product);
			}
			
		}
		ArrayList<MakeupProduct> result = new ArrayList<>(search_wordbank.get(value));
		main.results=result;
		
	}
	
	//makes the results all the products that match the filter type 
	public void filterType(String value) {
	
		
		if (value.equals(" ")) {

			main.results=main.makeupCollection;
			return;
		}
		
		Multimap<String, MakeupProduct> search_wordbank = ArrayListMultimap.create();
		
		for (int i =0; i<main.makeupCollection.size();i++) {
			
			MakeupProduct product = main.makeupCollection.get(i);
			
			String type = product.getType();
			
			search_wordbank.put(type,product);
			
		}
		ArrayList<MakeupProduct> result = new ArrayList<>(search_wordbank.get(value));
		main.results=result;
		return;
		
	}
	
	
	public void actionPerformed (ActionEvent event) {
		String eventName = event.getActionCommand();

		//selects sort mode and reorders products
		if (eventName == "Sort") {
			
		JComboBox comboBox = (JComboBox)event.getSource ();
		String mode = (String)comboBox.getSelectedItem ();
		
				sortMode=mode;
				setProducts(sortMode, typeFilter,searchValue);
		
		//updates the display to only show the products that matches the user's search term
		} else if (eventName=="Search") {
			if (searchBar.getText().isEmpty()) {
				searchValue="";
				
			} 
			searchValue=searchBar.getText().toLowerCase();
			setProducts(sortMode,typeFilter,searchValue);
			
		
		//creates a new product and takes the user to a page where they can edit it
		} else if (eventName=="Add") {
			main.makeupCollection.add(new MakeupProduct(main.makeupCollection.size()+1,"Unnamed",0," ",0,-1," ",null));
			
			try {
			main.makeupCollection.get(main.makeupCollection.size()-1).setEditPage("Edit");
			}catch(IOException e) {
				
			}
			main.makeupCollection.get(main.makeupCollection.size()-1).getEditPage().setPage();
			main.makeupCollection.get(main.makeupCollection.size()-1).setDisplay();
			
			
		//switches to "Delete mode" and allows the user to select items in which they want to delete
		} else if (eventName=="Delete") {
			btnDelete.setVisible(false);
			for (MakeupProductDisplay m: resultDisplays) {
				m.setCheckBox();
				
			}
			btnDeleteSelected.setVisible(true);
			
		//deletes the items the user selected and resets the display without the deleted objects
		} else if (eventName=="DeleteSelected") {
			btnDeleteSelected.setVisible(false);
			
			for (MakeupProductDisplay m: resultDisplays) {
				m.setPress();
				
			}
			
			for (MakeupProductDisplay m: resultDisplays) {
				if (m.getChecked()==true) {
				main.makeupCollection.remove(m.getProduct());
				main.results.remove(m.getProduct());
				}

			}
			try {
			main.updateFile();
			} catch(IOException e) {
				
			}
			
			setProducts(sortMode, typeFilter,searchValue);
			btnDelete.setVisible(true);
		
		//updates the display to only show the products that matches the type selected
		} else if(eventName=="Filter") {
			JComboBox comboBox = (JComboBox)event.getSource ();
		
			String typeSelected = (String)comboBox.getSelectedItem ();
			
			
			typeFilter=typeSelected;
			setProducts(sortMode, typeFilter,searchValue);
		}
		
	}
}
