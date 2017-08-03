import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Pion extends JButton {
	public ImageIcon pionImg = new ImageIcon(this.getClass().getResource("/pion.png"));
	public ImageIcon pionImgActive = new ImageIcon(this.getClass().getResource("/pionactif.png"));
	public int posX;
	public int posY;
	
	public Pion(int x, int y, boolean isDisabled) {
				
		this.setIcon(pionImg);
		//button.setBorder(BorderFactory.createEmptyBorder());
		this.posX = x;
		this.posY = y;
		
		//rend le bouton transparent
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		
		if (!isDisabled) {
			this.addActionListener(new PionListener());
		}
		this.setPreferredSize(new Dimension(90, 50));
	}
}

class PionListener implements ActionListener {
	
	//Si on clique sur le pion, cette méthode est appelée
	@Override
	public void actionPerformed(ActionEvent e) {
		Pion pion = (Pion)e.getSource();
		int x = pion.posX;
		int y = pion.posY;
		
		if (y < 2) {
			//lignes du haut
			//on fait une simple permutation
			if (y == 0) {
				Main.soroban.pions[x][y] = false;
				Main.soroban.pions[x][y+1] = true;
			} else {
				Main.soroban.pions[x][y] = false;
				Main.soroban.pions[x][y-1] = true;
			}
		} else {
			//lignes du bas
			//on vérifie chaque emplacement et on déplace le pion à l'emplacement vide
			for (int i = 3; i < 8; i++) {
				if (Main.soroban.pions[x][i] == false) {
					Main.soroban.pions[x][y] = false;
					Main.soroban.pions[x][i] = true;
					break;
				}
			}
		}
		System.out.println("Pion appuyé à " + x + ", " + y);
		
		Main.soroban.afficherSoroban();
		Main.window.update();
	}
	
}
