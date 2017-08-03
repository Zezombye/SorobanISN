import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
	
	ImageIcon enabled, disabled;
	
	public ImageButton(ImageIcon enabled, ImageIcon disabled) {
		this.enabled = enabled;
		this.disabled = disabled;
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setIcon(enabled);
		this.setPreferredSize(new Dimension(enabled.getIconWidth(), enabled.getIconHeight()));
	}
	
	@Override public void setEnabled(boolean isEnabled) {
		super.setEnabled(isEnabled);
		if (isEnabled) {
			this.setIcon(enabled);
		} else {
			this.setIcon(disabled);
		}
	}
}