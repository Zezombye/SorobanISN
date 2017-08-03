import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PionVide extends JLabel {
	
	public ImageIcon pionVideImg = new ImageIcon(this.getClass().getResource("/pionvide.png"));
	
	public PionVide() {
		this.setIcon(pionVideImg);
		this.setPreferredSize(new Dimension(90, 50));
	}
}
