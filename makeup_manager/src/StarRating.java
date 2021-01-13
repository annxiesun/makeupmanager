import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class StarRating extends JComponent implements ActionListener{
	int ratingValue;
	JPanel panel=new JPanel();
	
	JButton[] buttons = new JButton[5];
	ImageIcon fullStar;
	ImageIcon emptyStar;
	
	public StarRating(int height){
		
		ratingValue = -1;
		int width=(int)(height*1.05);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setOpaque(false);
		panel.setBounds(0, 0, width*5+width/2*5, height);
		 
		panel.setLayout(null);
		
		Image img = main.img.getScaledInstance(width, height,Image.SCALE_SMOOTH);
		emptyStar = new ImageIcon(img);
		
		Image img2 = main.img2.getScaledInstance(width, height,Image.SCALE_SMOOTH);
		fullStar = new ImageIcon(img2);
		
		for (int i=0;i<5;i++) {
		buttons[i]=(new JButton(""));
		
		buttons[i].setBackground(UIManager.getColor("Button.select"));
		buttons[i].setBounds(width*i+width/2*i, 0, width, height);
		
		buttons[i].setOpaque(false);
		buttons[i].setContentAreaFilled(false);
		buttons[i].setBorderPainted(false);
		
		buttons[i].addActionListener(this);
		buttons[i].setActionCommand(""+i);
		
		//starButton.addActionListener(this);
		buttons[i].setIcon(emptyStar);
		panel.add(buttons[i]);
		}

		add(panel);

	}

	
	public void actionPerformed (ActionEvent event) {
		int num =  Integer.parseInt(event.getActionCommand());
		this.ratingValue=num;

		setVisual();
		
		
	}
	
	public int getValue() {
		return this.ratingValue;
	}
	
	//displays the number of empty and full stars that correlate with the rating
	public void setVisual () {
		
		if (this.ratingValue<0) {
			for(int i=0;i<5;i++) {
				changeStar(buttons[i], emptyStar);
			}
		}
		
		for(int i=this.ratingValue;i>=0;i--) {
			changeStar(buttons[i], fullStar);
		}
		
		for(int i =this.ratingValue+1;i<5;i++) {
			changeStar(buttons[i], emptyStar);
		}
	}
	
	public void setEditable(boolean value) {
		for (int i =0; i<5;i++) {
			buttons[i].setEnabled(value);
		}
	}

	public void setRating (int value) {
		this.ratingValue=value;
	}
	public void changeStar (JButton button, ImageIcon picture) {
		button.setIcon(picture);
	}
	
}