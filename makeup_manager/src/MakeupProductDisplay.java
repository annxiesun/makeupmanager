import java.awt.*;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import keeptoo.KButton;
import javax.swing.border.LineBorder;

public class MakeupProductDisplay extends JComponent implements ActionListener{

	private JPanel panel = new JPanel();
	private MakeupProduct product;
	private boolean deleteMode;
	private JCheckBox checkBox;
	private JButton btnNewButton;
	
	private BufferedImage img = null;

	private ImageIcon pic;
	private JLabel  image;
	private JLabel lblProductType;
	
	private StarRating rating;
	private NumberFormat formatter = new DecimalFormat("$0.00");
	
	//creates the product display
	public MakeupProductDisplay(MakeupProduct product){
		 
	
		 this.product = product;
		 
		 panel = new JPanel();
		 panel.setBounds(6, 6, 216, 262);
		 add(panel);
		 
		//panel.setBackground(Color.WHITE);
		panel.setOpaque(false);
		panel.setLayout(null);
		
		checkBox = new JCheckBox("");
		checkBox.setBounds(183, 6, 33, 23);
		checkBox.setVisible(false);
		panel.add(checkBox);
		
		rating = new StarRating(22);
		rating.setEditable(false);
		rating.setRating(product.getRating());
		rating.setVisual();
		rating.setBounds(28, 205, 170, 37);
		panel.add(rating);
		
		JLabel lblNewLabel = new JLabel(product.getName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (product.getName().equals("Unnamed")) {
			lblNewLabel.setFont(new Font("Proxima Nova Alt", Font.PLAIN| Font.ITALIC, 19));
		} else {
		lblNewLabel.setFont(new Font("Proxima Nova Alt", Font.BOLD, 19));
		}
		lblNewLabel.setBounds(28, 136, 160, 37);
		panel.add(lblNewLabel);
		
		if (product.getType().equals(" ")) {
			lblProductType = new JLabel("N/A");
		} else {
		lblProductType = new JLabel(product.getType());
		}
		lblProductType.setFont(new Font("Proxima Nova Alt", Font.PLAIN, 14));
		lblProductType.setBounds(28, 158, 90, 58);
		panel.add(lblProductType);
		
		JLabel label = new JLabel(formatter.format(product.getPrice()));
		label.setFont(new Font("Proxima Nova Alt", Font.PLAIN, 14));
		label.setBounds(138, 158, 60, 58);
		panel.add(label);
		
		btnNewButton = new JButton();

		//btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(0, 0, 216, 260);
		btnNewButton.setPreferredSize(new Dimension(300,370));
		btnNewButton.setFocusable(false);
		//btnNewButton.setBorder(new LineBorder(new Color(224, 224, 224)));
		
		btnNewButton.setActionCommand("Press");
		btnNewButton.addActionListener(this);
		
		image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setBounds(14, 27, 190, 106);
		panel.add(image);
		
		product.setImage(200,100, image);
		panel.add(btnNewButton);

	}
	
	public void actionPerformed (ActionEvent event) {
		String eventName = event.getActionCommand();
		
		//If clicked in edit mode: goes to edit page
		if (eventName =="Press") {
			product.getEditPage().setPage();
			
		//If clicked in delete mode: selects the object for deletion
		} else if (eventName=="Select") {
			
			if (this.getChecked()==true) {
				this.checkBox.setSelected(false);
			} else {
			this.checkBox.setSelected(true);
			}
		}
	}

	//sets the image
	public void changeImage() {
		product.setImage(200,100, image);
	}
	
	//changes mode to delete
	public void setCheckBox() {
		this.checkBox.setVisible(true);
		btnNewButton.setActionCommand("Select");
	}
	
	//changes mode to edit mode
	public void setPress() {
		this.checkBox.setVisible(false);
		btnNewButton.setActionCommand("Press");
	}
	

	public boolean getChecked() {
		return this.checkBox.isSelected();
	}
	
	public MakeupProduct getProduct() {
		return this.product;
	}
}
