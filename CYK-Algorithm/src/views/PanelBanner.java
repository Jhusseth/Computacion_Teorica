package views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class PanelBanner extends JPanel {

	public PanelBanner() {
		setBackground(Color.BLACK);
		try {
			banner();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No se cargo la imagen");
		}
	}
	
	
	public void banner() throws IOException{
		String path = "data/logo.png";
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBackground(Color.DARK_GRAY);
        add(label);
	}
}
