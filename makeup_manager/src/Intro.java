import javax.swing.*;
import java.awt.*;

public class Intro extends JPanel {

	private JLabel lblNewLabel;
	
	//Displays animated gif in the beginning
	public Intro() {
		setLayout(null);
		
		this.setSize(new Dimension(1200,700));
	
		lblNewLabel = new JLabel(new ImageIcon(Intro.class.getResource("/intro2.gif")));
		lblNewLabel.setSize(1200, 1200);
		
		this.add(lblNewLabel);
		main.frame.getContentPane().add(lblNewLabel);
		main.frame.pack ();
		main.frame.setVisible (true);
		


	}
	
	public void setPic() {
	
	}

}
