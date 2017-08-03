import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BacASablePanel extends JPanel {
	
	Image background = new ImageIcon(this.getClass().getResource("/wallpapers/bg_bacasable.jpg")).getImage();
	public ImageIcon retourImg = new ImageIcon(Main.class.getResource("/return.png"));
	public ImageButton retour = new ImageButton(retourImg, null);
	
	public BacASablePanel() {

		this.setLayout(null);
		this.setBackground(Color.white);
		retour.setFocusable(false);
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.window.setContentPane(new Menu());
				Main.window.update();
			}
		});
		
		Main.soroban = new Soroban(9, false);
		this.add(Main.soroban);
		this.add(retour);
		Dimension size = Main.soroban.getPreferredSize();
		Main.soroban.setBounds(this.getWidth()/2-size.width/2, this.getHeight()/2-size.height/2, size.width, size.height);
		Main.soroban.afficherSoroban();
	}
	
	@Override protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		this.setPreferredSize(this.getParent().getSize());
		Dimension size = Main.soroban.getPreferredSize();
		Main.soroban.setBounds(this.getWidth()/2-size.width/2, this.getHeight()/2-size.height/2, size.width, size.height);
		size = retour.getPreferredSize();
		retour.setBounds(20, 20, size.width, size.height);
	}
}
