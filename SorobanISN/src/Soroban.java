import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Soroban extends JPanel {
	
	ImageIcon separator = new ImageIcon(this.getClass().getResource("/separator.png"));
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagLayout gl = new GridBagLayout();

	int longueur;
	boolean pions[][];// = new int[9][7];
	boolean isDisabled;
	
	JLabel separatorLbl = new JLabel(separator);
	
	public Soroban(int longueur, boolean isDisabled) {
		this.setLayout(gl);
		this.setOpaque(false);
		this.isDisabled = isDisabled;
		
		this.longueur = longueur;
		init();
		separatorLbl.setPreferredSize(new Dimension(separator.getIconWidth(), separator.getIconHeight()));
		this.setPreferredSize(new Dimension(90*longueur, 50*7+separator.getIconHeight()+110));
		//gbc.insets = new Insets(5, 5, 5, 5);
	}
	
	
	/*public void displayPion(Pion pion) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = pion.posX;
		gbc.gridy = pion.posY;
		this.add(pion, gbc);
		this.repaint();
		//pion.button.setPreferredSize(new Dimension(b.getWidth(), b.getHeight()));
		//pion.button.setLocation(pion.posX, pion.posY);
	}*/
	
	public void afficherSoroban() {
			
		this.removeAll();
		//barre de séparation
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = this.longueur;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(separatorLbl, gbc);
		gbc.gridwidth = 1;
		for (int i = 0; i < this.pions.length; i++) {
			
			for (int j = 0; j < this.pions[j].length; j++) {
				if (this.pions[i][j]) {
					Pion pion = new Pion(i, j, isDisabled);
					gbc.gridx = pion.posX;
					gbc.gridy = pion.posY + (pion.posY >= 2 ? 1 : 0);
					this.add(pion, gbc);
					
				} else if (j != 2) { //don't place empty boxes in separator
					gbc.gridx = i;
					gbc.gridy = j + (j >= 2 ? 1 : 0);
					//this.add(Box.createRigidArea(new Dimension(50, 50)), gbc);
					this.add(new PionVide(), gbc);
					
				}
			}
		}
		
		
		//affiche la valeur du soroban
		for (int h = 0; h < 9; h++) {
			int valeurCol = 0;
			if (this.pions[h][1]) {
				valeurCol += 5;
				Pion pion = getPionWithXY(h, 1);
				pion.setIcon(pion.pionImgActive);
			}
			for (int i = 3; i < 7; i++) {
				if (this.pions[h][i]) {
					valeurCol++;
					Pion pion = getPionWithXY(h, i+1);
					pion.setIcon(pion.pionImgActive);
				} else {
					break;
				}
			}
			gbc.gridy = 9;
			gbc.gridx = h;
			this.add(new NumberLabel(String.valueOf(valeurCol)), gbc);
		}
		
		//Main.window.update();
	}
	
	
	
	public Pion getPionWithXY(int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		for (int i = 0; i < this.getComponentCount(); i++) {
			GridBagConstraints gbc = gl.getConstraints(this.getComponent(i));
		    if (gbc.gridx == x && gbc.gridy == y) {
		        return (Pion) this.getComponent(i);
		    }
		}
		return null;
	}
	
	public long getValeur() {
		long valeur = 0;
		
		//affiche la valeur du soroban
		for (int h = 0; h < 9; h++) {
			int valeurCol = 0;
			if (this.pions[h][1]) {
				valeurCol += 5;
			}
			for (int i = 3; i < 7; i++) {
				if (this.pions[h][i]) {
					valeurCol++;
				} else {
					break;
				}
			}
			valeur += valeurCol * Math.pow(10, 8-h);
		}
		return valeur;
	}
	
	public void init() {
		pions = new boolean[longueur][8];
		int[] lignesRemplies = {0, 4, 5, 6, 7};
		for (int i = 0; i < lignesRemplies.length; i++) {
			for (int j = 0; j < pions.length; j++) { 
				pions[j][lignesRemplies[i]] = true;
			}
		}
		afficherSoroban();
	}
}

