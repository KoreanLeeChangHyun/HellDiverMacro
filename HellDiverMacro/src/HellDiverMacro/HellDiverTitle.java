package HellDiverMacro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HellDiverTitle extends JLabel{
	private static final long serialVersionUID = 1L;

	/*
	 * Constructor
	 */
	public HellDiverTitle() {
		this.setText(" ");
		Font font = new Font("°íµñ", Font.PLAIN, 40);
		this.setFont(font);
		this.setOpaque(true);
		this.setBackground(Color.black);
		
		Toolkit tool = Toolkit.getDefaultToolkit();
		URL imageURL = getClass().getClassLoader().getResource("title.png");
		Image img = tool.getImage(imageURL); // Default
		img = img.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img);

		this.setIcon(icon);
		this.setHorizontalAlignment(JLabel.CENTER);
	}
}
