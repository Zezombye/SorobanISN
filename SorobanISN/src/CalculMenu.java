import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CalculMenu extends JPanel {
	
	Image background = new ImageIcon(this.getClass().getResource("/wallpapers/bg_calculmenu.jpg")).getImage();
	JLabel addition = new JLabel(new ImageIcon(this.getClass().getResource("/addition.png")));
	JLabel soustraction = new JLabel(new ImageIcon(this.getClass().getResource("/soustraction.png")));
	JLabel mult = new JLabel(new ImageIcon(this.getClass().getResource("/multiplication.png")));
	JLabel div = new JLabel(new ImageIcon(this.getClass().getResource("/division.png")));
	ImageIcon tuto = new ImageIcon(this.getClass().getResource("/tuto.png"));
	ImageIcon lvl1 = new ImageIcon(this.getClass().getResource("/lvl1.png"));
	ImageIcon lvl2 = new ImageIcon(this.getClass().getResource("/lvl2.png"));
	ImageIcon lvl3 = new ImageIcon(this.getClass().getResource("/lvl3.png"));
	public ImageIcon retourImg = new ImageIcon(this.getClass().getResource("/return.png"));
	public ImageButton retour = new ImageButton(retourImg, null);
	
	
	
	ImageIcon[] imgs = {tuto, lvl1, lvl2, lvl3};
	MenuBouton[] boutons = new MenuBouton[16];
	
	public CalculMenu() {
		
		retour.setFocusable(false);
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.window.setContentPane(new Menu());
				Main.window.update();
			}
		});
		
		this.setLayout(null);
		this.setOpaque(false);
		for (int i = 0; i < 16; i++) {
			boutons[i] = new MenuBouton(imgs[i%4], null, i/4, i%4);
			this.add(boutons[i]);
		}
		this.add(retour);
		this.add(addition);
		this.add(soustraction);
		this.add(mult);
		this.add(div);
	}
	
	@Override protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		this.setPreferredSize(this.getParent().getSize());
		//System.out.println(this.getHeight() + " " + this.getWidth());
		Dimension size = retour.getPreferredSize();
		retour.setBounds(20, 20, size.width, size.height);
		size = addition.getPreferredSize();
		addition.setBounds((this.getWidth()-4*size.width)/5, this.getHeight()/2-size.height/2, size.width, size.height);
		soustraction.setBounds(2*(this.getWidth()-4*size.width)/5+size.width, this.getHeight()/2-size.height/2, size.width, size.height);
		mult.setBounds(3*(this.getWidth()-4*size.width)/5+2*size.width, this.getHeight()/2-size.height/2, size.width, size.height);
		div.setBounds(4*(this.getWidth()-4*size.width)/5+3*size.width, this.getHeight()/2-size.height/2, size.width, size.height);
		Dimension size2 = boutons[0].getPreferredSize();
		for (int i = 0; i < 16; i++) {
			boutons[i].setBounds(
					(boutons[i].x+1)*(this.getWidth()-4*size.width)/5+boutons[i].x*size.width,
					(boutons[i].y+1)*(389-4*size2.height)/5+boutons[i].y*size2.height+this.getHeight()/2-size.height/2+89,
					size.width,
					size2.height
			);
		}
	}
	
}

class MenuBouton extends ImageButton {
	String[] operations = {"add", "sub", "mult", "div"};
	public int x;
	public int y;
	public MenuBouton(ImageIcon enabled, ImageIcon disabled, int x, int y) {
		super(enabled, disabled);
		this.x = x;
		this.y = y;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = ((MenuBouton)e.getSource()).x;
				int y = ((MenuBouton)e.getSource()).y;
				if (y == 0) {
					if (x == 3) {
						JOptionPane.showMessageDialog(Main.window, "Désolé, mais le tutoriel de division n'est pas encore disponible.", "Soroban", JOptionPane.INFORMATION_MESSAGE);
					} else {
						Main.window.setContentPane(new Tutoriel("tuto_"+operations[x]));
					}
				} else {
					Main.window.setContentPane(new CalculPanel(CalculFactory.TypeCalcul.values()[x], y));
				}
				Main.window.update();
			}
		});
	}
	
}
