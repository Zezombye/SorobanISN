import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NumberLabel extends JLabel {
	
	public NumberLabel(String text) {
		this.setOpaque(false);
		this.setFont(new Font("Serif", Font.BOLD, 96));
		this.setForeground(Color.GREEN);
		this.setPreferredSize(new Dimension(90, 110));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setText(text);
	}
}
