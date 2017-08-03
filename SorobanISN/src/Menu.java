import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {
	
	Image background = new ImageIcon(this.getClass().getResource("/wallpapers/bg_menu.jpg")).getImage();
	public ImageIcon bacasableImg = new ImageIcon(this.getClass().getResource("/bacasable.png"));
	public ImageIcon modeCalculImg = new ImageIcon(this.getClass().getResource("/modecalcul.png"));
	ImageButton bacasable = new ImageButton(bacasableImg, null);
	ImageButton modeCalcul = new ImageButton(modeCalculImg, null);
	JLabel titre = new JLabel(new ImageIcon(this.getClass().getResource("/titre.png")));
	//JLabel auteurs = new JLabel(new ImageIcon(this.getClass().getResource("/auteurs.png")));
	JLabel serie = new JLabel(new ImageIcon(this.getClass().getResource("/serie.png")));
	
	public Menu() {
		
		this.add(titre);
		this.add(bacasable);
		//this.add(auteurs);
		this.add(serie);
		bacasable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.window.setContentPane(new BacASablePanel());
				Main.window.update();
			}
		});
		this.add(modeCalcul);
		modeCalcul.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.window.setContentPane(new CalculMenu());
				Main.window.update();
			}
		});
		
		
	}
	
	@Override protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		this.setPreferredSize(this.getParent().getSize());
		//System.out.println(this.getHeight() + " " + this.getWidth());
		Dimension size = titre.getPreferredSize();
		titre.setBounds(this.getWidth()/2-size.width/2, this.getHeight()/5-size.height/2, size.width, size.height);
		size = bacasable.getPreferredSize();
		bacasable.setBounds(this.getWidth()/2-size.width/2, this.getHeight()/2-size.height-10, size.width, size.height);
		size = modeCalcul.getPreferredSize();
		modeCalcul.setBounds(this.getWidth()/2-size.width/2, this.getHeight()/2+10, size.width, size.height);
		//size = auteurs.getPreferredSize();
		//auteurs.setBounds(10, this.getHeight()-size.height-10, size.width, size.height);
		size = serie.getPreferredSize();
		serie.setBounds(this.getWidth()-size.width-10, this.getHeight()-size.height-10, size.width, size.height);
	}
	
}
